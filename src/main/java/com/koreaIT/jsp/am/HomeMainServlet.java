package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/main")
// URL맵핑 주소는 중복이 불가능하다!!

public class HomeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("", session);
		session.getAttribute("");
		// session은 request안에 들어있다!
		// 즉, 어디서나 접근할 수 있다!
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

}