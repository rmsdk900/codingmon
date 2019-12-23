<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<link href="css/comment.css" rel="stylesheet" type="text/css" />
<%@ include file="../../../common/header.jsp"%>

<section class="hero is-primary is-fullheight-with-navbar">
   <!-- hero속성추가 -->

   <%-- <form action="boardUpdateSubmit.bo" method="POST"
      enctype="multipart/form-data">
      <input type="hidden" name="cbq_writer_name"
         value="${board.cbq_writer_name}" /> <input type="hidden"
         name="cbq_writer_num" value="${board.cbq_writer_num}" /> <input
         type="hidden" name="cbq_num" value="${board.cbq_num}" />

 --%>
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
               <div class="column is-one-third has-text-left">
                  <h1 class="title is-1">회원 검색</h1>
                  
                  
                  
                  
                  
                  //////
                  
               <form action="search" method="get">
            <table class="table is-hoverable is-fullwidth"border=1>
               <tbody>
                  <tr>
                     <th>성별</th>
                     <td><select name="searchGender">
                           <option value="" disabled selected>성별</option>
                           <option value="Male">남성</option>
                           <option value="Female">여성</option>   
                     </select></td>
                  </tr>
                  <tr>
                     <th>나이(만)</th>
                     <td><c:forEach var="i" begin="10" end="70" step="10">
                           <input type="checkbox" name="searchAge" value="${i}" />
                        ${i}대
                     </c:forEach></td>
                  </tr>
                  <tr>
                     <th>활동 가능 지역</th>
                     <td><c:forEach var="i" items="${requestScope.er}">
                           <input type="checkbox" name="searchRegion" value="${i.cr_code}" />
                        ${i.cr_name}
                     </c:forEach></td>
                  </tr>
                  <tr>
                     <th>직업</th>
                     <td><c:forEach var="i" items="${requestScope.ej}">
                           <input type="checkbox" name="searchJob" value="${i.cj_code}" />
                        ${i.cj_name}
                     </c:forEach></td>
                  </tr>
                  <tr>
                     <th>자신있는 언어</th>
                     <td>
                     <div>
                        <h3>Java</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 1}">
                              <input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>C</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 2}">
                              <input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>Javascript</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 3}">
                              <input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>Python</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 4}">
                              <input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>ETC</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 5}">
                              <input type="checkbox" name="searchWorkLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     </td>
                  </tr>
                  <tr>
                     <th>학습하고 싶은 언어</th>
                     <td>
                     <div>
                        <h3>Java</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 1}">
                              <input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>C</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 2}">
                              <input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>Javascript</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 3}">
                              <input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>Python</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 4}">
                              <input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     <div>
                        <h3>ETC</h3>
                        <c:forEach var="i" items="${requestScope.es}">
                           <c:if test="${i.cs_category eq 5}">
                              <input type="checkbox" name="searchLearnLang" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </c:if>
                        </c:forEach>
                     </div>
                     </td>
                  </tr>
                  <tr>
                     <td colspan=2><input type="submit" value="검색" /> <input
                        type="reset" value="초기화" /></td>
                  </tr>

               </tbody>
            </table>
         </form>
                  //////
                  
                  
                  
                  
                  

               </div>
               <!--오른쪽 공간  -->

               <!-- 오른쪽 상단 글 -->
               <div class="column  is-two-third has-text-left ">
                  <div class="column  has-text-left">
                     <h2 class="subtitle is-1 ">회원 목록</h2>
                  </div>
                  <hr>
                  <!--////////////////////////////////////////////  -->
<!-- 아래 내용은 write와 같음  -->
                  <div class="field">
                  
                  
                     <section class="hero-body">
   <div class="container is-fluid">

      
         <table class="table is-hoverable is-fullwidth " border=1>
            <tr>
               <th>회원번호</th>
               <th>회원명</th>
               <th>이력서 제목</th>
               <th>간략정보</th>
               <th>회원가입일</th>
            </tr>
            <!-- 게시물 목록 -->
            <c:choose>
               <c:when test="${!empty requestScope.rl}">
                  <c:forEach var="ml" items="${requestScope.rl}" varStatus="status">
                  <c:set var="birth" value="${ml.cmi_age}"/>
                  <c:set var="birthYear" value="${fn:substring(birth, 0, 4)}"/>
                  <c:set var="birthMonth" value="${fn:substring(birth, 4, 6)}"/>
                  <c:set var="birthDay" value="${fn:substring(birth, 6, 8)}"/>
                  
                  <jsp:useBean id="now" class="java.util.Date"/>
                  <f:formatDate value="${now}" pattern="yyyyMMdd" var="nows"/>
                  <c:set var="nowsYear" value="${fn:substring(nows, 0, 4)}"/>
                  <c:set var="nowsMonth" value="${fn:substring(nows, 4, 6)}"/>
                  <c:set var="nowsDay" value="${fn:substring(nows, 6, 8)}"/>
                  
                  <c:set var="age" value="${nowsYear-birthYear}"/>
                  <c:if test="${birthMonth*100+birthDay > nowsMonth*100+nowsDay}">
                     <c:set var="age" value="${nowsYear-birthYear-1}"/>
                  </c:if>
                  <c:choose>
                     <c:when test="${ml.cmi_private eq 'N' }">
                        <tr onclick="javascript:promotionDetail(${ml.cm_num})">
                           <td>${ml.cm_num}</td>
                           <td>${ml.cm_name}</td>
                           <td>${ml.cmi_title}</td>
                           <td>만  ${age} 세 / ${ml.cmi_gender eq 'Male' ? '남성' : '여성'}</td>
                           <td><f:formatDate value="${ml.cm_regdate}" pattern="yyyy년 MM월 dd일"/></td>
                        </tr>
                     </c:when>
                     <c:otherwise>
                        <tr>
                           <td>${ml.cm_num}</td>
                           <td>비공개</td>
                           <td>비공개</td>
                           <td>만  ${age} 세 / ${ml.cmi_gender eq 'Male' ? '남성' : '여성'}</td>
                           <td><f:formatDate value="${ml.cm_regdate}" pattern="yyyy년 MM월 dd일"/></td>
                        </tr>
                     </c:otherwise>
                  </c:choose>
                     
                  </c:forEach>
                     <tr>
                        <td colspan=5>
                           <a href="${pageContext.request.contextPath}/user/home">메인</a>
                        </td>
                     </tr>
                     <tr>
                     	<td colspan=5>
                     		<div class="pageWrap" align="center">
						         <c:if test="${pm.startPage > 1}">
						            <a href="search${pm.makingQuery(1)}">&lt;&lt;</a>
						         </c:if>
						         <c:if test="${pm.prev}">
						            <a href="search${pm.makingQuery(pm.startPage-1)}">&lt;</a>
						         </c:if>
						         <c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
						            <c:choose>
						               <c:when test="${pm.cri.page eq i}">
						                  <a href="search${pm.makingQuery(i)}">${i}</a>
						               </c:when>
						               <c:otherwise>
						                  <a href="search${pm.makingQuery(i)}">${i}</a>
						               </c:otherwise>
						            </c:choose>
						         </c:forEach>
						         <c:if test="${pm.next}">
						            <a href="search${pm.makingQuery(pm.endPage+1)}">&gt;</a>
						         </c:if>
						         <c:if test="${pm.cri.page < pm.maxPage}">
						            <a href="search${pm.makingQuery(pm.maxPage)}">&gt;&gt;</a>
						         </c:if>
						      </div>
                     	</td>
                     </tr>
               </c:when>
               <c:otherwise>
                  <tr>
                     <td colspan=5>받아온 정보가 없습니다.</td>
                  </tr>
               </c:otherwise>
            </c:choose>
            <tr></tr>
         </table>
          
      </div>
      <!-- paging 처리 -->
     
   </section>

                  </div>
               </div>
            </div>
         </div>
      </div>
   
</section>

</body>
<script>
   function promotionDetail(num){
      location.href="detail?cm_num="+num;
   }
</script>
   <%@ include file="../../../common/footer.jsp"%>









