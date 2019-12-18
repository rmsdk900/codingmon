package member.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;

public interface MemberService {
	/**
	 * 20191214 시작
	 */
	// 회원가입 처리
	boolean memberSignUp(HttpServletRequest request);
	/**
	 * 20191215 시작
	 */
	// 부가 정보 입력 처리(직업 정보랑, 지역 정보, 언어 정보까지 넣자)
	void insertInfo(HttpServletRequest request, HttpServletResponse response) throws IOException;
	// 로그인 처리(암호 대조, 쿠키 심기 등)
	boolean memberLogin(HttpServletRequest request, HttpServletResponse response);
	// 암호 찾기 코드 생성 & 암호 찾기 메일 보내기 
	void sendPasscode(HttpServletRequest request, HttpServletResponse response);
	// 입력한 암호 코드 체크
	boolean checkPwCode(HttpServletRequest request, HttpServletResponse response);
	// 비밀번호 변경
	void changePw(HttpServletRequest request, HttpServletResponse response);
	// 로그아웃
	void logOut(HttpServletRequest request, HttpServletResponse response);
	// 내 부가 정보들 가져오기.
	void getMyInfo(HttpServletRequest request);
	// 아이디 중복 체크
	boolean checkId(HttpServletRequest request);
	// 비동기 로그인 확인
	boolean loginCheckAsync(HttpServletRequest request);
	// 비밀번호 확인
	boolean checkPwAsync(HttpServletRequest request);
	// 직업 / 지역 / 언어 데이터 가져오기. 
	void getFullReSource(HttpServletRequest request);
	// 부가 정보 업데이트.
	void updateInfo(HttpServletRequest request, HttpServletResponse response);
	// 비밀번호 업데이트
	void updatePw(HttpServletRequest request, HttpServletResponse response);
	// 회원 탈퇴
	void deleteMember(HttpServletRequest request, HttpServletResponse response);
	
	
}
