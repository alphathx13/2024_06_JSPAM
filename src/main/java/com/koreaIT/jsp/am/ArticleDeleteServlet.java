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

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {
	private final String URL;
	private final String USER;
	private final String PASSWORD;
	public Connection conn;

	{
		URL = "jdbc:mysql://localhost:3306/jsp_am?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
		USER = "root";
		PASSWORD = "";
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			SecSql sql = new SecSql();
			
			sql.append("DELETE FROM article");
			sql.append("WHERE ID = ?", request.getParameter("id"));
			
			DBUtil.delete(conn, sql);
			
//			PrintWriter out = response.getWriter();
//			out.printf("<script> alert('%s번 글이 삭제되었습니다.') </script>", request.getParameter("id"));
//			out.flush();

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