<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<link href="../../css/list.css" rel="stylesheet" type="text/css" />
<%@ include file="../../../common/header.jsp"%>
<!-- <section>  밑에 추가-->
<section class="hero is-primary is-fullheight-with-navbar">
   <!-- hero속성추가 -->


   <form action="boardReplySubmit.bo" method="post">
      <input type="hidden" name="cbq_num" value="${boardVO.cbq_num}" /> <input
         type="hidden" name="cbq_re_ref" value="${boardVO.cbq_re_ref}" /> <input
         type="hidden" name="cbq_re_lev" value="${boardVO.cbq_re_lev}" /> <input
         type="hidden" name="cbq_re_seq" value="${boardVO.cbq_re_seq}" /> <input
         type="hidden" name="cbq_writer_name"
         value="${boardVO.cbq_writer_name}" /> <input type="hidden"
         name="cbq_writer_num" value="${boardVO.cbq_writer_num}" /> <br>
      <div class="hero-body ">
         <!-- class="hero-body" -->
         <div class="container  has-text-centered">
            <!--  -->
            <div class="columns is-8 is-variable ">
               <div class="column is-two-thirds has-text-left">
                  <h1 class="title is-1">질문과 답변</h1>

               </div>
               <div class="column is-one-third has-text-left ">
                  <div class="field">
                  
                     <div class="column  has-text-left">
                        <h2 class="subtitle is-1 ">답변글 작성</h2>
                     </div>
                     <hr>

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
                        <br>
                        <div class="field">

                           <div class="control">
                              <input
                                 class="button is-primary is-inverted is-outlined  has-text-weight-medium is-medium"
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