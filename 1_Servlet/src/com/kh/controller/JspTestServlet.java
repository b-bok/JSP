package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JspTestServlet
 */
@WebServlet("/test3.do")
public class JspTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JspTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청시 전달값 => request의 parameter영역에 담겨있음
		// 즉, 요청시 전달값 뽑을 때 request.getParameter("키"),request.getParameterValues("키")
		
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
		System.out.println("city : " + city );
		System.out.println("height : " + height);
		
		if(foods == null) {
			
			System.out.println("좋아하는게 없다....");
		}else {
			
			/*
			 * for(int i=0; i<foods.length;i++) { System.out.println("foods : " + foods[i]);
			 * }
			 */
			
			for(String str : foods) {
				System.out.println(str);
			}
			
			
			// 요청처리 : Service > Dao > sql문
			
			// 요청처리 다 했다는 가정하에 사용자가 볼 페이지 만들기 
			// Servlet에서 응답html 작성 및 출력
			
			// 응답페이지를 만들어서 사용자에게 출력했던 Servlet이 하는 일을 JSP에게 위임
			// 단, 응답화면(jsp)에서 보여져야할 데이터(필요한 데이터)들은 담아서 보내줘야함
			// 담을 공간 => request의 attribute 영역에 담아서 전달하면됨 (키-밸류 세트로 담아야함)
			
			//request.setAttribute("키값", 벨류값);
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			request.setAttribute("city", city);
			request.setAttribute("height", height);
			request.setAttribute("gender", gender);
			
			request.setAttribute("foods", foods);
			

			// 위임시 필요한 객체 : RequestDispatcher 클래스
			// 응답할 뷰(응답할 페이지jsp)를 선택하면서 RequestDispatcher객체 생성
			RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
			
			//선택된 view를 포워딩
			// request 전달 이유 : 그 응답 페이지에 필요한  데이터 request영역에 담아 놓았기 때문에
			// request 전달하는 이유 : 응답 페이지 만들때 
			view.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
