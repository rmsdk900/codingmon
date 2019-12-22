<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<section class="hero is-fullheight is-primary">
	<form action="login" id="loginForm" method="post">
		<div class="hero-body">
			<div class="container has-text-centered">
				<div class="columns is-8 is-variable ">
					<div class="column is-two-thirds has-text-left">
						<h1 class="title is-1">로그인</h1>
					</div>
					<div class="column is-one-third has-text-left ">
						<!--  -->
						<article class="message" id="login-danger">
							<label class="has-text-danger">비밀번호가 일치하지 않습니다.</label>
						</article>
						<div class="field">
							<label class="label">Email</label>

							<div class="control">
								<input class="input is-medium" type="email" id="cm_email"
									name="cm_email" placeholder="email" />
							</div>
						</div>
						<!-- 비밀번호 -->
						<div class="field">
							<label class="label">비밀번호</label>

							<div class="control">
								<input class="input is-medium " type="password" id="cm_pw"
									name="cm_pw" placeholder="비밀번호 " required />
							</div>
						</div>
						<div class="field">
							<div class="control">
								<label class="checkbox"> <input type="checkbox"
									name="cm_auto" /> 로그인 정보 저장
								</label>
							</div>
						</div>
						<div>
							<input
								class="button is-primary is-fullwidth has-text-weight-medium is-medium is-inverted is-outlined"
								type="button" id="loginAsync" value="로그인" /> <input
								class="button is-primary is-fullwidth has-text-weight-medium is-medium is-inverted is-outlined"
								type="button" onclick="location.href='findPw';" value="비밀번호 찾기" />

						</div>

					</div>
				</div>
			</div>
			
		</div>

	</form>
</section>
</body>
<script>
	$(function() {
		$("#login-danger").hide();
		$("#loginAsync").click(function() {
			var cm_email = $("#cm_email").val();
			var cm_pw = $("#cm_pw").val();
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/login.async",
				data : {
					cm_email : cm_email,
					cm_pw : cm_pw
				},
				dataType : "json",
				success : function(data) {
					console.log(data);
					if (data) {
						$("#loginForm").submit();
					} else {
						$("#login-danger").show();
					}
				}
			});

		});
	})
</script>
</html>