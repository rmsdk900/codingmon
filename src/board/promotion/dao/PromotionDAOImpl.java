package board.promotion.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		
		
		try {
			if(gender==null && ages==null && regions==null && jobs==null && work==null && learn==null) {
				String sql = "SELECT * FROM codingmon_member INNER JOIN codingmon_member_info ON codingmon_member.cm_num=codingmon_member_info.cmi_owner_num ORDER BY codingmon_member.cm_num DESC LIMIT ?, ?";
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
				// 검색 처리 전 번호만 들고 오기.
				ArrayList <Integer> nums = null;
				// 비교할 리스트(항목별로 놔두자
				ArrayList <Integer> compareGenders = null;
				ArrayList <Integer> compareAges = null;
				ArrayList <Integer> compareRegions = null;
				ArrayList <Integer> compareJobs = null;
				ArrayList <Integer> compareWorks = null;
				ArrayList <Integer> compareLearns = null;
				 System.out.println("검색 전 : "+nums);
				if(work != null) {
					System.out.println("작업용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=0 AND cms_code IN( ";
					for(int i=0;i<work.size();i++) {
						if(i+1==work.size() && i != 0) {
							sql += ",";
						}
						sql +=work.get(i); 
					}
					sql+=")";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));
							System.out.println(rs.getInt(1));

						}
					}else {
						compareWorks = new ArrayList<>();
						while(rs.next()) {
							
							compareWorks.add(rs.getInt(1));
						}
					}
					
					System.out.println("처음 생성 : "+nums+"/ 비교 리스트 생성 ? "+compareWorks);
					if(nums!=null && compareWorks!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareWorks.contains(itr.next())) {
								itr.remove();
							}
							System.out.println("삭제 중 : "+nums);
						}
					}
					
					System.out.println(nums);
				}
				
				if(learn != null) {
					System.out.println("공부용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=1 AND cms_code IN( ";
					for(int i=0;i<learn.size();i++) {
						if(i+1==learn.size() && i != 0) {
							sql += ",";
						}
						sql +=learn.get(i); 
					}
					sql+=")";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareLearns = new ArrayList<>();
						while(rs.next()) {
							
							compareLearns.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareLearns!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareLearns.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					
					System.out.println(nums);
					
				}
				
				if (jobs !=null) {
					System.out.println("직업 조건 있음");
					//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
					// sql 만드는 것 함수화 하기
					String sql = makeSql(jobs, "cij_owner_num", "codingmon_info_job", "cij_code");
					
					System.out.println(sql);
					
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareJobs = new ArrayList<>();
						while(rs.next()) {
							
							compareJobs.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareJobs!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareJobs.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
					
				}
				
				if (regions != null) {
					System.out.println("활동 지역 조건 있음");
					String sql = makeSql(regions, "cmr_owner_num", "codingmon_member_region", "cmr_code");
					System.out.println(sql);
					
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareRegions = new ArrayList<>();
						while(rs.next()) {
							
							compareRegions.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareRegions!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareRegions.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
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
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));
							System.out.println(rs.getInt(1));
						}
					}else {
						compareAges = new ArrayList<>();
						while(rs.next()) {
							
							compareAges.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareAges!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareAges.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);

				}
				
				if (gender != null) {
					System.out.println("성별 검색 조건 있음");
					String sql = "SELECT cmi_owner_num FROM codingmon_member_info WHERE cmi_gender='"+gender+"'";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareGenders = new ArrayList<>();
						while(rs.next()) {
							
							compareGenders.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareGenders!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareGenders.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
				}
				System.out.println("최종 회원번호 리스트 : "+nums);
				// 번호 대로 일단 리스트 다 받자.
				for(int i=0; i<nums.size(); i++) {
					String sql = "SELECT * FROM codingmon_member INNER JOIN codingmon_member_info ON codingmon_member.cm_num=codingmon_member_info.cmi_owner_num WHERE codingmon_member.cm_num="+nums.get(i);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) {
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
				}
				
				for(ResumeVO resume : list) {
					ArrayList<MemberJob> jbs = new ArrayList<>();
					String sql = "SELECT * FROM codingmon_info_job WHERE cij_owner_num=?";
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
					String sql = "SELECT * FROM codingmon_member_region WHERE cmr_owner_num=?";
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
					String sql = "SELECT * FROM codingmon_member_subject WHERE cms_owner_num=?";
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
				
				 //페이지 메이커 적용
				int fromIndex=cri.getPageStart();
				int toIndex = cri.getPerPageNum()*cri.getPage();
				if(toIndex > list.size()) toIndex = list.size();
				ArrayList<ResumeVO> listSub = new ArrayList<>();
				for(int i=fromIndex;i<toIndex;i++) {
					listSub.add(list.get(i));
				}
				
				list = listSub;
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs, pstmt, conn);
		}

		return list;
	}
	
	
	
	@Override
	public int getSearchTotal(SearchCriteria cri) {
		int totalCount = 0;
		
		conn = TestDBUtil.getConnection();
		
		String gender = cri.getSearchGender();
		
		ArrayList<AgeVO> ages = cri.getSearchAge();
		ArrayList<Integer> regions = cri.getSearchRegion();
		ArrayList<Integer> jobs = cri.getSearchJob();
		ArrayList<Integer> work = cri.getSearchWorkLang();
		ArrayList<Integer> learn = cri.getSearchLearnLang();
		
		try {
			if(gender==null && ages==null && regions==null && jobs==null && work==null && learn==null) {
				String sql = "SELECT count(*) FROM codingmon_member INNER JOIN codingmon_member_info ON codingmon_member.cm_num=codingmon_member_info.cmi_owner_num ORDER BY codingmon_member.cm_num DESC";
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalCount = rs.getInt(1);
				}
			}else {
				// 검색 처리 전 번호만 들고 오기.
				ArrayList <Integer> nums = null;
				// 비교할 리스트(항목별로 놔두자
				ArrayList <Integer> compareGenders = null;
				ArrayList <Integer> compareAges = null;
				ArrayList <Integer> compareRegions = null;
				ArrayList <Integer> compareJobs = null;
				ArrayList <Integer> compareWorks = null;
				ArrayList <Integer> compareLearns = null;
				 System.out.println("검색 전 : "+nums);
				if(work != null) {
					System.out.println("작업용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=0 AND cms_code IN( ";
					for(int i=0;i<work.size();i++) {
						if(i+1==work.size() && i != 0) {
							sql += ",";
						}
						sql +=work.get(i); 
					}
					sql+=")";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));
							System.out.println(rs.getInt(1));

						}
					}else {
						compareWorks = new ArrayList<>();
						while(rs.next()) {
							
							compareWorks.add(rs.getInt(1));
						}
					}
					
					System.out.println("처음 생성 : "+nums+"/ 비교 리스트 생성 ? "+compareWorks);
					if(nums!=null && compareWorks!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareWorks.contains(itr.next())) {
								itr.remove();
							}
							System.out.println("삭제 중 : "+nums);
						}
					}
					
					System.out.println(nums);
				}
				
				if(learn != null) {
					System.out.println("공부용 언어 조건 있음");
					String sql = "SELECT DISTINCT cms_owner_num FROM codingmon_member_subject WHERE cms_category=1 AND cms_code IN( ";
					for(int i=0;i<learn.size();i++) {
						if(i+1==learn.size() && i != 0) {
							sql += ",";
						}
						sql +=learn.get(i); 
					}
					sql+=")";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareLearns = new ArrayList<>();
						while(rs.next()) {
							
							compareLearns.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareLearns!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareLearns.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					
					System.out.println(nums);
					
				}
				
				if (jobs !=null) {
					System.out.println("직업 조건 있음");
					//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
					// sql 만드는 것 함수화 하기
					String sql = makeSql(jobs, "cij_owner_num", "codingmon_info_job", "cij_code");
					
					System.out.println(sql);
					
					pstmt = conn.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareJobs = new ArrayList<>();
						while(rs.next()) {
							
							compareJobs.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareJobs!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareJobs.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
					
				}
				
				if (regions != null) {
					System.out.println("활동 지역 조건 있음");
					String sql = makeSql(regions, "cmr_owner_num", "codingmon_member_region", "cmr_code");
					System.out.println(sql);
					
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareRegions = new ArrayList<>();
						while(rs.next()) {
							
							compareRegions.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareRegions!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareRegions.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
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
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));
							System.out.println(rs.getInt(1));
						}
					}else {
						compareAges = new ArrayList<>();
						while(rs.next()) {
							
							compareAges.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareAges!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareAges.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);

				}
				
				if (gender != null) {
					System.out.println("성별 검색 조건 있음");
					String sql = "SELECT cmi_owner_num FROM codingmon_member_info WHERE cmi_gender='"+gender+"'";
					System.out.println(sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(nums==null) {
						nums=new ArrayList<>();
						while(rs.next()) {

							
							nums.add(rs.getInt(1));

						}
					}else {
						compareGenders = new ArrayList<>();
						while(rs.next()) {
							
							compareGenders.add(rs.getInt(1));
						}
					}
					if(nums!=null && compareGenders!=null) {
						Iterator<Integer> itr = nums.iterator();
						while(itr.hasNext()) {
							if(!compareGenders.contains(itr.next())) {
								itr.remove();
							}
						}
					}
					System.out.println(nums);
				}
				System.out.println("최종 회원번호 리스트 : "+nums);
				totalCount = nums.size();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs, pstmt, conn);
		}
		
		return totalCount;
	}



	String makeSql(ArrayList<Integer> list, String column, String table, String where) {
		String sql =null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DISTINCT "+column+" FROM "+table+" WHERE ");
		for(int i=0;i<list.size(); i++) {
			if(i+1==list.size() && i != 0) {
				sb.append(" OR");
			}
			sb.append(" "+where+" = "+list.get(i));
		}
		sql = sb.toString();
		return sql;
	}

	
	
}
