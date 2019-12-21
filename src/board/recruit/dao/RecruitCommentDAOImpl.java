package board.recruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.recruit.vo.CommentVO;
import util.Criteria;
import util.DBCPUtil;

public class RecruitCommentDAOImpl implements RecruitCommentDAO{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// recruit 게시판 코멘트등록
	@Override
	public boolean insertComment(CommentVO vo) {
		System.out.println("dao insertComment 요청 받음");
		
		boolean isSuccess = false;

		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		String sql = "INSERT INTO codingmon_comment_recruit VALUES("
					+ " null, ?, ?, ?, ?, now(), 'N')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCcr_board_num());
			pstmt.setInt(2, vo.getCcr_writer_num());
			pstmt.setString(3, vo.getCcr_writer_name());
			pstmt.setString(4, vo.getCcr_content());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("dao insertComment true");
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
		return isSuccess;
	}

	// recruit 게시판 코멘트 목록
	@Override
	public ArrayList<CommentVO> getCommentList(int ccr_board_num, Criteria cri) {
		System.out.println("dao commentList 목록 요청받음");
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		String sql = "SELECT * FROM codingmon_comment_recruit "
					+ " WHERE ccr_board_num = ? "
					+ " ORDER BY ccr_num DESC LIMIT ?, ?";
		
		ArrayList<CommentVO> ccrList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccr_board_num);
			pstmt.setInt(2, cri.getPageStart());
			pstmt.setInt(3, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentVO cv = new CommentVO();
				cv.setCcr_num(rs.getInt("ccr_num"));
				cv.setCcr_board_num(rs.getInt("ccr_board_num"));
				cv.setCcr_writer_num(rs.getInt("ccr_writer_num"));
				cv.setCcr_writer_name(rs.getString("ccr_writer_name"));
				cv.setCcr_content(rs.getString("ccr_content"));
				cv.setCcr_regdate(rs.getTimestamp("ccr_regdate"));
				cv.setCcr_delete(rs.getString("ccr_delete"));
				
				ccrList.add(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		System.out.println("ccrList 반환");
		return ccrList;
	}

	// 게시판 번호로 코멘트 목록 받아오기
	@Override
	public int getCommentListCount(int ccr_board_num) {
		System.out.println("dao comment list count 요청받음");
		
		int clCount = 0;

		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM codingmon_comment_recruit"
					+ " WHERE ccr_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccr_board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) clCount = rs.getInt(1);
			System.out.println(clCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		System.out.println("ccrListCount 반환");
		return clCount;
	}

	// 코멘트 삭제( 작성자 or 관리자 )
	@Override
	public boolean deleteComment(int ccr_num, int ccr_writer_num) {
		System.out.println("dao deleteComment 요청 받음");
		
		boolean isDeleted = false;
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();		
		String sql = "UPDATE codingmon_comment_recruit SET "
					+ " ccr_delete = 'Y' "
					+ " WHERE ccr_num = ? "
					+ " AND (ccr_writer_num = 1 OR ccr_writer_num = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccr_num);
			pstmt.setInt(2, ccr_writer_num);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("dao deleteComment true");
				isDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
		return isDeleted;
	}

	// 전체 코멘트 삭제(게시글 삭제 시)
	@Override
	public void delBoardComment(int num) {
		System.out.println("dao 전체 코멘트 삭제 요청");
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_comment_recruit WHERE ccr_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0) System.out.println(num+"번 게시글의 전체 코멘트 삭제 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
	}
}
