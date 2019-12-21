<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/board/rec/update_info" method="post">
		<input type="hidden" name="cbr_num" value="${cbr_num}" />
		<h2>부가 정보 수정</h2>
		<table>
			<tr>
				<th>직업</th>
					<td>
						<div>
							<label>
								<input type="checkbox" name="crj_code" value="1"/>
								프론트엔드
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="2"/>
								백엔드
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="3"/>
								풀 스택
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="4"/>
								모바일
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="5"/>
								데이터&amp;머신러닝 전문가
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="6"/>
								데스크탑 어플리케이션
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="7"/>
								게임
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="8"/>
								임베디드 어플리케이션
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="101"/>
								시스템 관리자
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="102"/>
								CEO/CTO
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="103"/>
								제품 또는 기술 매니저
							</label>
							<label>
								<input type="checkbox" name="crj_code" value="999"/>
								기타
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>지역</th>
					<td>
						<div>
							<label>
								<input type="checkbox" name="crr_code" value="1"/>
								서울특별시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="2"/>
								부산광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="3"/>
								대구광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="4"/>
								인천광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="5"/>
								광주광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="6"/>
								대전광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="7"/>
								울산광역시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="8"/>
								세종특별자치시
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="9"/>
								경기도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="10"/>
								강원도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="11"/>
								충청북도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="12"/>
								충청남도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="13"/>
								전라북도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="14"/>
								전라남도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="15"/>
								경상북도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="16"/>
								경상남도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="17"/>
								제주특별자치도
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="99"/>
								전국
							</label>
							<label>
								<input type="checkbox" name="crr_code" value="100"/>
								해외
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<th>필요 언어</th>
						<td>
						<div>
							<h3>Java</h3>
							<label>
								<input type="checkbox" name="crs_code" value="1"/>
								Java
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="2"/>
								Spring
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="3"/>
								전자정부표준
							</label>
						</div>
						<div>
							<h3>C</h3>
							<label>
								<input type="checkbox" name="crs_code" value="11"/>
								C
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="12"/>
								C++
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="13"/>
								C#
							</label>
						</div>
						<div>
							<h3>Javascript</h3>
							<label>
								<input type="checkbox" name="crs_code" value="21"/>
								Vanila JS
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="22"/>
								Vue
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="23"/>
								Angular
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="24"/>
								React
							</label>
						</div>
						<div>
							<h3>Python</h3>
							<label>
								<input type="checkbox" name="crs_code" value="31"/>
								Python
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="32"/>
								Flask
							</label>
							<label>
								<input type="checkbox" name="crs_code" value="33"/>
								Django
							</label>
						</div>
						<div>
							<h3>ETC</h3>
							<label>
								<input type="checkbox" name="crs_code" value="91"/>
								Ruby
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<h3>임금 구분</h3>
							<label>
								<input type="checkbox" name="crp_code" value="1" />
								시급
							</label>
							<label>
								<input type="checkbox" name="crp_code" value="2" />
								일급
							</label>
							<label>
								<input type="checkbox" name="crp_code" value="3" />
								월급		
							</label>
							<label>
								<input type="checkbox" name="crp_code" value="4" />
								연봉
							</label>
						</div>
					</td>
				</tr>
			<tr>
				<td colspan=2>
					<input type="submit" value="작성 완료" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>