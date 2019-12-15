<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="../common/header.jsp"/>
<section>
	<form action="sendPw" method="post">
		<table>
			<tr>
				<td>이메일(아이디)</td>
				<td><input type="email" name="cm_email"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="메일발송"/></td>
			</tr>
			
		</table>
	</form>

</section>


<jsp:include page="../common/footer.jsp"/>