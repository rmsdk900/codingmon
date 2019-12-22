<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="header.jsp" %>
<section class="hero is-primary is-fullheight-with-navbar">

<!-- 메인영역 -->
<div class="hero-body is-primary">
		<div class="container has-text-centered is-bold ">

			<section class="section">
				<div class="container">
					<h2 class="title has-text-centered"></h2>
					<div class="columns is-vcentered">
						<div class="column">
							<h1 class="title is-spaced">codingmon</h1>
							<p class="subtitle">코딩을 배우고 싶은사람 코딩실력을 뽐내고 싶은사람 코딩인재를 구하고싶은사람 요기요기 모여라</p>
							<c:choose>
								<c:when test="${!empty sessionScope.member}">
									<a class="button is-primary is-inverted is-outlined " href="${pageContext.request.contextPath}/boardList.bo">
									<label>함께하기</label>
									</a>
								</c:when>
								<c:otherwise>
									<a class="button is-primary is-inverted is-outlined " href="${pageContext.request.contextPath}/user/login">
									<label>함께하기</label>
									</a>
								</c:otherwise>
							</c:choose>
							
						</div>
						<div class="column">
							<img
								src="https://bulma.dev/placeholder/pictures/bg_16-9.svg?primary=00d1b2"
								alt="">
						</div>
					</div>
					<hr>
					<div class="level">
						<div class="level-item">
							<a class="button is-primary is-inverted is-outlined " href="${pageContext.request.contextPath}/board/promotion/search"><label>일 구합니다</label></a>
						</div>
						<div class="level-item">
							<a class="button is-primary is-inverted is-outlined " href="${pageContext.request.contextPath}/board/rec/search"><label>일 드립니다</label></a>
						</div>
						<div class="level-item">
							<a class="button is-primary is-inverted is-outlined " href="${pageContext.request.contextPath}/boardList.bo"><label>질문과 답변 </label></a>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</section>

<%@ include file="footer.jsp" %>
