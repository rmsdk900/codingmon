package board.qna.dao;

import java.util.ArrayList;

import board.qna.vo.QNA_BoardVO;
import board.qna.vo.QNA_CommentVO;
import util.Criteria;
import util.PageMaker;

public interface QNA_BoardDAO {

	
		int getListCount();
	
		public ArrayList<QNA_BoardVO> getQNA_BoardList();
	
		public ArrayList<QNA_BoardVO> getQNA_BoardList(PageMaker pageMaker);
		
		void qna_boardWrite(QNA_BoardVO qna_board);
		
		QNA_BoardVO getQNA_BoardVO(int cbq_num);
		
		void updateReadCount(int cbq_num);
		
		void qna_boardReplySubmit(QNA_BoardVO qna_board);
		
		boolean qna_boardUpdate(QNA_BoardVO qna_board);
		
		boolean qna_boardDelete(int cbq_num);
		//boolean qna_boardDelete(int cbq_num, int cbq_writer_num);
		
		///////////////////////////comment
		
		boolean insertComment(QNA_CommentVO qna_commentvo);
		
		ArrayList<QNA_CommentVO> getCommentList(int ccq_board_num, Criteria cri);
		
		int getCommentListCount(int ccq_board_num);

		boolean deleteComment(int ccq_num, int ccq_writer_num);

	/* JsonArray getCommentListAJAX(int cbq_num, Criteria cri); */
	}

	

	
