<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<c:set var="phone" value="${requestScope.prospects.cm_phone}"/>
<c:set var="phoneFirst" value="${fn:substring(phone, 0, 3)}"/>
<c:set var="phoneMiddle" value="${fn:substring(phone, 3, 7)}"/>
<c:set var="phoneLast" value="${fn:substring(phone, 7, 11)}"/>

<c:set var="birth" value="${requestScope.info.cmi_age}"/>
<c:set var="birthYear" value="${fn:substring(birth, 0, 4)}"/>
<c:set var="birthETC" value="${fn:substring(birth,4,8)}"/>

<jsp:useBean id="now" class="java.util.Date"/>
<f:formatDate value="${now}" pattern="yyyyMMdd" var="nows"/>
<c:set var="nowsYear" value="${fn:substring(nows, 0, 4)}"/>
<c:set var="nowsETC" value="${fn:substring(nows, 4, 8)}"/>

<c:set var="age" value="${nowsYear-birthYear}"/>
<c:if test="${birthETC > nowsETC}">
	<c:set var="age" value="${nowsYear-birthYear-1}"/>
</c:if>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<c:choose>
			<c:when test="${!empty requestScope.prospects 
			&& !empty requestScope.info 
			&& !empty requestScope.mj 
			&& !empty requestScope.mr
			&& !empty requestScope.ms
			}" >
				<h1>${requestScope.info.cmi_title}</h1>
				<table border=1>
					<tbody>
						<tr>
							<th>이름 </th>
							<td>${requestScope.prospects.cm_name}</td>
						</tr>
						<tr>
							<th>이메일 </th>
							<td>${requestScope.prospects.cm_email}</td>
						</tr>
						<tr>
							<th>전화번호 </th>
							<td>${phoneFirst}-${phoneMiddle}-${phoneLast}</td>
						</tr>
						<tr>
							<th>나이(만) / 성별 </th>
							<td>${age} 세 / ${requestScope.info.cmi_gender eq 'Male' ? '남성' : '여성'}</td>
						</tr>
						<tr>
							<th>직업 </th>
							<td>
								<c:forEach var="i" items="${requestScope.mj}" varStatus="status">
									${i.value}<c:if test="${!status.last}">, </c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>활동 가능 지역 </th>
							<td>
								<c:forEach var="i" items="${requestScope.mr}" varStatus="status">
									${i.value}<c:if test="${!status.last}">, </c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>업무 가능한 언어 </th>
							<td>
								<c:forEach var="i" items="${requestScope.ms}" varStatus="status">
									<c:set var="category" value="${fn:substring(i.key, 0, 1)}"/>
									<c:if test="${category eq '주'}">
										${i.value}<c:if test="${!status.last}"> / </c:if>
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>학습 중인 언어 </th>
							<td>
								<c:forEach var="i" items="${requestScope.ms}" varStatus="status">
									<c:set var="category" value="${fn:substring(i.key, 0, 1)}"/>
									<c:if test="${category eq '공'}">
										${i.value}<c:if test="${!status.last}"> / </c:if>
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>경력 사항</th>
							<td>
								<textarea cols=50 rows=10>${requestScope.info.cmi_career}</textarea>
							</td>
						</tr>
						<tr>
							<th>자기 소개</th>
							<td>
								<textarea cols=50 rows=10>${requestScope.info.cmi_intro}</textarea>
							</td>
						</tr>
						
						<tr>
							<td colspan=2>
								<c:if test="${sessionScope.member.cm_num eq requestScope.prospects.cm_num}">
									<a href="${pageContext.request.contextPath}/user/updateInfo?cm_num=${requestScope.prospects.cm_num}">이력서 수정</a> |
								</c:if>
								<a href="list">목록</a>
							</td>
						</tr>
						
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				받은 정보 없음.
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>