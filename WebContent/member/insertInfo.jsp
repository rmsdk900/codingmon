<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 날짜 입력창 관련 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
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
	<form id="insertInfoForm" action="insertInfo" method="post">
		<input type="hidden" name="cm_num" value="${sessionScope.joinMember.cm_num}"/>
		<h1>이력서 입력</h1>
		<table border=1>
			<tbody>
				<tr>
					<th>제목</th>
					<td><input type="text" id="cmi_title" name="cmi_title" placeholder="기본 : ${sessionScope.joinMember.cm_name}의 이력서"/></td>
				</tr>
				<tr>
					<th>생년월일 *</th>
					<td><input type="text" id="cmi_age" name="cmi_age" required/></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<label>
							<input type="radio" name="cmi_gender" value="Male" checked/>
							남성
						</label>
						<label>
							<input type="radio" name="cmi_gender" value="Female" />
							여성
						</label>
					</td>
				</tr>
				<tr>
					<th>직업</th>
					<td>
						<div>
							<c:forEach var="i" items="${requestScope.ej}">
										<label>
											<input type="checkbox" class="cij_code" name="cij_code" value="${i.cj_code}"/>
											${i.cj_name}
										</label>
								</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<th>선호 지역</th>
					<td>
						<div>
							<c:forEach var="i" items="${requestScope.er}">
										<label>
											<input type="checkbox" name="cmr_code" value="${i.cr_code}"/>
											${i.cr_name}
										</label>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<th>주 언어</th>
					<td>
						<div>
							<h3>Java</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 1}">
									<label>
										<input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>C 언어</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 2}">
									<label>
										<input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>Javascript</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 3}">
									<label>
										<input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>Python</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 4}">
									<label>
										<input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>ETC</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 5}">
									<label>
										<input type="checkbox" name="cms_code_work" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<th>학습 중인 언어</th>
					<td>
						<div>
							<h3>Java</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 1}">
									<label>
										<input type="checkbox" name="cms_code_learning" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>C 언어</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 2}">
									<label>
										<input type="checkbox" name="cms_code_learning" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>Javascript</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 3}">
									<label>
										<input type="checkbox" name="cms_code_learning" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>Python</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 4}">
									<label>
										<input type="checkbox" name="cms_code_learning" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
						<div>
							<h3>ETC</h3>
							<c:forEach var="i" items="${requestScope.es}">
								<c:if test="${i.cs_category eq 5}">
									<label>
										<input type="checkbox" name="cms_code_learning" value="${i.cs_code}"/>
										${i.cs_name}
									</label>
								</c:if>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
					<th>경력사항</th>
					<td>
						<textarea name="cmi_career" cols=50 rows=10 placeholder="수상 경력, 자격증, 업무 경험 등..."></textarea>
					</td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td>
						<textarea name="cmi_intro" cols=50 rows=20 placeholder="자신의 업무 외적 부분을 어필..."></textarea>
					</td>
				</tr>
				<tr>
					<th>정보 공개 여부</th>
					<td>
						<label>
							<input type="radio" name="cmi_private" value="N" checked/>
							공개
						</label>
						<label>
							<input type="radio" name="cmi_private" value="Y" />
							비공개
						</label>
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="button" id="infoSubmit" value="작성 완료"/>
						<input type="reset" value="수정"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script>
	document.getElementById("infoSubmit").addEventListener("click", function(){
		var cmi_title = document.getElementById("cmi_title");
		if(cmi_title.value == '' || cmi_title.value == null){
			cmi_title.value = "'${sessionScope.joinMember.cm_name}'의 이력서";
		}
		
		document.getElementById("insertInfoForm").submit();
	});
</script>
</html>