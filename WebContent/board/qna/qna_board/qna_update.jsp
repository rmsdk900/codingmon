<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/comment.css" rel="stylesheet" type="text/css"/>
<%@ include file="../../../common/header.jsp" %>

<section>

	<form action="boardUpdateSubmit.bo" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="cbq_writer_name" value="${board.cbq_writer_name}"/>
		<input type="hidden" name="cbq_writer_num" value="${board.cbq_writer_num}"/>
		<input type="hidden" name="cbq_num" value="${board.cbq_num}"/>
		
		
		<div >
				<!-- class="hero-body" -->
				<div class="container" style="width:80%; margin:auto">
					<!--  -->
					<div class="columns is-8 is-variable ">
						<!--  -->

						<div class="column is-one-third has-text-left">

							<!-- 이름 -->
							<div class="field">
								<label class="label">제목</label>
								<div class="control">

									<input class="input is-medium" type="text" name="cbq_title"
										value="${board.cbq_title}"  required />

								</div>

								<!-- 내용 -->
								<div class="field">
									<label class="label">내용</label>
									<div class="control">
										<textarea class="textarea" name="cbq_content" cols=50 rows=10
										>${board.cbq_content}</textarea>
									</div>

									<!-- 첨부파일 -->
									<div class="field">
										<label class="label">첨부파일 </label>
										<div class="file has-name ">
											<label class="file-label"> <input class="file-input"
												type="file" name="cbq_file"> <span class="file-cta ">
													<span class="file-icon "> <i class="fas fa-upload"></i>
												</span> <span class="file-label"> Choose a file </span>
											</span> <span class="file-name  "> 선택된 파일</span>
											</label>
										</div>
									</div>
									<div class="field">
										<div class="control">
											<input
												class="button is-primary is-fullwidth has-text-weight-medium is-medium"
												type="submit" id="submit" value="작성완료" />
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	</form>
</section>
</body>
</html>










