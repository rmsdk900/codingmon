package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import member.vo.job.JobVO;
import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.region.RegionVO;
import member.vo.subject.MemberSubject;
import member.vo.subject.SubjectVO;
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
		String phone = request.getParameter("cm_phone_first")+request.getParameter("cm_phone_middle")+request.getParameter("cm_phone_last");
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
		if(isSignUp) {
			HttpSession session = request.getSession();
			session.setAttribute("joinMember", dao.getMemberByEmail(email));
			
		}
		
		return isSignUp;
	}
	// 부가 정보 입력 처리(직업 정보랑, 지역 정보, 언어 정보까지 넣자)
	@Override
	public void insertInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 정보 넣기
		int cmi_owner_num = Integer.parseInt(request.getParameter("cm_num"));
		String cmi_title = request.getParameter("cmi_title");
		String cmi_intro = request.getParameter("cmi_intro");
		String cmi_gender = request.getParameter("cmi_gender");
		System.out.println(request.getParameter("cmi_age"));
		int cmi_age = Integer.parseInt(request.getParameter("cmi_age"));
		String cmi_career = request.getParameter("cmi_career");
		String cmi_private = request.getParameter("cmi_private");
		
		MemberInfo info = new MemberInfo();
		
		info.setCmi_owner_num(cmi_owner_num);
		info.setCmi_title(cmi_title);
		info.setCmi_intro(cmi_intro);
		info.setCmi_gender(cmi_gender);
		info.setCmi_age(cmi_age);
		info.setCmi_career(cmi_career);
		info.setCmi_private(cmi_private);
		boolean isInfo = dao.insertInfo(info);
		
		
		// 직업 넣기 복수 응답도 고려해서 넣어야할 것 같은데...
		String[] jobs = request.getParameterValues("cij_code");
		if(jobs != null) {
			for(String job : jobs) {
				MemberJob mj = new MemberJob();
				int code = Integer.parseInt(job);
				mj.setCij_code(code);
				mj.setCij_owner_num(cmi_owner_num);
				dao.insertJob(mj);
			}
		}
		
		
		// 지역 넣기 복수 응답 고려
		String[] regions = request.getParameterValues("cmr_code");
		if(regions != null) {
			for(String region : regions) {
				MemberRegion mr = new MemberRegion();
				int code = Integer.parseInt(region);
				mr.setCmr_code(code);
				mr.setCmr_owner_num(cmi_owner_num);
				dao.insertRegion(mr);
			}
		}
		
		
		// 언어 정보 입력. 복수 응답 고려 - 주 언어 
		String[] majors = request.getParameterValues("cms_code_work");
		if(majors != null) {
			for(String major : majors) {
				MemberSubject ms = new MemberSubject();
				int code = Integer.parseInt(major);
				ms.setCms_category(0);
				ms.setCms_code(code);
				ms.setCms_owner_num(cmi_owner_num);
				dao.insertSubject(ms);
			}
		}
		
		
		// 언어 정보 입력. 복수 응답 고려 - 학습 중인 언어
		String[] learnings = request.getParameterValues("cms_code_learning");
		if(learnings != null) {
			for(String learning : learnings) {
				MemberSubject ms = new MemberSubject();
				int code = Integer.parseInt(learning);
				ms.setCms_category(1);
				ms.setCms_code(code);
				ms.setCms_owner_num(cmi_owner_num);
				dao.insertSubject(ms);
			}
		}
		
		// 부가 정보 입력 결과 처리 창. 
		if(isInfo) {
			HttpSession session = request.getSession();
			session.removeAttribute("joinMember");
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
		if(autoLogin != null && autoLogin.equals("on")) {
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
	
	public static void loginCookie(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("member")==null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("cm_email")) {
						String cm_email = c.getValue();
						System.out.println("쿠키 내의 이메일 정보 : "+cm_email);
						MemberDAO dao = new MemberDAOImpl();
						MemberVO member = dao.getMemberByEmail(cm_email);
						if(member != null) {
							session.setAttribute("member", member);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("세션 초기화 완료");
		
		Cookie cookie = new Cookie("cm_email", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("쿠키 삭제 완료");
		
		
		try {
			response.sendRedirect(request.getContextPath()+"/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getMyInfo(HttpServletRequest request) {
		String num = request.getParameter("cm_num");
		int cm_num = Integer.parseInt(num);
		// 정보도 같이 들고 오자
		MemberInfo info = dao.getInfoByNum(cm_num);
		System.out.println(cm_num+"번 회원의 이력서 리스트 : "+info);
		// 직업, 지역, 언어 정보도
		ArrayList<MemberJob> userJobCodes = dao.getJobList(cm_num);
		HashMap<Integer, String> userJobs = dao.getJobs(userJobCodes); 
//		ArrayList<String> userJobs = dao.getJobs(userJobCodes);
		System.out.println(cm_num+"번 회원의 직업 리스트 : "+userJobs);
		
		ArrayList<MemberRegion> userRegionCodes = dao.getRegionList(cm_num);
		HashMap<Integer, String> userRegions = dao.getRegions(userRegionCodes);
		System.out.println(cm_num+"번 회원의 선호 지역 리스트 : "+userRegions);
					
		ArrayList<MemberSubject> userSubjectCodes = dao.getSubjectList(cm_num);
		HashMap<String, String> userSubjects = dao.getSubjects(userSubjectCodes);
		System.out.println("언어 리스트 정리"+userSubjectCodes);
		System.out.println(cm_num+"번 회원의 언어 리스트 : "+userSubjects);
		
		// 데이터베이스에 저장된 애들도 불러와!
		ArrayList<JobVO> entireJobs = dao.getEntireJobs();
		ArrayList<RegionVO> entireRegions = dao.getEntireRegions();
		ArrayList<SubjectVO> entireSubjects = dao.getEntireSubjects();
		
		
		request.setAttribute("info", info);
		request.setAttribute("userJobs", userJobs);
		request.setAttribute("userRegions", userRegions);
		request.setAttribute("userSubjects", userSubjectCodes);
		
		request.setAttribute("ej", entireJobs);
		request.setAttribute("er", entireRegions);
		request.setAttribute("es", entireSubjects);
		
	}
	
	@Override
	public void getFullReSource(HttpServletRequest request) {
		// 데이터베이스에 저장된 애들도 불러와!
		ArrayList<JobVO> entireJobs = dao.getEntireJobs();
		ArrayList<RegionVO> entireRegions = dao.getEntireRegions();
		ArrayList<SubjectVO> entireSubjects = dao.getEntireSubjects();
		
		request.setAttribute("ej", entireJobs);
		request.setAttribute("er", entireRegions);
		request.setAttribute("es", entireSubjects);
		
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
		try {
			if(isChanged) {
				System.out.println("비밀번호 변경 성공");
				response.sendRedirect("home");
				
			}else {
				System.out.println("비밀번호 변경 실패");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('비밀번호 수정에 실패하셨습니다!');");
				out.print("history.go(-1);");
				out.print("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public boolean checkId(HttpServletRequest request) {
		boolean isDuplicated = false;
		
		String cm_email = request.getParameter("cm_email");
		System.out.println(cm_email);
		
		MemberVO member = dao.getMemberByEmail(cm_email);
		if(member != null)isDuplicated = true;
		
		return isDuplicated;
	}
	
	@Override
	public boolean loginCheckAsync(HttpServletRequest request) {
		boolean isOk = false;
		
		String email = request.getParameter("cm_email");
		String inputPw = request.getParameter("cm_pw");
		
		// 소금 가져오기
		int salt = dao.getSalt(email);
				
		String encryPw = UserSha256.encrypt(inputPw+salt);
				
		MemberVO member = dao.loginMember(email, encryPw);
		
		if(member != null)isOk = true;
		
		return isOk;
	}
	
	@Override
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) {
		
		int cm_num = Integer.parseInt(request.getParameter("cm_num"));
		
		String cm_phone = request.getParameter("cm_phone_first")+request.getParameter("cm_phone_middle")+request.getParameter("cm_phone_last");
		String cm_addr = request.getParameter("cm_addr");
		
		MemberVO vo = new MemberVO();
		vo.setCm_num(cm_num);
		vo.setCm_phone(cm_phone);
		vo.setCm_addr(cm_addr);
		boolean isMember = dao.updateMember(vo);
		
		
		String cmi_career = request.getParameter("cmi_career");
		String cmi_title = request.getParameter("cmi_title");
		String cmi_intro = request.getParameter("cmi_intro");
		String cmi_private = request.getParameter("cmi_private");
		
		MemberInfo info = new MemberInfo();
		info.setCmi_owner_num(cm_num);
		info.setCmi_title(cmi_title);
		info.setCmi_career(cmi_career);
		info.setCmi_intro(cmi_intro);
		info.setCmi_private(cmi_private);
		
		boolean isInfo = dao.updateInfo(info);
		
		try {
			if(isMember && isInfo) {
				System.out.println("가입 정보 및 부가 정보 수정 완료");
				// 있던 것들 삭제하기.
				dao.deleteJobs(cm_num);
				dao.deleteRegions(cm_num);
				dao.deleteSubjects(cm_num);
				
				// 없앤 곳에 새로 넣기. 
				String[] jobs = request.getParameterValues("cij_code");
				for(String job : jobs) {
					MemberJob mj = new MemberJob();
					int code = Integer.parseInt(job);
					mj.setCij_code(code);
					mj.setCij_owner_num(cm_num);
					dao.insertJob(mj);
				}
						
				// 지역 넣기 복수 응답 고려
				String[] regions = request.getParameterValues("cmr_code");
				for(String region : regions) {
					MemberRegion mr = new MemberRegion();
					int code = Integer.parseInt(region);
					mr.setCmr_code(code);
					mr.setCmr_owner_num(cm_num);
					dao.insertRegion(mr);
				}
						
				// 언어 정보 입력. 복수 응답 고려 - 주 언어 
				String[] majors = request.getParameterValues("cms_code_work");
				for(String major : majors) {
					MemberSubject ms = new MemberSubject();
					int code = Integer.parseInt(major);
					ms.setCms_category(0);
					ms.setCms_code(code);
					ms.setCms_owner_num(cm_num);
					dao.insertSubject(ms);
				}
						
				// 언어 정보 입력. 복수 응답 고려 - 학습 중인 언어
				String[] learnings = request.getParameterValues("cms_code_learning");
				for(String learning : learnings) {
					MemberSubject ms = new MemberSubject();
					int code = Integer.parseInt(learning);
					ms.setCms_category(1);
					ms.setCms_code(code);
					ms.setCms_owner_num(cm_num);
					dao.insertSubject(ms);
				}
				// 세션에 새로운 member 불러와서 저장하자. 
				MemberVO member = dao.getMemberByNum(cm_num);
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				// home으로 가자. 
				response.sendRedirect("home");
				
			}else {
				System.out.println("가입 정보 및 부가정보 수정 실패");
				// 부가정보 입력 페이지로 다시 넘어가기
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원 정보 수정에 실패하셨습니다!');");
				out.print("history.go(-1);");
				out.print("</script>");
				
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	
	@Override
	public boolean checkPwAsync(HttpServletRequest request) {

		
		int cm_num = Integer.parseInt(request.getParameter("cm_num"));
		
		String inputPw = request.getParameter("cur_pw");
		
		// 소금 가져오기
		int salt = dao.getSalt(cm_num);
				
		String encryPw = UserSha256.encrypt(inputPw+salt);
				
		boolean isPass = dao.existPw(cm_num, encryPw);
		
		return isPass;
	}
	
	@Override
	public void updatePw(HttpServletRequest request, HttpServletResponse response) {
		int cm_num = Integer.parseInt(request.getParameter("cm_num"));
		String new_pw = request.getParameter("cm_pw");
		
		// 소금 가져오기
		int salt = dao.getSalt(cm_num);
						
		String encryPw = UserSha256.encrypt(new_pw+salt);
		
		boolean updatePw = dao.changePass(cm_num, encryPw);
		
		try {
			if(updatePw) {
				System.out.println("비밀번호 변경 성공");
				// 세션에 새로운 member 불러와서 저장하자. 
				MemberVO member = dao.getMemberByNum(cm_num);
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				// home으로 가자. 
				response.sendRedirect("home");
			}else {
				System.out.println("비밀번호 변경 실패");
				// 부가정보 입력 페이지로 다시 넘어가기
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('비밀번호 변경에 실패하셨습니다!');");
				out.print("location.href='home';");
				out.print("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMember(HttpServletRequest request, HttpServletResponse response) {
		int cm_num = Integer.parseInt(request.getParameter("cm_num"));
		String cm_pw = request.getParameter("cm_pw");
		
		// 소금 가져오기
		int salt = dao.getSalt(cm_num);			
		String encryPw = UserSha256.encrypt(cm_pw+salt);
		
		// 회원 탈퇴
		boolean withdrawal = dao.deleteMember(cm_num, encryPw);
		if(withdrawal) {
			// 부가정보도 보이지 않게 하기
			dao.deleteInfo(cm_num);
			// 로그아웃 하기 
			logOut(request, response);
			System.out.println("회원탈퇴 및 로그아웃");
		}else {
			System.out.println("회원탈퇴 실패");
			try {
				response.sendRedirect("home");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 염전 천일염 짠 소금 원소기호 13번 Na 
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
