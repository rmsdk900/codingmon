package board.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.service.QNA_BoardService;
import board.qna.service.QNA_BoardServiceImpl;
import board.qna.vo.QNA_BoardVO;



/**
 * @date 2019-12-02 추가
 * @apiNote 질문과 답변 게시판 추가
 */
@WebServlet("*.bo")
public class QNA_BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	QNA_BoardService service = new QNA_BoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" GET 요청");
		request.setCharacterEncoding("UTF-8");
		
		
		String cmd 
		= request.getRequestURI().substring(
				request.getContextPath().length()+1
				);
		System.out.println("요청 URI : " + cmd);
		
		
		String nextPage = null;
		
		if(cmd.equals("boardList.bo")) {
			ArrayList<QNA_BoardVO> list = service.getQNA_BoardList(request);
			request.setAttribute("qna_List", list);
			System.out.println("qna 목록 가즈아");
			nextPage ="/board/qna/qna_board/qna_list.jsp";
		}
		
		if(cmd.equals("boardWrite.bo")) {
			nextPage ="/board/qna/qna_board/qna_write.jsp";
		}
		
		if(cmd.equals("boardWriteSubmit.bo")) {
			System.out.println("게시물을 등록 요청");
			service.boardWrite(request);
			response.sendRedirect("boardList.bo");
		}
		
	
		if(cmd.equals("boardDetail.bo")) {
			service.updateReadCount(request);
			response.sendRedirect("boardRead.bo?cbq_num="+request.getParameter("cbq_num"));
		}
		
		if(cmd.equals("boardRead.bo")){
			QNA_BoardVO board = service.getQNA_BoardVO(request);
			request.setAttribute("boardVO", board);
			
			nextPage ="/board/qna/qna_board/qna_detail.jsp";
		}
		
		if(cmd.equals("boardUpdateForm.bo")) {
			QNA_BoardVO board = service.getQNA_BoardVO(request);
			System.out.println("게시물 수정 요청"+ board);
			request.setAttribute("board", board);
			nextPage ="/board/qna/qna_board/qna_update.jsp";
		}
		if(cmd.equals("boardUpdateSubmit.bo")) {
			System.out.println("수정된게시물 등록요청");
			service.QNAboardUpdate(request, response);
		}
		
		if(cmd.equals("boardReplyForm.bo")) {
			QNA_BoardVO board = service.getQNA_BoardVO(request);
			System.out.println("답글 작성 여청");
			request.setAttribute("boardVO", board);
			nextPage ="/board/qna/qna_board/qna_reply.jsp";
		}
		
		if(cmd.equals("boardReplySubmit.bo")) {
			System.out.println("답글 등록 여청");
			service.boardReplySubmit(request);
			response.sendRedirect("boardList.bo");
		}
		
		if(cmd.equals("file_down.bo")) {
			System.out.println("파일 다운로드 요청");
			service.fileDown(request, response);
		}
		
		if(cmd.equals("boardDelete.bo")) {
			System.out.println("게시물 삭제 요청");
			service.QNAboardDelete(request, response);
		}
		
		/*
		 * 20191206 comment 추가
		 */
		if(cmd.equals("commentWrite.bo")) {
			System.out.println("댓글 작성 요청");
			service.insertComment(request, response);
		}
		
		if(cmd.equals("commentDelete.bo")) {
			System.out.println("댓글 삭제 요청");
			service.deleteComment(request, response);
		}
		/*
		 * if(cmd.equals("commentWriteAJAX.bo")) { System.out.println("AJAX 댓글 작성 요청");
		 * JsonObject json = service.insertCommentAJAX(request, response);
		 * response.setContentType("application/json;charset=utf-8"); PrintWriter out =
		 * response.getWriter(); out.print(json); }
		 * 
		 * if(cmd.equals("commentListAJAX.bo")) { System.out.println("AJAX 댓글 리스트 요청");
		 * service.getCommentListAJAX(request, response);
		 * 
		 * }
		 */
		
		
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
