package member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import member.vo.MemberInfo;
import member.vo.MemberVO;
import member.vo.job.MemberJob;
import member.vo.region.MemberRegion;
import member.vo.subject.MemberSubject;
import util.Criteria;

public interface MemberDAO {
	// 회원가입
	boolean signUpMember(MemberVO vo);
	// 부가 정보 입력
	boolean insertInfo(MemberInfo info);
	// 직업 정보 입력
	void insertJob(MemberJob mj);
	// 활동지역 정보 입력
	void insertRegion(MemberRegion mr);
	// 주 언어 및 공부 언어 입력
	void insertSubject(MemberSubject ms);
	
	
	// 로그인
	MemberVO loginMember(String email, String pass);
	// email로 가입 정보 가져오기
	MemberVO getMemberByEmail(String email);
	// 회원번호로 부가 정보 가져오기
	MemberInfo getInfoByNum(int num);
	// 소금 가져오기
	int getSalt(String email);
	
	
	// 가입 정보 수정
	boolean updateMember(MemberVO vo);
	// 부가 정보 수정
	boolean updateInfo(MemberInfo info);
	
	// 비밀번호 찾기용 코드 생성 
	void addPassCode(String email, String code);
	// 비밀번호 찾기용 코드 대조
	boolean checkPassCode(String email, String code);
	// 비밀번호 변경
	boolean changePass(String email, String pass);
	
	// 회원 탈퇴 회원번호와 비밀번호 필요
	boolean deleteMember(int num, String pw);
	// 부가 정보 삭제 회원번호 필요
	boolean deleteInfo(int num);
	// 그 사람의 직업 정보 전체 지우기
	void deleteJobs(int num);
	// 그 사람의 지역 정보 전체 지우기
	void deleteRegions(int num);
	// 그 사람의 언어 정보 전체 지우기 
	void deleteSubjects(int num);
	
	// 가입 정보 총 갯수(관리자 용)
	int getMemberTotal();
	// 부가 정보 총 갯수
	int getInfoTotal();
	// 가입 정보 목록
	ArrayList<MemberVO> getMemberList(Criteria cri);
	// 부가 정보 목록
	ArrayList<MemberInfo> getInfoList(Criteria cri);
	
	// Read - 그 사람의 직업 번호 목록 검색
	ArrayList<MemberJob> getJobList(int num);
	ArrayList<String> getJobs(ArrayList<MemberJob> list);
	// Read - 그 사람의 선호 지역 번호 목록 검색
	ArrayList<MemberRegion> getRegionList(int num);
	ArrayList<String> getRegions(ArrayList<MemberRegion> list);
	// Read - 그 사람의 주 언어 및 공부 언어 코드 검색
	ArrayList<MemberSubject> getSubjectList(int num);
	HashMap<String, String> getSubjects(ArrayList<MemberSubject> list);
	
	
	// 검색 조건에 맞는 가입정보 갯수
	// 검색 조건에 맞는 부가정보 갯수
	// 검색 조건에 맞는 가입정보 목록
	// 검색 조건에 맞는 부가정보 목록
	
}
