package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Get방식으로 요청했따면, 이 doGet메소드가 내부적으로 호출 됨!
		 * 
		 * HttpServletRequest request : 요청시 전달된 내용 (사용자가 입력한 값, 요청전송방식, 요청한 사용자의 ip 등등)
		 * HttpServletResponse response : 요청 처리 후 응답 할 때 사용하는 객체 (즉, 응답페이지에 대한 작업시 사용)
		 * 
		 * 우선 요청 처리하기 위해 전달된 값들을 뽑아야함! (request에 담겨있음!)
		 * > 요청시 전달값들은 request안의 "parameter"영역안에
		 * 	 데이터가 (키 = 밸류) 형태로 무더기로 담겨있음 (name속성값 = value값)
		 * 
		 * 	따라서 request로 부터 값들을 뽑을 수 있는 메소드
		 * 	request.getParameter("키값"); : 키 값에 해당하는 value 값 리턴 (단, String으로 리턴 (즉, 문자열로)
		 *  request.getParameterValues("키값") : 키 값에 해당하는 value값들이 배열에 담겨 리턴
 		 */
		
		String name = request.getParameter("name"); // "홍길동"	/ ""
		String gender = request.getParameter("gender"); // "M" "F" / null
		int age = Integer.parseInt(request.getParameter("age"));	// "10" => 10	/ "" => 오류(NumberFormatException)
		String city = request.getParameter("city");					// "서울", "강원도"
		double height = Double.parseDouble(request.getParameter("height"));	//"170" => 170.0
		
		//체크박스 같은 복수 개의 정보를 받을 때는 키값 제시해서 뽑힌 value 값들을 배열로 받아낼 수 있음!
		String[] foods = request.getParameterValues("food"); // ["한식","양식"] / null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city :" + city);
		System.out.println("height : " + height);
		
		if(foods != null) {
			
			for(int i=0;i<foods.length;i++) {
			
				System.out.println("foods["+i+"] : " + foods[i] );
			}

		}else {
			System.out.println("좋아하는 음식 없음");
		}
		
		
		// 뽑아낸 값들을 --> service --> Dao --> sql문 실행
		// 위와 같이 요청 처리 했다는 가정하에
		// int result = new MemberService().insertMember(name, age, ..);
		/*
		 * if(result > 0) {// 성공
		 * 
		 * }else {// 실패
		 * }
		 */
		
		// 다 요청처리 했다는 가정하에 그에 해당하는 응답페이지를 만들어서 사용자에게 전달하기
		
		// Servlet이 하는 역할 : 요청처리 다하고 나서 그에 해당하는 응답페이지를 "만들어서" 사용자에게 출력
		// 즉, 여기 Java코드 내에 사용자가 보게될 응답 html만드는 구문 작성해야함!
		
		// 장점 : Java코드 내에 작성하기 때문에 반복문, 조건문, 유용한 메소드 활용 할 수 있음!
		// 단점 : 복잡함!, 혹시나 html을 수정하고자 했을 대 수정을 Java코드 내에서 자바코드를 수정하는 것이기 때문에
		//		 수정된 내용을 다시 반영시키고자 한다면, 서버 재실행 해야함!
		
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
		
		out.println("<h2>개인정보결과(GET) 응답화면 </h2>");
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
