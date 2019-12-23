<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<c:set var="birth" value="${requestScope.info.cmi_age}" />
<c:set var="birthYear" value="${fn:substring(birth, 0, 4)}" />
<c:set var="birthMonth" value="${fn:substring(birth, 4, 6)}" />
<c:set var="birthDay" value="${fn:substring(birth, 6, 8)}" />

<c:set var="phone" value="${sessionScope.member.cm_phone}" />
<c:set var="phoneFirst" value="${fn:substring(phone, 0, 3)}" />
<c:set var="phoneMiddle" value="${fn:substring(phone, 3, 7)}" />
<c:set var="phoneLast" value="${fn:substring(phone, 7, 11)}" />
<!--드롭다운  -->
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<section class="hero is-primary is-fullheight-with-navbar">
	<form action="${pageContext.request.contextPath}/user/updateInfoSubmit"
		method="POST">
		<input type="hidden" name="cm_num"
			value="${sessionScope.member.cm_num}" />
		<div class="hero-body">
			<div class="container has-text-centered">
				<div class="columns is-8 is-variable">
					<div class="column is-one-thirds has-text-left">
						<h1 class="title is-1">정보 수정</h1>
						<p class="is-size-4">이력서 수정</p>
					</div>
					<div class="column is-two-third has-text-left">
						<div class="columns is-8 is-variable ">
							<div class="column is-two-thirds has-text-left"></div>
						</div>
						<div class="hero-body is-fullheight is-primary">
							<nav class="tabs is-boxed is-fullwidth is-large">
								<div class="container">
									<ul>
										<li class="tab is-active" onclick="openTab(event,'info1')"><a>1단계</a></li>
										<li class="tab" onclick="openTab(event,'info2')"><a>
												2단계</a></li>
										<li class="tab" onclick="openTab(event,'info3')"><a>3단계</a></li>
										<li class="tab" onclick="openTab(event,'info4')"><a>4단계</a></li>
										<li class="tab" onclick="openTab(event,'info5')"><a>5단계</a></li>
									</ul>
								</div>
							</nav>
						</div>
						<hr>
						<div id="info1" class="content-tab ">
							<div class="field">
								<label class="label has-text-white">이력서 제목</label> <input
									class="input is-medium is-4" type="text" name="cmi_title"
									value="${requestScope.info.cmi_title}" id="cmi_title" required />
								<div class="control"></div>
							</div>
							<article class="message">
								<div class="message-header">
									<p>Email</p>
								</div>
								<div class="message-body">${sessionScope.member.cm_email}
								</div>
							</article>
							<article class="message">
								<div class="message-header">
									<p>이름</p>
								</div>
								<div class="message-body">${sessionScope.member.cm_name}</div>
							</article>
							<article class="message">
								<div class="message-header">
									<p>생년월일 / 성별</p>
								</div>
								<div class="message-body">
									<c:if test="${!empty birth}">
										<c:out value="${birthYear}-${birthMonth}-${birthDay}" />
									</c:if>
									/ ${requestScope.info.cmi_gender eq 'Male' ? '남자' : '여자'}
								</div>
							</article>
							<div class="field">
								<label class="label has-text-white">연락처</label>
								<c:if test="${!empty  phone}">
									<input id="cm_phone_first" class="input inputPhone"
										style="width: 125px;" type="number" name="cm_phone_first"
										maxlength="3" value="${phoneFirst}" />
									<input class="input inputPhone" style="width: 125px;"
										type="number" name="cm_phone_middle" maxlength="4"
										value="${phoneMiddle}" />
									<input class="input is-on-fifth inputPhone"
										style="width: 125px;" type="number" name="cm_phone_last"
										maxlength="4" value="${phoneLast}" />
								</c:if>
								<div class="control"></div>
							</div>
							<div class="field">
								<label class="label has-text-white">주소</label>

								<div class="control">
									<input class="input is-medium" type="text" id="inputAddr"
										name="cm_addr" value="${sessionScope.member.cm_addr}" />
								</div>
							</div>
						</div>
						<div id="info2" class="content-tab" style="display: none">
							<div class="dropdown">
								<div class="dropdown-trigger">
									<button type="button" class="button is-inverted is-outlined"
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
													name="cij_code" value="${i.cj_code}"
													<c:forEach var="j" items="${requestScope.userJobs}">
												${j.key eq i.cj_code ? "checked" : ""}
											</c:forEach> />
													${i.cj_name}
												</label>
											</c:forEach>


										</div>

									</div>
								</div>
							</div>
							<div class="dropdown">
								<div class="dropdown-trigger">
									<button type="button" class="button is-inverted is-outlined"
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
													value="${i.cr_code}"
													<c:forEach var="j" items="${requestScope.userRegions}">	
												${j.key eq i.cr_code ? "checked" : ""}
											</c:forEach> />
													${i.cr_name}
												</label>
											</c:forEach>
										</a>

									</div>
								</div>

							</div>
						</div>
						<div id="info3" class="content-tab" style="display: none">
							<div class="field"></div>
							주 언어&nbsp;&nbsp;
							<div class="dropdown">
								<div class="dropdown-trigger">
									<button type="button" class="button is-inverted is-outlined"
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
													<label> <input type="checkbox" name="cms_code_work"
														value="${i.cs_code}"
														<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 0}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
									<button type="button" class="button is-inverted is-outlined"
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
													<label> <input type="checkbox" name="cms_code_work"
														value="${i.cs_code}"
														<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 0}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
									<button type="button" class="button is-inverted is-outlined"
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
													<label> <input type="checkbox" name="cms_code_work"
														value="${i.cs_code}"
														<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 0}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
									<button type="button" class="button is-inverted is-outlined"
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
													<label> <input type="checkbox" name="cms_code_work"
														value="${i.cs_code}"
														<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 0}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
									<button type="button" class="button is-inverted is-outlined"
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
													<label> <input type="checkbox" name="cms_code_work"
														value="${i.cs_code}"
														<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 0}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
								<button type="button" class="button is-inverted is-outlined"
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
												<label> <input type="checkbox" name="cms_code_work"
													value="${i.cs_code}"
													<c:forEach var="j" items="${requestScope.userSubjects}">
								<c:if test="${j.cms_category eq 1}">
								${j.cms_code eq i.cs_code ? "checked" : ""}
								</c:if>
							</c:forEach> />
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
								<button type="button" class="button is-inverted is-outlined"
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
												<label> <input type="checkbox" name="cms_code_work"
													value="${i.cs_code}"
													<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 1}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
								<button type="button" class="button is-inverted is-outlined"
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
												<label> <input type="checkbox" name="cms_code_work"
													value="${i.cs_code}"
													<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 1}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
								<button type="button" class="button is-inverted is-outlined"
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
												<label> <input type="checkbox" name="cms_code_work"
													value="${i.cs_code}"
													<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 1}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
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
								<button type="button" class="button is-inverted is-outlined"
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
												<label> <input type="checkbox" name="cms_code_work"
													value="${i.cs_code}"
													<c:forEach var="j" items="${requestScope.userSubjects}">
									<c:if test="${j.cms_category eq 1}">
											${j.cms_code eq i.cs_code ? "checked" : ""}
									</c:if>
								</c:forEach> />
													${i.cs_name}
												</label>
											</c:if>
										</c:forEach>

									</div>

								</div>
							</div>
						</div>
					
					</div>
					
					<div id="info4" class="content-tab" style="display: none">
						<div class="field">
							<label class="label has-text-white">경력사항</label>
							<textarea name="cmi_career" cols=50 rows=10
								placeholder="수상 경력, 자격증, 업무 경험 등...">${requestScope.info.cmi_career}</textarea>

						</div>

						<div class="field">
							<label class="label has-text-white">자기소개</label>
							<textarea name="cmi_intro" cols=30 rows=20
								placeholder="자신의 업무 외적 부분을 어필...">${requestScope.info.cmi_intro}</textarea>

						</div>

						
					</div>
					<div id="info5" class="content-tab" style="display: none">
						<div class="control">
							<label class="label has-text-white is ">정보 공개 여부</label> <label>
								<input type="radio" name="cmi_private" value="N"
								${requestScope.info.cmi_private eq 'N' ? "checked" : ""} /> 공개
							</label> <label> <input type="radio" name="cmi_private" value="Y"
								${requestScope.info.cmi_private eq 'Y' ? "checked" : ""} /> 비공개
							</label>
						</div>
						<hr>
						<input type="submit" value="수정 완료" /> <input type="button"
							onclick="javascript:location.href='${pageContext.request.contextPath}/user/home'"
							value="취소" />
					</div>
					
					</div>
				</div>
			</div>
		</div>

	</form>

</section>
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
