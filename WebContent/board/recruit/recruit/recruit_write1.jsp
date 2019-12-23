
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!--    헤더 영역 -->
   <%@ include file="../../../common/header.jsp"%>
<!--bulma  -->


<!--주언어  -->
<link rel="stylesheet"
   href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
   src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


<!-- 날짜 입력창 관련 -->
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
  $(function(){
     $.datepicker.setDefaults($.datepicker.regional['ko']);

     $('#date_dead').datepicker({
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
          minDate: 0,
          changeYear: true,
          changeMonth: true                 // 월을 이동하기 위한 선택상자 표시여부
     });
     
     $('#date_start').datepicker({
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
         changeMonth: true,                  // 월을 이동하기 위한 선택상자 표시여부
         minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
         onClose: function( selectedDate ) {    
             // 시작일(fromDate) datepicker가 닫힐때
             // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
             $("#date_end").datepicker( "option", "minDate", selectedDate );
         }                
     });

     //종료일
     $('#date_end').datepicker({
         showOn: "both", 
         buttonImage: "${pageContext.request.contextPath}"+"/"+"images/calendar.gif", 
         buttonImageOnly : true,
         buttonText: "날짜선택",
         nextText: '다음 달',
         prevText: '이전 달', 
         dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
         dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
         dateFormat: "yymmdd",
         changeYear: true,
         changeMonth: true,
         //minDate: 0, // 오늘 이전 날짜 선택 불가
         onClose: function( selectedDate ) {
             // 종료일(toDate) datepicker가 닫힐때
             // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
             $("#date_start").datepicker( "option", "maxDate", selectedDate );
         }                
     });

  });

</script>
<!-- -------- -->
<style type="text/css">
img.ui-datepicker-trigger {
   margin-left: 5px;
   vertical-align: middle;
   cursor: pointer;
}
</style>
</head>
<body>
   
   <section class="hero is-primary is-fullheight-with-navbar">
      <form id="cbrWriteForm" action="${pageContext.request.contextPath}/board/rec/write_submit" method="post">
         <input type="hidden" name="cbr_writer_num"
            value="${sessionScope.member.cm_num}" />
         <div class="hero-body">
            <!-- class="hero-body" -->
            <div class="container has-text-centered">
               <!--  -->
               <div class="columns is-8 is-variable ">
                  <!-- 왼쪽 상단  -->
                  <div class="column is-one-thirds has-text-left">
                     <h1 class="title is-1">recruit</h1>
                  </div>
                  <div class="column is-two-third has-text-left">
                     <!-- 오른쪽 회원가입  -->
                     <div class="hero-body is-fullheight is-primary">
                     <div class="column  has-text-left">
                           <h2 class="subtitle is-1 ">글 작성</h2>
                           <br></div>
                        <nav class="tabs is-boxed is-fullwidth is-large">
                              <ul>
                                 <li class="tab is-active" onclick="openTab(event,'info1')"><a>1 단계</a></li>
                                 <li class="tab" onclick="openTab(event,'info2')"><a>
                                       2 단계</a></li>
                              </ul>
                        </nav>
                     </div>

<hr>
                     <div id="info1" class="content-tab ">

         <div class="field">
                     작성자
                     <div class="control">
                        <input class="input is-medium"  type="text" name="cbr_writer" value="${sessionScope.member.cm_name}" readonly />

                     </div>
                     제목
                     <div class="control">

                        <input class="input is-medium"  type="text" name="cbr_title" required />
                     </div>

                  </div>
                  내용
                  <div class="control">

                     <textarea class="textarea is-large" name="cbr_content" cols=50 rows=10 required></textarea>
                  </div>

                     </div>

                     <div id="info2" class="content-tab" style="display: none">
                     
                           pay
                  <div class="control">

                     <input class="input is-medium"  type="number" id="cbr_pay" name="cbr_pay" />

                  </div>
                  작업 시작일
                  <div class="control">

                     <input class="input is-medium" type="text" id="date_start" class="cbr_date"
                        name="cbr_date_from" required />
                  </div>
                  작업 종료일
                  <div class="control">

                     <input class="input is-medium" type="text" id="date_end" class="cbr_date"
                        name="cbr_date_to" required />

                  </div>
                  등록 종료일

                  <!-- 내용 -->

                     <div class="control">
                        <input class="input is-medium" type="text" id="date_dead" class="cbr_date"
                           name="cbr_deadline" required />
                     </div>
                  <div class="field">

                     <div class="field">
                        <div class="control">
                           <input
                              class="button is-primary is-inverted is-outlined is-fullwidth has-text-weight-medium is-medium"
                              type="button" id="cbrWriteSubmit" value="작성완료" />
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
   
   document.getElementById("cbrWriteSubmit").addEventListener("click",function(){
		var cbr_pay = document.getElementById("cbr_pay");
		
		if(cbr_pay.value == '' || cbr_pay.value == null){
			cbr_pay.value = 0.0;
		}
		document.getElementById("cbrWriteForm").submit();
	});
</script>
<%@ include file="../../../common/footer.jsp"%>