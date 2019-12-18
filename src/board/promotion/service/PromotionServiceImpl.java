package board.promotion.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import member.dao.MemberDAO;
import member.dao.MemberDAOImpl;
import member.vo.MemberInfo;
import member.vo.MemberVO;
import util.Criteria;
import util.PageMaker;

public class PromotionServiceImpl implements PromotionService{
	MemberDAO mdao = new MemberDAOImpl();
	// 아직은 검색 기능 없음. 
	@Override
	public void search(HttpServletRequest request) {
		int page= 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Criteria cri = new Criteria(page, 10);
		System.out.println(cri);
		int totalCount = mdao.getMemberTotal();
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
	
}
