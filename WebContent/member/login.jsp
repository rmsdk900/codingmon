<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<meta charset="UTF-8">

<title>코딩몬</title>
</head>
<body>
	<section>
		<form action="login" id="loginForm" method="post">
			<table>
				<tr>
					<th colspan="2">로그인</th>
				</tr>
				<tr>
					<td id="login-danger" colspan="2">아이디와 비밀번호를 확인해주세요!</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" id="cm_email" name="cm_email" required/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="cm_pw" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td colspan="2">
						<label>
							<input type="checkbox" name="cm_auto"/>
							로그인 정보 저장
						</label>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="button" id="loginAsync" value="로그인"/>
						<input type="button" onclick="location.href='findPw';" value="비밀번호 찾기"/>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	$(function(){
		$("#login-danger").hide();
		$("#loginAsync").click(function(){
			var cm_email = $("#cm_email").val();
			var cm_pw = $("#cm_pw").val();
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/login.async",
				data: {
					cm_email: cm_email,
					cm_pw: cm_pw
				},
				dataType: "json",
				success: function(data){
					console.log(data);
					if(data){
						$("#loginForm").submit();
					}else{
						$("#login-danger").show();
					}
				}
			});
			
		});
	})
</script>
</html>