<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<section class="hero is-primary is-fullheight-with-navbar">
      <form action="${pageContext.request.contextPath}/user/checkPwCode" method="post">
         <input type="hidden" name="cpc_email" value="${param.cm_email}" />
         <div class="hero-body ">
            <!-- class="hero-body" -->
            <div class="container  has-text-centered">
               <!--  -->
               <div class="columns is-8 is-variable ">
                  <div class="column is-two-thirds has-text-left">
                     <h1 class="title is-1">비밀번호 찾기</h1>

                  </div>
                  <div class="column is-one-third has-text-left ">
                     <!--  -->
                     <div class="field">
                        <div class="column  has-text-left">
                           <h2 class="subtitle is-1 ">코드 입력</h2>
                        </div>
                           <!-- <br>대신 <hr>사용 선추가  --><hr>

                        <div class="control">
                           <input class="input is-medium" type="text" name="cpc_code"
                              required />
                        </div>
                     </div>

                     <div class="control">
                           
                                 <a class="button is-primary is-inverted is-outlined "
                           href="${pageContext.request.contextPath}/user/findPw"> 재전송
                        </a> <input class="button is-primary is-inverted is-outlined"
                           type="submit" value="입력" />

                     </div>

                     </div>

                  </div>
               </div>
            </div>
      </form>
   </section>
   <%@ include file="../common/footer.jsp"%>