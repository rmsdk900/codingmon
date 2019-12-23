<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--    헤더 영역 -->
<%@ include file="../common/header.jsp"%>
<style type="text/css">
.in {
	border-right: #888888 1px solid;
	border-left: #888888 1px solid;
	border-top: #888888 1px solid;
	border-bottom: #888888 1px solid;
}
</style>


<section class="hero is-primary is-fullheight-with-navbar">
	<form id="joinForm" action="join" method="post">
		<div class="hero-body">
			<!-- class="hero-body" -->
			<div class="container has-text-centered">
				<!--  -->
				<div class="columns is-8 is-variable ">
					<!-- 왼쪽 상단  -->
					<div class="column is-two-thirds has-text-left">
						<h1 class="title is-1">회원 가입</h1>
						<p class="is-size-4">빈칸을 채워주세요</p>
					</div>
					<div class="column is-one-third has-text-left">
						<!-- 오른쪽 회원가입  -->
						<div class="field">
							<!-- 아이디 -->
							<label class="label">Email</label>
							<div class="control has-background-primary">
								<input class="input is-medium " type="email" id="cm_email"
									name="cm_email" placeholder="email" />
							</div>
							<br>
							<div class="control">
								<input
									class="button is-primary is-fullwidth has-text-weight-medium is-medium is-inverted is-outlined"
									type="button" id="checkId" value="아이디 중복 체크" />
							</div>
							<article class="message">
								<div class="">

									<div id="id-use">
										<label class="has-text-primary">아이디를 사용할 수 있습니다.</label>
									</div>
									<div id="id-duplicated">
										<label class="has-text-danger">중복된 아이디가 존재합니다.</label>
									</div>
								</div>
							</article>

						</div>
						<!-- 비밀번호 -->
						<div class="field">
							<label class="label">비밀번호</label>

							<div class="control">
								<input class="input is-medium  " type="password" id="cm_pw"
									name="cm_pw" required />
							</div>
						</div>

						<!-- 비밀번호확인 -->
						<div class="control">
							<label class="label">비밀번호 확인</label> <input
								class="input is-medium" type="password" id="re_pw" required />

						</div>


						<article class="message">
							<div class="">
								<div id="alert-danger">
									<label class="has-text-danger">비밀번호가 일치하지 않습니다.</label>
								</div>
								<div id="alert-success">
									<label class="has-text-primary">비밀번호가 일치합니다.</label>
								</div>
								<div id="alert-six">
									<label class="has-text-danger">비밀번호가 일치합니다.</label>
								</div>
							</div>
						</article>
						<!-- 이름 -->
						<div class="field">
							<label class="label">이름</label>
							<div class="control">

								<input class="input is-medium " type="text" name="cm_name" id="cm_name"/>


							</div>

							<div class="field">
								<label class="label">휴대폰 번호</label> <input id="cm_phone_first" class="input inputPhone"
									style="width: 125px;" type="number"
									name="cm_phone_first" maxlength="3" /> <input class="input inputPhone"
									style="width: 125px;" type="number" 
									name="cm_phone_middle" maxlength="4" /> <input
									class="input is-on-fifth inputPhone" style="width: 125px;" type="number"
									name="cm_phone_last" maxlength="4" />
							</div>

							<label class="label">주소</label>

							<div class="control">
								<input class="input is-medium" type="text" id="inputAddr"
									name="cm_addr" />
							</div>



							<div class="control">
								<br> <input
									class="button is-primary is-fullwidth has-text-weight-medium is-medium is-inverted is-outlined"
									type="button" id="btnSubmit" value="회원가입"
									onclick="javascript:GoToSignUP()" />

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</section>
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
	<%@ include file="../common/footer.jsp"%>