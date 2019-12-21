package board.qna.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.qna.dao.QNA_BoardDAO;
import board.qna.dao.QNA_BoardDAOImpl;
import board.qna.vo.QNA_BoardVO;
import board.qna.vo.QNA_CommentVO;
import util.Criteria;
import util.PageMaker;





public class QNA_BoardServiceImpl implements QNA_BoardService{

	QNA_BoardDAO qna_dao =  new QNA_BoardDAOImpl();
	
	@Override
	public ArrayList<QNA_BoardVO> getQNA_BoardList() {
		return qna_dao.getQNA_BoardList();
	}
	@Override
	public ArrayList<QNA_BoardVO> getQNA_BoardList(HttpServletRequest request) {
		System.out.println("리스트 요청");
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listCount = qna_dao.getListCount();
		
		System.out.println(listCount);
		
		Criteria cri = new Criteria(page,15);
		PageMaker pageMaker  = new PageMaker();
		pageMaker.setDisplayPageNum(5);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(listCount);
		
		request.setAttribute("pageMaker", pageMaker);
		
		return qna_dao.getQNA_BoardList(pageMaker);
	
	}



	@Override
	public void boardWrite(HttpServletRequest request) {

		String saveDir ="/upload";
		
		try {
			
			String realPath 
			= request.getServletContext().getRealPath(saveDir);
			System.out.println("realPath : "+realPath);
			
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath, 
					1024*1024*30, 
					"utf-8", 
					new DefaultFileRenamePolicy()
			);
			
			QNA_BoardVO qna_board = new QNA_BoardVO();
			int cbq_writer_num 
			= Integer.parseInt(multi.getParameter("cbq_writer_num"));
		
			String cbq_writer_name = multi.getParameter("cbq_writer_name");
			String cbq_title = multi.getParameter("cbq_title");
			String  cbq_content= multi.getParameter("cbq_content");
			String  file= (String)multi.getFileNames().nextElement();
			String  cbq_file= multi.getFilesystemName(file);
			String  cbq_file_origin= multi.getOriginalFileName(file);
			
			qna_board.setCbq_writer_name(cbq_writer_name);
			qna_board.setCbq_title(cbq_title);
			qna_board.setCbq_content(cbq_content);
			qna_board.setCbq_file(cbq_file);
			qna_board.setCbq_file_origin(cbq_file_origin);
			qna_board.setCbq_writer_num(cbq_writer_num);
			
			
			qna_dao.qna_boardWrite(qna_board);		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public QNA_BoardVO getQNA_BoardVO(HttpServletRequest request) {
		System.out.println("출력");
	int cbq_num = Integer.parseInt(request.getParameter("cbq_num"));
	return qna_dao.getQNA_BoardVO(cbq_num);
	}

	@Override
	public void updateReadCount(HttpServletRequest request) {
		int cbq_num = Integer.parseInt(request.getParameter("cbq_num"));
		qna_dao.updateReadCount(cbq_num);	
		
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
	System.out.println("file down 요청");
	
	String realPath = request.getServletContext().getRealPath("/upload");
	String file_name = request.getParameter("file_name");
	System.out.println(file_name);
	String filePath = realPath+"/"+file_name;
	System.out.println(filePath);
	String mimeType = request.getServletContext().getMimeType(filePath);
	System.out.println(mimeType);
	
	if(mimeType == null) mimeType = "application/octet-stream";
	response.setContentType(mimeType);
	try {
		
		String agent = request.getHeader("User-Agent");
		if(agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1) {
			file_name = URLEncoder.encode(file_name,"utf-8").replaceAll("\\+", "%20");
		}else {
			file_name = new String(file_name.getBytes("utf-8"),"iso-8859-1");
		}
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	
	response.setHeader(
			"Content-Disposition", 
			"attachment;fileName="+file_name
	);
	
	try {
		FileInputStream fis = new FileInputStream(filePath);
		OutputStream os = response.getOutputStream();
		
		byte[] bytes = new byte[4096];
		int numRead = 0;
		while((numRead = fis.read(bytes,0, bytes.length)) != -1) {
			os.write(bytes,0,numRead);
		}
		os.flush();
		os.close();
		fis.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	

	}


	@Override
	public void QNAboardUpdate(HttpServletRequest request , HttpServletResponse response) {

		String saveDir = "/upload";
		
		try {
			
			String realPath = request.getServletContext().getRealPath(saveDir);
			
			System.out.println("realPath : "+realPath);
			
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath, 
					1024*1024*30, 
					"utf-8", 
					new DefaultFileRenamePolicy()
			);
			QNA_BoardVO qna_board = new QNA_BoardVO();
			int cbq_num = Integer.parseInt(multi.getParameter("cbq_num"));
			int cbq_writer_num = Integer.parseInt(multi.getParameter("cbq_writer_num"));
			String cbq_title = multi.getParameter("cbq_title");
			String cbq_content = multi.getParameter("cbq_content");
			String file = (String)multi.getFileNames().nextElement();
			String cbq_file = multi.getFilesystemName(file);
			String cbq_file_origin = multi.getOriginalFileName(file);
			
			
			// 현재 로그인되어있는 녀석의 회원번호 
			//int loginMember_num = ((MemberVO)request.getSession().getAttribute("member")).getNum();
			// 일치하면 다음 단계 
			if (cbq_writer_num == 1) {
				// 바꿀 파일이 있을 때 원래 파일 삭제
				if(file != null) {
					QNA_BoardVO originBoard = qna_dao.getQNA_BoardVO(cbq_num);
					String path = request.getServletContext().getRealPath("/upload");
					String filePath = path+File.separator+originBoard.getCbq_file();
					File file1 = new File(filePath);
					if(file1.exists()) {
						file1.delete();
					}
				}
				
				qna_board.setCbq_num(cbq_num);
				qna_board.setCbq_title(cbq_title);
				qna_board.setCbq_content(cbq_content);
				qna_board.setCbq_file(cbq_file);
				qna_board.setCbq_file_origin(cbq_file_origin);
				qna_board.setCbq_writer_num(cbq_writer_num);
				System.out.println(qna_board);
				qna_dao.qna_boardUpdate(qna_board);
				
				response.sendRedirect("boardDetail.bo?cbq_num="+cbq_num);
				
			// 일치하지 않음. 
			}else {
				// 파일이 이미 만들어짐.
				if(file != null) {
					String path = request.getServletContext().getRealPath("/upload");
					String filePath = path+File.separator+cbq_file;
					File file1 = new File(filePath);
					if(file1.exists()) {
						file1.delete();
					}
					response.sendRedirect("boardDetail.bo?cbq_num="+cbq_num);
				// 만들어진 파일이 없음.
				}else {
					response.sendRedirect("boardDetail.bo?cbq_num="+cbq_num);
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

//회원정보 받기 껄끄러워서 	
	@Override
	public void QNAboardDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cbq_num = Integer.parseInt(request.getParameter("cbq_num"));

	
		System.out.println(cbq_num);
		try {
			if(qna_dao.qna_boardDelete(cbq_num)) {
				System.out.println("삭제성공");
				response.sendRedirect("boardList.bo");
			}else {
				System.out.println("삭제실패");
			response.sendRedirect("boardDelete.bo?cbq_num="+cbq_num);
			}
	}catch(Exception e) {}
		
		
	}

	@Override
	public void boardReplySubmit(HttpServletRequest request) {
	
		int cbq_num = Integer.parseInt(request.getParameter("cbq_num"));
		int cbq_writer_num = Integer.parseInt(request.getParameter("cbq_writer_num"));
		int cbq_re_ref = Integer.parseInt(request.getParameter("cbq_re_ref"));
		int cbq_re_lev = Integer.parseInt(request.getParameter("cbq_re_lev"));
		int cbq_re_seq = Integer.parseInt(request.getParameter("cbq_re_seq"));
		String cbq_writer_name = request.getParameter("cbq_writer_name");
		String cbq_title = request.getParameter("cbq_title");
		String cbq_content = request.getParameter("cbq_content");
//		String cbq_delete = request.getParameter("cbq_delete");
		
		QNA_BoardVO qna_boardvo = new QNA_BoardVO();
		qna_boardvo.setCbq_num(cbq_num);
		qna_boardvo.setCbq_writer_num(cbq_writer_num);
		qna_boardvo.setCbq_re_ref(cbq_re_ref);
		qna_boardvo.setCbq_re_lev(cbq_re_lev);
		qna_boardvo.setCbq_re_seq(cbq_re_seq);
		qna_boardvo.setCbq_writer_name(cbq_writer_name);
		qna_boardvo.setCbq_title(cbq_title);
		qna_boardvo.setCbq_content(cbq_content);
//		qna_boardvo.setCbq_delete(cbq_delete);
		System.out.println(qna_boardvo);
		qna_dao.qna_boardReplySubmit(qna_boardvo);
		
	}
	/*
	 * @Override public JsonObject insertCommentAJAX(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { Gson gson = new Gson();
	 * JsonObject json = new JsonObject(); int ccq_writer_num =
	 * Integer.parseInt(request.getParameter("ccq_writer_num")); int ccq_board_num =
	 * Integer.parseInt(request.getParameter("ccq_board_num")); String
	 * ccq_writer_name = request.getParameter("ccq_writer_name"); String
	 * ccq_writer_content = request.getParameter("ccq_writer_content");
	 * 
	 * QNA_CommentVO vo = new QNA_CommentVO(ccq_writer_num,
	 * ccq_writer_name,ccq_writer_content,ccq_board_num);
	 * 
	 * if(qna_dao.insertComment(vo)) { System.out.println("댓글 작성 성공");
	 * json.addProperty("isSuccess", "success");
	 * 
	 * }else { System.out.println("댓글 작성 실패"); json.addProperty("isSuccess",
	 * "fail"); }
	 * 
	 * String test = gson.toJson(json); System.out.println(test); return json;
	 * 
	 * }
	 */
	/*
	 * @Override public void getCommentListAJAX(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { int ccq_num =
	 * Integer.parseInt(request.getParameter("ccq_num"));
	 * 
	 * int page = 1; String pageNum = request.getParameter("page");
	 * 
	 * if(pageNum != null) { page = Integer.parseInt(pageNum); } int
	 * commentListCount = qna_dao.getCommentListCount(ccq_num);
	 * 
	 * Criteria cri = new Criteria(page, 5); PageMaker pageMaker = new PageMaker();
	 * pageMaker.setDisplayPageNum(5); pageMaker.setCri(cri);
	 * pageMaker.setTotalCount(commentListCount); Gson gson = new Gson();
	 * 
	 * 
	 * String jsonToString = gson.toJson(qna_dao.getCommentListAJAX(ccq_num, cri));
	 * 
	 * ArrayList<QNA_CommentVO> commentList = gson.fromJson(jsonToString,
	 * ArrayList.class); System.out.println(commentList);
	 * 
	 * HashMap<String , Object> map = new HashMap<>(); map.put("list",commentList);
	 * map.put("pm", pageMaker);
	 * 
	 * jsonToString = gson.toJson(map); System.out.println(jsonToString);
	 * 
	 * response.setContentType("application/json;charset=utf-8"); PrintWriter out =
	 * response.getWriter(); out.print(jsonToString); }
	 */



@Override
public ArrayList<QNA_CommentVO> getCommentList(HttpServletRequest request) {
	ArrayList<QNA_CommentVO> list = null;
	
	int ccq_num = Integer.parseInt(request.getParameter("ccq_num"));
	
	int page = 1;
	String pageNum = request.getParameter("page");
	if(pageNum != null) {
		page = Integer.parseInt(pageNum);
	}
	int commentListCount = qna_dao.getCommentListCount(ccq_num);
	Criteria cri = new Criteria(page,5);
	PageMaker pageMaker = new PageMaker();
	pageMaker.setDisplayPageNum(5);
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(commentListCount);
	list = qna_dao.getCommentList(ccq_num, cri);
	request.setAttribute("pm", pageMaker);
	System.out.println(list);
	System.out.println(pageMaker);
	return list;
}


@Override
public void insertComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
	int ccq_writer_num 
	= Integer.parseInt(request.getParameter("ccq_writer_num"));
	int ccq_board_num 
	= Integer.parseInt(request.getParameter("ccq_board_num"));
	String ccq_name = request.getParameter("ccq_name");
	String ccq_content = request.getParameter("ccq_content");
	
	QNA_CommentVO qcvo = new QNA_CommentVO(
			ccq_writer_num,
			ccq_name,
			ccq_content,
			ccq_board_num);
	
	if(qna_dao.insertComment(qcvo)) {
		System.out.println("댓글작성 성공");
		response.sendRedirect("boardDetail.bo?cbq_num="+ccq_board_num);
	}else {
		System.out.println("댓글작성 실패");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(
				"<script> "
						+ " alert('댓글 등록 실패!'); "
						+ " history.go(-1);"
						+ "</script>"
				);
	}
}


@Override
public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
	int ccq_num 
	= Integer.parseInt(request.getParameter("ccq_num"));
	int ccq_writer_num 
	= Integer.parseInt(request.getParameter("ccq_writer_num"));
	int ccq_board_num 
	= Integer.parseInt(request.getParameter("ccq_board_num"));
	
	qna_dao.deleteComment(ccq_num, ccq_writer_num);
	
	response.sendRedirect("boardRead.bo?cbq_num="+ccq_board_num);
	
}
}
