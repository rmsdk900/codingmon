<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--    헤더 영역 -->
<%@ include file="../common/header.jsp"%>
<section class="hero is-primary is-fullheight-with-navbar">
	<form id="delMemForm" action="deleteMemberSubmit" method="post">
		<input type="hidden" name="cm_num" value="${param.cm_num}" />
		<div class="hero-body ">
			<!-- class="hero-body" -->
			<div class="container  has-text-centered">
				<!--  -->
				<div class="columns is-8 is-variable ">
					<div class="column is-two-thirds has-text-left">
						<h1 class="title is-1">회원 탈퇴</h1>

					</div>
					<div class="column is-one-third has-text-left ">
						<!--  -->
						<div class="field">
							<div class="column  has-text-left">
								<h2 class="subtitle is-1 ">비밀번호 확인</h2>
								<hr>


								<input style="border-bottom: 1px solid white;"
									class="input is-medium" type="password" id="cm_pw" name="cm_pw"
									required /> <br>

								<article class="message">

									<div id="wrongPw">
										<label class="has-text-danger">올바른 비밀번호를 입력해주세요.</label>
									</div>

								</article>
								<div class="control">
									<br> <input
										class="button is-primary is-inverted is-outlined"
										id="btnSubmit" type="button" value="제출" /> <input
										class="button is-primary is-inverted is-outlined"
										type="button" onclick="history.go(-1);" value="취소" />

								</div>
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
<%@ include file="../common/footer.jsp"%>

