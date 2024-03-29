package member.test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import member.dao.MemberDAO;
import member.dao.MemberDAOImpl;
import member.vo.MemberVO;

class MemberDAOTest {
	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	void testSignUpMember() {
		System.out.println("회원가입 테스트");
//		MemberVO vo = new MemberVO();
//		vo.setCm_email("find@password.com");
//		vo.setCm_pw("12345678");
//		vo.setCm_name("비밀번호모르는 애");
//		vo.setCm_phone("01012341234");
//		vo.setCm_addr("부산");
//		vo.setCm_salt(22222);
//		boolean isSignUp=dao.signUpMember(vo);
//		System.out.println("회원가입 성공 여부 : "+isSignUp);
		
	}
	@Test
	void testInsertJob() {
		System.out.println("직업 정보 추가 테스트");
//		MemberJob mj = new MemberJob();
//		mj.setCij_code(1);
//		mj.setCij_owner_num(1);
//		dao.insertJob(mj);
	}
	
	@Test
	void testInsertRegion() {
		System.out.println("지역 정보 추가 테스트");
//		MemberRegion mr = new MemberRegion();
//		mr.setCmr_code(2);
//		mr.setCmr_owner_num(1);
//		dao.insertRegion(mr);
	}
	
	@Test
	void testInsertSubject() {
		System.out.println("언어 정보 추가 테스트");
//		MemberSubject ms = new MemberSubject();
//		ms.setCms_code(1);
//		ms.setCms_owner_num(1);
//		ms.setCms_category(1);
//		dao.insertSubject(ms);
	}
	
	
	@Test
	void testLoginMember() {
		System.out.println("로그인 테스트");
//		String email = "admin";
//		String pass = "admin";
//		MemberVO login = dao.loginMember(email, pass);
//		System.out.println(login);	
	}
	
	@Test
	void testGetSalt() {
		System.out.println("소금 가져오기 테스트");
//		String email = "admin";
//		int salt = dao.getSalt(email);
//		System.out.println(salt);
	}

	@Test
	void testGetMemberByEmail() {
		System.out.println("email로 Member 가져오기 테스트");
//		String email = "admin";
//		MemberVO member = dao.getMemberByEmail(email);
//		System.out.println(member);
	}

	@Test
	void testInsertInfo() {
		System.out.println("부가 정보 입력 테스트");
//		MemberInfo info = new MemberInfo();
//		info.setCmi_owner_num(1);
//		info.setCmi_intro("내가 관리자다");
//		info.setCmi_gender("Male");
//		info.setCmi_age(1);
//		info.setCmi_career("실험체로 일함");
//		boolean isInfo = dao.insertInfo(info);
//		System.out.println("부가정보 입력 여부 : "+isInfo);
	}
	
	@Test
	void testGetInfoByNum() {
		System.out.println("부가정보 회원번호로 가져오기 테스트");
//		int cmi_owner_num = 1;
//		MemberInfo info = dao.getInfoByNum(cmi_owner_num);
//		System.out.println("가져온 부가 정보 확인 : "+info);
	}

	@Test
	void testUpdateMember() {
		System.out.println("가입 정보 수정 테스트");
//		MemberVO vo = new MemberVO();
//		vo.setCm_num(2);
//		vo.setCm_email("update");
//		vo.setCm_pw("1234");
//		vo.setCm_phone("01012345678");
//		vo.setCm_addr("서울시 수정");
//		boolean isSuccess = dao.updateMember(vo);
//		System.out.println("가입 정보 수정 여부 : "+isSuccess);
		
	}

	@Test
	void testUpdateInfo() {
		System.out.println("부가 정보 수정 테스트");
//		MemberInfo info = new MemberInfo();
//		info.setCmi_job("학생");
//		info.setCmi_intro("학생이 짱이다!");
//		info.setCmi_private("Y");
//		info.setCmi_gender("Female");
//		info.setCmi_age(24);
//		info.setCmi_career("수정했다.");
//		info.setCmi_owner_num(2);
//		boolean isSuccess = dao.updateInfo(info);
//		System.out.println("부가 정보 수정 여부 : "+isSuccess);
		
	}

	@Test
	void testAddPassCode() {
		System.out.println("비밀번호 찾기 코드 생성 테스트");
//		String email = "find@password.com";
//		String code = "12345";
//		dao.addPassCode(email, code);
	}

	@Test
	void testCheckPassCode() {
		System.out.println("비밀번호 코드 일치 확인 테스트");
//		String email = "find@password.com";
//		String code = "12345";
//		boolean isRight = dao.checkPassCode(email, code);
//		System.out.println("비밀번호 코드가 일치하니? : "+isRight);
	}

	@Test
	void testChangePass() {
		System.out.println("비밀번호 변경 테스트");
		String email = "find@password.com";
		String newPass = "change";
		boolean isChange = dao.changePass(email, newPass);
		System.out.println("비밀번호 변경 여부 : "+isChange);
	}

	@Test
	void testDeleteMember() {
		System.out.println("회원 탈퇴 테스트");
//		int num = 3;
//		String pw = "1234";
//		boolean isDelete = dao.deleteMember(num, pw);
//		System.out.println("회원탈퇴 여부 : "+isDelete);
	}

	@Test
	void testDeleteInfo() {
		System.out.println("부가 정보 삭제 테스트");
//		int num = 3;
//		boolean isDelete = dao.deleteInfo(num);
//		System.out.println("부가정보 삭제 여부 : "+isDelete);
	}

	@Test
	void testGetMemberTotal() {
		System.out.println("회원 가입 정보 총 갯수 호출 테스트");
//		int totalCount = dao.getMemberTotal();
//		System.out.println("회원 가입자 수 : "+totalCount);
	}

	@Test
	void testGetInfoTotal() {
		System.out.println("부가 정보 총 갯수 호출 테스트");
//		int totalCount = dao.getInfoTotal();
//		System.out.println("부가 정보 수 : "+totalCount);
	}

	@Test
	void testGetMemberList() {
		System.out.println("회원 가입 정보 리스트 테스트");
//		int page = 1;
//		String pageNum = "1";
//		if(pageNum != null) {
//			page = Integer.parseInt(pageNum);
//		}
//		
//		int totalCount = dao.getMemberTotal();
//		Criteria cri = new Criteria(page, 5);
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(totalCount);
//		
//		ArrayList<MemberVO> list = dao.getMemberList(cri);
//		System.out.println(list);

		
	}

	@Test
	void testGetInfoList() {
		System.out.println("회원 추가 정보 리스트 테스트");
//		int page = 1;
//		String pageNum = "1";
//		if(pageNum != null) {
//			page = Integer.parseInt(pageNum);
//		}
//		
//		int totalCount = dao.getInfoTotal();
//		Criteria cri = new Criteria(page, 5);
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(totalCount);
//		ArrayList<MemberInfo> list = dao.getInfoList(cri);
//		System.out.println(list);
		
	}
	
	@Test
	void testGetJobList() {
		System.out.println("회원의 직업 불러오기");
//		int num = 1;
//		ArrayList<MemberJob> list = dao.getJobList(num);
//		System.out.println(list);
	}
	
	@Test
	void testGetJobs() {
		System.out.println("회원의 직업 불러오기 2");
//		ArrayList<String> list = dao.getJobs(dao.getJobList(1));
//		System.out.println(list);
		
	}
	
	@Test
	void testGetRegionList() {
		System.out.println("회원의 선호 지역 리스트 불러오기");
//		int num=1;
//		ArrayList<MemberRegion> list = dao.getRegionList(num);
//		System.out.println(list);
	}
	
	@Test
	void testGetRegions() {
		System.out.println("회원의 선호 지역 리스트 불러오기2");
//		ArrayList<String> list = dao.getRegions(dao.getRegionList(1));
//		System.out.println(list);
	}
	
	@Test
	void testGetSubjectList() {
		System.out.println("회원의 사용 언어 리스트 불러오기");
//		int num=1;
//		ArrayList<MemberSubject> list = dao.getSubjectList(num);
//		System.out.println(list);
	}
	
	@Test
	void testGetSubjects() {
		System.out.println("회원의 사용 언어 리스트 불러오기2");
//		HashMap<String, String> map = dao.getSubjects(dao.getSubjectList(1));
//		System.out.println(map);
	}
	
	@Test
	void testDeleteJobs() {
		System.out.println("회원의 모든 직업 정보 삭제 테스트");
//		dao.deleteJobs(1);
	}
	
	@Test
	void testDeleteRegions() {
		System.out.println("회원의 모든 지역 정보 삭제 테스트");
//		dao.deleteRegions(1);
	}
	
	@Test
	void testDeleteSubjects() {
		System.out.println("회원의 모든 언어 정보 삭제 테스트");
//		dao.deleteSubjects(1);
	}
	
}
