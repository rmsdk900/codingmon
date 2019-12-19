package board.promotion.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import board.promotion.dao.PromotionDAO;
import board.promotion.dao.PromotionDAOImpl;
import member.dao.MemberDAO;
import member.dao.MemberDAOImpl;
import member.vo.MemberInfo;
import member.vo.MemberVO;
import util.PageMaker;
import util.SearchCriteria;

public class PromotionServiceImpl implements PromotionService{
	MemberDAO mdao = new MemberDAOImpl();
	PromotionDAO pdao = new PromotionDAOImpl();
	
	// 아직은 검색 기능 없음. 
	@Override
	public void search(HttpServletRequest request) {
		int page= 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String searchName = request.getParameter("searchName");
		String searchGender = request.getParameter("searchGender");
		String[] ages = request.getParameterValues("searchAge");
		String[] regions = request.getParameterValues("searchRegion");
		String[] jobs = request.getParameterValues("searchJob");
		String[] workLangs = request.getParameterValues("searchWorkLang");
		String[] learnLangs = request.getParameterValues("searchLearnLang");
		
		ArrayList<Integer> searchAge = new ArrayList<>();
		ArrayList<Integer> searchRegion = new ArrayList<>();
		ArrayList<Integer> searchJob = new ArrayList<>();
		ArrayList<Integer> searchWorkLang = new ArrayList<>();
		ArrayList<Integer> searchLearnLang = new ArrayList<>();
		
		if(ages !=null) {
			for(String a : ages) {
				int age = Integer.parseInt(a);
				searchAge.add(age);
			}
		}
		
		if(regions != null) {
			for(String r : regions) {
				int region = Integer.parseInt(r);
				searchRegion.add(region);
			}
		}
		
		if(jobs != null) {
			for(String j : jobs) {
				int job = Integer.parseInt(j);
				searchJob.add(job);
			}
		}
		
		if(workLangs != null) {
			for(String w : workLangs) {
				int work = Integer.parseInt(w);
				searchWorkLang.add(work);
			}
		}
		
		if(learnLangs != null) {
			for(String l : learnLangs) {
				int learn = Integer.parseInt(l);
				searchLearnLang.add(learn);
			}
		}
		
		
		
		SearchCriteria cri = new SearchCriteria(page, 10, searchName, searchGender, searchAge, searchRegion, searchJob, searchWorkLang, searchLearnLang);
		
		System.out.println("searchCriteria"+cri);
		
		int totalCount = pdao.getSearchTotal(cri);
		
		// 여기부터 또 바꿔야함. 
		System.out.println("totalCount : "+totalCount);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		
		
		ArrayList<MemberVO> memberList = mdao.getMemberList(cri);
		ArrayList<MemberInfo> infoList = mdao.getInfoList(cri);
		
		
		request.setAttribute("ml", memberList);
		request.setAttribute("il", infoList);
		request.setAttribute("pm", pageMaker);
		
	}
	// 상세보기... 내용 다 때려박아놓자.
	@Override
	public void getDetail(HttpServletRequest request) {
		int cm_num = Integer.parseInt(request.getParameter("cm_num"));
		
		MemberVO member = mdao.getMemberByNum(cm_num);
		MemberInfo info = mdao.getInfoByNum(cm_num);
		
		HashMap<Integer, String> jobs = mdao.getJobs(mdao.getJobList(cm_num));
		HashMap<Integer, String> regions = mdao.getRegions(mdao.getRegionList(cm_num));
		HashMap<String, String> subjects = mdao.getSubjects(mdao.getSubjectList(cm_num));
		
		request.setAttribute("prospects", member);
		request.setAttribute("info", info);
		request.setAttribute("mj", jobs);
		request.setAttribute("mr", regions);
		request.setAttribute("ms", subjects);
		
	}
	
	
}
