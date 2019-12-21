<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/comment.css" rel="stylesheet" type="text/css" />

<c:if test="${!empty member}">
	<div class="commentWrap">
		<h3>댓글 작성</h3>
		<br/>
		<div class="commentWrite">
			<textarea class="ccr_content" id="ccr_content" name="ccr_content" placeholder="댓글을 입력 해주세요."></textarea>
			<input type="button" id="commentWriteBtn" value="등록" />
		</div>
	</div>
</c:if>
<br/>

<h3 id="ccr_count">댓글 목록[0]</h3>
<div id="comment">

</div>
<div id="pagingWrap" class="pagingWrap">

</div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var page = 1;
	
	$(document).ready(function(){
		getList(page);
	});
	
	$("#commentWriteBtn").click(function(){
		$.ajax({
			type : "POST",
			url : "commentWrite.ccr",
			data : {
				ccr_board_num : '${recruitVO.cbr_num}',
				ccr_writer_num : '${sessionScope.member.cm_num}',
				ccr_writer_name : '${sessionScope.member.cm_name}',
				ccr_content : $("#ccr_content").val()
			},
			dataType : "json",
			success : function(data){
				
				$("#ccr_content").val("");
				getList(1);
			}
		});
	});
	
	function getList(pageNum){
		$.ajax({
			type : "GET",
			url : "commentList.ccr",
			data : {
				ccr_board_num : '${recruitVO.cbr_num}',
				page : pageNum
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				console.log(data.pm);
				
				$("#ccr_count").html("댓글 목록["+data.pm.totalCount+"]");
				$("#comment").append(data.toString());
				page = data.pm.cri.page;
				printList(data.list);
				printPage(data.pm);
			}
		});
	}
	
	function printList(list){
		console.log(list);
		
		var html = "";
		if(list.length < 1){
			html = "등록된 댓글이 없습니다.";
		}else{
			$.each(list,function(){
				html += "<div class='commentListWrap'>";
				if(this.ccr_delete == 'N'){
					if('${!empty member}' && '${member.cm_num}' == this.ccr_writer_num){ 
						// 삭제 버튼 활성화
						html += "<form>";
						html += "<div class='closeBtn'>";
						html += "<input type='button' data-num='"+this.ccr_num+"' value='X' />";
						html += "</div>";
						html += "</form>";
					}
					html += "<div>";
					html += "<a href='${pageContext.request.contextPath}/board/promotion/detail?cm_num=${member.cm_num}'>"+this.ccr_writer_name+"</a>"+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"+getDate(this.ccr_regdate);
					html += "</div>";
					html += "<div>";
					html += "<textarea readonly>"+this.ccr_content+"</textarea>";
					html += "</div>";
				}else{
					// 삭제된 댓글
					html += "<div>";
					html += "삭제된 게시물 입니다.";
					html += "</div>";
				}
				html += "</div>";
			});
		}
		$("#comment").html(html);
	}
	
	function getDate(date){
		var dt = new Date(date);
		var year = dt.getFullYear();
		var month = dt.getMonth()+1;
		var day = dt.getDate();
		
		if(month < 10) month ="0"+month;
		if(day < 10) day ="0"+day;
		
		var hour = dt.getHours();
		var minute = dt.getMinutes();
		var second = dt.getSeconds();
		
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
	
	function printPage(pm){
		console.log(pm);
		
		var html = "";
		
		if(pm.cri.page > 1){
			html += "<a href='javascript:getList(1);'>[처음]</a>";
			if(pm.prev){
				html += "<a href='javascript:getList("+(pm.startPage-1)+");'>[이전]</a>";
			}
		}
		
		for(var i=pm.startPage; i<=pm.endPage; i++){
			html += "<a href='javascript:getList("+i+");'>["+i+"]</a>";
		}
		
		if(pm.cri.page < pm.maxPage){
			if(pm.next){
				html += "<a href='javascript:getList("+(pm.endPage+1)+");'>[다음]</a>"
			}
			html += "<a href='javascript:getList("+pm.maxPage+");'>[마지막]</a>";
		}
		$("#pagingWrap").html(html);
	}
	
	$("#comment").on("click",".closeBtn input",function(){
		var ccr_num = $(this).attr("data-num");
		console.log(ccr_num);
		if(confirm(ccr_num + "댓글을 삭제하시겠습니까?")){
			deleteComment(ccr_num);
		}
	});
	
	function deleteComment(ccr_num){
		$.ajax({
			type : "POST",
			url : "commentDelete.ccr",
			data : {
				ccr_writer_num : '${member.cm_num}',
				ccr_num : ccr_num
			},
			success : function(isSuccess){
				
				getList(page);
			}
		});
	}
</script>
