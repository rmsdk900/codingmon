<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/comment.css" rel="stylesheet" type="text/css"/>
<br><br>
	<div class="commentWrap" style="width:60%; margin:auto">
		<h3>댓글 작성</h3>
		<br/>
		<div class="commentWrite">
		
			<textarea name="ccq_content" id="ccq_content" class="comment_content"></textarea>
			
		</div>
		<input  style=" margin: right" class="button is-primary is-pulled-right is-large " type="button" id="commentWriteBtn" value="등록"/>
	</div>
<br><br>
<!-- 댓글 목록 -->
<h3 id="ccq_title" style="width:60%; margin:auto">댓글 목록  0</h3>
<br>

<div  style="width:70%; margin:auto" id="comment">

</div>
<div id="pagingWrap" class="pagingWrap">

</div>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var page = 1;
	
	getList(page);
	
	$("#commentWriteBtn").click(function(){
		$.ajax({
			type : "POST",
			url : "commentWrite.co",
			data : {
				cbq_num : '${boardVO.cbq_num}',
				ccq_writer_num : 1, 
				ccq_writer_name :  '관리자',
				ccq_content : $("#ccq_content").val()
			},
			dataType: "json",
			success : function(data){
				
				getList(1);
				
			}
		});
	});
	
	
	
	function getList(pageNum){
		$.ajax({
			type : "GET",
			url : "list.co",
			data : {
				ccr_board_num : '${boardVO.cbq_num}',
				page : pageNum
			},
			dataType : "json",
			success : function(data){
				console.log(data);
				console.log(data.pagemaker);
				
				$("#ccq_title").html("댓글 목록["+data.pagemaker.totalCount+"]");
				$("#comment").append(data.toString());
				page = data.pagemaker.cri.page;
				printList(data.list);
				printPage(data.pagemaker);
			}
		});
	}
	
	function printList(list){
		console.log(list);
		var html = "";
		$.each(list,function(){
			html +="<div class='commentListWrap'>";
			if(this.ccq_delete == 'N'){
				if('${!empty member}' && '${member.cm_num}' == this.ccq_writer_num){
					// 삭제 버튼
					html +="<form>";
					html +="<div class='closeBtn'>";
					html +="<input type='button' data-num='"+this.ccq_num+"' value='X'/>";
					html +="</div>";
					html +="</form>";
				 } 
				html += "<div>";
				html += this.ccq_writer_name+"&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;"+getDate(this.ccq_regdate);
				html += "</div>";
				html += "<div>";
				html += "<textarea readonly>"+this.ccq_content+"</textarea>";
				html += "</div>";
				
			
			}
	
			html+="</div>";
		});
		
		$("#comment").html(html);
	}
	function getDate(date){
		
		var dt = new Date(date);
		
		var year = dt.getFullYear();
		var month = dt.getMonth()+1;
		var day = dt.getDate();
		
		if(month < 10) month = "0"+month;
		if(day < 10) day = "0"+day;
		
		var hour = dt.getHours();
		var minute = dt.getMinutes();
		var second = dt.getSeconds();
		
		return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
	
	function printPage(pm){
		console.log(pm);
		
		var html = "";
		
		if(pm.cri.page > 1){
			html += "<a  href='javascript:getList(1);'>[처음]</a>";
			if(pm.prev){
				html += "<a href='javascript:getList("+(pm.startPage-1)+");'>[이전]</a>";	
			}
		}
		
		for(var i= pm.startPage; i<=pm.endPage; i++){
			html +="<a href='javascript:getList("+i+")'>["+i+"]</a>";
		}
		
		if(pm.cri.page < pm.maxPage){
			if(pm.next){
				html += "<a  href='javascript:getList("+(pm.endPage+1)+");'>[다음]</a>";
			}
			html += "<a href='javascript:getList("+pm.maxPage+");'>[마지막]</a>";	
		}
		
		$("#pagingWrap").html(html);
		
	}
	
	$("#comment").on("click",".closeBtn input",function(){
		var comment_num = $(this).attr("data-num");
		console.log(comment_num);
		if(confirm(comment_num +" 댓글을 삭제하시겠습니까?")){
			deleteComment(comment_num);
		}
	});
	
	function deleteComment(comment_num){
		$.ajax({
			type : "POST",
			url : "commentDelete.co",
			data : {
				ccq_writer_num : '${member.cm_num}',
				ccq_num : comment_num
			},
			success : function(isSuccess){
				alert(isSuccess ? '삭제 성공' : '삭제 실패');
				getList(page);
			}
		});
	}
	
	
</script>










