package board.qna.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import board.qna.dao.QNA_BoardDAO;
import board.qna.dao.QNA_BoardDAOImpl;
import board.qna.vo.QNA_CommentVO;
import util.Criteria;
import util.PageMaker;

public class QNA_CommentServiceImpl  implements QNA_CommentService{

	QNA_BoardDAO qna_dao = new QNA_BoardDAOImpl();
	
	
	@Override
	public boolean insertComment(HttpServletRequest request) {
		System.out.println("서비스 댓글 등록 요청");
		String cbq_num = request.getParameter("cbq_num");
		String ccq_writer_num = request.getParameter("ccq_writer_num");
		String ccq_writer_name = request.getParameter("ccq_writer_name");
		String ccq_content = request.getParameter("ccq_content");
		
		QNA_CommentVO qcv = new QNA_CommentVO();
		qcv.setCcq_board_num(Integer.parseInt(cbq_num));
		qcv.setCcq_writer_num(Integer.parseInt(ccq_writer_num));
		qcv.setCcq_writer_name(ccq_writer_name);
		qcv.setCcq_content(ccq_content);
		
		return qna_dao.insertComment(qcv);
	}

	@Override
	public HashMap<String, Object> getCommentList(HttpServletRequest request) {
		System.out.println("서비스 댓글 목록 요청");
		HashMap<String, Object> map = new HashMap<>();

		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int board_num = Integer.parseInt(request.getParameter("ccr_board_num"));
		int listCount = qna_dao.getCommentListCount(board_num);
		System.out.println(listCount);

		Criteria cri = new Criteria(page,5);
		PageMaker pageMaker  = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
				
		map.put("pagemaker", pageMaker);
	    map.put("list", qna_dao.getCommentList(board_num, cri));
		return map;
	}
	

	@Override
	public boolean deleteComment(HttpServletRequest request) {
		System.out.println("서비스 댓글 삭제 요청");
			int ccq_num 
			= Integer.parseInt(request.getParameter("ccq_num"));
			int ccq_writer_num
			= Integer.parseInt(request.getParameter("ccq_writer_num"));
			return qna_dao.deleteComment(ccq_num, ccq_writer_num);
	}

}
