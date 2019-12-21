<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="../../css/list.css" rel="stylesheet" type="text/css" />
<%@ include file="../../../common/header.jsp" %>
<section>

	<form action="boardReplySubmit.bo" method="post">
		<input type="hidden" name="cbq_num" value="${boardVO.cbq_num}" /> <input
			type="hidden" name="cbq_re_ref" value="${boardVO.cbq_re_ref}" /> <input
			type="hidden" name="cbq_re_lev" value="${boardVO.cbq_re_lev}" /> <input
			type="hidden" name="cbq_re_seq" value="${boardVO.cbq_re_seq}" /> <input
			type="hidden" name="cbq_writer_name"
			value="${boardVO.cbq_writer_name}" /> <input type="hidden"
			name="cbq_writer_num" value="${boardVO.cbq_writer_num}" />

		<div class="field" style="width: 70%; margin: auto">
			<label class="label">원본글 번호${boardVO.cbq_re_ref}/ 원본글 레벨
				${boardVO.cbq_re_lev} / 원본글 정렬번호${boardVO.cbq_re_seq}/</label>
		</div>
		<br>

		<div class="field" style="width: 70%; margin: auto">
			<label class="label">제목</label>
			<div class="control">

				<input class="input is-medium" type="text" name="cbq_title" required />

			</div>

			<!-- 내용 -->
			<div class="field">
				<label class="label">내용</label>
				<div class="control">
					<textarea class="textarea" name="cbq_content" cols=50 rows=10></textarea>
				</div>
				<br>
				<div class="field">

					<div class="control">
						<input
							class="button is-primary is-fullwidth has-text-weight-medium is-medium"
							type="submit" id="submit" value="작성완료" />
					</div>

				</div>
			</div>
		</div>

	</form>
</section>
</body>
</html>
