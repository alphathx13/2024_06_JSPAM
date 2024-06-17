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

import com.koreaIT.jsp.am.config.Config;
import com.koreaIT.jsp.am.util.*;

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	public Connection conn;

	{
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			HttpSession session = request.getSession();
			int loginMemberNumber = -1;
			
			if (session.getAttribute("loginMemberNumber") != null) 
				loginMemberNumber = (int) session.getAttribute("loginMemberNumber");
			
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUsr(), Config.getDBPW());

			String title = request.getParameter("title");
			String body = request.getParameter("body");

			SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append("SET regDATE = NOW()");
			sql.append(", updateDATE = NOW()");
			sql.append(", title = ?", title);
		    sql.append(", `body` = ?", body);
		    sql.append(", writer = ?", loginMemberNumber);
			
			DBUtil.insert(conn, sql);

			sql = new SecSql();
			sql.append("SELECT * FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT 1");
			
			int writeId = (int) DBUtil.selectRow(conn, sql).get("id");
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append(String.format("<script> alert('%d번 글을 작성하였습니다.'); location.replace('list');</script>", writeId));
			
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
  	
