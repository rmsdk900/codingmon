package board.recruit.dao;

import java.util.ArrayList;

import board.recruit.vo.CommentVO;
import util.Criteria;

public interface RecruitCommentDAO {
	
	// 댓글 등록
	boolean insertComment(CommentVO vo);
	
	// 페이징 처리된 댓글
	ArrayList<CommentVO> getCommentList(int ccr_board_num, Criteria cri);
	
	// 게시물의 총 댓글 수
	int getCommentListCount(int ccr_board_num);
	
	// 댓글 삭제
	boolean deleteComment(int ccr_num, int ccr_writer_num);
	
	// 전체 댓글 삭제
	void delBoardComment(int num);

}
