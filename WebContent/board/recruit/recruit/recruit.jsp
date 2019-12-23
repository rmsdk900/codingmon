<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>

<!-- -------- -->
<style type="text/css">
img.ui-datepicker-trigger {
   margin-left: 5px;
   vertical-align: middle;
   cursor: pointer;
}
</style>


<section class="hero-body">
   <div class="container is-fluid">


      <div style="width: 50%; margin: auto;">
         <table class="table is-hoverable is-fullwidth
"><tr>
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
   
   <div style="width: 50%; margin: auto;">
         <table class="table is-hoverable is-fullwidth">
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
         <nav>
            <div class="container">
               <div class="pagination is-centered" role="navigation"
                  aria-label="pagination">
                  <ul class="pagination-list">
                  <c:if test="${pm.cri.page > 1}">
                  	<c:if test="${pm.prev}">
                  		<li><a class="button is-light" href="${pageContext.request.contextPath}/board/rec/search${pm.make(pm.startPage-1)}">Previous</a></li>
                  	</c:if>
                  </c:if>
                    <c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
						<c:choose>
							<c:when test="${pm.cri.page eq i}">
								<li><span class="button is-primary">${i}</span></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/board/rec/search${pm.make(i)}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pm.cri.page < pm.maxPage}">
						<c:if test="${pm.next}">
							<li><a href="${pageContext.request.contextPath}/board/rec/search${pm.make(pm.endPage+1)}">Next page</a></li>
						</c:if>
					</c:if>

                  </ul>
               </div>
            </div>
         </nav>



      </div>
   </div>
</section>
</body>
</html>