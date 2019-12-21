package board.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.qna.service.QNA_CommentService;
import board.qna.service.QNA_CommentServiceImpl;

/**
 * Servlet implementation class AsyncCommentServlet
 */
@WebServlet("*.co")
public class AsyncCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QNA_CommentService service = new QNA_CommentServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getRequestURI().substring(request.getContextPath().length()+1);
		Gson gson = new Gson();
		String json = "";
		
		if(cmd.equals("commentWrite.co")) {
			System.out.println("댓글 등록");
			json = gson.toJson(service.insertComment(request));
		}
		if(cmd.equals("list.co")) {
			System.out.println("댓글 목록");
			json = gson.toJson(service.getCommentList(request));
		}
		if(cmd.equals("commentDelete.co")) {
			System.out.println("댓글 삭제");
			json = gson.toJson(service.deleteComment(request));
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
