<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="insertInfo" method="post">
		<input type="hidden" name="cm_num" value="${requestScope.joinMember.cm_num}"/>
		<table>
			<tbody>
				<tr>
					<th>생년월일</th>
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
							<label>
								<input type="checkbox" name="cij_code" value="1"/>
								프론트엔드
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="2"/>
								백엔드
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="3"/>
								풀 스택
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="4"/>
								모바일
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="5"/>
								데이터&머신러닝 전문가
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="6"/>
								데스크탑 어플리케이션
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="7"/>
								게임
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="8"/>
								임베디드 어플리케이션
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="101"/>
								시스템 관리자
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="102"/>
								CEO/CTO
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="103"/>
								제품 또는 기술 매니저
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="104"/>
								교육가
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="105"/>
								학생
							</label>
							<label>
								<input type="checkbox" name="cij_code" value="999"/>
								기타
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>선호 지역</th>
					<td>
						<div>
							<label>
								<input type="checkbox" name="cmr_code" value="1"/>
								서울특별시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="2"/>
								부산광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="3"/>
								대구광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="4"/>
								인천광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="5"/>
								광주광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="6"/>
								대전광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="7"/>
								울산광역시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="8"/>
								세종특별자치시
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="9"/>
								경기도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="10"/>
								강원도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="11"/>
								충청북도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="12"/>
								충청남도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="13"/>
								전라북도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="14"/>
								전라남도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="15"/>
								경상북도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="16"/>
								경상남도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="17"/>
								제주특별자치도
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="99"/>
								전국
							</label>
							<label>
								<input type="checkbox" name="cmr_code" value="100"/>
								해외
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>주 언어</th>
					<td>
						<div>
							<h3>Java</h3>
							<label>
								<input type="checkbox" name="cms_code_work" value="1"/>
								Java
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="2"/>
								Spring
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="3"/>
								전자정부표준
							</label>
						</div>
						<div>
							<h3>C</h3>
							<label>
								<input type="checkbox" name="cms_code_work" value="11"/>
								C
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="12"/>
								C++
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="13"/>
								C#
							</label>
						</div>
						<div>
							<h3>Javascript</h3>
							<label>
								<input type="checkbox" name="cms_code_work" value="21"/>
								Vanila JS
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="22"/>
								Vue
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="23"/>
								Angular
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="24"/>
								React
							</label>
						</div>
						<div>
							<h3>Python</h3>
							<label>
								<input type="checkbox" name="cms_code_work" value="31"/>
								Python
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="32"/>
								Flask
							</label>
							<label>
								<input type="checkbox" name="cms_code_work" value="33"/>
								Django
							</label>
						</div>
						<div>
							<h3>ETC</h3>
							<label>
								<input type="checkbox" name="cms_code_work" value="91"/>
								Ruby
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>학습 중인 언어</th>
					<td>
						<div>
							<h3>Java</h3>
							<label>
								<input type="checkbox" name="cms_code_learning" value="1"/>
								Java
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="2"/>
								Spring
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="3"/>
								전자정부표준
							</label>
						</div>
						<div>
							<h3>C</h3>
							<label>
								<input type="checkbox" name="cms_code_learning" value="11"/>
								C
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="12"/>
								C++
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="13"/>
								C#
							</label>
						</div>
						<div>
							<h3>Javascript</h3>
							<label>
								<input type="checkbox" name="cms_code_learning" value="21"/>
								Vanila JS
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="22"/>
								Vue
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="23"/>
								Angular
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="24"/>
								React
							</label>
						</div>
						<div>
							<h3>Python</h3>
							<label>
								<input type="checkbox" name="cms_code_learning" value="31"/>
								Python
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="32"/>
								Flask
							</label>
							<label>
								<input type="checkbox" name="cms_code_learning" value="33"/>
								Django
							</label>
						</div>
						<div>
							<h3>ETC</h3>
							<label>
								<input type="checkbox" name="cms_code_learning" value="91"/>
								Ruby
							</label>
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
						<input type="submit" value="작성 완료"/>
						<input type="reset" value="수정"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>