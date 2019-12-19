package board.promotion.dao;

import util.SearchCriteria;

public interface PromotionDAO {
	// 검색 결과 총 갯수 구하기
	int getSearchTotal(SearchCriteria cri);

}
