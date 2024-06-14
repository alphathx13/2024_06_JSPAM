package com.koreaIT.jsp.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.koreaIT.jsp.am.util.*;
import com.koreaIT.jsp.am.config.*;

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {
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
			
			sql.append("DELETE FROM article");
			sql.append("WHERE ID = ?", request.getParameter("id"));
			
			DBUtil.delete(conn, sql);

			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append(String.format("<script> alert('%s번 글이 삭제되었습니다.'); location.replace('list');</script>", request.getParameter("id")));
			
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