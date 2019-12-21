<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="../../css/comment.css" rel="stylesheet" type="text/css"/>
<%@ include file="../../../common/header.jsp"%>/>
<section>
    <div class="container is-fluid">
                

<div style="width:60%; margin:auto">
<br><br>
  
  <table class="table is-hoverable is-fullwidth">
		<tr>
			<td style="width:10%;">작성자</td>
			<td>${boardVO.cbq_writer_name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardVO.cbq_title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea  style='border: 0px solid white; resize:none;  '  readonly cols=50 rows=10>${boardVO.cbq_content}</textarea></td>
		</tr>
		<c:if test="${!empty boardVO.cbq_file}">
			<tr>
				<td>첨부파일</td>
				<td>
					<a class="button is-light" href="file_down.bo?file_name=${boardVO.cbq_file}">
						${boardVO.cbq_file_origin}
					</a>
				</td>
			</tr>	
		</c:if>
		<tr >
			<td colspan=2 >
						<a class="button is-light is-left" href="boardReplyForm.bo?cbq_num=${boardVO.cbq_num}">답글</a>
						<a  class="button is-light" href="boardUpdateForm.bo?cbq_num=${boardVO.cbq_num}">수정</a>
						<a class="button is-light" href="javascript:deleteBoard();">삭제</a>
						<a class="button is-light" href="boardList.bo">목록</a>
			</td>
		</tr>
	</table>
	</div>
	</div>
</section>




<script>
	function deleteBoard(){
		if(confirm("게시물을 삭제하시겠습니까? 첨부된 파일도 삭제됩니다.")){
			
			
			location.href='boardDelete.bo?cbq_num=${boardVO.cbq_num}';
		}
	}
</script>
<jsp:include page="../qna_comment/commentAsync.jsp"/>
</body>
</html>
 




