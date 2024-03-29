<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<link href="css/comment.css" rel="stylesheet" type="text/css" />
<%@ include file="../../../common/header.jsp"%>

<section class="hero is-primary is-fullheight-with-navbar">
   <!-- hero속성추가 -->

   <form action="${pageContext.request.contextPath}/boardUpdateSubmit.bo" method="POST"
      enctype="multipart/form-data">
      <input type="hidden" name="cbq_writer_name"
         value="${board.cbq_writer_name}" /> <input type="hidden"
         name="cbq_writer_num" value="${board.cbq_writer_num}" /> <input
         type="hidden" name="cbq_num" value="${board.cbq_num}" />


      <!-- <div class="hero-body ">
            class="hero-body"
            <div class="container" style="width:80%; margin:auto">
               
               <div class="columns is-8 is-variable ">
                  

                  <div class="column is-one-third has-text-left">
    -->
      <div class="hero-body ">
         <!-- class="hero-body" -->
         <div class="container  has-text-centered">
            <!-- 아래가 공간 분할 -->
            <div class="columns is-8 is-variable ">
               <!--왼쪽공간  -->
               <div class="column is-two-thirds has-text-left">
                  <h1 class="title is-1">묻고 답하기</h1>

               </div>
               <!--오른쪽 공간  -->

               <!-- 오른쪽 상단 글 -->
               <div class="column is-one-third has-text-left ">
                  <div class="column  has-text-left">
                     <h2 class="subtitle is-1 ">글 수정</h2>
                  </div>
                  <hr>

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
										<div class="file has-name "  id="file-js">
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
<script>
	const fileInput = document.querySelector("#file-js input[type=file]");
	fileInput.onchange = function(){
		if(fileInput.files.length > 0){
			const fileName = document.querySelector('#file-js .file-name');
			fileName.textContent = fileInput.files[0].name;
		}
	}
</script>
</html>










