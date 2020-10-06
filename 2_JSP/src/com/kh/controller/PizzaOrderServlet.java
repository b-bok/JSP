package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaOrderServlet
 */
@WebServlet("/order.do")
public class PizzaOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// request(parameter)안에 요청시 전달 되는 값들이 키=value 셋트로 담겨있음
		
		// 1) 전달 값 중에 한글이 있을 경우 utf-8로 작업
		
		request.setCharacterEncoding("utf-8");
		
		// 2) 요청시 전달값 뽑기(request의 parameter영역으로부터)
		//		request.getParameter("키") : "밸류" (String)
		//		request.getParameterValues("키") : ["밸류","밸류"] (String[]타입)
		// 		공통적으로 해당 키 값을 찾지 못했을 경우 : null 반환
		
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message"); // "빨리와주셈" / ""
		
		String pizza = request.getParameter("pizza");
		
		String[] toppings = request.getParameterValues("topping"); //["",""] / null
		String[] sides = request.getParameterValues("side"); //["",""] / null
		
		
		// 3) 요청처리 (Service로 전달)
		
		int price = 0;	// 총 가격
		
		
		switch(pizza) {
		case "콤비네이션피자" : price += 5000; break;
		case "치즈피자" :  
		case "고구마피자" : price += 6000; break;
		case "불고기피자" : 
		case "페퍼로니피자" : price += 7000; break;
		}
		
		if(toppings !=null) {
			for(String topping: toppings) {
				switch(topping) {
				case "고구마무스" :
				case "콘크림무스" : price+= 1500; break;
				case "파인애플" :
				case "치즈토핑" : price+= 2000; break;
				case "치즈크러스트" :
				case "치즈바이트" : price+=4000; break;
				}
			}
		}
		
		if(sides != null) {
			for(String side : sides) {
				switch(side) {
				case "콜라" :
				case "사이다" : price +=1500; break;
				case "핫소스" :
				case "피클" :
				case "갈릭소스" :
				case "파마산치즈" : price += 500; break;
				}
			}
		}

		// 4) 사용자가 보게될 응답페이지 만들어주기 (JSP에게 위임)
		// 		응답페이지에 필요한 응답데이터를 request의 attribute 영역에 담아서 전달!
		
		request.setAttribute("userName", userName);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);	// ""
		
		request.setAttribute("pizza", pizza);
		request.setAttribute("toppings", toppings);	// null
		request.setAttribute("sides", sides);	// null
		request.setAttribute("price", price);
		
		
		// JSP에게 위임할때 필요한 객체 RequestDispatcher
		
		RequestDispatcher view = request.getRequestDispatcher("views/05_pizzaPayment.jsp");
		
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
