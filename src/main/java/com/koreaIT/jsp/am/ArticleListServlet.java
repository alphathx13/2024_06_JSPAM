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
import java.util.List;
import java.util.Map;

import com.koreaIT.jsp.am.config.Config;
import com.koreaIT.jsp.am.util.*;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
	public Connection conn;
	static int itemsInPage;
	
	static {
		itemsInPage = 10;
	}

	{
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUsr(), Config.getDBPW());

			List<Map<String, Object>> articleList;
			int cPage;
			int tPage;
			
			if (request.getParameter("itemsInPage") != null && itemsInPage != Integer.parseInt(request.getParameter("itemsInPage")))
				itemsInPage = Integer.parseInt(request.getParameter("itemsInPage"));
			
			String inputPage = request.getParameter("page");
			if (inputPage == null || inputPage.length() == 0)
				cPage = 1;
			try {
				cPage = Integer.parseInt(inputPage);
			} catch (NumberFormatException e) {
				cPage = 1;
			}
			
			int from = ((cPage - 1) / 10) * 10 + 1;
			int end = (((cPage - 1) / 10) +1) * 10;
					
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*) FROM article");
			int tArticle = DBUtil.selectRowIntValue(conn, sql);
			
			tPage = tArticle % itemsInPage == 0 ? tArticle / itemsInPage : tArticle / itemsInPage + 1;
//			tPage = (int) Math.ceil((double) tArticle / 10);	
			
			sql = new SecSql();
			sql.append("SELECT a.*, m.memberId FROM article a");
			sql.append("INNER JOIN `member` m");
			sql.append("on a.memberNumber = m.memberNumber");
			sql.append("LIMIT ?, ?", (cPage-1)*itemsInPage, itemsInPage);
			articleList = DBUtil.selectRows(conn, sql);
			
			HttpSession session = request.getSession();
			int loginMemberNumber = -1;
			
			if (session.getAttribute("loginMemberNumber") != null) 
				loginMemberNumber = (int) session.getAttribute("loginMemberNumber");
			
			request.setAttribute("loginMemberNumber", loginMemberNumber);
			request.setAttribute("loginMemberId", session.getAttribute("loginMemberId"));
			request.setAttribute("articleList", articleList);
			request.setAttribute("cPage", cPage);
			request.setAttribute("tPage", tPage);
			request.setAttribute("from", from);
			request.setAttribute("end", end);
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
  	
}