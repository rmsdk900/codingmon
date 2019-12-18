<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<h1>
			비밀번호 확인
		</h1>
		<form id="infoForm" action="infoUpdate" method="post">
			<input type="hidden" id="cm_num" name="cm_num" value="${param.cm_num}"/>
			<table>
				<tr>
					<td></td>
					<td><div id="wrongPw">비밀번호를 다시 확인해주세요!</div></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${sessionScope.member.cm_email}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="cm_pw" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="btnSubmit" type="button" value="제출"/>
						<input type="button" onclick="history.go(-1);" value="취소"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	$(function(){
		$("#wrongPw").hide();
	});
	$("#btnSubmit").on('click', function(){
		$.ajax({
			type: 'POST',
			url: "${pageContext.request.contextPath}/checkPw.async",
			data: {
				cur_pw: $("#cm_pw").val(),
				cm_num: '${param.cm_num}'
			},
			dataType: "json",
			success: function(data){
				if(data){
					
					$("#infoForm").submit();
				}else{
					$("#wrongPw").show();
				}
			}
		});
	});
</script>
</html>