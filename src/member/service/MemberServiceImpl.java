package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dao.MemberDAO;
import member.dao.MemberDAOImpl;
import member.vo.MemberInfo;
import member.vo.MemberVO;
import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.subject.MemberSubject;
import util.GoogleAuthenticator;
import util.UserSha256;

public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public boolean memberSignUp(HttpServletRequest request) {
		boolean isSignUp = false;
		String email = request.getParameter("cm_email");
		String pw = request.getParameter("cm_pw");
		String name = request.getParameter("cm_name");
		String phone = request.getParameter("cm_phone");
		String addr = request.getParameter("cm_addr");
		int salt = makingSalt();
		String encryPassword = UserSha256.encrypt(pw+salt);
		System.out.println("소금 : "+salt);
		System.out.println("암호화한 비밀번호 : "+encryPassword);
		MemberVO member = new MemberVO();
		member.setCm_email(email);
		member.setCm_pw(encryPassword);
		member.setCm_name(name);
		member.setCm_phone(phone);
		member.setCm_addr(addr);
		member.setCm_salt(salt);
		isSignUp = dao.signUpMember(member);
		request.setAttribute("joinMember", dao.getMemberByEmail(email));
		return isSignUp;
	}
	// 부가 정보 입력 처리(직업 정보랑, 지역 정보, 언어 정보까지 넣자)
	@Override
	public void insertInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 정보 넣기
		int cmi_owner_num = Integer.parseInt(request.getParameter("cm_num"));
		String cmi_intro = request.getParameter("cmi_intro");
		String cmi_gender = request.getParameter("cmi_gender");
		int cmi_age = Integer.parseInt(request.getParameter("cmi_age"));
		String cmi_career = request.getParameter("cmi_career");
		String cmi_private = request.getParameter("cmi_private");
		
		MemberInfo info = new MemberInfo();
		
		info.setCmi_owner_num(cmi_owner_num);
		info.setCmi_intro(cmi_intro);
		info.setCmi_gender(cmi_gender);
		info.setCmi_age(cmi_age);
		info.setCmi_career(cmi_career);
		info.setCmi_private(cmi_private);
		boolean isInfo = dao.insertInfo(info);
		
		
		// 직업 넣기 복수 응답도 고려해서 넣어야할 것 같은데...
		String[] jobs = request.getParameterValues("cij_code");
		for(String job : jobs) {
			MemberJob mj = new MemberJob();
			int code = Integer.parseInt(job);
			mj.setCij_code(code);
			mj.setCij_owner_num(cmi_owner_num);
			dao.insertJob(mj);
		}
		
		// 지역 넣기 복수 응답 고려
		String[] regions = request.getParameterValues("cmr_code");
		for(String region : regions) {
			MemberRegion mr = new MemberRegion();
			int code = Integer.parseInt(region);
			mr.setCmr_code(code);
			mr.setCmr_owner_num(cmi_owner_num);
			dao.insertRegion(mr);
		}
		
		// 언어 정보 입력. 복수 응답 고려 - 주 언어 
		String[] majors = request.getParameterValues("cms_code_work");
		for(String major : majors) {
			MemberSubject ms = new MemberSubject();
			int code = Integer.parseInt(major);
			ms.setCms_category(0);
			ms.setCms_code(code);
			ms.setCms_owner_num(cmi_owner_num);
			dao.insertSubject(ms);
		}
		
		// 언어 정보 입력. 복수 응답 고려 - 학습 중인 언어
		String[] learnings = request.getParameterValues("cms_code_learning");
		for(String learning : learnings) {
			MemberSubject ms = new MemberSubject();
			int code = Integer.parseInt(learning);
			ms.setCms_category(1);
			ms.setCms_code(code);
			ms.setCms_owner_num(cmi_owner_num);
			dao.insertSubject(ms);
		}
		// 부가 정보 입력 결과 처리 창. 
		if(isInfo) {
			response.sendRedirect("login");
		}else {
			// 부가정보 입력 페이지로 다시 넘어가기
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('부가 정보 입력에 실패하셨습니다.\n 다시 기입해주세요!');");
			out.print("history.go(-1);");
			out.print("</script>");
		}
	}

	@Override
	public boolean memberLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean isLogin = false;
		
		String email = request.getParameter("cm_email");
		String inputPw = request.getParameter("cm_pw");
		String autoLogin = request.getParameter("cm_auto");
		boolean isAuto = false;
		if(autoLogin.equals("on")) {
			isAuto = true;
		}
		// 소금 가져오기
		int salt = dao.getSalt(email);
		
		
		String encryPw = UserSha256.encrypt(inputPw+salt);
		
		
		
		MemberVO member = dao.loginMember(email, encryPw);
		// null이라면 실패 
		if(member != null) {
			System.out.println("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			if(isAuto) {
				// Cookie 처리
				Cookie cookie = new Cookie("cm_email", member.getCm_email());
				cookie.setMaxAge(60*60*24*7);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			isLogin = true;
			
		}else {
			System.out.println("로그인 실패");
		}
		
		
		return isLogin;
	}

	@Override
	public void sendPasscode(HttpServletRequest request, HttpServletResponse response) {
		String cm_email = request.getParameter("cm_email");
		MemberVO member = dao.getMemberByEmail(cm_email);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		
		try {
			// 이메일이 아이디로 존재하지 않으면
			if(member == null) {
				System.out.println("일치하는 아이디가 없음.");
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('일치하는 아이디가 없습니다.');");
				out.print("history.go(-1);");
				out.print("</script>");
				return;
			}
			String code = Integer.toString(makingSalt());
			System.out.println("비밀번호 찾기용 코드 : "+code);
			dao.addPassCode(cm_email, code);
			
			// 메일 발송
			GoogleAuthenticator auth = new GoogleAuthenticator();
			Session session = Session.getDefaultInstance(auth.getProperties(), auth);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(cm_email));
			msg.setFrom(new InternetAddress("admin@gmail.com", "ADMIN"));
			msg.setSubject("비밀번호 변경 관련 메일입니다.", "UTF-8");
			StringBuilder mail = new StringBuilder();
			mail.append("<!DOCTYPE html>");
			mail.append("<html>");
			mail.append("<head>");
			mail.append("<meta charset='utf-8'>");
			mail.append("<title>비밀번호 찾기</title>");
			mail.append("</head>");
			mail.append("<body>");
			mail.append("<h1>@@@ 사이트 비밀번호 찾기 이메일 인증번호</h1>");
			mail.append("<table>");
			mail.append("<tbody>");
			mail.append("<tr>");
			mail.append("<th>비밀번호 찾기 코드 : </th>");
			mail.append("<th>"+code+"</th>");
			mail.append("</tr>");
			mail.append("</tbody>");
			mail.append("</table>");
			mail.append("</body>");
			mail.append("</html>");
			String content = new String(mail);
			msg.setContent(content, "text/html;charset=utf-8");
			Transport.send(msg);
			
			request.getRequestDispatcher("/member/checkCode.jsp").forward(request, response);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkPwCode(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = false;
		
		String cpc_email = request.getParameter("cpc_email");
		String cpc_code = request.getParameter("cpc_code");
		
		boolean isCheck = dao.checkPassCode(cpc_email, cpc_code);
		
		if(isCheck) {
			isSuccess=true;
			request.setAttribute("email", cpc_email);
		}
		
		return isSuccess;
	}
	
	@Override
	public void changePw(HttpServletRequest request, HttpServletResponse response) {
		String cm_email = request.getParameter("cm_email");
		String input_pw = request.getParameter("cm_pw");
		
		// 소금 가져오기
		int salt = dao.getSalt(cm_email);
				
		String cm_pw = UserSha256.encrypt(input_pw+salt);
		
		boolean isChanged = dao.changePass(cm_email, cm_pw);
		// 비밀번호 변경
		if(isChanged) {
			System.out.println("비밀번호 변경 성공");
		}else {
			System.out.println("비밀번호 변경 실패");
		}
		
		
	}
	
	// 염전 
	int makingSalt(){
		int salt = 0;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<5; i++) {
			//0 ~ 9
			int random = (int)(Math.random()*10);
			sb.append(random);
		}
		salt = Integer.parseInt(sb.toString());
		
		return salt;
	}
	
	

}
