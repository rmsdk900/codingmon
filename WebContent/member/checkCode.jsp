<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코딩몬 - 비밀번호 코드 입력</title>
</head>
<body>
	<form action="checkPwCode" method="post">
		<input type="hidden" name="cpc_email" value="${param.cm_email}"/>
		<table>
			<tr>
				<td>코드 입력 : ${param.cm_email}</td>
				<td>
					<input type="text" name="cpc_code"/> 
					<a href="findPw">재전송</a>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력"/></td>
			</tr>
		</table>
	</form>
</body>
</html>