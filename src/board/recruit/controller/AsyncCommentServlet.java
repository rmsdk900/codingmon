package board.recruit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.recruit.service.RecruitCommentService;
import board.recruit.service.RecruitCommentServiceImpl;
import util.FactoryUtil;

/**
 * Servlet implementation class AsyncCommentServlet
 */
@WebServlet("*.ccr")
public class AsyncCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RecruitCommentService ccrs = new RecruitCommentServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("comment controller get 요청");
		
		request.setCharacterEncoding("UTF-8");
		String cmd = FactoryUtil.getCommand(request);
		
		Gson gson = new Gson();
		
		String json = "";
		
		// 댓글 목록
		if(cmd.equals("commentList.ccr")) {
			System.out.println("댓글 목록 요청");
			json = gson.toJson(ccrs.getCommentList(request));
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("comment controller post 요청");
		
		request.setCharacterEncoding("UTF-8");
		String cmd = FactoryUtil.getCommand(request);
		
		Gson gson = new Gson();
		
		String json = "";
		
		// 댓글 등록
		if(cmd.equals("commentWrite.ccr")) {
			System.out.println("댓글 등록 요청");
			json = gson.toJson(ccrs.insertComment(request));
		}
		
		// 댓글 삭제
		if(cmd.equals("commentDelete.ccr")) {
			System.out.println("댓글 삭제 요청");
			json = gson.toJson(ccrs.deleteComment(request));
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
