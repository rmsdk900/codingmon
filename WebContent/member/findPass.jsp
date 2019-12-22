<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="../common/header.jsp"/>
<section class="hero is-fullheight is-primary">

<div style="width:70%; margin:auto;">
	<form action="${pageContext.request.contextPath}/user/sendPw" method="post">
		
		<div class="tile is-parent is-vertical">
			<article class="tile is-child notification is-primary">
				<p class="">
					이메일 입력 <br/> 
					
					<input class= "input" type="email" name="cm_email" /> 
						<input class= "button is-light" type="submit" value="메일 발송" />
						
				</p>
			</article>
		</div>
	</form>
</div>
</section>


<jsp:include page="../common/footer.jsp"/>