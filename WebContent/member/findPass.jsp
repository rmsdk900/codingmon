<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../common/header.jsp"/>


       <section class="hero is-primary is-fullheight-with-navbar">
      <form action="${pageContext.request.contextPath}/user/sendPw"
         method="post">
         

         <div class="hero-body ">
            <!-- class="hero-body" -->
            <div class="container  has-text-centered">
               <!--  -->
               <div class="columns is-8 is-variable ">
                  <div class="column is-two-thirds has-text-left">
                     <h1 class="title is-1">비밀번호 찾기</h1>
                  </div>
                  
                  <!--/////////////////////////-  -->
                  <!-- 오른쪽 공간 구분 값 입력  -->


               <div class="column is-one-third has-text-left ">
                     <!--  -->
                     <div class="column  has-text-left">
                        <h2 class="subtitle is-1 ">이메일 주소</h2>
                        
                     </div>
                     <hr>
                     <div class="control">
                           <label class="label has-text-white">이메일 주소를 입력해주세요</label>
                           
                        <input class="input" type="email" name="cm_email" />
                     </div>
                      <br>
                      <div>
                     <input class="button is-primary is-inverted is-outlined"
                        type="submit" value="제출" />
                  </div></div>
               </div>
            </div>
         </div>
      </form>
</section>


<jsp:include page="../common/footer.jsp"/>