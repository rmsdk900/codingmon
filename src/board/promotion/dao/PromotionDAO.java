package board.promotion.dao;

import java.util.ArrayList;

import board.promotion.vo.ResumeVO;
import util.SearchCriteria;

public interface PromotionDAO {
	// 검색 결과 총 갯수 구하기 - Deprecated
	int getSearchTotal(SearchCriteria cri);
	// 검색 결과로 불러오자!!
	ArrayList<ResumeVO> getSearchResume(SearchCriteria cri);

}
