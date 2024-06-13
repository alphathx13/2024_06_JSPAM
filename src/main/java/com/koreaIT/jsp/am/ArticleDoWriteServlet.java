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

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
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

			String title = request.getParameter("title");
			String body = request.getParameter("body");

			SecSql sql = new SecSql();
			sql.append("INSERT INTO article");
			sql.append("SET regDATE = NOW(),");
			sql.append("updateDATE = NOW(),");
			sql.append("title = ?,", title);
		    sql.append("`body` = ?", body);
			
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
  	
