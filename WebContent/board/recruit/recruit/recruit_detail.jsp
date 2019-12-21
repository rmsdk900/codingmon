<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp" %>
		<section>
		<h2>Recruit</h2>
			<table class="list">
				<tr>
					<th>작성자</th>
					<td>${recruitVO.cbr_writer}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${recruitVO.cbr_title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea style="resize:none;" readonly cols=50 rows=10>${recruitVO.cbr_content}</textarea></td>
				</tr>
				<c:choose>
					<c:when test="${!empty job}">
						<tr>
							<th>직업</th>
							<td>
								<c:forEach var="i" items="${job}" varStatus="status">
									${i.value}<c:if test="${!status.last}"> | </c:if>
								</c:forEach>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>직업</th>
							<td>직업 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${!empty sub}">
						<tr>
							<th>필요 언어</th>
							<td>
								<c:forEach var="i" items="${sub}" varStatus="status">
									${i.value}<c:if test="${!status.last}"> | </c:if>
								</c:forEach>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>필요 언어</th>
							<td>언어 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${!empty reg}">
						<tr>
							<th>지역</th>
							<td>
								<c:forEach var="i" items="${reg}" varStatus="status">
									${i.value}<c:if test="${!status.last}"> | </c:if>
								</c:forEach>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>지역</th>
							<td>지역 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${!empty pay}">
						<tr>
							<th>임금사항</th>
							<td>
								<c:forEach var="i" items="${pay}" varStatus="status">
									${i.value} | 
									<c:if test="${!empty recruitVO.cbr_pay}">
										<c:if test="${recruitVO.cbr_pay eq 0.0}">
											상호 협의하여 결정
										</c:if>
										<c:if test="${recruitVO.cbr_pay ne 0.0}">
											<f:formatNumber value="${recruitVO.cbr_pay}" type="currency" currencySymbol="￦"/>
										</c:if>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th>임금사항</th>
							<td>임금 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<th>작업 시작일</th>
					<td>${recruitVO.cbr_date_from}</td>
				</tr>
				<tr>
					<th>작업 종료일</th>
					<td>${recruitVO.cbr_date_to}</td>
				</tr>
				<tr>
					<td colspan=2>
						<c:if test="${!empty member}">
							<c:if test="${recruitVO.cbr_writer_num eq sessionScope.member.cm_num}">
								<a href="${pageContext.request.contextPath}/board/rec/update?cbr_num=${recruitVO.cbr_num}">수정</a>
								&nbsp;
								<a href="#" onclick="deleteRecruit('${recruitVO.cbr_num}','${recruitVO.cbr_writer_num}',event);">삭제</a>
								&nbsp;
							 </c:if> 
						</c:if>
						<a href="list">목록으로</a>
					</td>
				</tr>
			</table>
		</section>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script>
			function deleteRecruit(cbr_num,cbr_writer_num,event){
				event.preventDefault();
				if(confirm(cbr_num+"게시물을 삭제 하시겠습니까?")){
					location.href='${pageContext.request.contextPath}/board/rec/delete?cbr_num='+cbr_num+'&cbr_writer_num='+cbr_writer_num;
				}
			}
		</script>
		<%@ include file="../comment/comment.jsp" %>
	<footer>
		Copyright &copy; since 2019.
	</footer>
</body>
</html>