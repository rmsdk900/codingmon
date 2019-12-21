package board.recruit.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import board.recruit.dao.RecruitCommentDAO;
import board.recruit.dao.RecruitCommentDAOImpl;
import board.recruit.vo.CommentVO;
import util.Criteria;
import util.PageMaker;

public class RecruitCommentServiceImpl implements RecruitCommentService{

	RecruitCommentDAO dao = new RecruitCommentDAOImpl();
	
	// 댓글 작성
	@Override
	public boolean insertComment(HttpServletRequest request) {
		System.out.println("서비스 comment 등록 요청");
		
		String ccr_board_num = request.getParameter("ccr_board_num");
		String ccr_writer_num = request.getParameter("ccr_writer_num");
		String ccr_writer_name = request.getParameter("ccr_writer_name");
		String ccr_content = request.getParameter("ccr_content");
		
		CommentVO cv = new CommentVO();
		cv.setCcr_board_num(Integer.parseInt(ccr_board_num));
		cv.setCcr_writer_num(Integer.parseInt(ccr_writer_num));
		cv.setCcr_writer_name(ccr_writer_name);
		cv.setCcr_content(ccr_content);
		
		return dao.insertComment(cv);
	}

	// 댓글 목록
	@Override
	public HashMap<String, Object> getCommentList(HttpServletRequest request) {
		System.out.println("서비스 comment 목록 요청");
		
		HashMap<String, Object> map = new HashMap<>();
		
		int ccr_board_num = Integer.parseInt(request.getParameter("ccr_board_num"));
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = dao.getCommentListCount(ccr_board_num);
		Criteria cri = new Criteria(page, 5);
		PageMaker pm = new PageMaker(cri, listCount);
		
		System.out.println(dao.getCommentList(ccr_board_num, cri));
		
		map.put("list", dao.getCommentList(ccr_board_num, cri));
		map.put("pm", pm);
		
		return map;
	}

	// 댓글 삭제
	@Override
	public boolean deleteComment(HttpServletRequest request) {
		System.out.println("서비스 댓글 삭제 요청");
		
		int ccr_num = Integer.parseInt(request.getParameter("ccr_num"));
		int ccr_writer_num = Integer.parseInt(request.getParameter("ccr_writer_num"));
		
		return dao.deleteComment(ccr_num, ccr_writer_num);
	}
}
