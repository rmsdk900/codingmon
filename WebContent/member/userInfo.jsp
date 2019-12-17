<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="name" value="${sessionScope.member.cm_name}"/>
<c:set var="totalLength" value="${fn:length(name) }" />
<c:set var="first"      value="${fn:substring(name, 0, 1)}" />
<c:set var="last"      value="${fn:substring(name, 2, totalLength) }" />

<c:set var="phone" value="${sessionScope.member.cm_phone}"/>
<c:set var="phoneFirst"      value="${fn:substring(phone, 0, 3)}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
</head>
<body>
<table>
<tbody>
<c:choose>
	<c:when test="${!empty sessionScope.member}">
		<tr>
			<th>이메일 : </th>
			<td>${sessionScope.member.cm_email}</td>
		</tr>
		<tr>
			<th>이름 : </th>
			<td><c:if test="${!empty  name}"><c:out value="${first}*${last}"/></c:if></td>
		</tr>
		<tr>
			<th>휴대폰 번호 : </th>
			<td><c:if test="${!empty  phone}"><c:out value="${phoneFirst}-****-****"/></c:if></td>
		</tr>
		<tr>
			<th>주소 : </th>
			<td>${sessionScope.member.cm_addr}</td>
		</tr>
	</c:when>
	<c:otherwise>
		정보 없음.
	</c:otherwise>
</c:choose>
	<tr>
		<td colspan=2>
			<input type="button" onclick="javascript:updateInfo()" value="수정" />
		</td>
	</tr>
</tbody>
</table>
</body>
<script>
	function updateInfo(){
		location.href='updateInfo?cm_num=${sessionScope.member.cm_num}';
	}
</script>
</html>