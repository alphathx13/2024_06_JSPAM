package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/article/write")
public class ArticleWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMemberNumber") == null) { 
			response.setContentType("text/html; charset=UTF-8;");
			response.getWriter().append("<script> alert('로그인 되어있지 않습니다. 로그인 해주세요.'); location.replace('/member/login'); </script>");
			return;
		}
		
		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
	}
}