<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="google-signin-client_id" content="368714236805-3qcuh1rav6q7qlpet3rsn3lbps7u3cuu.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript">
	function onSignIn(googleUser){
		var profile = googleUser.getBasicProfile();
		console.log("ID: "+profile.getId());
		console.log("Name: "+profile.getName());
		console.log("Image URL: "+profile.getImageUrl());
		console.log("Email: "+profile.getEmail());
	}
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function(){
			console.log('User signed out.');
		});
		auth2.disconnect();
	}
</script> -->
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>