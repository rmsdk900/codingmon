package board.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.qna.vo.QNA_BoardVO;
import board.qna.vo.QNA_CommentVO;
import util.Criteria;
import util.DBCPUtil;
import util.PageMaker;

public class QNA_BoardDAOImpl implements QNA_BoardDAO{

	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	@Override
	public int getListCount() {
		System.out.println("DAO 리스트 카운트");
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		String sql ="SELECT count(*) FROM codingmon_board_qna";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {DBCPUtil.close(rs,pstmt,conn);}
					
		return listCount;
	}

	@Override
	public ArrayList<QNA_BoardVO> getQNA_BoardList() {
		ArrayList<QNA_BoardVO> list = new ArrayList<>();
		conn = DBCPUtil.getConnection();
		
		try {
			String sql = "SELECT * FROM  codingmon_board_qna ORDER BY cbq_re_ref DESC, cbq_re_seq ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(getQNA_Board(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	
	
	
	
	@Override
	public ArrayList<QNA_BoardVO> getQNA_BoardList(PageMaker pageMaker) {
			
			System.out.println("dao boardList 요청");
		
			ArrayList<QNA_BoardVO> list = new ArrayList<>();
			
			conn = DBCPUtil.getConnection();
			
			String sql = "SELECT * FROM codingmon_board_qna "
					+ "ORDER BY cbq_re_ref DESC ,"
					+ "cbq_re_seq ASC "
					+ "limit ? , ? ";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pageMaker.getCri().getPageStart());
				pstmt.setInt(2, pageMaker.getCri().getPerPageNum());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					list.add(getQNA_Board(rs));
				}
				System.out.println(list);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBCPUtil.close(rs,pstmt,conn);
			}
			return list;
			

	}

	@Override
	public void qna_boardWrite(QNA_BoardVO qna_board) {
		
		
			conn = DBCPUtil.getConnection();
			
			try {
			
			
				conn.setAutoCommit(false);
				String sql = " INSERT INTO codingmon_board_qna VALUES(null,?,?,?,?,0,0,0,0,now(),?,?,?,'N')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, qna_board.getCbq_writer_num());
				pstmt.setString(2, qna_board.getCbq_writer_name());
				pstmt.setString(3,qna_board.getCbq_title());
				pstmt.setString(4, qna_board.getCbq_content());
				pstmt.setString(5,qna_board.getCbq_file());
				pstmt.setString(6, qna_board.getCbq_file_origin());
				pstmt.setInt(7, qna_board.getCbq_up());
				pstmt.executeUpdate();
				
				sql = "SELECT LAST_INSERT_ID()";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int cbq_num = 0;
				if(rs.next())cbq_num=rs.getInt(1);
				System.out.println("cbq_num : " +cbq_num);
				
				sql = " UPDATE codingmon_board_qna SET "
						+ "cbq_re_ref = ? "
						+ "WHERE cbq_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cbq_num);
				pstmt.setInt(2, cbq_num);
				pstmt.executeUpdate();
				System.out.println("등록완료");
				conn.commit();
				
}  catch (SQLException e) {
	e.printStackTrace();
	try {
		conn.rollback();
	} catch (SQLException e1) {}
}finally {
	DBCPUtil.close(rs,pstmt,conn);
}	
		
	}
	@Override
	public QNA_BoardVO getQNA_BoardVO(int cbq_num) {
		System.out.println("dao 출력");
		QNA_BoardVO qna_board = null;
		conn = DBCPUtil.getConnection();
		String sql = "SELECT * FROM codingmon_board_qna WHERE cbq_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbq_num);
			rs = pstmt.executeQuery();
			if(rs.next()) qna_board = getQNA_Board(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return qna_board;
	}
	public QNA_BoardVO getQNA_Board(ResultSet rs) throws SQLException{
		
		QNA_BoardVO qna_board = new QNA_BoardVO();
		qna_board.setCbq_num(rs.getInt("cbq_num"));
		qna_board.setCbq_writer_num(rs.getInt("cbq_writer_num"));
		qna_board.setCbq_writer_name(rs.getString("cbq_writer_name"));
		qna_board.setCbq_title(rs.getString("cbq_title"));
		qna_board.setCbq_content(rs.getString("cbq_content"));
		qna_board.setCbq_re_ref(rs.getInt("cbq_re_ref"));
		qna_board.setCbq_re_lev(rs.getInt("cbq_re_lev"));
		qna_board.setCbq_re_seq(rs.getInt("cbq_re_seq"));
		qna_board.setCbq_readcount(rs.getInt("cbq_readcount"));
		qna_board.setCbq_regdate(rs.getTimestamp("cbq_regdate"));
		qna_board.setCbq_file(rs.getNString("cbq_file"));
		qna_board.setCbq_file_origin(rs.getNString("cbq_file_origin"));
		qna_board.setCbq_up(rs.getInt("cbq_up"));
		qna_board.setCbq_delete(rs.getString("cbq_delete"));
		
		return qna_board;
	}

	@Override
	public void updateReadCount(int cbq_num) {
		conn = DBCPUtil.getConnection();
		
		String sql = " UPDATE codingmon_board_qna SET "
				   + " cbq_readcount = cbq_readcount + 1 "
				   + " WHERE cbq_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbq_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
	}




	@Override
	public boolean qna_boardUpdate(QNA_BoardVO qna_board) {
		
		boolean isSuccess = false;
		
		String updateFile = qna_board.getCbq_file();
		conn = DBCPUtil.getConnection();
		String sql = null;
		try {
			// 바꿀 파일이 있을 때
			if(updateFile!=null) {
				sql = "UPDATE codingmon_board_qna SET cbq_title=?, cbq_content=?, cbq_file=?, cbq_file_origin=?, cbq_regdate=now() WHERE cbq_num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, qna_board.getCbq_title());
				pstmt.setString(2, qna_board.getCbq_content());
				pstmt.setString(3, qna_board.getCbq_file());
				pstmt.setString(4, qna_board.getCbq_file_origin());
				pstmt.setInt(5, qna_board.getCbq_num());
				isSuccess = true;
			// 바꿀 파일이 없을 때
			}else {
				sql = "UPDATE codingmon_board_qna SET cbq_title=?, cbq_content=?, cbq_regdate=now() WHERE cbq_num=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, qna_board.getCbq_title());
				pstmt.setString(2, qna_board.getCbq_content());
				pstmt.setInt(3, qna_board.getCbq_num());
				isSuccess = true;
			}
			System.out.println("수정 sql : "+sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt, conn);
		}
		
		
		return isSuccess;
	}
	
	@Override
	public boolean qna_boardDelete(int cbq_num) {


			boolean isDeleted=false;
			
			conn = DBCPUtil.getConnection();
			
			try {
				String sql = "DELETE FROM codingmon_board_qna "
						+ "WHERE cbq_num = ? ";
						//+ "AND cnq_writer_num = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cbq_num);
				//pstmt.setInt(2, cbq_writer_num);
				
				if(pstmt.executeUpdate()>0) {
					isDeleted = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {DBCPUtil.close(pstmt,conn);}
			return isDeleted;
		}

	@Override
	public void qna_boardReplySubmit(QNA_BoardVO qna_board) {
		
		
		int ref = qna_board.getCbq_re_ref();
		int lev = qna_board.getCbq_re_lev();
		int seq = qna_board.getCbq_re_seq();
		
		
		String sql = "UPDATE codingmon_board_qna SET "
					+ " cbq_re_seq = cbq_re_seq + 1 "
					+ " WHERE cbq_re_ref = ? AND cbq_re_seq > ? ";
		
		conn = DBCPUtil.getConnection();
		
		
		try {
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			pstmt.executeUpdate();
			
			sql = "INSERT INTO codingmon_board_qna "
				+ " VALUES(null,?,?,?,?,?,?,?,0,now(),?,?,?,'N')";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_board.getCbq_writer_num());
			pstmt.setString(2, qna_board.getCbq_writer_name());
			pstmt.setString(3, qna_board.getCbq_title());
			pstmt.setString(4, qna_board.getCbq_content());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, lev+1);
			pstmt.setInt(7, seq+1);
			pstmt.setString(8,"");
			pstmt.setString(9,"");
			pstmt.setInt(10, qna_board.getCbq_up());
		
			pstmt.executeUpdate();
			System.out.println(pstmt);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {}
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
	}


	@Override
	public int getCommentListCount(int ccq_board_num) {
		String sql = "SELECT count(*) FROM codingmon_comment_qna WHERE ccq_board_num=?";
		int listCount = 0;
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccq_board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) listCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(rs,pstmt,conn);}
		return listCount;
	}

	
	@Override
	public boolean insertComment(QNA_CommentVO qna_commentvo) {
		System.out.println("dao 댓글 등록 요청");
		boolean isSuccess = false;
		
		conn = DBCPUtil.getConnection();
		try {
			String sql = "INSERT INTO codingmon_comment_qna VALUES(NULL,?,?,?,?,now(),'N')";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, qna_commentvo.getCcq_board_num());
			pstmt.setInt(2, qna_commentvo.getCcq_writer_num());
			pstmt.setString(3,qna_commentvo.getCcq_writer_name());
			pstmt.setString(4,qna_commentvo.getCcq_content());
			if(pstmt.executeUpdate()>0) isSuccess = true;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
	}
		return isSuccess;}
	

	@Override
	public ArrayList<QNA_CommentVO> getCommentList(int ccq_board_num, Criteria cri) {
		System.out.println("dao 댓글 목록 요청");
		String sql = "SELECT * FROM codingmon_comment_qna "
				+ " WHERE ccq_board_num =? AND ccq_delete = 'N'"
				+ " ORDER BY ccq_num DESC limit ?,?";
		ArrayList<QNA_CommentVO> list = new ArrayList<>();
		
		conn = DBCPUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccq_board_num);
			pstmt.setInt(2, cri.getPageStart());
			pstmt.setInt(3, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				QNA_CommentVO qcv = new QNA_CommentVO();
			
				qcv.setCcq_num(rs.getInt("ccq_num"));
				qcv.setCcq_board_num(rs.getInt("ccq_board_num"));
				qcv.setCcq_writer_num(rs.getInt("ccq_writer_num"));
				qcv.setCcq_writer_name(rs.getString("ccq_writer_name"));
				qcv.setCcq_content(rs.getString("ccq_content"));
				qcv.setCcq_regdate(rs.getTimestamp("ccq_regdate"));
				qcv.setCcq_delete(rs.getString("ccq_delete"));
				
				list.add(qcv);
				}
			} catch (SQLException e) {}
			finally {DBCPUtil.close(rs,pstmt,conn);}
		System.out.println("반환?");
			return list;
		}

	@Override
	public boolean deleteComment(int ccq_num, int ccq_writer_num) {
		System.out.println("dao 댓글 삭제 요청");
		boolean isSuccess = false;
		
		/*
		 * String sql = "UPDATE codingmon_comment_qna SET " + " ccq_delete = 'Y' " +
		 * " WHERE ccq_num = ? " + " AND ccq_writer_num = ?";
		 */
		String sql = "DELETE from codingmon_comment_qna  "
				  + " WHERE ccq_num = ? "
				  + " AND ccq_writer_num = ?";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccq_num);
			pstmt.setInt(2, ccq_writer_num);
			if(pstmt.executeUpdate() > 0 ) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {DBCPUtil.close(pstmt,conn);}
		return isSuccess;
	}

	/*
	 * @Override public ArrayList<String, Object> getCommentList(int ccq_num,
	 * Criteria cri) { String sql =
	 * "SELECT * FROM codingmon_comment_qna WHERE ccq_board_num=? ORDER BY ccq_num DESC limit ?,?"
	 * ;
	 * 
	 * Gson gson = new Gson(); JsonArray json = new JsonArray(); conn =
	 * DBCPUtil.getConnection();
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, ccq_num);
	 * pstmt.setInt(2, cri.getPageStart()); pstmt.setInt(3, cri.getPerPageNum()); rs
	 * = pstmt.executeQuery(); while(rs.next()) { JsonObject object = new
	 * JsonObject(); object.addProperty("ccq_num", rs.getInt("ccq_num"));
	 * object.addProperty("ccq_writer_num", rs.getInt("ccq_writer_num"));
	 * object.addProperty("ccq_writer_name", rs.getString("ccq_writer_name"));
	 * object.addProperty("ccq_content", rs.getString("ccq_content"));
	 * object.addProperty("ccq_regdate", rs.getTimestamp("ccq_regdate").toString());
	 * object.addProperty("ccq_delete", rs.getString("ccq_delete"));
	 * json.add(object); }
	 * 
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }finally {DBCPUtil.close(rs,
	 * pstmt, conn);}
	 * 
	 * 
	 * 
	 * return json; }
	 */

	//@Override
//	public boolean qna_boardDelete(int cbq_num, int cbq_writer_num) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
}
