<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
table {
	border : 2px solid pink;

}
td {
 width : 300px;
 height : 50px;
 text-align : center;
}

.title {
	background : pink;
}

</style>

</head>
<body>

<h1> JSP : Java Server Page </h1>

<%
	request.setCharacterEncoding("UTF-8"); //한글로 변형시키는 방법

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	String userCar = request.getParameter("car");
	
	String cars[] = request.getParameterValues("cars");
	
	String str = ""; // 초기값 null해도 되는데 그럼 null도 출력됨
	
	for(String car : cars) {
		str += car + "&nbsp;&nbsp;&nbsp;"; //<br> 써도 됨
	}
%>

<table border="1">
	<tr>
	 <td class = "title">아이디</td>
	 <td><%= userId %></td>
	</tr>
	
	<tr>
	 <td class = "title">이름</td>
	 <td><%= userName %></td>
	</tr>
	
	<tr>
	 <td class = "title">자동차</td>
	 <td><%= userCar %></td>
	</tr>
	
	<tr>
	 <td class = "title">자동차들</td>
	 <td><%= str %></td>
	</tr>
	

</table>

</body>
</html>