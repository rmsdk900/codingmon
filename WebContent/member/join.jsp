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
		<form action="join" method="post">
			<table>
				<tr>
					<th colspan="2">회원가입</th>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="cm_email" placeholder="email형식" required/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="cm_name" required/></td>
				</tr>
				<tr>
					<td>휴대폰 번호</td>
					<td><input type="text" name="cm_phone"  required/></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="cm_addr" required/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="회원가입"/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>
<!-- 회원가입 -->



