<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
table{
	border : 1px solid blue;
	margin : 50px;
}

td{
	width : 200px;
	height : 50px;
	text-align : center;
}
.title{
	background : lightblue;
}

</style>
</head>
<body>
<h1>JSP : Java Server Page </h1>

<%
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userTel = request.getParameter("tel");
	String userAddr = request.getParameter("addr");
	String uesrAge = request.getParameter("age");
	String userFile = request.getParameter("file");
	
	//db crud처리 결과를 얻어, 응답 데이터를 생성한다.
%>
<table border = "1"> <!--세로줄이 제목-->
	<tr>
		<td class="title">아이디</td>
		<td><%=userId %></td>
	</tr>
	
	<tr>
		<td class="title">이름</td>
		<td><%=userName %></td>
	</tr>
		
	<tr>
		<td class="title">전화번호</td>
		<td><%=userTel %></td>
	</tr>
		
	<tr>
		<td class="title">주소</td>
		<td><%=userAddr %></td>
	</tr>
		
	<tr>
		<td class="title">나이</td>
		<td><%=uesrAge %></td>
	</tr>
	
	<tr>
		<td class="title">첨부파일</td>
		<td><%= userFile %></td>
	</tr>
	
	
</table>

</body>
</html>