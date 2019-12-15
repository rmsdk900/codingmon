f<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<h1>
			비밀번호 변경
		</h1>
		<form id="changePwForm" action="changePw" method="post">
			<input type="hidden" name="cm_email" value="${requestScope.email}"/>
			<table>
				<tr>
					<td>새 비밀번호</td>
					<td><input type="password" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td>새 비밀번호 확인</td>
					<td><input type="password" id="re_pw" required/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" onclick="javascript:checkPassword()" value="변경요청"/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	function checkPassword(){
		var form = document.getElementById('changePwForm');
		var pw = form.cm_pw;
		var re_pw = document.getElementById('re_pw');
		if(pw.value != re_pw.value){
			alert('비밀번호가 일치하지 않습니다.');
			re_pw.value="";
			re_pw.focus();
		}else{
			form.submit();
		}
	}
</script>
</html>