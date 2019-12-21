<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp"%>


<section class="hero-body">
	<div class="container is-fluid">


		<div style="width: 50%; margin: auto;">
			<table class="table is-hoverable is-fullwidth
">

				<c:choose>
					<c:when test="${!empty qna_List}">

						<tr>
							<th align=center>글번호</th>
							<th align=center>제목</th>
							<th align=center>작성자</th>
							<th align=center>작성일</th>
							<th align=center>조회수</th>
						</tr>


						<c:forEach var="board" items="${qna_List}">
							<tr>
								<td align=center width=90 style="word-break: break-all">${board.cbq_num}</td>
								<td align=center><c:if test="${board.cbq_re_lev != 0}">
										<c:forEach var="i" begin="1" end="${board.cbq_re_lev}">
									&nbsp;&nbsp;&nbsp;
								</c:forEach>
								└▶
							</c:if> <a href="boardDetail.bo?cbq_num=${board.cbq_num}">${board.cbq_title}</a>
								</td>
								<td align=center width=90 style="word-break: break-all">${board.cbq_writer_name}</td>
								<td align=center width=200 style="word-break: break-all">${board.cbq_regdate}</td>
								<td align=center width=90 style="word-break: break-all">${board.cbq_readcount}</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan=5>등록된 게시물이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>

				<tr>
					<td colspan="5"><a class="button is-primary"
						href="boardWrite.bo">질문 하기</a></td>
				</tr>




			</table>

			<nav>
				<div class="container">
					<div class="pagination is-centered" role="navigation"
						aria-label="pagination">
						<ul class="pagination-list">
							<li><a class="button is-light">Previous</a></li>


							<c:forEach var="i" begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}">
								<c:choose>
									<c:when test="${pageMaker.cri.page eq i}">
										<li><span class="button is-primary">${i}</span></li>
									</c:when>
									<c:otherwise>
										<li><a class="button is-light"
											href="boardList.bo?page=${i}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>



							<li><a class="button is-light">Next page</a></li>
						</ul>
					</div>
				</div>
			</nav>



		</div>
	</div>
</section>
</body>
</html>

