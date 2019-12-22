<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp"%>

<section class="hero is-fullheight is-primary">
	<form id="changePwForm" action="changePw" method="post">
		<input type="hidden" name="cm_email" value="${requestScope.email}" />
		<div class="hero-body ">
			<!-- class="hero-body" -->
			<div class="container  has-text-centered">
				<!--  -->
				<div class="columns is-8 is-variable ">
					<div class="column is-two-thirds has-text-left">
						<h1 class="title is-1">비밀번호 변경</h1>

					</div>
					<div class="column is-one-third has-text-left ">
						<!-- 변경 구간 오른쪽 상단 글 및 hr 추가  -->
		                     <div class="column  has-text-left">
		                        <h2 class="subtitle is-1 ">비밀번호 입력</h2>
		                     </div>
		                     <hr>
		                  <!--////////////////  -->

						<div class="field">
							<label class="label">새 비밀번호</label> <input
								class="input is-medium" type="password" name="cm_pw"
								placeholder="새 비밀번호 " required />
							<div class="control"></div>
						</div>
						<!-- 비밀번호 -->
						<div class="field">
							<label class="label">새 비밀번호 확인</label> <input
								class="input is-medium " type="password" id="re_pw"
								placeholder="새 비밀번호  확인" required />
							<div class="control"></div>
						</div>
						<div class="control">
							<input class="button is-primary is-inverted is-outlined"
								type="button" onclick="javascript:checkPassword()" value="변경요청" />

						</div>

					</div>
				</div>
			</div>
		</div>
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