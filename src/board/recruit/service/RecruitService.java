package board.recruit.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.recruit.vo.RecruitVO;

public interface RecruitService {
	
	// 페이징 처리된 게시물 목록 요청
	ArrayList<RecruitVO> getRecruitList(HttpServletRequest request);
	
	// 게시물 등록 요청
	boolean cbrWrite(HttpServletRequest request);
	
	// 상세보기 요청
	RecruitVO getCbrVO(HttpServletRequest request);
	
	// 게시글 수정 요청
	boolean cbrUpdate(HttpServletRequest request);
	
	// 게시글 삭제 요청
	void cbrDelete(HttpServletRequest request, HttpServletResponse response);
	
	// 최근 등록글 검색 요청
	RecruitVO getRecent(HttpServletRequest request);
	
	// 조건 검색 요청
	public void search(HttpServletRequest request);
	
	/**
	 * 20191219 부가 정보 추가
	 * @param request
	 * @param response
	 */
	// 추가 입력 사항
	void insertInfo(HttpServletRequest request, HttpServletResponse response);
	
	// 추가 정보 가져오기
	void getFullResource(HttpServletRequest request);
	
	// 게시글 부가 정보 가져오기
	void getRecruitInfo(HttpServletRequest request);
	
	// 부가정보 수정요청
	void updateInfo(HttpServletRequest request, HttpServletResponse response);
}
