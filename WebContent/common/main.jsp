<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="header.jsp" %>
	<c:choose>
		<c:when test="${!empty sessionScope.member}">
			<a href="myInfo?cm_num=${sessionScope.member.cm_num}">내 정보</a>
			<a href="logout">로그아웃</a>
			
		</c:when>
		<c:otherwise>
			<a href="join">회원가입</a>
			<a href="login">로그인</a>
			<!-- <div class="g-signin2" data-onsuccess="onSignIn"></div>
			<a href="#" onclick="signOut();">구글 로그아웃</a> -->
		</c:otherwise>
	</c:choose>
	<a href="${pageContext.request.contextPath}/board/promotion/search">인재들</a>
	<a href="${pageContext.request.contextPath}/board/rec/search">recruit</a>
</body>
</html>