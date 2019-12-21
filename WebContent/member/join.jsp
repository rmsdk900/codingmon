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
		<form action="join" id="joinForm" method="post">
			<table>
				<tr>
					<th colspan="2">회원가입</th>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" id="cm_email" name="cm_email" placeholder="email형식" />
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
						<div id="alert-six">비밀번호는  영문, 숫자 혼합 6자리 이상이어야 합니다.</div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" id="cm_name" name="cm_name" required/></td>
				</tr>
				<tr>
					<td>휴대폰 번호</td>
					<td>
						<input type="number" id="cm_phone_first" class="inputPhone" name="cm_phone_first" maxlength="3"/> -
						<input type="number" class="inputPhone" name="cm_phone_middle" maxlength="4"/> -
						<input type="number" class="inputPhone" name="cm_phone_last" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="inputAddr" name="cm_addr" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="btnSubmit" value="회원가입" onclick="javascript:GoToSignUP()"/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	$(function(){
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("#alert-six").hide();
		$("#id-use").hide();
		$("#id-duplicated").hide();
		$("#btnSubmit").attr("disabled", true);
		
		$("input").keyup(function(){
			var cm_pw = $("#cm_pw").val();
			var re_pw = $("#re_pw").val();
			if(cm_pw != "" || re_pw != ""){
				if(cm_pw == re_pw){
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#alert-six").hide();
					$("#btnSubmit").removeAttr("disabled");
				}else {
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#alert-six").hide();
					$("#btnSubmit").attr("disabled", true);
				}
				
			}
		});
		document.getElementById("checkId").addEventListener("click", function(){
			var inputEmail = document.getElementById("cm_email");
			if(!inputEmail.value){
				alert("이메일을 입력하세요!");
				inputEmail.focus();
				return;
			}else{
				if(!checkEmail(inputEmail.value)){
					alert("이메일 형식이 잘못되었습니다.");
					inputEmail.focus();
					return;
				}else{
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
								$("#checkId").attr("disabled", true);
							}
						},
						error: function(request,status,error){
							console.log("AJAX 실패");
						}
					});
				}
			}
			
		});
		function checkEmail(str){
			var reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			if (!reg_email.test(str)){
				return false;
			}else {
				return true;
			}
		}
		
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
	function GoToSignUP(){
		var inputPw = document.getElementById("cm_pw");
		if(!checkPw(inputPw.value)){
			$("#alert-success").hide();
			$("#alert-six").show();
			inputPw.focus();
			return;
		}else{
			$("#alert-six").hide();
			if(document.getElementById("cm_name").value==""){
				alert("이름을 입력해주세요!");
				document.getElementById("cm_name").focus();
				return;
			}else if(document.getElementById("cm_phone_first").value==null 
					|| document.getElementById("cm_phone_first").value==""){
				alert("휴대폰 번호를 입력해주세요!");
				document.getElementById("cm_phone_first").focus();
				return;
			}else if(document.getElementById("inputAddr").value==null || document.getElementById("inputAddr").value=="" ){
				alert("주소를 입력해주세요!");
				$("#inputAddr").focus();
				return;
			}else{
				$("#joinForm").submit();
			}
		}
		
	}
	function checkPw(str){
		var reg_pw = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
		if (!reg_pw.test(str)){
			return false;
		}else {
			return true;
		}
	}
</script>
</html>
<!-- 회원가입 -->



