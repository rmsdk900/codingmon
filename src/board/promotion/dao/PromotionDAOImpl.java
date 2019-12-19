package board.promotion.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.promotion.vo.ResumeVO;
import member.vo.AgeVO;
import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.subject.MemberSubject;
import util.DBCPUtil;
import util.SearchCriteria;
import util.TestDBUtil;

public class PromotionDAOImpl implements PromotionDAO{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	
	@Override
	public ArrayList<ResumeVO> getSearchResume(SearchCriteria cri) {
		ArrayList<ResumeVO> list = new ArrayList<>();
		
		conn = TestDBUtil.getConnection();
		
		String gender = cri.getSearchGender();
		
		ArrayList<AgeVO> ages = cri.getSearchAge();
		ArrayList<Integer> regions = cri.getSearchRegion();
		ArrayList<Integer> jobs = cri.getSearchJob();
		ArrayList<Integer> work = cri.getSearchWorkLang();
		ArrayList<Integer> learn = cri.getSearchLearnLang();
		
		ArrayList <Integer> nums = new ArrayList<>();
		
		try {
			if(gender==null && ages==null && regions==null && jobs==null && work==null && learn==null) {
				String sql = "SELECT * FROM codingmon_member INNER JOIN codingmon_member_info ON codingmon_member.cm_num=codingmon_member_info.cmi_owner_num LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cri.getPageStart());
				pstmt.setInt(2, cri.getPerPageNum());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ResumeVO resume = new ResumeVO();
					resume.setCm_num(rs.getInt("cm_num"));
					resume.setCm_name(rs.getString("cm_name"));
					resume.setCm_phone(rs.getString("cm_phone"));
					resume.setCm_regdate(rs.getTimestamp("cm_regdate"));
					resume.setCm_join(rs.getString("cm_join"));
					
					resume.setCmi_private(rs.getString("cmi_private"));
					resume.setCmi_gender(rs.getString("cmi_gender"));
					resume.setCmi_age(rs.getInt("cmi_age"));
					resume.setCmi_title(rs.getString("cmi_title"));
					list.add(resume);
				}
				for(ResumeVO resume : list) {
					ArrayList<MemberJob> jbs = new ArrayList<>();
					sql = "SELECT * FROM codingmon_info_job WHERE cij_owner_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, resume.getCm_num());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						MemberJob mj = new MemberJob();
						mj.setCij_code(rs.getInt("cij_code"));
						mj.setCij_owner_num(rs.getInt("cij_owner_num"));
						jbs.add(mj);
					}
					resume.setCij(jbs);
				}
				for(ResumeVO resume : list) {
					ArrayList<MemberRegion> rgn = new ArrayList<>();
					sql = "SELECT * FROM codingmon_member_region WHERE cmr_owner_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, resume.getCm_num());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						MemberRegion mr = new MemberRegion();
						mr.setCmr_code(rs.getInt("cmr_code"));
						mr.setCmr_owner_num(rs.getInt("cmr_owner_num"));
						rgn.add(mr);
					}
					resume.setCmr(rgn);
				}
				for(ResumeVO resume : list) {
					ArrayList<MemberSubject> sbj = new ArrayList<>();
					sql = "SELECT * FROM codingmon_member_subject WHERE cms_owner_num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, resume.getCm_num());
					rs = pstmt.executeQuery();
					while(rs.next()) {
						MemberSubject ms = new MemberSubject();
						ms.setCms_category(rs.getInt("cms_category"));
						ms.setCms_code(rs.getInt("cms_code"));
						ms.setCms_owner_num(rs.getInt("cms_owner_num"));
						sbj.add(ms);
					}
					resume.setCms(sbj);
				}
				
			}else {
				if(work != null) {
					System.out.println("작업용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=0 AND cms_code IN( ";
					for(int i=0;i<work.size();i++) {
						if(i+1==work.size() && i != 0) {
							sql += ",";
						}
						sql +=work.get(i)+")"; 
					}
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}
					
				}
				
				if(learn != null) {
					System.out.println("공부용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=1 AND cms_code IN( ";
					for(int i=0;i<learn.size();i++) {
						if(i+1==learn.size() && i != 0) {
							sql += ",";
						}
						sql +=learn.get(i)+")"; 
					}
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}
					
				}
				
				if (jobs !=null) {
					System.out.println("직업 조건 있음");
					//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
					// sql 만드는 것 함수화 하기
					String sql = makeSql(jobs, "cij_owner_num", "codingmon_info_job", "cij_code");

					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}
					
				}
				
				if (regions != null) {
					System.out.println("활동 지역 조건 있음");
					String sql = makeSql(regions, "cmr_owner_num", "codingmon_member_region", "cms_code");
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}
				}
				
				if (ages != null) {
					System.out.println("나이대 검색 조건 있음");
					//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
					String sql = "SELECT cmi_owner_num FROM codingmon_member_info WHERE ";
					for	(int i=0;i<ages.size();i++) {
						if(i+1 == ages.size() && i != 0) {
							sql +=" OR ";
						}
						sql += " cmi_age BETWEEN "+ages.get(i).getStartAge()+" AND "+ages.get(i).getEndAge();
					}
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
					}

				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs, pstmt, conn);
		}

		return list;
	}
	
	String makeSql(ArrayList<Integer> list, String column, String table, String where) {
		String sql =null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT "+column+" FROM "+table+" WHERE ");
		for(int i=0;i<list.size(); i++) {
			if(i+1==list.size() && i != 0) {
				sb.append("OR");
			}
			sb.append(" "+where+" = "+list.get(i));
		}
		sql = sb.toString();
		return sql;
	}


//	@Override
//	public int getSearchTotal(SearchCriteria cri) {
//		int totalCount = 0;
//		
//		//conn = DBCPUtil.getConnection();
//		conn = TestDBUtil.getConnection();
//		
//		
//		System.out.println(cri.getSearchGender());
//		System.out.println(cri.getSearchAge());
//		System.out.println(cri.getSearchRegion());
//		System.out.println(cri.getSearchJob());
//		System.out.println(cri.getSearchWorkLang());
//		System.out.println(cri.getSearchLearnLang());
//		
//		
//		String gender = cri.getSearchGender();
//		
//		ArrayList<Integer> ages = cri.getSearchAge();
//		ArrayList<Integer> regions = cri.getSearchRegion();
//		ArrayList<Integer> jobs = cri.getSearchJob();
//		ArrayList<Integer> work = cri.getSearchWorkLang();
//		ArrayList<Integer> learn = cri.getSearchLearnLang();
//		
//		//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
//		
//		ArrayList<Integer> cm_num_count = new ArrayList<>();
//		// 
//		String sql = "SELECT count(*) FROM codingmon_member WHERE cm_join='Y'";
//		
//		
//		
//		if(gender == null) {
//			System.out.println("성별 검색 안함");
//		}
//		
//		if(ages == null) {
//			System.out.println("나이대 검색 안함.");
//		}
//		
//		if(regions == null) {
//			System.out.println("활동 지역 검색 안함.");
//		}
//		
//		if(jobs != null) {
//			System.out.println("직업 검색 안함.");
//			//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
//			String sqls = "SELECT cij_owner_num FROM codingmon_info_job WHERE ";
//			for(int j=0; j<jobs.size(); j++) {
//				if(j+1 == jobs.size() && j != 0) {
//					sqls+="OR";
//				}
//				sqls+=" cij_code = "+j;
//			}
//			
//			try {
//				pstmt = conn.prepareStatement(sqls);
//				rs = pstmt.executeQuery();
//				while(rs.next()){
//					int cm_num = rs.getInt(1);
//					if(!cm_num_count.contains(cm_num)) {
//						cm_num_count.add(cm_num);
//					}
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//		}
//		if(work == null) {
//			System.out.println("업무용 언어 검색 안함");
//		}
//		
//		if(learn == null) {
//			System.out.println("학습용 언어 검색 안함. ");
//		}
//		
////		if(cri.getSearchName() == null || cri.getSearchName().trim().equals("") ||
////				cri.getSearchName().trim().equals("null")) {
////			System.out.println("회원명 검색 안함."); }else { System.out.println("회원명 검색 조건 추가");
////			sql += " AND cm_name LIKE CONCAT('%',?,'%')"; }
////
////		if(cri.getSearchGender() == null || cri.getSearchGender().trim().equals("")
////				|| cri.getSearchGender().trim().equals("null")) {
////			System.out.println("성별 조건 없음."); }else { System.out.println("성별 조건 추가"); sql
////			+=
////			" AND cm_num=ANY(SELECT cmi_owner_num FROM codingmon_member_info WHERE cmi_gender=?)"
////			; }
////
////		if(ages == null || ages.size() == 0) { System.out.println("나잇대 조건 없음.");
////		}else { System.out.println("나이대 조건 추가"); for(int i=0;i<ages.size();i++) { int
////			num = ages.get(i); switch (num) { case 10:
////
////				break;
////
////			default: break; }
////
////		} sql += "SELECT cmi_owner_num FROM codingmon_member_info WHERE "; }
//		
////		// 모두 다 아닐 때 
////		if((name == null || name.trim().equals("")) 
////				&& gender == null 
////				&& ages == null 
////				&& regions == null 
////				&& jobs == null 
////				&& work == null
////				&& learn == null
////			) {
////			sql = "SELECT count(*) FROM codingmon_member WHERE cm_join='Y'";
////		}else {
////			if()
////		}
//		
//		return cm_num_count.size();
//	}
	
}
