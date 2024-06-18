package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.koreaIT.jsp.am.config.Config;
import com.koreaIT.jsp.am.util.*;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	public Connection conn;

	{
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {		
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUsr(), Config.getDBPW());

			SecSql sql = new SecSql();
			sql.append("SELECT a.*, m.memberId `memberId` FROM article a");
			sql.append("INNER JOIN `member` m");
			sql.append("on a.memberNumber = m.memberNumber");
			sql.append("WHERE a.id = ?", request.getParameter("id"));
			sql.append("order by a.id desc");
			
			Map<String, Object> article = DBUtil.selectRow(conn, sql);
			
			HttpSession session = request.getSession();
			int loginMemberNumber = -1;
			
			if (session.getAttribute("loginMemberNumber") != null) 
				loginMemberNumber = (int) session.getAttribute("loginMemberNumber");
			
			request.setAttribute("loginMemberNumber", loginMemberNumber);
			request.setAttribute("loginMemberId", session.getAttribute("loginMemberId"));
			request.setAttribute("article", article);
				
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
  	
}