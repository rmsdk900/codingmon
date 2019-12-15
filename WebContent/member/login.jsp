<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<form action="login" method="post">
			<table>
				<tr>
					<th colspan="2">로그인</th>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="cm_email" required/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="cm_pw" required/></td>
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
						<input type="submit" value="로그인"/>
						<input type="button" onclick="location.href='findPw';" value="비밀번호 찾기"/>
					</td>
				</tr>
			</table>
		</form>
		
	</section>
</body>
</html>