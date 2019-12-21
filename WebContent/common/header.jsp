<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- google Login -->
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
<!--bulma -->
<!--bulma -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css">
<script src="../BulmaJS-0.10.0/dist/dropdown.js"></script>
<link rel="icon" href="../images/favicon.png">
<title>코딩몬</title>
</head>
<body>
	<nav class="navbar is-dark is-fluid " role="navigation"
		aria-label="main navigation">

		<div class="navbar-brand">
			<a class="navbar-item"
				href="http://localhost:8080/project_codingmon/user/home"> <img
				src="${pageContext.request.contextPath}/images/cover.png" alt="MAIN">

			</a> <a role="button" class="navbar-burger" aria-label="menu"
				aria-expanded="false"> <span aria-hidden="true"></span> <span
				aria-hidden="true"></span> <span aria-hidden="true"></span>
			</a> <a class="navbar-item"
				href="${pageContext.request.contextPath}/board/promotion/search">일
				구합니다</a> <a class="navbar-item"
				href="${pageContext.request.contextPath}/board/rec/search">일
				드립니다</a> <a class="navbar-item has-text-light"
				href="${pageContext.request.contextPath}/boardList.bo">묻고 답하기</a>



		</div>
		<div class="navbar-end is-fluid ">
			<!--로그인 회원가입  -->

			<div class="navbar-item">
				<div class="buttons">
					<c:choose>
						<c:when test="${!empty sessionScope.member}">
							<a class="navbar-item has-text-light"
								href="${pageContext.request.contextPath}/user/myInfo?cm_num=${sessionScope.member.cm_num}">내
								정보</a>
							<a class="navbar-item has-text-light"
								href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
						</c:when>
						<c:otherwise>
							<a class="navbar-item has-text-light"
								href="${pageContext.request.contextPath}/user/login"> Log in</a>
							<a class="navbar-item  has-text-primary"
								href="${pageContext.request.contextPath}/user/join">Sign up</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</nav>