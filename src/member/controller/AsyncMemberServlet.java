package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import util.FactoryUtil;

/**
 * Servlet implementation class AsyncMemberServlet
 */
@WebServlet("*.async")
public class AsyncMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService service = new MemberServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String cmd = FactoryUtil.getCommand(request);
		
		Gson gson = new Gson();
		String json = "";
		
		if(cmd.equals("checkId.async")) {
			System.out.println("비동기 아이디 체크 실행");
			json = gson.toJson(service.checkId(request));
		}
		
		if(cmd.equals("login.async")) {
			System.out.println("비동기 로그인 확인");
			json = gson.toJson(service.loginCheckAsync(request));
		}
		
		if(cmd.equals("checkPw.async")) {
			System.out.println("정보 변경을 위한 비밀번호 확인");
			json = gson.toJson(service.checkPwAsync(request));
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
