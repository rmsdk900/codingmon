package board.recruit.dao;

import java.util.ArrayList;
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


public interface RecruitDAO {
	
	// 전체 게시물 개수
	public int getTotalCount();
	// 페이징 처리된 게시물 개수
	public ArrayList<RecruitVO> getCbrList(Criteria cri);
	// 게시물 정보(상세보기)
	public RecruitVO getCbrVO(int cbr_num);
	// 검색 결과에 포함된 게시물 수
	int getSearchListCount(SearchCriteria cri);
	// 게시글 작성
	public boolean cbrWrite(RecruitVO cbr);
	// 게시글 수정
	public boolean cbrUpdate(RecruitVO cbr);
	// 게시물 삭제
	boolean cbrDelete(int cbr_num, int cbr_writer_num);
	// 최근 등록 게시글 검색
	public RecruitVO getRecent(int cbr_writer_num);
	// 조건 검색
	public ArrayList<RecruitVO> getSearchList(PageMaker pageMaker);
	
	/**
	 * 20191219 부가정보 처리 추가
	 */
	
	// 직업 정보 입력
	void insertJob(RecruitJob rj);
	// 지역 정보 입력
	void insertRegion(RecruitRegion rr);
	// 언어 정보 입력
	void insertSubject(RecruitSubject rs);
	// 임금 정보 입력
	void insertPayment(RecruitPayment rp);
	
	// 게시판의 직업 번호 목록 검색
	ArrayList<RecruitJob> getJobList(int num);
	HashMap<Integer, String> getJobs(ArrayList<RecruitJob> list);
	// 게시판의 지역 번호 목록 검색
	ArrayList<RecruitRegion> getRegionList(int num);
	HashMap<Integer, String> getRegions(ArrayList<RecruitRegion> list);
	// 게시판의 언어 번호 목록 검색
	ArrayList<RecruitSubject> getSubjectList(int num);
	HashMap<Integer, String> getSubjects(ArrayList<RecruitSubject> list);
	// 게시판의 임금 번호 목록 검색
	ArrayList<RecruitPayment> getPaymentList(int num);
	HashMap<Integer, String> getPayments(ArrayList<RecruitPayment> list);

	// 직업/지역/언어/임금 전체목록
	ArrayList<codingmon_jobVO> getEntireJobs();
	ArrayList<codingmon_regionVO> getEntireRegions();
	ArrayList<codingmon_subjectVO> getEntireSubjects();
	ArrayList<codingmon_paymentVO> getEntirePayments();
	
	// 게시글의 직업 정보 전체 지우기
	void deleteJobs(int num);
	// 게시글의 지역 정보 전체 지우기
	void deleteRegions(int num);
	// 게시글의 언어 정보 전체 지우기 
	void deleteSubjects(int num);
	// 게시글의 임금 정보 지우기
	void deletePayment(int num);
	
}
