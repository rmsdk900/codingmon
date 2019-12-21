<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../common/header.jsp" %>
<!-- 날짜 입력창 관련 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
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
         buttonImage: "${pageContext.request.contextPath}"+"/"+"/images/calendar.gif", // 버튼 이미지
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
         buttonImage: "${pageContext.request.contextPath}"+"/"+"/images/calendar.gif", 
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
                margin-left:5px; vertical-align:middle; cursor:pointer;
	}
   
</style>
</head>
<body>
	<h2>recruit 게시판 글 작성</h2>
	<section>
		<form id="cbrWriteForm" action="${pageContext.request.contextPath}/board/rec/write_submit" method="post">
			<input type="hidden" name="cbr_writer_num" value="${sessionScope.member.cm_num}" />
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="cbr_writer" value="${sessionScope.member.cm_name}" readonly /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="cbr_title" required/></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="cbr_content" cols=50 rows=10 required></textarea>
					</td>
				</tr>
				<tr>
					<td>pay</td>
					<td><input type="number" id="cbr_pay" name="cbr_pay" /></td>
				</tr>
				<tr>
					<td>작업 시작일</td>
					<td><input type="text" id="date_start" class="cbr_date" name="cbr_date_from" required/></td>
				</tr>
				<tr>
					<td>작업 종료일</td>
					<td><input type="text" id="date_end" class="cbr_date" name="cbr_date_to" required/></td>
				</tr>
				<tr>
					<td>등록 종료일</td>
					<td><input type="text" id="date_dead" class="cbr_date" name="cbr_deadline" required/></td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="button" id="cbrWriteSubmit" value="작성 완료" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
<script>
	document.getElementById("cbrWriteSubmit").addEventListener("click",function(){
		var cbr_pay = document.getElementById("cbr_pay");
		
		if(cbr_pay.value == '' || cbr_pay.value == null){
			cbr_pay.value = 0.0;
		}
		document.getElementById("cbrWriteForm").submit();
	});
</script>
</html>