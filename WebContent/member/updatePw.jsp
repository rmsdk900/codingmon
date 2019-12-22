<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--    헤더 영역 -->
<%@ include file="../common/header.jsp"%>

<section class="hero is-fullheight is-primary">
	<form id="updatePwForm" action="updatePwSubmit" method="post">
		<input type="hidden" name="cm_num" value="${param.cm_num}" />
		<div class="hero-body ">
			<!-- class="hero-body" -->
			<div class="container  has-text-centered">
				<!--  -->
				<div class="columns is-8 is-variable ">
					<div class="column is-two-thirds has-text-left">
						<h1 class="title is-1">비밀번호 변경</h1>

					</div>
					<div class="column is-one-third has-text-left ">

						<div class="field">
							<label class="label">현재 비밀번호</label>

							<div class="control">
								<input class="input is-medium" type="password" id="current_pw"
									required />
							</div>
							<article class="message">
								<div id="wrong-current">
									<label class="has-text-danger">현재 비밀번호가 일치하지 않습니다.</label>
								</div>
							</article>
							<!-- 비밀번호확인 -->
							<label class="label">새 비밀번호</label>
							<div class="control">

								<input class="input is-medium" type="password" id="cm_pw"
									name="cm_pw" required />

							</div>
							<label class="label">새비밀번호 확인</label>
							<div class="control">

								<input class="input is-medium" type="password" id="re_pw"
									required />
							</div>


							<article class="message">
								<div>
									<div id="alert-danger">
										<label class="has-text-danger">비밀번호가 일치하지 않습니다.</label>
									</div>
									<div id="alert-success">
										<label class="has-text-primary">비밀번호가 일치합니다.</label>
									</div>
									<div id="alert-null">
										<label class="has-text-danger">비밀번호를 입력해주세요!</label>
									</div>
								</div>
							</article>

						</div>
						<div class="control">
							<input
								class="button is-primary is-fullwidth has-text-weight-medium is-medium is-inverted is-outlined"
								type="button" id="update" value="변경요청" />
						</div>
					</div>
				</div>
			</div>
		</div>
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