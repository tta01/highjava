<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>3
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

<h1>JSP : Java Server Page</h1>

<%
	request.setCharacterEncoding("UTF-8");
	
	String userName = request.getParameter("name");
	String gender = request.getParameter("gender");
	
	String fruits[] = request.getParameterValues("fruit");
	
	String str = "";
	
	for(String fr : fruits) {
		str += fr + "&nbsp&nbsp";
	}
%>
<table border = "1"> 
	
	<tr>
		<td class="title">이름</td> <!-- 동시에 여러 개를 똑같은 스타일을 줄 때 클래스를 이용함 스타일에서는 .title 쩜을 붙여서 사용함-->
		<td><%=userName %></td>
	</tr>
		
	<tr>
		<td class="title">성별</td>
		<td><%=gender %></td>
	</tr>
		
	<tr>
		<td class="title">좋아하는 과일</td>
		<td><%=str %></td>
	</tr>
	
</table>

</body>
</html>