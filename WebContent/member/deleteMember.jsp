<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<section>
		<h1>회원 탈퇴</h1>
		<form id="delMemForm" action="deleteMemberSubmit" method="post">
			<input type="hidden" name="cm_num" value="${param.cm_num}"/>
			<table>
				<tbody>
					<tr>
						<td>비밀번호 : </td>
						<td>
							<input type="password" id="cm_pw" name="cm_pw"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><div id="wrongPw">비밀번호를 다시 확인해주세요!</div></td>
					</tr>
					<tr>
						<td colspan=2>
							<input id="btnSubmit" type="button" value="제출"/>
							<input type="button" onclick="history.go(-1);" value="취소"/>
						</td>
					</tr>
				</tbody>
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
					$("#delMemForm").submit();
				}else{
					$("#wrongPw").show();
				}
			}
		});
	});
</script>
</html>