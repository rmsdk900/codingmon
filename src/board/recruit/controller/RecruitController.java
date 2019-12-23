package board.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.recruit.service.RecruitService;
import board.recruit.service.RecruitServiceImpl;
import board.recruit.vo.RecruitVO;
import member.service.MemberServiceImpl;
import util.FactoryUtil;

/**
 * Servlet implementation class RecruitController
 */
@WebServlet("/board/rec/*")
public class RecruitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RecruitService cbrs = new RecruitServiceImpl();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RecruitController get요청");
		String cmd = FactoryUtil.getCommand(request);

		String nextPage = null;
		
		MemberServiceImpl.loginCookie(request);
		
		// recruit 게시판 화면
		if(cmd.equals("board/rec/list")) {
			System.out.println("recruit 게시판 화면 요청");
			ArrayList<RecruitVO> list = cbrs.getRecruitList(request);
			System.out.println(list);
			request.setAttribute("cbrList", list);
			nextPage = "/board/recruit/recruit/recruit.jsp";
		}
		
		// recruit 상세보기 화면
		if(cmd.equals("board/rec/detail")) {
			System.out.println("recruit 상세보기 화면 요청");
			RecruitVO cbr = cbrs.getCbrVO(request);
			request.setAttribute("recruitVO", cbr);
			cbrs.getRecruitInfo(request);
			nextPage = "/board/recruit/recruit/recruit_detail.jsp";
		}
		
		// recruit 게시글 작성 화면
		if(cmd.equals("board/rec/write")) {
			System.out.println("recruit 게시글 작성 화면 요청");
			nextPage = "/board/recruit/recruit/recruit_write1.jsp";
		}
		
		// recruit 게시글 수정 화면
		if(cmd.equals("board/rec/update")) {
			System.out.println("recruit 게시글 수정 화면 요청");
			request.setAttribute("RecruitVO", cbrs.getCbrVO(request));
			nextPage = "/board/recruit/recruit/recruit_update.jsp";
		}
		
		// recruit 게시글 삭제 요청
		if(cmd.equals("board/rec/delete")) {
			System.out.println("게시글 삭제 요청");
			
			System.out.println("cbr_num : " + request.getParameter("cbr_num"));
			System.out.println("cbr_writer_num : "+ request.getParameter("cbr_writer_num"));
			
			request.setAttribute("cbr_num", request.getParameter("cbr_num"));
			request.setAttribute("cbr_writer_num", request.getParameter("cbr_writer_num"));
			cbrs.cbrDelete(request, response);
		}
		
		// recruit 게시글 검색 요청
		if(cmd.equals("board/rec/search")) {
			System.out.println("게시글 검색 요청");
			cbrs.search(request);
			nextPage = "/board/recruit/recruit/recruit.jsp";
		}
		
		FactoryUtil.nextPage(request, response, nextPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("RecruitController post 요청");
		
		String cmd = FactoryUtil.getCommand(request);
		System.out.println("post 요청 : "+ cmd);
		
		String nextPage = null;
		
		// recruit 게시글 등록 요청
		if(cmd.equals("board/rec/write_submit")) {
			System.out.println("게시글 등록 요청");
			
			boolean isWrite = cbrs.cbrWrite(request);
			
			int num = Integer.parseInt(request.getParameter("cbr_writer_num"));
			System.out.println("writer_num : "+num);
			request.setAttribute("cbr_writer_num", num);
			RecruitVO vo = cbrs.getRecent(request);
			System.out.println("등록된 글 : "+vo);
		
			if(isWrite) {
				System.out.println("게시글 등록 성공");
				request.setAttribute("vo", vo);
				nextPage = "/board/recruit/recruit/recruit_more_info.jsp";
			}else {
				System.out.println("게시글 등록 실패");
				response.setContentType("test/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('게시글 등록에 실패하셨습니다.')");
				out.print("history.go(-1)");
				out.print("</script>");
			}
		}
		
		// recruit 게시글 부가정보 입력
		if(cmd.equals("board/rec/write_info")) {
			System.out.println("부가정보 등록 요청");
			int num = Integer.parseInt(request.getParameter("cbr_num"));
			System.out.println(num);
			request.setAttribute("cbr_num", num);
			cbrs.insertInfo(request, response);
		}
		
		// recruit 게시글 수정 등록 요청
		if(cmd.equals("board/rec/update_submit")) {
			System.out.println("게시글 수정 등록 요청");
			int num = Integer.parseInt(request.getParameter("cbr_num"));
			System.out.println("수정 요청 들어온 글 번호 : "+num);
			
			boolean isUpdate = cbrs.cbrUpdate(request);
			
			if(isUpdate) {
				System.out.println("게시글 수정 성공");
				request.setAttribute("cbr_num", num);
				cbrs.getRecruitInfo(request);
				nextPage = "/board/recruit/recruit/recruit_info_update.jsp";
			}else {
				System.out.println("게시글 수정 실패");
				response.setContentType("test/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('게시글 수정에 실패하셨습니다.')");
				out.print("history.go(-1)");
				out.print("</script>");
			}
		}
		
		// recruit 게시글 부가정보 수정
		if(cmd.equals("board/rec/update_info")) {
			System.out.println("부가 정보 수정 등록 요청");
			int num = Integer.parseInt(request.getParameter("cbr_num"));
			System.out.println("수정 요청 들어온 글 번호 : "+num);
			request.setAttribute("cbr_num", num);
			cbrs.updateInfo(request, response);
		}
		
		FactoryUtil.nextPage(request, response, nextPage);
	}

}
