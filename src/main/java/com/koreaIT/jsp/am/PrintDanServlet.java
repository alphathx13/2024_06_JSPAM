package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/printDan")
public class PrintDanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8;");
		
		String inputDan = request.getParameter("dan");
		String inputLimit = request.getParameter("limit");
		String inputColor = request.getParameter("color");

		if (inputDan == null) {
			inputDan = "1";
		} else if (inputLimit == null) { 
			inputLimit = "1";
		} else if (inputColor == null || inputColor == "") 
			inputColor = "black";

		// http://localhost:8080/printDan?dan=6&limit=11&color=
		// http://localhost:8080/printDan?dan=6&limit=11&blue
		// 2개의 차이를 좀 생각해보자
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		response.getWriter().append(String.format("<div style = \"color : %s;\"> == %d단 == </div>", inputColor, dan));

		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style = \"color : %s;\">%d * %d = %d</div>", inputColor, dan, i, dan*i));
		}
	
	}

}