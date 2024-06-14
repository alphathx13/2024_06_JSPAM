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

import com.koreaIT.jsp.am.config.Config;
import com.koreaIT.jsp.am.util.*;

@WebServlet("/article/doModify")
public class ArticleDoModifyServlet extends HttpServlet {
	public Connection conn;

	{
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8;");
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String id = request.getParameter("id");
		
		try {
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUsr(), Config.getDBPW());

			SecSql sql = new SecSql();
			
			sql.append("update article");
			sql.append("set title = ?,", title);
			sql.append("updateDate = NOW(),");
			sql.append("`body` = ?", body);
			sql.append("WHERE ID = ?", id);
			
			DBUtil.update(conn, sql);

			response.getWriter().append(String.format("<script> alert('%s번 글을 수정하였습니다.'); location.replace('detail?id=%s');</script>", id, id));
			
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
  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}