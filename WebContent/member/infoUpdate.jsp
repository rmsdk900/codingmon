<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="birth" value="${requestScope.info.cmi_age}"/>
<c:set var="birthYear" value="${fn:substring(birth, 0, 4)}"/>
<c:set var="birthMonth" value="${fn:substring(birth, 4, 6)}"/>
<c:set var="birthDay" value="${fn:substring(birth, 6, 8)}"/>

<c:set var="phone" value="${sessionScope.member.cm_phone}"/>
<c:set var="phoneFirst"      value="${fn:substring(phone, 0, 3)}" />
<c:set var="phoneMiddle"     value="${fn:substring(phone, 3, 7)}" />
<c:set var="phoneLast"     value="${fn:substring(phone, 7, 11)}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>코딩몬</title>
</head>
<body>
	<form action="updateInfoSubmit" method="POST">
		<input type="hidden" name="cm_num" value="${sessionScope.member.cm_num}"/>
		<h1>이력서 수정</h1>
		<table border=1>
			<tbody>
			<c:choose>
				<c:when test="${!empty requestScope.info 
				&& !empty requestScope.userJobs
				&& !empty requestScope.userRegions
				&& !empty requestScope.userSubjects
				}">	
					<tr>
						<th>이력서 제목</th>
						<td><input type="text" name="cmi_title" value="${requestScope.info.cmi_title}"/></td>
					</tr>
					<tr>
						<th>이메일(아이디)</th>
						<td>${sessionScope.member.cm_email}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${sessionScope.member.cm_name}</td>
					</tr>
					<tr>
						<th>생년월일/성별</th>
						<td>
						<c:if test="${!empty birth}">
							<c:out value="${birthYear}-${birthMonth}-${birthDay}"/>
						</c:if> / 
							${requestScope.info.cmi_gender eq 'Male' ? '남자' : '여자'}
						</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>
							<c:if test="${!empty  phone}">
								<input type="number" class="inputPhone" name="cm_phone_first" maxlength="3"/> -
								<input type="number" class="inputPhone" name="cm_phone_middle" maxlength="4"/> -
								<input type="number" class="inputPhone" name="cm_phone_last" maxlength="4"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" id="inputAddr" name="cm_addr" value="${sessionScope.member.cm_addr}"/>
						</td>
					</tr>
					<tr>
						<th>직업</th>
						<td>
							<div>
								<c:forEach var="i" items="${requestScope.ej}">
										<label>
											<input type="checkbox" class="cij_code" name="cij_code" value="${i.cj_code}" 
										<c:forEach var="j" items="${requestScope.userJobs}">
											${j.key eq i.cj_code ? "checked" : ""}
										</c:forEach>
											/>
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
											<input type="checkbox" name="cmr_code" value="${i.cr_code}" 
										<c:forEach var="j" items="${requestScope.userRegions}">	
											${j.key eq i.cr_code ? "checked" : ""}
										</c:forEach>	
											/>
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
												<input type="checkbox" name="cms_code_work" value="${i.cs_code}" 
										<c:forEach var="j" items="${requestScope.userSubjects}">
										<c:if test="${j.cms_category eq 0}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
										</c:if>
										</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_work" value="${i.cs_code}" 
										<c:forEach var="j" items="${requestScope.userSubjects}">
										<c:if test="${j.cms_category eq 0}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
										</c:if>
										</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_work" value="${i.cs_code}" 
										<c:forEach var="j" items="${requestScope.userSubjects}">
										<c:if test="${j.cms_category eq 0}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
										</c:if>
										</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_work" value="${i.cs_code}" 
										<c:forEach var="j" items="${requestScope.userSubjects}">
										<c:if test="${j.cms_category eq 0}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
										</c:if>
										</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_work" value="${i.cs_code}" 
										<c:forEach var="j" items="${requestScope.userSubjects}">
										<c:if test="${j.cms_category eq 0}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
										</c:if>
										</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_learning" value="${i.cs_code}" 
											<c:forEach var="j" items="${requestScope.userSubjects}">
												<c:if test="${j.cms_category eq 1}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
												</c:if>
											</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_learning" value="${i.cs_code}" 
											<c:forEach var="j" items="${requestScope.userSubjects}">
												<c:if test="${j.cms_category eq 1}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
												</c:if>
											</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_learning" value="${i.cs_code}" 
											<c:forEach var="j" items="${requestScope.userSubjects}">
												<c:if test="${j.cms_category eq 1}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
												</c:if>
											</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_learning" value="${i.cs_code}" 
											<c:forEach var="j" items="${requestScope.userSubjects}">
												<c:if test="${j.cms_category eq 1}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
												</c:if>
											</c:forEach>
												/>
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
												<input type="checkbox" name="cms_code_learning" value="${i.cs_code}" 
											<c:forEach var="j" items="${requestScope.userSubjects}">
												<c:if test="${j.cms_category eq 1}">
												${j.cms_code eq i.cs_code ? "checked" : ""}
												</c:if>
											</c:forEach>
												/>
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
							<textarea name="cmi_career" cols=50 rows=10 placeholder="수상 경력, 자격증, 업무 경험 등...">
								${requestScope.info.cmi_career}
							</textarea>
						</td>
					</tr>
					<tr>
						<th>자기소개</th>
						<td>
							<textarea name="cmi_intro" cols=50 rows=20 placeholder="자신의 업무 외적 부분을 어필...">
								${requestScope.info.cmi_intro}
							</textarea>
						</td>
					</tr>
					<tr>
						<th>정보 공개 여부</th>
						<td>
						
							<label>
								<input type="radio" name="cmi_private" value="N" ${requestScope.info.cmi_private eq 'N' ? "checked" : ""}/>
								공개
							</label>
							<label>
								<input type="radio" name="cmi_private" value="Y" ${requestScope.info.cmi_private eq 'Y' ? "checked" : ""}/>
								비공개
							</label>
						</td>
					</tr>
					<tr>
						<td colspan=2>
							<input type="submit" value="수정 완료"/>
							<input type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/user/home'" value="취소"/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan=2>해당 정보 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</form>
</body>
<script>
	$(function(){
		var count = 2;
		$(".inputPhone").keyup(function(){
				
			var charLimit = $(this).attr("maxlength");
			if(this.value.length >= charLimit){
				console.log(this.value.length);
				console.log("next : "+$(this).next());
				console.log(count);
				if(count != 0){
					$(this).next().focus();
					count--;
					
				}else{
					$("#inputAddr").focus();
				}
				
				return false;
			}
		});
	});

</script>
</html>