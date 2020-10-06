<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Date now = new Date();
		// 2020년 10월 06일
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일");
		
		String today = sdf.format(now);
		
		String msg = "하이욤";
		
	%>
	
	<h5><%=today%></h5>

</body>
</html>