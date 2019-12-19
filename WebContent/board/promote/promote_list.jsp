<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트</title>
</head>
<body>
	<section>
		<div>
			<h1>인재 게시판</h1>
			<form action="search" method="get">
				<table border=1>
					<tbody>
						<tr>
							<th>회원명</th>
							<td><input type="text" name="searchName" /></td>
						</tr>
						<tr>
							<th>성별</th>
							<td><select name="searchGender">
									<option value="" disabled selected>성별</option>
									<option value="Male">남성</option>
									<option value="Female">여성</option>
							</select></td>
						</tr>
						<tr>
							<th>나이(만)</th>
							<td><c:forEach var="i" begin="10" end="70" step="10">
									<input type="checkbox" name="searchAge" value="${i}" />
								${i}대
							</c:forEach></td>
						</tr>
						<tr>
							<th>활동 가능 지역</th>
							<td><c:forEach var="i" items="${requestScope.er}">
									<input type="checkbox" name="searchRegion" value="${i.cr_code}" />
								${i.cr_name}
							</c:forEach></td>
						</tr>
						<tr>
							<th>직업</th>
							<td><c:forEach var="i" items="${requestScope.ej}">
									<input type="checkbox" name="searchJob" value="${i.cj_code}" />
								${i.cj_name}
							</c:forEach></td>
						</tr>
						<tr>
							<th>자신있는 언어</th>
							<td>
							<div>
								<h3>Java</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 1}">
										<input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>C</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 2}">
										<input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>Javascript</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 3}">
										<input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>Python</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 4}">
										<input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>ETC</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 5}">
										<input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							</td>
						</tr>
						<tr>
							<th>학습하고 싶은 언어</th>
							<td>
							<div>
								<h3>Java</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 1}">
										<input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>C</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 2}">
										<input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>Javascript</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 3}">
										<input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>Python</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 4}">
										<input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							<div>
								<h3>ETC</h3>
								<c:forEach var="i" items="${requestScope.es}">
									<c:if test="${i.cs_category eq 5}">
										<input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
										${i.cs_name}
									</c:if>
								</c:forEach>
							</div>
							</td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" value="검색" /> <input
								type="reset" value="초기화" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<table border=1>
				<tr>
					<th>회원번호</th>
					<th>회원명</th>
					<th>이력서 제목</th>
					<th>간략정보</th>
					<th>회원가입일</th>
				</tr>
				<!-- 게시물 목록 -->
				<c:choose>
					<c:when test="${!empty requestScope.ml && !empty requestScope.il}">
						<c:forEach var="ml" items="${requestScope.ml}" varStatus="status">
						<c:set var="birth" value="${il[status.index].cmi_age}"/>
						<c:set var="birthYear" value="${fn:substring(birth, 0, 4)}"/>
						<c:set var="birthMonth" value="${fn:substring(birth, 4, 6)}"/>
						<c:set var="birthDay" value="${fn:substring(birth, 6, 8)}"/>
						
						<jsp:useBean id="now" class="java.util.Date"/>
						<f:formatDate value="${now}" pattern="yyyyMMdd" var="nows"/>
						<c:set var="nowsYear" value="${fn:substring(nows, 0, 4)}"/>
						<c:set var="nowsMonth" value="${fn:substring(nows, 4, 6)}"/>
						<c:set var="nowsDay" value="${fn:substring(nows, 6, 8)}"/>
						
						<c:set var="age" value="${nowsYear-birthYear}"/>
						<c:if test="${birthMonth*100+birthDay > nowsMonth*100+nowsDay}">
							<c:set var="age" value="${nowsYear-birthYear-1}"/>
						</c:if>
							<tr onclick="javascript:promotionDetail(${ml.cm_num})">
								<td>${ml.cm_num}</td>
								<td>${ml.cm_name}</td>
								<td>${il[status.index].cmi_title}</td>
								<td>만  ${age} 세 / ${il[status.index].cmi_gender eq 'Male' ? '남성' : '여성'}</td>
								<td><f:formatDate value="${ml.cm_regdate}" pattern="yyyy년 MM월 dd일"/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan=5>받아온 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<!-- paging 처리 -->
		<div class="pageWrap">
			<c:if test="${pm.startPage > 1}">
				<a href="?page=1">&lt;&lt;</a>
			</c:if>
			<c:if test="${pm.prev}">
				<a href="?page=${pm.startPage-1}">&lt;</a>
			</c:if>
			<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
				<c:choose>
					<c:when test="${pm.cri.page eq i}">
						<a href="#">${i}</a>
					</c:when>
					<c:otherwise>
						<a href="?page=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pm.next}">
				<a href="?page=${pm.endPage+1}">&gt;</a>
			</c:if>
			<c:if test="${pm.cri.page < pm.maxPage}">
				<a href="?page=${pm.maxPage}">&gt;&gt;</a>
			</c:if>
		</div>
	</section>
</body>
<script>
	function promotionDetail(num){
		location.href="detail?cm_num="+num;
	}
</script>
</html>