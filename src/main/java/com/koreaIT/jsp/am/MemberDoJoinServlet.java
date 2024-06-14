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

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
	public Connection conn;

	{
		conn = null;
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8;");
		
		try {
			Class.forName(Config.getDBDriverName());
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUsr(), Config.getDBPW());
			
			String memberId = request.getParameter("id");
			String memberPassword = request.getParameter("password");
			String name = request.getParameter("name");
			
			SecSql sql = new SecSql();
			sql.append("select COUNT(*) from `member`");
			sql.append("where memberId = ?", memberId);
			
			if ((int) DBUtil.selectRow(conn, sql).get("COUNT(*)") == 1) 
				response.getWriter().append(String.format("<script> alert('입력하신 [%s]은(는) 이미 사용중인 아이디입니다.'); history.back(); </script>", memberId));				
			
			sql = new SecSql();
			sql.append("INSERT INTO `member`");
			sql.append("SET memberId = ?", memberId);
			sql.append(", memberPassword = ?", memberPassword);
			sql.append(", name = ?", name);
			sql.append(", regDate = NOW()");
			sql.append(", updateDate = NOW()");

			DBUtil.insert(conn, sql);
			
			response.getWriter().append(String.format("<script> alert('%s님 회원가입을 축하합니다.'); location.replace('/home/main'); </script>", name));				

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
  	
