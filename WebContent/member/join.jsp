<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
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
					<td>
						<input type="email" id="cm_email" name="cm_email" placeholder="email형식" required/>
						<input type="button" id="checkId" value="아이디 중복 체크"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="id-use">아이디를 사용할 수 있습니다.</div>
						<div id="id-duplicated">중복된 아이디 혹은 탈퇴한 회원의 아이디입니다.</div>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="cm_pw" name="cm_pw" required/></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="re_pw" required/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="alert-danger">비밀번호가 일치하지 않습니다.</div>
						<div id="alert-success">비밀번호가 일치합니다.</div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="cm_name" required/></td>
				</tr>
				<tr>
					<td>휴대폰 번호</td>
					<td>
						<input type="number" class="inputPhone" name="cm_phone_first" maxlength="3"/> -
						<input type="number" class="inputPhone" name="cm_phone_middle" maxlength="4"/> -
						<input type="number" class="inputPhone" name="cm_phone_last" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="inputAddr" name="cm_addr" required/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" id="submit" value="회원가입"/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	$(function(){
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("#id-use").hide();
		$("#id-duplicated").hide();
		$("#submit").attr("disabled", true);
		
		$("input").keyup(function(){
			var cm_pw = $("#cm_pw").val();
			var re_pw = $("#re_pw").val();
			if(cm_pw != "" || re_pw != ""){
				if(cm_pw == re_pw){
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#submit").removeAttr("disabled");
				}else {
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#submit").attr("disabled", true);
				}
				
			}
		});
		document.getElementById("checkId").addEventListener("click", function(){
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/checkId.async",
				data: {
					cm_email: $("#cm_email").val()
				},
				dataType: "json",
				success: function(data){
					if(data){
						$("#id-use").hide();
						$("#id-duplicated").show();
					}else{
						$("#id-use").show();
						$("#id-duplicated").hide();
						$("#submit").removeAttr("disabled");
					}
				},
				error: function(request,status,error){
					console.log("AJAX 실패");
				}
			});
		});
		var count = 2;
		$(".inputPhone").keyup(function(){
				
			var charLimit = $(this).attr("maxlength");
			if(this.value.length >= charLimit){
				console.log(this.value.length);
				console.log("next : "+$(this).next());
				console.log(count);
				if(count != 0){
					$(this).next().focus();
					count--;
					
				}else{
					$("#inputAddr").focus();
				}
				
				return false;
			}
		});
		
		
	});
	
</script>
</html>
<!-- 회원가입 -->



