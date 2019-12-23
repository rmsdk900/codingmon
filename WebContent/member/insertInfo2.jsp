
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<!--드롭다운  -->
<link rel="stylesheet"
   href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
   src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>



<!-- 날짜 입력창 관련 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<!-- -------- -->
<style type="text/css">
   
     img.ui-datepicker-trigger {
                margin-left:5px; vertical-align:middle; cursor:pointer;
	}
   
</style>
<script>
  $(function(){
     $.datepicker.setDefaults($.datepicker.regional['ko']);

     $('#cmi_age').datepicker({
          showOn: "both",                     // 달력을 표시할 타이밍 (both: focus or button)
          buttonImage: "${pageContext.request.contextPath}"+"/"+"images/calendar.gif", // 버튼 이미지
          buttonImageOnly : true,             // 버튼 이미지만 표시할지 여부
          buttonText: "날짜선택",             // 버튼의 대체 텍스트
          nextText: '다음 달',
          prevText: '이전 달', 
          dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
          dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
          monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          dateFormat: "yymmdd",             // 날짜의 형식
          changeYear: true,
          yearRange: "-100:+0",
          changeMonth: true                 // 월을 이동하기 위한 선택상자 표시여부
      });



  });

</script>

   <!--    헤더 영역 -->
   
   <section class="hero is-primary is-fullheight-with-navbar">
      <form id="insertInfoForm" action="insertInfo" method="post">
         <input type="hidden" name="cm_num"
            value="${sessionScope.joinMember.cm_num}" />
         <div class="hero-body">
            <!-- class="hero-body" -->
            <div class="container has-text-centered">
               <!--  -->
               <div class="columns is-8 is-variable ">
                  <!-- 왼쪽 상단  -->
                  <div class="column is-one-thirds has-text-left">
                     <h1 class="title is-1">회원 가입</h1>
                     <p class="is-size-4">부가정보 입력</p>
                  </div>
                  <div class="column is-two-third has-text-left">
                     <!-- 오른쪽 회원가입  -->
                     <div class="columns is-8 is-variable ">
                        <!-- 왼쪽 상단  -->
                        <div class="column is-two-thirds has-text-left"></div>
                     </div>

                     <div class="hero-body is-fullheight is-primary">
                        <nav class="tabs is-boxed is-fullwidth is-large">
                           <div class="container">
                              <ul>
                                 <li class="tab is-active" onclick="openTab(event,'info1')"><a>정보1</a></li>
                                 <li class="tab" onclick="openTab(event,'info2')"><a>
                                       정보2</a></li>
                                 <li class="tab" onclick="openTab(event,'info3')"><a>정보3</a></li>
                              </ul>
                           </div>
                        </nav>
                     </div>

<hr>
                     <div id="info1" class="content-tab ">



                        <div class="field">
                           <label class="label has-text-white">이력서 제목</label> <input
                              class="input is-medium is-4" type="text" name="cmi_title"
                              placeholder="기본 : ${sessionScope.joinMember.cm_name}의 이력서"
                              />
                           <div class="control"></div>
                        </div>
                        <div class="field">
                           <label class="label has-text-white">생년월일</label> <input
                              class="input is-medium " type="text" id="cmi_age"
                              name="cmi_age" placeholder="" />
                        </div>
                        <div class="control">
                        성별
                           <div class="dropdown">
                              <div class="dropdown-trigger">
                                 <button class="button is-inverted is-outlined"
                                    aria-haspopup="true" aria-controls="dropdown-menu">
                                    <span>성별 선택</span> <span class="icon is-small"> <i
                                       class="fas fa-angle-down" aria-hidden="true"></i>
                                    </span>
                                 </button>
                              </div>
                              <div class="dropdown-menu" id="dropdown-menu" role="menu">
                                 <div class="dropdown-content">
                                    <a class="dropdown-item"> <label> <input
                                          class="radio" type="radio" name="cmi_gender" value="Male"
                                          checked /> 남성
                                    </label></a> <a class="dropdown-item"> <label> <input
                                          class="input" type="radio" name="cmi_gender"
                                          value="Female" /> 여성
                                    </label>
                                    </a>

                                 </div>
                              </div>
                           </div>

                           <div class="dropdown">
                              <div class="dropdown-trigger">
                                 <button class="button is-inverted is-outlined"
                                    aria-haspopup="true" aria-controls="dropdown-menu">
                                    <span> 직업</span> <span class="icon is-small"> <i
                                       class="fas fa-angle-down" aria-hidden="true"></i>
                                    </span>
                                 </button>
                              </div>
                              <div class="dropdown-menu" id="dropdown-menu" role="menu">
                                 <div class="dropdown-content">

                                    <div class="dropdown-item">

                                       <c:forEach var="i" items="${requestScope.ej}">
                                          <label> <input type="checkbox" class="cij_code"
                                             name="cij_code" value="${i.cj_code}" /> ${i.cj_name}
                                          </label>
                                       </c:forEach>


                                    </div>

                                 </div>
                              </div>
                           </div>

                           <div class="dropdown">
                              <div class="dropdown-trigger">
                                 <button class="button is-inverted is-outlined"
                                    aria-haspopup="true" aria-controls="dropdown-menu">
                                    <span>선호지역</span> <span class="icon is-small"> <i
                                       class="fas fa-angle-down" aria-hidden="true"></i>
                                    </span>
                                 </button>
                              </div>
                              <div class="dropdown-menu" id="dropdown-menu" role="menu">
                                 <div class="dropdown-content">
                                    <a class="dropdown-item"> <c:forEach var="i"
                                          items="${requestScope.er}">
                                          <label> <input type="checkbox" name="cmr_code"
                                             value="${i.cr_code}" /> ${i.cr_name}
                                          </label>
                                       </c:forEach>
                                    </a>

                                 </div>
                              </div>
                           </div>


                        </div>

                     </div>

                     <div id="info2" class="content-tab" style="display: none">
                        <div class="field"></div>
주 언어&nbsp;&nbsp;
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> JAVA</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 1}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> C언어</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 2}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> Javascript</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">
<c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 3}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> Python</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 4}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>

                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> ETC</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 5}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>

                                 </div>

                              </div>
                           </div>
                        </div>

<hr>
학습 언어 &nbsp;&nbsp;
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> JAVA</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 1}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> C언어</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.ej}">
                                       <label> <input type="checkbox" class="cij_code"
                                          name="cij_code" value="${i.cj_code}" /> ${i.cj_name}
                                       </label>
                                    </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> Javascript</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">
<c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 3}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>


                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> Python</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 4}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>

                                 </div>

                              </div>
                           </div>
                        </div>
                        <div class="dropdown">
                           <div class="dropdown-trigger">
                              <button class="button is-inverted is-outlined"
                                 aria-haspopup="true" aria-controls="dropdown-menu">
                                 <span> ETC</span> <span class="icon is-small"> <i
                                    class="fas fa-angle-down" aria-hidden="true"></i>
                                 </span>
                              </button>
                           </div>
                           <div class="dropdown-menu" id="dropdown-menu" role="menu">
                              <div class="dropdown-content">

                                 <div class="dropdown-item">

                                    <c:forEach var="i" items="${requestScope.es}">
                        <c:if test="${i.cs_category eq 5}">
                           <label>
                              <input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
                              ${i.cs_name}
                           </label>
                        </c:if>
                     </c:forEach>

                                 </div>

                              </div>
                           </div>
                        </div>

                     </div>




                     <div id="info3" class="content-tab" style="display: none">
                        <div class="field">
                           <label class="label has-text-white">경력사항</label>
                           <textarea name="cmi_career" cols=50 rows=10
                              placeholder="수상 경력, 자격증, 업무 경험 등..."></textarea>

                        </div>

                        <div class="field">
                           <label class="label has-text-white">자기소개</label>
                           <textarea name="cmi_intro" cols=30 rows=20
                              placeholder="자신의 업무 외적 부분을 어필..."></textarea>

                        </div>

                        <div class="control">
                           <label class="label has-text-white is ">정보 공개 여부</label> <label> <input
                              type="radio" name="cmi_private" value="N" checked /> 공개
                           </label> <label> 
                           
                           <input type="radio" name="cmi_private"
                              value="Y" /> 비공개
                           </label>
                        </div>
                        <hr>
                           <input class="button " type="button" id="infoSubmit"
                  value="작성 완료" /> 
                  <input class="button  " type="reset" value="수정" />
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </form>
      <%@ include file="../common/footer.jsp"%>
   </section>
</body>
<script>
   function openTab(evt, tabName) {
      var i, x, tablinks;
      x = document.getElementsByClassName("content-tab");
      for (i = 0; i < x.length; i++) {
         x[i].style.display = "none";
      }
      tablinks = document.getElementsByClassName("tab");
      for (i = 0; i < x.length; i++) {
         tablinks[i].className = tablinks[i].className.replace(" is-active",
               "");
      }
      document.getElementById(tabName).style.display = "block";
      evt.currentTarget.className += " is-active";
   }
</script>
</html>