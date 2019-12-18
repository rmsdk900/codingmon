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
			비밀번호 변경
		</h1>
		<form id="updatePwForm" action="updatePwSubmit" method="post">
			<input type="hidden" name="cm_num" value="${param.cm_num}"/>
			<table>
				<tr>
					<td>현재 비밀번호</td>
					<td><input type="password" id="current_pw" required/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="wrong-current">현재 비밀번호가 일치하지 않습니다. </div>
					</td>
				</tr>
				<tr>
					<td>새 비밀번호</td>
					<td><input type="password" id="cm_pw" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td>새 비밀번호 확인</td>
					<td><input type="password" id="re_pw" required/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="alert-danger">비밀번호가 일치하지 않습니다.</div>
						<div id="alert-success">비밀번호가 일치합니다.</div>
						<div id="alert-null">비밀번호를 입력해주세요!</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="update" value="변경요청"/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	$(function(){
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("#alert-null").hide();
		$("#wrong-current").hide();
		
		$("#update").on("click", function(){
			var cm_pw = $("#cm_pw").val();
			var re_pw = $("#re_pw").val();
			if(cm_pw != "" || re_pw != ""){
				if(cm_pw == re_pw){
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#alert-null").hide();
					$.ajax({
						type: "POST",
						url: "${pageContext.request.contextPath}/checkPw.async",
						data: {
							cm_num: '${param.cm_num}',
							cur_pw: $("#current_pw").val()
						},
						dataType: "json",
						success: function(data){
							if(data){
								$("#updatePwForm").submit();
							}else{
								$("#wrong-current").show();
							}
						}
					});
				}else{
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#alert-null").hide();
				}
			}else{
				$("#alert-null").show();
			}
		});
		
		
	})
</script>
</html>