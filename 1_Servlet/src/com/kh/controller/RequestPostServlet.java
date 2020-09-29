package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doGet실행");
		
		// 요청시 전달값들을 뽑아서 변수에 기록해두기
		// 요청시 전달값 : request의 parameter 영역안에 담겨있음
		
		//**** POST방식 같은 경우 request에 담겨있는 값 뽑기 전에 !!!  ****
		// 인코딩 utf-8로 작업할것!!
		request.setCharacterEncoding("utf-8");    
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		String[] foods = request.getParameterValues("food");
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods == null) {
			System.out.println("좋아하는게 없수다!");
		}else {
			for(int i = 0; i<foods.length;i++) {
				
				System.out.println(foods[i]);
			}
			System.out.println("를 좋아한다!~");
		}
		
		// 요청 처리 : > service > Dao > sql문
		
		// 요청처리 다 했다는 가정하에 응답페이지 만들어서 전달
		//* response객체를 통해 사용자에게 html(응답화면) 전달
		
				// 1) 이제부터 내가 전달할 내용은 문서형태의 html이고, 문자셋은 utf-8이다 라는 걸 지정
				response.setContentType("text/html; charset=utf-8");
				// 2) 응답하고자 하는 사용자 (요청했던 사용자)와의 스트림(클라이언트와의 길) 생성
				PrintWriter out = response.getWriter(); // out == 스트림 == 통로 == 길
				// 3) 저 스트림(out)을 통해서 html을 한줄씩 출력
				out.println("<html>");
				out.println("<head>");
				out.println("<title>개인정보출력화면</title>");
				out.println("<style>");
				out.println("h2{color:red}");
				out.println("#name{color:orange; font-weight:bold}");
				out.println("#age{color:yellow;}");
				out.println("#city{color:green;}");
				out.println("#height{color:blue;}");
				out.println("#gender{color:violet;}");
				out.println("</style>");
				out.println("</head>");
				
				out.println("<body>");
				
				out.println("<h2>개인정보결과(POST) 응답화면 </h2>");
				
				// <span id='name'>XXX</span>님은
				//out.println("<span id='name'>" + name + "</span>님은 ");
				out.printf("<span id='name'>%s</span>님은 ", name);
				out.printf("<span id='age'>%d</span>살이며", age);
				out.printf("<span id='city'>%s</span>에사는 ", city);
				out.printf("<span id='height'>%.1f</span>cm이며 ", height);
				
				
				out.print("성별은 ");
				// Java코드내에 html문을 만들고 있기 때문에 조건문 및 메소드 사용 가능!
				if(gender == null) {
					out.print("선택을 안했네요 <br>");
				}else {
					if(gender.equals("M")) {
						out.print("<span id='gender'>남자입니다.</span>");
					}else {
						out.print("<span id='gender'>여자입니다.</span>");
					}
				}
				
				out.print("<br>좋아하는 음식은 ");
				
				if(foods == null) {
					out.print("없네요...");
				}else {
					out.print("<ul>");
					
					for(int i = 0; i<foods.length;i++) {
						
						out.print("<li>" + foods[i] + "</li>");
						
					}
					
					out.print("</ul>");
					out.print("을 좋아합니다.");
				}
				
				
				
				out.println("</body>");
				out.println("</html>");
				
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doPost실행");
		doGet(request, response);
	}

}
