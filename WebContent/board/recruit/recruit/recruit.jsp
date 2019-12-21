<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>

<!-- 날짜 입력창 관련 -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
  $(function(){
     $.datepicker.setDefaults($.datepicker.regional['ko']);

     $('.cbr_date').datepicker({
          showOn: "both",                     // 달력을 표시할 타이밍 (both: focus or button)
          buttonImage: "${pageContext.request.contextPath}"+"/"+"images/calendar.gif", // 버튼 이미지
          buttonImageOnly : true,             // 버튼 이미지만 표시할지 여부
          buttonText: "날짜선택",             // 버튼의 대체 텍스트
          nextText: '다음 달',
          prevText: '이전 달', 
          dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
          dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
          monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          dateFormat: "yymmdd",             // 날짜의 형식
          changeYear: true,
          changeMonth: true                 // 월을 이동하기 위한 선택상자 표시여부
      });
  });

</script>
<!-- -------- -->
<style type="text/css">
img.ui-datepicker-trigger {
	margin-left: 5px;
	vertical-align: middle;
	cursor: pointer;
}
</style>


<section>
	<div>
		<h2>Recruit</h2>
		<table class="list">
			<tr>
				<th colspan="3">recruit 게시판</th>
				<th><c:choose>
						<c:when test="${!empty sessionScope.member}">
							<a href="write">글 작성</a>
						</c:when>
					</c:choose></th>
			</tr>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${!empty cbrList}">
					<c:forEach var="list" items="${cbrList}">
						<tr>
							<td>${list.cbr_num}</td>
							<td><a href="detail?cbr_num=${list.cbr_num}">
									${list.cbr_title} </a></td>
							<td>${list.cbr_writer}</td>
							<td>${list.cbr_regdate}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">등록된 게시물이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<!-- 검색 기능 추가 -->
	<div>
		<table class="list">
			<tr>
				<td colspan="3">
					<form action="${pageContext.request.contextPath}/board/rec/search"
						method="get">
						<select name="searchRecruit">
							<option value="writer">작성자</option>
							<option value="title">제목</option>
						</select> <input type="text" name="searchRecruitVal" /> <input
							type="submit" value="검색" />
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div class="pageWrap">
		<c:if test="${pm.cri.page > 1}">
			<a href="${pageContext.request.contextPath}/board/rec/search${pm.make(1)}">&lt;&lt;</a>
			<c:if test="${pm.prev}">
				<a href="${pageContext.request.contextPath}/board/rec/search${pm.make(pm.startPage-1)}">&lt;</a>
			</c:if>
		</c:if>
		<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
			<c:choose>
				<c:when test="${pm.cri.page eq i}">
					<span style="color: red">[${i}]</span>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/board/rec/search${pm.make(i)}">[${i}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pm.cri.page < pm.maxPage}">
			<c:if test="${pm.next}">
				<a href="${pageContext.request.contextPath}/board/rec/search${pm.make(pm.endPage+1)}">&gt;</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/board/rec/search${pm.make(pm.maxPage)}">&gt;&gt;</a>
		</c:if>
	</div>
</section>
<footer> Copyright &copy; since 2019. </footer>
</body>
</html>