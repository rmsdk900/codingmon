package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import member.vo.MemberInfo;
import member.vo.MemberVO;
import member.vo.job.JobVO;
import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.region.RegionVO;
import member.vo.subject.MemberSubject;
import member.vo.subject.SubjectVO;
import util.Criteria;
import util.DBCPUtil;
import util.LaptopDBUtil;
import util.TestDBUtil;

public class MemberDAOImpl implements MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public boolean signUpMember(MemberVO vo) {
		System.out.println("DB 회원가입 시작");
		boolean isSuccess = false;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member WHERE cm_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCm_email());
			rs = pstmt.executeQuery();
			// 중복 아이디 없을 경우
			if(!rs.next()) {
				sql = "INSERT INTO codingmon_member VALUES(null,?,?,?,?,?,now(),'Y',?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getCm_email());
				pstmt.setString(2, vo.getCm_pw());
				pstmt.setString(3, vo.getCm_name());
				pstmt.setString(4, vo.getCm_phone());
				pstmt.setString(5, vo.getCm_addr());
				pstmt.setInt(6, vo.getCm_salt());
				if(pstmt.executeUpdate() > 0)isSuccess=true;
			}
		} catch (SQLException e) {
			System.out.println("DB 회원가입 실패");
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs,pstmt, conn);
			DBCPUtil.close(rs,pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return isSuccess;
	}
	
	@Override
	public boolean insertInfo(MemberInfo info) {
		boolean isSuccess = false;
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_member_info VALUES(?,?,?,?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info.getCmi_owner_num());
			pstmt.setString(2, info.getCmi_intro());
			pstmt.setString(3, info.getCmi_private());
			pstmt.setString(4, info.getCmi_gender());
			pstmt.setInt(5, info.getCmi_age());
			pstmt.setString(6, info.getCmi_career());
			if(pstmt.executeUpdate() > 0)isSuccess=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public void insertJob(MemberJob mj) {
		System.out.println("DB 직업 정보 추가");
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_info_job VALUES(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mj.getCij_code());
			pstmt.setInt(2, mj.getCij_owner_num());
			if(pstmt.executeUpdate() > 0)System.out.println("직업 정보 저장 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
//			DBCPUtil.close(pstmt, conn);
			LaptopDBUtil.close(pstmt,conn);
		}
		
	}

	@Override
	public void insertRegion(MemberRegion mr) {
		System.out.println("DB 지역 정보 추가");
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_member_region VALUES(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mr.getCmr_code());
			pstmt.setInt(2, mr.getCmr_owner_num());
			if(pstmt.executeUpdate() > 0)System.out.println("지역 정보 저장 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		
	}

	@Override
	public void insertSubject(MemberSubject ms) {
		System.out.println("DB 언어 정보 추가");
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "INSERT INTO codingmon_member_subject VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ms.getCms_code());
			pstmt.setInt(2, ms.getCms_owner_num());
			pstmt.setInt(3, ms.getCms_category());
			if(pstmt.executeUpdate() > 0)System.out.println("언어 정보 저장 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
	}

	@Override
	public MemberVO loginMember(String email, String pass) {
		System.out.println("DB 로그인 허가");
		MemberVO member = null;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member WHERE cm_email=? AND cm_pw=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO(
						rs.getInt("cm_num"), 
						rs.getString("cm_email"), 
						rs.getString("cm_pw"), 
						rs.getString("cm_name"), 
						rs.getString("cm_phone"), 
						rs.getString("cm_addr"), 
						rs.getTimestamp("cm_regdate"), 
						rs.getString("cm_join"), 
						rs.getInt("cm_salt")
				);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return member;
	}
	
	
	@Override
	public int getSalt(String email) {
		System.out.println("DB 소금 가져오기");
		int salt = 0;
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT cm_salt FROM codingmon_member WHERE cm_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next())salt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		
		return salt;
	}

	@Override
	public MemberVO getMemberByEmail(String email) {
		System.out.println("DB email로 member 가져오기");
		MemberVO member = null;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member WHERE cm_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO(
						rs.getInt("cm_num"), 
						rs.getString("cm_email"), 
						rs.getString("cm_pw"), 
						rs.getString("cm_name"), 
						rs.getString("cm_phone"), 
						rs.getString("cm_addr"), 
						rs.getTimestamp("cm_regdate"), 
						rs.getString("cm_join"), 
						rs.getInt("cm_salt")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return member;
	}

	
	
	@Override
	public MemberInfo getInfoByNum(int num) {
		MemberInfo info = null;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member_info WHERE cmi_owner_num="+num;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				info = new MemberInfo(
						rs.getInt("cmi_owner_num"), 
						rs.getString("cmi_intro"), 
						rs.getString("cmi_private"), 
						rs.getString("cmi_gender"), 
						rs.getInt("cmi_age"), 
						rs.getString("cmi_career"), 
						rs.getInt("cmi_achieve")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return info;
	}

	@Override
	public boolean updateMember(MemberVO vo) {
		boolean isSuccess = false;
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_member SET cm_pw=?, cm_phone=?, cm_addr=? WHERE cm_num=? AND cm_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCm_pw());
			pstmt.setString(2, vo.getCm_phone());
			pstmt.setString(3, vo.getCm_addr());
			pstmt.setInt(4, vo.getCm_num());
			pstmt.setString(5, vo.getCm_email());
			
			if(pstmt.executeUpdate() > 0)isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public boolean updateInfo(MemberInfo info) {
		boolean isSuccess = false;
				
		//서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
		//conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_member_info SET cmi_intro=?, cmi_private=?, cmi_gender=?, cmi_age=?, cmi_career=? WHERE cmi_owner_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getCmi_intro());
			pstmt.setString(2, info.getCmi_private());
			pstmt.setString(3, info.getCmi_gender());
			pstmt.setInt(4, info.getCmi_age());
			pstmt.setString(5, info.getCmi_career());
			pstmt.setInt(6, info.getCmi_owner_num());
			
			if(pstmt.executeUpdate() > 0)isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		
		return isSuccess;
	}

	@Override
	public void addPassCode(String email, String code) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_passcode WHERE cpc_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				sql="UPDATE codingmon_passcode SET cpc_code=? WHERE cpc_email=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				pstmt.setString(2, email);
			}else {
				sql = "INSERT INTO codingmon_passcode VALUES(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, code);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		
	}

	@Override
	public boolean checkPassCode(String email, String code) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_passcode WHERE cpc_email=? AND cpc_code=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, code);
			
			rs = pstmt.executeQuery();
			if(rs.next())return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return false;
	}

	@Override
	public boolean changePass(String email, String pass) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_member SET cm_pw=? WHERE cm_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, email);
			if(pstmt.executeUpdate() > 0) {
				sql = "DELETE FROM codingmon_passcode WHERE cpc_email=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				if(pstmt.executeUpdate() > 0) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		
		return false;
	}

	@Override
	public boolean deleteMember(int num, String pw) {
		boolean isDelete = false;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_member SET cm_join=? WHERE cm_num=? AND cm_pw=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "N");
			pstmt.setInt(2, num);
			pstmt.setString(3, pw);
			if(pstmt.executeUpdate() > 0)isDelete = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		
		return isDelete;
	}

	@Override
	public boolean deleteInfo(int num) {
		boolean isDelete = false;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "UPDATE codingmon_member_info SET cmi_private=? WHERE cmi_owner_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Y");
			pstmt.setInt(2, num);
			
			if(pstmt.executeUpdate() > 0)isDelete = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		
		return isDelete;
	}
	
	

	@Override
	public void deleteJobs(int num) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_info_job WHERE cij_owner_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0)System.out.println(num+"번의 모든 직업 정보 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		
	}

	@Override
	public void deleteRegions(int num) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_member_region WHERE cmr_owner_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0)System.out.println(num+"번의 모든 지역 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
	}

	@Override
	public void deleteSubjects(int num) {
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "DELETE FROM codingmon_member_subject WHERE cms_owner_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			if(pstmt.executeUpdate() > 0)System.out.println(num+"번의 모든 언어 정보 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
	}

	@Override
	public int getMemberTotal() {
		int totalCount = 0;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT count(*) FROM codingmon_member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) totalCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs,pstmt, conn);
			DBCPUtil.close(rs,pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return totalCount;
	}

	@Override
	public int getInfoTotal() {
		int totalCount = 0;
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT count(*) FROM codingmon_member_info";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) totalCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(pstmt, conn);
			DBCPUtil.close(pstmt, conn);
//			LaptopDBUtil.close(pstmt,conn);
		}
		return totalCount;
	}

	@Override
	public ArrayList<MemberVO> getMemberList(Criteria cri) {
		ArrayList<MemberVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member ORDER BY cm_num DESC limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCm_num(rs.getInt("cm_num"));
				vo.setCm_email(rs.getString("cm_email"));
				vo.setCm_pw(rs.getString("cm_pw"));
				vo.setCm_name(rs.getString("cm_name"));
				vo.setCm_phone(rs.getString("cm_phone"));
				vo.setCm_addr(rs.getString("cm_addr"));
				vo.setCm_regdate(rs.getTimestamp("cm_regdate"));
				vo.setCm_join(rs.getString("cm_join"));
				vo.setCm_salt(rs.getInt("cm_salt"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs,pstmt, conn);
			DBCPUtil.close(rs,pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}

	@Override
	public ArrayList<MemberInfo> getInfoList(Criteria cri) {
		ArrayList<MemberInfo> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member_info ORDER BY cmi_owner_num DESC limit ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberInfo info = new MemberInfo();
				info.setCmi_owner_num(rs.getInt("cmi_owner_num"));
				info.setCmi_intro(rs.getString("cmi_intro"));
				info.setCmi_private(rs.getString("cmi_private"));
				info.setCmi_gender(rs.getString("cmi_gender"));
				info.setCmi_age(rs.getInt("cmi_age"));
				info.setCmi_career(rs.getString("cmi_career"));
				info.setCmi_achieve(rs.getInt("cmi_achieve"));
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs,pstmt, conn);
			DBCPUtil.close(rs,pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	public ArrayList<MemberJob> getJobList(int num) {
		ArrayList<MemberJob> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_info_job WHERE cij_owner_num=? ORDER BY cij_code ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberJob mj = new MemberJob();
				mj.setCij_code(rs.getInt("cij_code"));
				mj.setCij_owner_num(rs.getInt("cij_owner_num"));
				list.add(mj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}
	

	@Override
	public HashMap<Integer, String> getJobs(ArrayList<MemberJob> list) {
		HashMap<Integer, String> li = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		try {
			for(int i=0;i<list.size();i++) {
				String sql = "SELECT * FROM codingmon_job WHERE cj_code=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCij_code());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					li.put(rs.getInt("cj_code"), rs.getString("cj_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return li;
	}

	@Override
	public ArrayList<MemberRegion> getRegionList(int num) {
		ArrayList<MemberRegion> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member_region WHERE cmr_owner_num=? ORDER BY cmr_code ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberRegion mr = new MemberRegion();
				mr.setCmr_code(rs.getInt("cmr_code"));
				mr.setCmr_owner_num(rs.getInt("cmr_owner_num"));
				list.add(mr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	
	@Override
	public HashMap<Integer, String> getRegions(ArrayList<MemberRegion> list) {
		HashMap<Integer, String> li = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		try {
			for(int i=0;i<list.size();i++) {
				String sql = "SELECT * FROM codingmon_region WHERE cr_code=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCmr_code());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					li.put(rs.getInt("cr_code"), rs.getString("cr_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return li;
	}

	@Override
	public ArrayList<MemberSubject> getSubjectList(int num) {
		ArrayList<MemberSubject> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_member_subject WHERE cms_owner_num=? ORDER BY cms_category ASC, cms_code DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberSubject ms = new MemberSubject();
				ms.setCms_code(rs.getInt("cms_code"));
				ms.setCms_owner_num(rs.getInt("cms_owner_num"));
				ms.setCms_category(rs.getInt("cms_category"));
				list.add(ms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	
	@Override
	public HashMap<String, String> getSubjects(ArrayList<MemberSubject> list) {
		HashMap<String, String> map = new HashMap<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		// 테스트용 연결
//		conn = TestDBUtil.getConnection();
		// 노트북용 연결
//		conn = LaptopDBUtil.getConnection();
		// 주 언어 카운트
		int mainCount = 1;
		// 공부용 언어 카운트
		int learningCount = 1;
		
		try {
			for(int i=0;i<list.size();i++) {
				String sql = "SELECT cs_name FROM codingmon_subject WHERE cs_code=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getCms_code());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					// 직업 카테고리에 따라 나누기 0 : 주 언어 / 1 : 공부용 언어
					if(list.get(i).getCms_category()==0) {
						map.put("주 언어"+mainCount, rs.getString(1));
						mainCount++;
					}else if(list.get(i).getCms_category()==1) {
						map.put("공부용 언어"+learningCount, rs.getString(1));
						learningCount++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			TestDBUtil.close(rs, pstmt, conn);
			DBCPUtil.close(rs, pstmt, conn);
//			LaptopDBUtil.close(rs,pstmt,conn);
		}
		return map;
	}

	@Override
	public ArrayList<JobVO> getEntireJobs() {
		ArrayList<JobVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_job ORDER BY cj_code ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JobVO job = new JobVO();
				job.setCj_code(rs.getInt("cj_code"));
				job.setCj_name(rs.getString("cj_name"));
				list.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}

	@Override
	public ArrayList<RegionVO> getEntireRegions() {
		ArrayList<RegionVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_region ORDER BY cr_code ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RegionVO reg = new RegionVO();
				reg.setCr_code(rs.getInt("cr_code"));
				reg.setCr_name(rs.getString("cr_name"));
				list.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}

	@Override
	public ArrayList<SubjectVO> getEntireSubjects() {
		ArrayList<SubjectVO> list = new ArrayList<>();
		
		// 서버로 연결
		conn = DBCPUtil.getConnection();
		
		String sql = "SELECT * FROM codingmon_subject ORDER BY cs_code ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SubjectVO sub = new SubjectVO();
				sub.setCs_code(rs.getInt("cs_code"));
				sub.setCs_name(rs.getString("cs_name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		
		return list;
	}
	
	
	
}
