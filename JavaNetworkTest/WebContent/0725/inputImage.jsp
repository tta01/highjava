<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
div {
	font-size : 2.0em;
	background : yellow;
}

span{
	color : red;
}
h1 {
color : blue;
}
</style>
</head>
<body>
<h1>JSP : Java Server Page</h1>
<%
	String userId = request.getParameter("id");
	String userPass = request.getParameter("pass");
	
	//데이터 처리
	
%>
<div><span><%= userId %></span>님 환영합니다!</div>

</body>
</html>