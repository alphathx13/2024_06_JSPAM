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
import java.util.List;
import java.util.Map;

import com.koreaIT.jsp.am.util.*;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
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
			int itemsInPage = 10;
			int cPage;
			int tPage;
			List<Map<String, Object>> articleList;
			
			String inputPage = request.getParameter("page");
			if (inputPage == null || inputPage.length() == 0)
				cPage = 1;
			try {
				cPage = Integer.parseInt(inputPage);
			} catch (NumberFormatException e) {
				cPage = 1;
			}
			
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) FROM article");
			int tArticle = DBUtil.selectRowIntValue(conn, sql);
			
			tPage = tArticle % itemsInPage == 0 ? tArticle / itemsInPage : tArticle / itemsInPage + 1;
//			int tPage = (int) Math.ceil((double) tArticle / 10);	
			
			sql = new SecSql();
			sql.append("SELECT * FROM article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?", (cPage-1)*itemsInPage+1, itemsInPage);
			articleList = DBUtil.selectRows(conn, sql);
			
			request.setAttribute("articleList", articleList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("tPage", tPage);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
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