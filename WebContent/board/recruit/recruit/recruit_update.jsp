<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${RecruitVO.cbr_num}</title>

<!-- 날짜 입력창 관련 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
  $(function(){
     $.datepicker.setDefaults($.datepicker.regional['ko']);

     $('.cbr_date').datepicker({
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
          changeMonth: true                 // 월을 이동하기 위한 선택상자 표시여부
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
		<h1>recruit 게시판 수정</h1>
		<form action="${pageContext.request.contextPath}/board/rec/update_submit" method="post">
			<input type="hidden" name="cbr_num" value="${RecruitVO.cbr_num}" />
			<input type="hidden" name="cbr_writer_num" value="${RecruitVO.cbr_writer_num}" />
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="cbr_writer" value="${RecruitVO.cbr_writer}" readonly/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="cbr_title" value="${RecruitVO.cbr_title}" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="cbr_content" cols=50 rows=10>${RecruitVO.cbr_content}</textarea>
					</td>
				</tr>
				<tr>
	               <td>pay</td>
	               <td><input type="number" id="cbr_pay" name="cbr_pay" /></td>
            	</tr>
				<tr>
					<td>작업 시작일</td>
					<td><input type="text" class="cbr_date" name="cbr_date_from" value="${RecruitVO.cbr_date_from}" /></td>
				</tr>
				<tr>
					<td>작업 종료일</td>
					<td><input type="text" class="cbr_date" name="cbr_date_to" value="${RecruitVO.cbr_date_to}" /></td>
				</tr>
				<tr>
					<td>글 게시 종료일</td>
					<td><input type="text" class="cbr_date" name="cbr_deadline" value="${RecruitVO.cbr_deadline}" /></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="수정 등록" />
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/board/rec/list">목록으로</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>