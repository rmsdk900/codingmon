package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import util.FactoryUtil;

/**
 * Servlet implementation class MemberController
 * 
 * 유킹볼 왔다감. 
 * 게임을 시작하지
 * (숨은 v 찾기. 힌트 : 1개입니다.)
 */
@WebServlet("/user/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService service = new MemberServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberController GET 요청");
		
		MemberServiceImpl.loginCookie(request);
		
		String cmd = FactoryUtil.getCommand(request);
		
		String nextPage = null;
		
		if(cmd.equals("user/home")) {
			System.out.println("홈 화면 가즈아");
			nextPage = "/common/main.jsp";
		}
		
		if(cmd.equals("user/join")) {
			System.out.println("회원가입 화면 호출");
			nextPage = "/member/join1.jsp";
		}
		
		if(cmd.equals("user/login")) {
			System.out.println("로그인 화면 호출");
			nextPage = "/member/login.jsp";
		}
		
		if(cmd.equals("user/logout")) {
			System.out.println("로그아웃 시도");
			service.logOut(request, response);
		}
		
		if(cmd.equals("user/myInfo")) {
			System.out.println("내 정보 화면 요청");
			nextPage = "/member/userInfo.jsp";
		}
		
		if(cmd.equals("user/findPw")) {
			System.out.println("비밀번호 찾기 화면 호출");
			nextPage = "/member/findPass.jsp";
		}
		
		if(cmd.equals("user/updateInfo")) {
			System.out.println("내정보 수정을 위한 비밀번호 확인 화면 호출");
			nextPage = "/member/checkPw.jsp";
		}
		
		if(cmd.equals("user/updatePw")) {
			System.out.println("비밀번호 변경 화면 호출");
			nextPage = "/member/updatePw.jsp";
		}
		
		if(cmd.equals("user/deleteMember")) {
			System.out.println("회원탈퇴 화면 호출");
			nextPage="/member/deleteMember.jsp";
		}
		
		FactoryUtil.nextPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberServiceImpl.loginCookie(request);
		
		System.out.println("MemberController POST 요청");
		String cmd = FactoryUtil.getCommand(request);
		System.out.println("post 요청 uri : "+cmd);
		String nextPage = null;
		
		if(cmd.equals("user/join")) {
			System.out.println("회원가입  시도");
			boolean isSignUp = service.memberSignUp(request);
			if(isSignUp) {
				System.out.println("회원가입 성공");
				service.getFullReSource(request);
				nextPage="/member/insertInfo1.jsp";
			}else {
				System.out.println("회원가입 실패");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원가입을 실패하셨습니다.');");
				out.print("hitory.go(-1)");
				out.print("</script>");
			}
			
		}
		
		if(cmd.equals("user/insertInfo")) {
			System.out.println("부가정보 입력 시도");
			service.insertInfo(request, response);
		}
		
		if(cmd.equals("user/login")) {
			System.out.println("로그인 시도");
			boolean isLogin = service.memberLogin(request, response);
			if(isLogin) {
				System.out.println("로그인 성공");
				response.sendRedirect(request.getContextPath()+"/");
			}else {
				System.out.println("로그인 실패");
				nextPage = "/member/login.jsp";
			}
		}
		
		if(cmd.equals("user/sendPw")) {
			System.out.println("pw code 이메일 보내기");
			service.sendPasscode(request, response);
			
		}
		if(cmd.equals("user/checkPwCode")) {
			System.out.println("pw code 대조");
			boolean isRight = service.checkPwCode(request, response);
			if(isRight) {
				System.out.println("비밀번호 코드가 일치함.");
				nextPage = "/member/changePw.jsp";
			}else {
				System.out.println("비밀번호 코드가 일치하지 않음.");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('비밀번호 코드가 일치하지 않습니다. 다시 입력해주세요.');");
				out.print("history.go(-1);");
				out.print("</script>");
			}
		}
		
		if(cmd.equals("user/changePw")) {
			System.out.println("비밀번호 변경 요청");
			service.changePw(request, response);
			
		}
		
		if(cmd.equals("user/infoUpdate")) {
			System.out.println("부가 정보 수정 화면 요청");
			service.getMyInfo(request);
			nextPage = "/member/infoUpdate.jsp";
		}
		
		if(cmd.equals("user/updateInfoSubmit")) {
			System.out.println("부가 정보 수정 처리");
			service.updateInfo(request, response);
		}
		
		
		
		if(cmd.equals("user/updatePwSubmit")) {
			System.out.println("비밀번호 변경 처리");
			service.updatePw(request, response);
		}
		
		if(cmd.equals("user/deleteMemberSubmit")) {
			System.out.println("회원 탈퇴 처리");
			service.deleteMember(request, response);
		}
		
		FactoryUtil.nextPage(request, response, nextPage);
		
		
		
		
		
	}

}
