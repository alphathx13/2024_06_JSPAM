package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/main")

public class HomeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int loginMemberNumber = -1;
		
		if (session.getAttribute("loginMemberNumber") != null) 
			loginMemberNumber = (int) session.getAttribute("loginMemberNumber");
		
		request.setAttribute("loginMemberNumber", loginMemberNumber);
		request.setAttribute("loginMemberId", session.getAttribute("loginMemberId"));
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

}