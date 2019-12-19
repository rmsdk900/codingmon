package board.promotion.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBCPUtil;
import util.SearchCriteria;

public class PromotionDAOImpl implements PromotionDAO{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public int getSearchTotal(SearchCriteria cri) {
		int totalCount = 0;
		
		conn = DBCPUtil.getConnection();
		
		System.out.println(cri.getSearchName());
		System.out.println(cri.getSearchGender());
		System.out.println(cri.getSearchAge());
		System.out.println(cri.getSearchRegion());
		System.out.println(cri.getSearchJob());
		System.out.println(cri.getSearchWorkLang());
		System.out.println(cri.getSearchLearnLang());
		
		ArrayList<Integer> ages = cri.getSearchAge();
		ArrayList<Integer> regions = cri.getSearchRegion();
		ArrayList<Integer> jobs = cri.getSearchJob();
		ArrayList<Integer> work = cri.getSearchWorkLang();
		ArrayList<Integer> learn = cri.getSearchLearnLang();
		
		
		String sql = "SELECT count(*) FROM codingmon_member WHERE cm_join='Y'";
		
		if(cri.getSearchName() == null 
				|| cri.getSearchName().trim().equals("") 
				|| cri.getSearchName().trim().equals("null")) {
			System.out.println("회원명 검색 안함.");
		}else {
			System.out.println("회원명 검색 조건 추가");
			sql += " AND cm_name LIKE CONCAT('%',?,'%')";
		}
		
		if(cri.getSearchGender() == null 
				|| cri.getSearchGender().trim().equals("") 
				|| cri.getSearchGender().trim().equals("null")) {
			System.out.println("성별 조건 없음.");
		}else {
			System.out.println("성별 조건 추가");
			sql += " AND cm_num=ANY(SELECT cmi_owner_num FROM codingmon_member_info WHERE cmi_gender=?)";
		}
		
		if(ages == null || ages.size() == 0) {
			System.out.println("나잇대 조건 없음.");
		}else {
			System.out.println("나이대 조건 추가");
			for(int i=0;i<ages.size();i++) {
				int num = ages.get(i);
				switch (num) {
				case 10:
					
					break;

				default:
					break;
				}
				
			}
			sql += "SELECT cmi_owner_num FROM codingmon_member_info WHERE ";
		}
		
		
		
		return totalCount;
	}
	
}
