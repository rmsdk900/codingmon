package board.promotion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.promotion.service.PromotionService;
import board.promotion.service.PromotionServiceImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import util.FactoryUtil;

/**
 * Servlet implementation class PromotionController
 */
@WebServlet("/board/promotion/*")
public class PromotionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PromotionService ps = new PromotionServiceImpl();
	MemberService ms = new MemberServiceImpl();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PromotionController GET 요청");
		MemberServiceImpl.loginCookie(request);
		String cmd = FactoryUtil.getCommand(request);
		String nextPage = null;
		
		// 여기부터!
		if(cmd.equals("board/promotion/list")) {
			System.out.println("이력서 리스트 페이지 호출");
			ms.getFullReSource(request);
			ps.search(request);
			nextPage= "/board/promote/promote_list.jsp";
		}
		
		if(cmd.equals("board/promotion/detail")) {
			System.out.println("이력서 상세보기 페이지 호출");
			ps.getDetail(request);
			nextPage="/board/promote/promote_detail.jsp";
		}
		
		if(cmd.equals("board/promotion/search")) {
			System.out.println("이력서 다중 검색 결과 화면 호출");
			ms.getFullReSource(request);
			ps.search(request);
			nextPage="/board/promote/promote_list.jsp";
		}
		
		FactoryUtil.nextPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PromotionController POST 요청");
		request.setCharacterEncoding("UTF-8");
		MemberServiceImpl.loginCookie(request);
		String cmd = FactoryUtil.getCommand(request);
		String nextPage = null;
		
		
		
		FactoryUtil.nextPage(request, response, nextPage);
		
		
	}

}
