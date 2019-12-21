package board.qna.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.vo.QNA_BoardVO;
import board.qna.vo.QNA_CommentVO;

public interface QNA_BoardService {
	
		ArrayList<QNA_BoardVO> getQNA_BoardList();
	
		ArrayList<QNA_BoardVO> getQNA_BoardList(HttpServletRequest request);

		QNA_BoardVO getQNA_BoardVO(HttpServletRequest request);
		
		void updateReadCount(HttpServletRequest request);
		
		void fileDown(HttpServletRequest request, HttpServletResponse response);
		
		void QNAboardUpdate(HttpServletRequest request,HttpServletResponse response)throws IOException;
		
		void QNAboardDelete(HttpServletRequest request,HttpServletResponse response)throws IOException;

		void boardWrite(HttpServletRequest request);
		
		void boardReplySubmit(HttpServletRequest request);
		
		
		
		void insertComment(
				HttpServletRequest request, 
				HttpServletResponse response) throws IOException;
		
		ArrayList<QNA_CommentVO> getCommentList(HttpServletRequest request);
		
		void deleteComment(
				HttpServletRequest request, 
				HttpServletResponse response) throws IOException;


	/*
	 * JsonObject insertCommentAJAX(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException;
	 * 
	 * void getCommentListAJAX(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException;
	 */
		

	
		
}
