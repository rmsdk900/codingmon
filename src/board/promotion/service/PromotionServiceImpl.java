package board.promotion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import board.promotion.dao.PromotionDAO;
import board.promotion.dao.PromotionDAOImpl;
import board.promotion.vo.ResumeVO;
import member.dao.MemberDAO;
import member.dao.MemberDAOImpl;
import member.vo.AgeVO;
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
		
		String searchGender = request.getParameter("searchGender");
		String[] ages = request.getParameterValues("searchAge");
		String[] regions = request.getParameterValues("searchRegion");
		String[] jobs = request.getParameterValues("searchJob");
		String[] workLangs = request.getParameterValues("searchWorkLang");
		String[] learnLangs = request.getParameterValues("searchLearnLang");
		
		ArrayList<AgeVO> searchAge = null;
		ArrayList<Integer> searchRegion = null;
		ArrayList<Integer> searchJob = null;
		ArrayList<Integer> searchWorkLang = null;
		ArrayList<Integer> searchLearnLang = null;
		
		if(ages !=null) {
			// 오늘 날짜
			Date tdy = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			int today = Integer.parseInt(fmt.format(tdy));
			
			searchAge = new ArrayList<>();
			for(String a : ages) {
				
				int age = Integer.parseInt(a);
				
				AgeVO vo = new AgeVO();
				vo.setStartAge(today-((age+9)*10000));
				vo.setEndAge(today-(age*10000));
				searchAge.add(vo);
			}
		}
		
		if(regions != null) {
			searchRegion = new ArrayList<>(); 
			for(String r : regions) {
				int region = Integer.parseInt(r);
				searchRegion.add(region);
			}
		}
		
		if(jobs != null) {
			searchJob = new ArrayList<>();
			for(String j : jobs) {
				int job = Integer.parseInt(j);
				searchJob.add(job);
			}
		}
		
		if(workLangs != null) {
			searchWorkLang = new ArrayList<>();
			for(String w : workLangs) {
				int work = Integer.parseInt(w);
				searchWorkLang.add(work);
			}
		}
		
		if(learnLangs != null) {
			searchLearnLang = new ArrayList<>();
			for(String l : learnLangs) {
				int learn = Integer.parseInt(l);
				searchLearnLang.add(learn);
			}
		}
		
		
		
		SearchCriteria cri = new SearchCriteria(page, 10, searchGender, searchAge, searchRegion, searchJob, searchWorkLang, searchLearnLang);
		
		System.out.println("searchCriteria"+cri);
		
		
		// 여기부터 조작합시다. 
		// 검색 및 표시용 리스트
		ArrayList<ResumeVO> ResumeList = pdao.getSearchResume(cri); 
		
		int totalCount = ResumeList.size();
		
		System.out.println(totalCount);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		request.setAttribute("rl", ResumeList);
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
