<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer>
<form action="delete.gh" method="post">
		<input type="text" name="table" placeholder="가즈아"/>
		<input type="submit" value="기헌테이블 삭제"/>
	</form>
			
	<form action="create.gh" method="post">
		<input type="text" name="tableUpdate" placeholder="가즈아"/>
		<!-- <textarea name="createGHTable" cols="50" rows="10" placeholder="sql넣기"></textarea> -->
		<input type="submit" value="테이블 sql"/>
	</form>
	Copyright &copy; since 2019.
</footer>
</body>
</html>