<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

  <!-- Hero head: will stick at the top -->
<%@ include file="../../../common/header.jsp"%>
<form action="boardWriteSubmit.bo" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="cbq_writer_name" value="admin"/>
		<input type="hidden" name="cbq_writer_num" value="1"/>
		<div class="hero is-primary">
				
				<div class="hero-body is-primary has-text-centered">
				
					<!--  -->
					<div class="columns is-8 is-variable ">
						<!--  -->

						<div class="column is-one-third has-text-left">

							<!-- 이름 -->
							<div class="field">
								<label class="label">제목</label>
								<div class="control">

									<input class="input is-medium" type="text" name="cbq_title"
										required />

								</div>

								<!-- 내용 -->
								<div class="field">
									<label class="label">내용</label>
									<div class="control">
										<textarea class="textarea" name="cbq_content" cols=50 rows=10></textarea>
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

  <!-- Hero footer: will stick at the bottom -->

</html>




