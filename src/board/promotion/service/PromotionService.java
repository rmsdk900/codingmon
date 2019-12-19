package board.promotion.service;

import javax.servlet.http.HttpServletRequest;

public interface PromotionService {
	
	// 검색 리스트 출력
	void search(HttpServletRequest request);
	// 상세 정보 출력
	void getDetail(HttpServletRequest request);

}
