<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/common.css" rel="stylesheet" type="text/css"/>
<link href="css/header.css" rel="stylesheet" type="text/css"/>
<link href="css/footer.css" rel="stylesheet" type="text/css"/>
<link href="css/list.css" rel="stylesheet" type="text/css"/>
</head>
<body>
${cookie.u_id.value}
<header>
	<div>
	<ul>
		<li>
			<a href="test">HOME</a>
		</li>
		<c:choose>
			<c:when test="${!empty sessionScope.member}">
				<li>
			<a href="info.mr">${member.name}님 반갑습니다.</a>
		</li>
		<c:if test="${sessionScope.member.id eq 'admin'}">
			<li>
				<a href="management.mr">회원관리</a>
			</li>
		</c:if>
		<li>
			<a href="logOut.mr">로그아웃</a>
		</li>
		<li>
			<a href="googleMailTest">Google SMTP TEST</a>
		</li>
		<li>
			<a href="naverMailTest">Naver SMTP TEST</a>
		</li>
			</c:when>
			<c:otherwise>
				<li>
			<a href="login.mr">로그인</a>
		</li>
		<li>
			<a href="join.mr">회원가입</a>
		</li>
		<li>
			<a href="googleMailTest">Google SMTP TEST</a>
		</li>
		<li>
			<a href="naverMailTest">Naver SMTP TEST</a>
		</li>
			</c:otherwise>
		</c:choose>
	</ul>
	</div>
	<div>
		<ul>
			<li><a href="noticeSearch.do">공지사항</a></li>
			<li><a href="boardList.bo">질문과 답변</a></li>
			<li><a href="upload.test">파일 업로드 테스트</a></li>
		</ul>
	</div>
	
</header>	
