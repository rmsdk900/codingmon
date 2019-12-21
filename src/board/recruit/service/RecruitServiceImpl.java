package board.recruit.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.recruit.dao.RecruitCommentDAO;
import board.recruit.dao.RecruitCommentDAOImpl;
import board.recruit.dao.RecruitDAO;
import board.recruit.dao.RecruitDAOImpl;
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



public class RecruitServiceImpl implements RecruitService{
	
	RecruitDAO dao = new RecruitDAOImpl();
	RecruitCommentDAO cdao = new RecruitCommentDAOImpl();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	// 게시글 목록 요청 보내기
	@Override
	public ArrayList<RecruitVO> getRecruitList(HttpServletRequest request) {
		System.out.println("서비스 recruitList 요청");
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int totalCount = dao.getTotalCount();
		
		Criteria cri = new Criteria(page, 10);
		PageMaker pm = new PageMaker();
		pm.setDisplayPageNum(5);
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		
		request.setAttribute("pm", pm);
		
		return dao.getCbrList(cri);
	}

	// 게시글 등록 요청 보내기
	@Override
	public boolean cbrWrite(HttpServletRequest request) {
		System.out.println("서비스 cbrWrite 요청");
		
		boolean isWrite = false;
		
		RecruitVO cbr = new RecruitVO();
		try {
			cbr.setCbr_title(request.getParameter("cbr_title"));
			cbr.setCbr_writer(request.getParameter("cbr_writer"));
			cbr.setCbr_writer_num(Integer.parseInt(request.getParameter("cbr_writer_num")));
			cbr.setCbr_content(request.getParameter("cbr_content"));
			cbr.setCbr_pay(Double.parseDouble(request.getParameter("cbr_pay")));
			cbr.setCbr_date_from(sdf.parse(request.getParameter("cbr_date_from")));
			cbr.setCbr_date_to(sdf.parse(request.getParameter("cbr_date_to")));
			cbr.setCbr_deadline(sdf.parse(request.getParameter("cbr_deadline")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		isWrite = dao.cbrWrite(cbr);
		return isWrite;
	}
	
	// 부가정보 입력(직업, 임금, 지역, 언어)
	@Override
	public void insertInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("서비스 insertInfo 요청");
		
		// 정보 넣기위해 게시판 글번호 가져오기
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		
		try {
			// 직업 정보 넣기
			String[] jobs = request.getParameterValues("crj_code");
			if(jobs != null) {
				for(String job : jobs) {
					RecruitJob rj = new RecruitJob();
					int code = Integer.parseInt(job);
					rj.setCrj_code(code);
					rj.setCrj_board_num(cbr_num);
					dao.insertJob(rj);
				}
			}
			
			// 지역 정보 넣기
			String[] regs = request.getParameterValues("crr_code");
			if(regs != null) {
				for(String reg : regs) {
					RecruitRegion rr = new RecruitRegion();
					int code = Integer.parseInt(reg);
					rr.setCrr_code(code);
					rr.setCrr_board_num(cbr_num);
					dao.insertRegion(rr);
				}
			}
			
			// 언어 정보 넣기
			String[] subs = request.getParameterValues("crs_code");
			if(subs !=null) {
				for(String sub : subs) {
					RecruitSubject rs = new RecruitSubject();
					int code = Integer.parseInt(sub);
					rs.setCrs_code(code);
					rs.setCrs_board_num(cbr_num);
					dao.insertSubject(rs);
				}
			}
			
			// 임금 정보 넣기
			String[] pay = request.getParameterValues("crp_code");
			if(pay != null) {
				for(String p : pay) {
					RecruitPayment rp = new RecruitPayment();
					int code = Integer.parseInt(p);
					rp.setCrp_code(code);
					rp.setCrp_board_num(cbr_num);
					dao.insertPayment(rp);
				}
			}
			
			try {
				// 부가정보 입력 결과 처리
				response.sendRedirect("search");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}

	// 상세보기 요청(글번호) 보내기
	@Override
	public RecruitVO getCbrVO(HttpServletRequest request) {
		System.out.println("서비스 getCbrVO 요청");
		
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		
		return dao.getCbrVO(cbr_num);
	}
	
	// 최근 등록 글 검색
	@Override
	public RecruitVO getRecent(HttpServletRequest request) {
		System.out.println("서비스 getRecent 요청");
		int num = Integer.parseInt(request.getParameter("cbr_writer_num"));
		return dao.getRecent(num);
	}
	
	// 조건 검색
	@Override
	public void search(HttpServletRequest request) {
		System.out.println("서비스 조건 검색 요청");
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String searchRecruit = request.getParameter("searchRecruit");
		String searchRecruitVal = request.getParameter("searchRecruitVal");
		
		SearchCriteria cri = new SearchCriteria(page, 10, searchRecruit, searchRecruitVal);
		System.out.println(cri);
		
		int listCount = dao.getSearchListCount(cri);
		System.out.println("FoundCount : " + listCount);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(listCount);
		
		ArrayList<RecruitVO> cbrList = dao.getSearchList(pm);
		
		request.setAttribute("cbrList", cbrList);
		request.setAttribute("pm", pm);
	}

	// 전체 부가 정보 가져오기
	@Override
	public void getFullResource(HttpServletRequest request) {
		System.out.println("서비스 getFullResource 요청");

		ArrayList<codingmon_jobVO> toJobs = dao.getEntireJobs();
		ArrayList<codingmon_regionVO> toRegions = dao.getEntireRegions();
		ArrayList<codingmon_subjectVO> toSubjects = dao.getEntireSubjects();
		ArrayList<codingmon_paymentVO> toPayments = dao.getEntirePayments();
		
		request.setAttribute("eJ", toJobs);
		request.setAttribute("eR", toRegions);
		request.setAttribute("eS", toSubjects);
		request.setAttribute("eP", toPayments);
		
	}

	// 게시글 부가 정보 가져오기
	@Override
	public void getRecruitInfo(HttpServletRequest request) {
		System.out.println("서비스 getRecruitInfo 요청");
		
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		System.out.println(cbr_num);
		// 글번호로 게시글 가져오기
		RecruitVO vo = dao.getCbrVO(cbr_num);
		
		HttpSession session = request.getSession();
		
		// 직업 정보 가져오기
		HashMap<Integer, String> recruitJobs = dao.getJobs(dao.getJobList(cbr_num));
		session.setAttribute("job", recruitJobs);
		System.out.println(cbr_num+"번 게시글의 직업리스트 :" + recruitJobs);
		// 지역 정보 가져오기
		HashMap<Integer, String> recruitRegions = dao.getRegions(dao.getRegionList(cbr_num));
		session.setAttribute("reg", recruitRegions);
		System.out.println(cbr_num+"번 게시글의 지역리스트 :" + recruitRegions);
		// 언어 정보 가져오기
		HashMap<Integer, String> recruitSubjects = dao.getSubjects(dao.getSubjectList(cbr_num));
		session.setAttribute("sub", recruitSubjects);
		System.out.println(cbr_num+"번 게시글의 언어리스트 :" + recruitSubjects);
		// 임금 정보 가져오기
		HashMap<Integer, String> recruitPayments = dao.getPayments(dao.getPaymentList(cbr_num));
		session.setAttribute("pay", recruitPayments);
		System.out.println(cbr_num+"번 게시글의 임금리스트 :" + recruitPayments);
		
		// 전체 부가 정보
		ArrayList<codingmon_jobVO> toJobs = dao.getEntireJobs();
		ArrayList<codingmon_regionVO> toRegions = dao.getEntireRegions();
		ArrayList<codingmon_subjectVO> toSubjects = dao.getEntireSubjects();
		ArrayList<codingmon_paymentVO> toPayments = dao.getEntirePayments();
		
		// request에 적재
		request.setAttribute("vo", vo);
		request.setAttribute("rj", recruitJobs);
		request.setAttribute("rr", recruitRegions);
		request.setAttribute("rs", recruitSubjects);
		request.setAttribute("rp", recruitPayments);
		
		request.setAttribute("eJ", toJobs);
		request.setAttribute("eR", toRegions);
		request.setAttribute("eS", toSubjects);
		request.setAttribute("eP", toPayments);
	}

	// 게시글 수정 요청
	@Override
	public boolean cbrUpdate(HttpServletRequest request) {
		System.out.println("서비스 cbrUpdate 요청");
		
		RecruitVO cbr = new RecruitVO();
		
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		
		try {
			cbr.setCbr_num(cbr_num);
			cbr.setCbr_title(request.getParameter("cbr_title"));
			cbr.setCbr_writer(request.getParameter("cbr_writer"));
			cbr.setCbr_writer_num(Integer.parseInt(request.getParameter("cbr_writer_num")));
			cbr.setCbr_content(request.getParameter("cbr_content"));
			cbr.setCbr_pay(Double.parseDouble(request.getParameter("cbr_pay")));
			cbr.setCbr_date_from((Date) sdf.parse(request.getParameter("cbr_date_from")));
			cbr.setCbr_date_to((Date) sdf.parse(request.getParameter("cbr_date_to")));
			cbr.setCbr_deadline((Date) sdf.parse(request.getParameter("cbr_deadline")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean isSuccess = dao.cbrUpdate(cbr);
		
		return isSuccess;
	}
	
	// 부가정보 수정
	public void updateInfo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("서비스 updateInfo 요청");
		
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		
		System.out.println("부가정보 수정 시작");
		// 기존 내용 삭제
		dao.deleteJobs(cbr_num);
		dao.deleteRegions(cbr_num);
		dao.deleteSubjects(cbr_num);
		dao.deletePayment(cbr_num);
		
		// 신규 입력
		// 직업 정보 넣기
		String[] jobs = request.getParameterValues("crj_code");
		if(jobs !=null) {
			for(String job : jobs) {
				RecruitJob rj = new RecruitJob();
				int code = Integer.parseInt(job);
				rj.setCrj_code(code);
				rj.setCrj_board_num(cbr_num);
				dao.insertJob(rj);
			}
		}
		
		// 지역 정보 넣기
		String[] regs = request.getParameterValues("crr_code");
		if(regs != null) {
			for(String reg : regs) {
				RecruitRegion rr = new RecruitRegion();
				int code = Integer.parseInt(reg);
				rr.setCrr_code(code);
				rr.setCrr_board_num(cbr_num);
				dao.insertRegion(rr);
			}
		}
		
		// 언어 정보 넣기
		String[] subs = request.getParameterValues("crs_code");
		if(subs != null) {
			for(String sub : subs) {
				RecruitSubject rs = new RecruitSubject();
				int code = Integer.parseInt(sub);
				rs.setCrs_code(code);
				rs.setCrs_board_num(cbr_num);
				dao.insertSubject(rs);
			}
		}
		
		// 임금 정보 넣기
		String[] pay = request.getParameterValues("crp_code");
		if(pay != null) {
			for(String p : pay) {
				RecruitPayment rp = new RecruitPayment();
				int code = Integer.parseInt(p);
				rp.setCrp_code(code);
				rp.setCrp_board_num(cbr_num);
				dao.insertPayment(rp);
			}
		}
		
		try {
			response.sendRedirect("cbrDetail.cbr?cbr_num="+cbr_num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 삭제 요청
	@Override
	public void cbrDelete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("서비스 cbrDelete 요청");
		
		int cbr_num = Integer.parseInt(request.getParameter("cbr_num"));
		int cbr_writer_num = Integer.parseInt(request.getParameter("cbr_writer_num"));
		
		// 부가정보 먼저 삭제 해주기
		dao.deleteJobs(cbr_num);
		dao.deleteRegions(cbr_num);
		dao.deleteSubjects(cbr_num);
		dao.deletePayment(cbr_num);
		
		// 코멘트가 있으면 코멘트도 삭제 해주기
		cdao.delBoardComment(cbr_num);
		
		try {
			if(dao.cbrDelete(cbr_num, cbr_writer_num)) {
				System.out.println("삭제 성공");
				response.sendRedirect("cbrList.cbr");
			}else {
				System.out.println("삭제 실패! 정보가 일치하지 않습니다.");
				response.sendRedirect("cbrDetail.cbr?cbr_num="+cbr_num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
