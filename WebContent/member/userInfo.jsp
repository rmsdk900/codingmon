<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<c:set var="name" value="${sessionScope.member.cm_name}" />
<c:set var="totalLength" value="${fn:length(name) }" />
<c:set var="first" value="${fn:substring(name, 0, 1)}" />
<c:set var="last" value="${fn:substring(name, 2, totalLength) }" />

<c:set var="phone" value="${sessionScope.member.cm_phone}" />
<c:set var="phoneFirst" value="${fn:substring(phone, 0, 3)}" />
<section class="hero is-primary is-fullheight-with-navbar">
	<div class="hero-body ">
		<!-- class="hero-body" -->
		<div class="container  has-text-centered">
			<!-- 왼쪽 상단  -->
			<div class="columns is-8 is-variable ">
				<div class="column is-two-thirds has-text-left">
					<h1 class="title is-1">회원 정보</h1>

				</div>
				<!--오른쪽  -->
				<div class="column is-one-third has-text-left ">
					<!--  -->
					<div class="field">
						<div class="column  has-text-left">
							<h2 class="subtitle is-1 ">내정보</h2>
							<br>
						</div>

						<div class="control">
							<c:choose>
								<c:when test="${!empty sessionScope.member}">
									<div class="">이메일 &nbsp;&nbsp;|
										&nbsp;&nbsp;${sessionScope.member.cm_email}</div>
									<hr>
									<div class="">
										이름 &nbsp;&nbsp;| &nbsp;&nbsp;
										<c:if test="${!empty  name}">
											<c:out value="${first}*${last}" />
										</c:if>
									</div>
									<hr>
									<div class="">
										번호 &nbsp;&nbsp;| &nbsp;&nbsp;
										<c:if test="${!empty  phone}">
											<c:out value="${phoneFirst}-****-****" />
										</c:if>
									</div>
									<hr>
									<div class="">주소 &nbsp;&nbsp;|
										&nbsp;&nbsp;${sessionScope.member.cm_addr}</div>
									<hr>



									<div class="">
										<div class="">
											<a class="button is-primary is-inverted is-outlined "
												href="${pageContext.request.contextPath}/user/updateInfo?cm_num=${sessionScope.member.cm_num}"><label>수정</label></a>
										</div>
										<div class="">
											<a class="button is-primary is-inverted is-outlined "
												href="${pageContext.request.contextPath}/user/updatePw?cm_num=${sessionScope.member.cm_num}"><label>비밀번호
													변경</label></a>
										</div>
										<div class="">
											<a class="button is-primary is-inverted is-outlined "
												href="${pageContext.request.contextPath}/user/deleteMember?cm_num=${sessionScope.member.cm_num}"><label>회원탈퇴
											</label></a>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<script type="text/javascript">
												location.href="${pageContext.request.contextPath}/user/login";
											</script>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/footer.jsp"%>