package board.promotion.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import board.promotion.dao.PromotionDAO;
import board.promotion.vo.ResumeVO;
import util.SearchCriteria;
import util.TestDBUtil;

class PromotionDAOTest {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Test
	void test() {
//		PromotionDAO dao = new PromotionDAO() {
//			@Override
//			public int getSearchTotal(SearchCriteria cri) {
//				//conn = DBCPUtil.getConnection();
//				conn = TestDBUtil.getConnection();
//				
//				System.out.println(cri.getSearchGender());
//				System.out.println(cri.getSearchAge());
//				System.out.println(cri.getSearchRegion());
//				System.out.println(cri.getSearchJob());
//				System.out.println(cri.getSearchWorkLang());
//				System.out.println(cri.getSearchLearnLang());
//				
////				String name = cri.getSearchName();
//				String gender = cri.getSearchGender();
//				
//				ArrayList<Integer> ages = cri.getSearchAge();
//				ArrayList<Integer> regions = cri.getSearchRegion();
//				ArrayList<Integer> jobs = cri.getSearchJob();
//				ArrayList<Integer> work = cri.getSearchWorkLang();
//				ArrayList<Integer> learn = cri.getSearchLearnLang();
//				
//				ArrayList<Integer> cm_num_count = new ArrayList<>();
//				
//				
//				if(jobs != null) {
//					System.out.println("직업 검색 안함.");
//					//SELECT cij_owner_num FROM codingmon_info_job WHERE cij_code = 105 OR cij_code = 3;
//					String sqls = "SELECT cij_owner_num FROM codingmon_info_job WHERE ";
//					for(int j=0; j<jobs.size(); j++) {
//						if(j+1 == jobs.size() && jobs.get(j) != 0) {
//							sqls+=" OR";
//						}
//						sqls+=" cij_code = "+jobs.get(j);
//					}
//					System.out.println(sqls);
//					try {
//						pstmt = conn.prepareStatement(sqls);
//						rs = pstmt.executeQuery();
//						while(rs.next()){
//							int cm_num = rs.getInt(1);
//							if(!cm_num_count.contains(cm_num)) {
//								cm_num_count.add(cm_num);
//							}
//						}
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}finally {
//						TestDBUtil.close(rs,pstmt,conn);
//					}
//				}
//				return cm_num_count.size();
//			}
//
//			@Override
//			public ArrayList<ResumeVO> getSearchResume(SearchCriteria cri) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//		
//		SearchCriteria cri = new SearchCriteria();
//		ArrayList<Integer> searchJob = new ArrayList<>();
//		searchJob.add(105);
//		searchJob.add(3);
//		cri.setSearchJob(searchJob);
//		int size = dao.getSearchTotal(cri);
//		System.out.println(size);
		
		
	}

}
