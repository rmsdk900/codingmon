package board.recruit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import board.recruit.util.Criteria;
import board.recruit.util.PageMaker;
import board.recruit.util.SearchCriteria;
import board.recruit.vo.RecruitVO;
import board.recruit.vo.job.RecruitJob;
import board.recruit.vo.job.codingmon_jobVO;
import board.recruit.vo.payment.RecruitPayment;
import board.recruit.vo.payment.codingmon_paymentVO;
import board.recruit.vo.region.RecruitRegion;
import board.recruit.vo.region.codingmon_regionVO;
import board.recruit.vo.subject.RecruitSubject;
import board.recruit.vo.subject.codingmon_subjectVO;
import util.DBCPUtil;



public class RecruitDAOImpl implements RecruitDAO{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 전체 게시글 개수
	@Override
	public int getTotalCount() {
		System.out.println("dao tatalCount 요청받음");
		
		int totalCount = 0;
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT COUNT(*) FROM codingmon_board_recruit WHERE Date(cbr_deadline) > Date(now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) totalCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		System.out.println("dao totalCount 반환");
		return totalCount;
	}

	// recruit 게시글 목록
	@Override
	public ArrayList<RecruitVO> getCbrList(Criteria cri) {
		System.out.println("dao cbrList 요청받음");
		
		ArrayList<RecruitVO> cbrList = new ArrayList<>();
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_board_recruit "
					+ " WHERE Date(cbr_deadline) > Date(now())"
					+ " ORDER BY cbr_num DESC LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitVO vo = new RecruitVO();
				vo.setCbr_num(rs.getInt("cbr_num"));
				vo.setCbr_title(rs.getString("cbr_title"));
				vo.setCbr_writer(rs.getString("cbr_writer"));
				vo.setCbr_writer_num(rs.getInt("cbr_writer_num"));
				vo.setCbr_pay(rs.getDouble("cbr_pay"));
				vo.setCbr_content(rs.getString("cbr_content"));
				vo.setCbr_regdate(rs.getDate("cbr_regdate"));
				vo.setCbr_date_from(rs.getDate("cbr_date_from"));
				vo.setCbr_date_to(rs.getDate("cbr_date_to"));
				vo.setCbr_deadline(rs.getDate("cbr_deadline"));
				cbrList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		System.out.println("dao cbrList 반환");
		
		return cbrList;
	}

	// recruit cbr_num 으로 게시글 검색
	@Override
	public RecruitVO getCbrVO(int cbr_num) {
		System.out.println("dao cbrVO 요청받음");
		
		RecruitVO vo = null;
		
		String sql = "SELECT * FROM codingmon_board_recruit WHERE cbr_num = " + cbr_num;
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new RecruitVO();
				vo.setCbr_num(rs.getInt("cbr_num"));
				vo.setCbr_title(rs.getString("cbr_title"));
				vo.setCbr_writer(rs.getString("cbr_writer"));
				vo.setCbr_writer_num(rs.getInt("cbr_writer_num"));
				vo.setCbr_content(rs.getString("cbr_content"));
				vo.setCbr_pay(rs.getDouble("cbr_pay"));
				vo.setCbr_regdate(rs.getDate("cbr_regdate"));
				vo.setCbr_date_from(rs.getDate("cbr_date_from"));
				vo.setCbr_date_to(rs.getDate("cbr_date_to"));
				vo.setCbr_deadline(rs.getDate("cbr_deadline"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
			//TestDBUtil.close(rs, pstmt, conn);
		}
		return vo;
	}
	
	// 최근 등록 글 검색
	@Override
	public RecruitVO getRecent(int cbr_writer_num) {
		System.out.println("dao 최근 등록 글 검색");
		
		RecruitVO vo = null;
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_board_recruit WHERE cbr_writer_num = ? ORDER BY cbr_num DESC LIMIT 1";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbr_writer_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new RecruitVO();
				vo.setCbr_num(rs.getInt("cbr_num"));
				vo.setCbr_title(rs.getString("cbr_title"));
				vo.setCbr_writer(rs.getString("cbr_writer"));
				vo.setCbr_writer_num(rs.getInt("cbr_writer_num"));
				vo.setCbr_content(rs.getString("cbr_content"));
				vo.setCbr_pay(rs.getDouble("cbr_pay"));
				vo.setCbr_regdate(rs.getDate("cbr_regdate"));
				vo.setCbr_date_from(rs.getDate("cbr_date_from"));
				vo.setCbr_date_to(rs.getDate("cbr_date_to"));
				vo.setCbr_deadline(rs.getDate("cbr_deadline"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
			//TestDBUtil.close(rs, pstmt, conn);
		}
		return vo;
	}

	// 검색 조건으로 목록 count
	@Override
	public int getSearchListCount(SearchCriteria cri) {
		System.out.println("dao searchListCount 요청받음");
		
		int listCount = 0;
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT COUNT(*) FROM codingmon_board_recruit WHERE Date(cbr_deadline) > Date(now())";
		System.out.println("searchRecruit : " + cri.getSearchName());
		System.out.println("searchRecruitVal : " + cri.getSearchValue());
		
		try {
			if(cri.getSearchName() == null
					||
				cri.getSearchName().trim().equals("")
					||
				cri.getSearchName().trim().equals("null")) {
				System.out.println("검색 내용이 없음");
				pstmt = conn.prepareStatement(sql);
			}else {
				if(cri.getSearchName().equals("writer")) {
					sql += " AND cbr_writer LIKE concat('%',?,'%')";
				}else if(cri.getSearchName().equals("title")) {
					sql += " AND cbr_title LIKE concat('%',?,'%')";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cri.getSearchValue());
			}
			
			System.out.println("검색 sql : " + sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return listCount;
	}
	
	// 조건 검색 
	@Override
	public ArrayList<RecruitVO> getSearchList(PageMaker pageMaker) {
		System.out.println("dao 조건에 맞는 게시글 검색 요청");
		
		ArrayList<RecruitVO> list = new ArrayList<>();
		
		System.out.println("검색 pageStart : "+ pageMaker.getCri().getPageStart());
		System.out.println("검색 perPageNum : " + pageMaker.getCri().getPerPageNum());
		
		SearchCriteria scri = null;
		if(pageMaker.getCri() instanceof SearchCriteria) {
			scri = (SearchCriteria)pageMaker.getCri();
		}
		
		System.out.println(scri);
		
		boolean isSearch = true;
		
		String sql = "SELECT * FROM codingmon_board_recruit WHERE Date(cbr_deadline) > Date(now())";
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		try {
			if(scri.getSearchName() == null
					||
				scri.getSearchName().trim().equals("")
					||
				scri.getSearchName().trim().equals("null")) {
				System.out.println("검색 내용이 없습니다.");
				isSearch = false;
			}else {
				if(scri.getSearchName().equals("writer")) {
					sql += " AND cbr_writer LIKE CONCAT('%', ?, '%')";
				}else if(scri.getSearchName().equals("title")) {
					sql += " AND cbr_title LIKE CONCAT('%', ?, '%')";
				}
			}
			sql += " ORDER BY cbr_num DESC LIMIT ?, ?";
			System.out.println("searchList sql : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			
			if(isSearch) {
				pstmt.setString(1, scri.getSearchValue());
				pstmt.setInt(2, scri.getPageStart());
				pstmt.setInt(3, scri.getPerPageNum());
			}else {
				pstmt.setInt(1, scri.getPageStart());
				pstmt.setInt(2, scri.getPerPageNum());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitVO vo = new RecruitVO();
				vo.setCbr_num(rs.getInt("cbr_num"));
				vo.setCbr_title(rs.getString("cbr_title"));
				vo.setCbr_writer(rs.getString("cbr_writer"));
				vo.setCbr_writer_num(rs.getInt("cbr_writer_num"));
				vo.setCbr_pay(rs.getDouble("cbr_pay"));
				vo.setCbr_content(rs.getString("cbr_content"));
				vo.setCbr_regdate(rs.getDate("cbr_regdate"));
				vo.setCbr_date_from(rs.getDate("cbr_date_from"));
				vo.setCbr_date_to(rs.getDate("cbr_date_to"));
				vo.setCbr_deadline(rs.getDate("cbr_deadline"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// recruit 게시글 작성
	@Override
	public boolean cbrWrite(RecruitVO cbr) {
		System.out.println("dao cbrWrite 요청받음");
		
		boolean isSuccess = false;
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_board_recruit "
					+ " VALUES("
					+ "null, ?, ?, ?, ?, now(), ?, ?, ?, ?)";
		
		// Date to Timestamp 형변환
		Date dateF = cbr.getCbr_date_from();
		Date dateT = cbr.getCbr_date_to();
		Date dateD = cbr.getCbr_deadline();
		
		Timestamp tsF = new Timestamp(dateF.getTime());
		Timestamp tsT = new Timestamp(dateT.getTime());
		Timestamp tsD = new Timestamp(dateD.getTime());
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cbr.getCbr_title());
			pstmt.setString(2, cbr.getCbr_writer());
			pstmt.setInt(3, cbr.getCbr_writer_num());
			pstmt.setString(4, cbr.getCbr_content());
			pstmt.setDouble(5, cbr.getCbr_pay());
			pstmt.setTimestamp(6, tsF);
			pstmt.setTimestamp(7, tsT);
			pstmt.setTimestamp(8, tsD);
			
			if(pstmt.executeUpdate() > 0) {
				isSuccess = true;
				System.out.println("dao cbrWrite 완료");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
			//TestDBUtil.close(pstmt, conn);
		}
		return isSuccess;
	}
	
	// 직업정보 추가
	@Override
	public void insertJob(RecruitJob rj) {
		System.out.println("dao 직업 정보 추가");
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection(); 
		
		String sql = "INSERT INTO codingmon_recruit_job VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rj.getCrj_code());
			pstmt.setInt(2, rj.getCrj_board_num());
			if(pstmt.executeUpdate() > 0) System.out.println("dao 직업 정보 저장 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//TestDBUtil.close(pstmt,conn);
			DBCPUtil.close(pstmt, conn);
		}
	}

	// 지역 정보 추가
	@Override
	public void insertRegion(RecruitRegion rr) {
		System.out.println("dao 지역 정보 추가");
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_recruit_region VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rr.getCrr_code());
			pstmt.setInt(2, rr.getCrr_board_num());
			if(pstmt.executeUpdate() > 0) System.out.println("dao 지역 정보 저장 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//TestDBUtil.close(pstmt,conn);
			DBCPUtil.close(pstmt, conn);
		}
	}

	// 언어 정보 추가
	@Override
	public void insertSubject(RecruitSubject rs) {
		System.out.println("dao 언어 정보 추가");
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_recruit_subject VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rs.getCrs_code());
			pstmt.setInt(2, rs.getCrs_board_num());
			if(pstmt.executeUpdate() > 0) System.out.println("dao 언어 정보 저장 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//TestDBUtil.close(pstmt,conn);
			DBCPUtil.close(pstmt, conn);
		}
	}

	// 임금 정보 추가
	@Override
	public void insertPayment(RecruitPayment rp) {
		System.out.println("dao 임금 정보 추가");
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_recruit_payment VALUES(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rp.getCrp_code());
			pstmt.setInt(2, rp.getCrp_board_num());
			if(pstmt.executeUpdate() > 0) System.out.println("dao 임금 정보 저장 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//TestDBUtil.close(pstmt,conn);
			DBCPUtil.close(pstmt, conn);
		}		
	}

	// recruit 게시글 수정
	@Override
	public boolean cbrUpdate(RecruitVO cbr) {
		System.out.println("dao cbrUpdate 요청받음");
		
		boolean isUpdated = false;
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_board_recruit SET "
					+ " cbr_title = ?, "
					+ " cbr_content = ?,"
					+ " cbr_pay = ?, "
					+ " cbr_date_from = ?, "
					+ " cbr_date_to = ?, "
					+ " cbr_deadline = ? "
					+ " WHERE cbr_num = ? AND ("
					+ " cbr_writer_num = ?)";
		// cbr_writer_num = 1 OR 관리자 계정 추가 후 삽입
		
		// Date to Timestamp 형변환
		Date dateF = cbr.getCbr_date_from();
		Date dateT = cbr.getCbr_date_to();
		Date dateD = cbr.getCbr_deadline();
		
		Timestamp tsF = new Timestamp(dateF.getTime());
		Timestamp tsT = new Timestamp(dateT.getTime());
		Timestamp tsD = new Timestamp(dateD.getTime());		
		
		System.out.println("cbr_num : "+cbr.getCbr_num());
		System.out.println("cbr_writer_num : "+cbr.getCbr_writer_num());
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cbr.getCbr_title());
			pstmt.setString(2, cbr.getCbr_content());
			pstmt.setDouble(3, cbr.getCbr_pay());
			pstmt.setTimestamp(4, tsF);
			pstmt.setTimestamp(5, tsT);
			pstmt.setTimestamp(6, tsD);
			pstmt.setInt(7, cbr.getCbr_num());
			pstmt.setInt(8, cbr.getCbr_writer_num());
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("dao cbrUpdate true");
				isUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
		return isUpdated;
	}

	// recruit 게시글 삭제
	@Override
	public boolean cbrDelete(int cbr_num, int cbr_writer_num) {
		System.out.println("dao cbrDelete 요청 받음");
		
		boolean isDeleted = false;
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();	
		String sql = "DELETE FROM codingmon_board_recruit "
					+ " WHERE cbr_num = ? "
					+ " AND cbr_writer_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cbr_num);
			pstmt.setInt(2, cbr_writer_num);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("dao cbrDelete true");
				isDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
		return isDeleted;
	}
	
	
	/**
	 * 20191219 - 부가정보 추가
	 */
	
	// 게시글의 직업 번호 목록 검색
	@Override
	public ArrayList<RecruitJob> getJobList(int num) {
		System.out.println("dao getJobList 요청");
		
		ArrayList<RecruitJob> list = new ArrayList<>();
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_recruit_job WHERE crj_board_num = ? ORDER BY crj_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitJob rj = new RecruitJob();
				rj.setCrj_code(rs.getInt("crj_code"));
				rj.setCrj_board_num(rs.getInt("crj_board_num"));
				list.add(rj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public HashMap<Integer, String> getJobs(ArrayList<RecruitJob> list) {
		System.out.println("dao getJobs 요청");
		System.out.println("인자 : "+list);
		HashMap<Integer, String> map = new HashMap<>();
		
		// 서버용 연결 객체
		conn = DBCPUtil.getConnection();
		// 테스트용 연결 객체
		//conn = TestDBUtil.getConnection();
		
		try {
			for(int i=0; i<list.size(); i++) {
				String sql = "SELECT * FROM codingmon_job WHERE cj_code = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCrj_code());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					map.put(rs.getInt("cj_code"), rs.getString("cj_name"));
					System.out.println("중간 과정 : "+map);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		
		return map;
	}

	// 게시글의 지역 번호 목록 검색
	@Override
	public ArrayList<RecruitRegion> getRegionList(int num) {
		System.out.println("dao getRegionList 요청");
		
		ArrayList<RecruitRegion> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_recruit_region WHERE crr_board_num = ? ORDER BY crr_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitRegion rr = new RecruitRegion();
				rr.setCrr_code(rs.getInt("crr_code"));
				rr.setCrr_board_num(rs.getInt("crr_board_num"));
				list.add(rr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public HashMap<Integer, String> getRegions(ArrayList<RecruitRegion> list) {
		System.out.println("dao getRegion 요청");
		
		HashMap<Integer, String> map = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		for(int i=0; i<list.size(); i++) {
			String sql = "SELECT * FROM codingmon_region WHERE cr_code = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCrr_code());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					map.put(rs.getInt("cr_code"), rs.getString("cr_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBCPUtil.close(rs, pstmt, conn);
			}
		}
		return map;
	}

	// 게시글의 언어 번호 목록 검색
	@Override
	public ArrayList<RecruitSubject> getSubjectList(int num) {
		System.out.println("dao getSubjectList 요청");
		
		ArrayList<RecruitSubject> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_recruit_subject WHERE crs_board_num = ? ORDER BY crs_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitSubject rsub = new RecruitSubject();
				rsub.setCrs_code(rs.getInt("crs_code"));
				rsub.setCrs_board_num(rs.getInt("crs_board_num"));
				list.add(rsub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public HashMap<Integer, String> getSubjects(ArrayList<RecruitSubject> list) {
		System.out.println("dao getSubject 요청");
		
		HashMap<Integer, String> map = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		try {
			for(int i=0; i<list.size(); i++) {
				String sql = "SELECT * FROM codingmon_subject WHERE cs_code = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCrs_code());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					map.put(rs.getInt("cs_code"), rs.getString("cs_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 게시글의 임금 번호 목록 검색
	@Override
	public ArrayList<RecruitPayment> getPaymentList(int num) {
		System.out.println("dao getPaymentList 요청");
		
		ArrayList<RecruitPayment> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_recruit_payment WHERE crp_board_num = ? ORDER BY crp_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitPayment rp = new RecruitPayment();
				rp.setCrp_code(rs.getInt("crp_code"));
				rp.setCrp_board_num(rs.getInt("crp_board_num"));
				list.add(rp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public HashMap<Integer, String> getPayments(ArrayList<RecruitPayment> list) {
		System.out.println("dao getPayment 요청");
		System.out.println("인자 : "+list);
		HashMap<Integer, String> map = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		
		for(int i=0; i<list.size(); i++) {
			String sql = "SELECT * FROM codingmon_payment_method WHERE cpm_code = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCrp_code());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					map.put(rs.getInt("cpm_code"), rs.getString("cpm_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBCPUtil.close(rs, pstmt, conn);
			}
		}
		return map;
	}

	// 전체 직업 목록
	@Override
	public ArrayList<codingmon_jobVO> getEntireJobs() {
		System.out.println("dao 전체 직업목록 요청");
		
		ArrayList<codingmon_jobVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_job ORDER BY cj_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				codingmon_jobVO job = new codingmon_jobVO();
				job.setCj_code(rs.getInt("cj_code"));
				job.setCj_name(rs.getString("cj_name"));
				list.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// 전체 지역 목록
	@Override
	public ArrayList<codingmon_regionVO> getEntireRegions() {
		System.out.println("dao 전체 지역 목록 요청");
		
		ArrayList<codingmon_regionVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_region ORDER BY cr_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				codingmon_regionVO reg = new codingmon_regionVO();
				reg.setCr_code(rs.getInt("cr_code"));
				reg.setCr_name(rs.getString("cr_name"));
				list.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// 전체 언어 목록 
	@Override
	public ArrayList<codingmon_subjectVO> getEntireSubjects() {
		System.out.println("dao 전체 언어 목록 요청");
		
		ArrayList<codingmon_subjectVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_subject ORDER BY cs_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				codingmon_subjectVO sub = new codingmon_subjectVO();
				sub.setCs_code(rs.getInt("cs_code"));
				sub.setCs_name(rs.getString("cs_name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// 전체 임금정보 목록
	@Override
	public ArrayList<codingmon_paymentVO> getEntirePayments() {
		System.out.println("dao 전체 임금 정보 요청");
		
		ArrayList<codingmon_paymentVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_payment_method ORDER BY cpm_code";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				codingmon_paymentVO pay = new codingmon_paymentVO();
				pay.setCpm_code(rs.getInt("cpm_code"));
				pay.setCpm_name(rs.getString("cpm_name"));
				list.add(pay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// 부가 정보 지우기 
	@Override
	public void deleteJobs(int num) {
		System.out.println("dao 직업정보 지우기 요청");
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_recruit_job WHERE crj_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			if(result > 0) System.out.println(num+"번의 직업 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteRegions(int num) {
		System.out.println("dao 지역 정보 지우기 요청");
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_recruit_region WHERE crr_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0) System.out.println(num+"번의 지역 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteSubjects(int num) {
		System.out.println("dao 언어 정보 지우기 요청");
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_recruit_subject WHERE crs_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0) System.out.println(num+"번의 언어 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deletePayment(int num) {
		System.out.println("dao 임금 정보 지우기 요청");
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_recruit_payment WHERE crp_board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0) System.out.println(num+"번의 임금 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt, conn);
		}
	}
}
