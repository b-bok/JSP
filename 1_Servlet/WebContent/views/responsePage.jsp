<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	// 이 구문을 스크립트슬릿 => 자바코드를 자유롭게 쓸 수 있는 여역	
    	// 이 응답페이지에서 꺼내 쓰라고 request영역의 attribute영역에 데이터들 담아 놓은 구문
    	// request에 setAttribute("키",벨류)로 담은 값을 getAttribute
    	// getAttribute("키")뽑을 수 있음 (벨류값 반환시, object)
    	
    	String name = (String)request.getAttribute("name");
    	
    	int age = (int)request.getAttribute("age");
    	
    	String city = (String)request.getAttribute("city");
    	
    	double height = (double)request.getAttribute("height");
    	
    	String gender = (String)request.getAttribute("gender");
    				
    	String[] foods = (String[])request.getAttribute("foods");		
    			
    	
    %>
    
    
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{color:red}
	#name{color:orange}
	#age{color:yellow}
	#city{color:green}
	#height{color:blue}
	#gender{color:violet}

</style>
</head>
<body>

	<!-- JspTestServlet에서 요청 처리 후에 보게될 응답 페이지 -->
	<h2>개인정보결과 응답화면</h2>
	
	<!--  표현식, 출력식 -->
	<span id="name"><%= name %></span>님은
	<span id="age"><%= age %></span>살이며,
	<span id="city"><%= city %></span>에 사는,
	키는 <span id="height"><%= height %></span>cm이고,
	
	성별은
	<% if(gender == null){ %>
		선택 안했소.. <br>
	<% }else { %>	
		
		<%if(gender.equals("M")) { %>
		<span id="gender">남자</span>입니다. <br />
		<%}else{ %>
		
		<span id="gender">여자</span>입둥      <br />
		<% } %>
	<% } %>
		
	
	좋아하는 음식은
	<% if(foods == null) { %>
		없습니다.
	<% }else { %>
		
		<ul>
		
		<%for(int i = 0; i<foods.length;i++) { %>
		
			<li><%= foods[i] %></li>
			
		<% } %>	
		
		</ul>
			
	<%} %>
	
	
</body>
</html>