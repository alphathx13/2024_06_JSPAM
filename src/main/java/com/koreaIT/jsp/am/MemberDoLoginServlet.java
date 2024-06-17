package com.koreaIT.jsp.am;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.koreaIT.jsp.am.config.Config;
import com.koreaIT.jsp.am.util.DBUtil;
import com.koreaIT.jsp.am.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
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
			String memberPw = request.getParameter("pw");
			
			SecSql sql = new SecSql();
			sql.append("select * from `member`");
			sql.append("where memberId = ?", memberId);
			
			Map<String, Object> memberMap = DBUtil.selectRow(conn, sql);
			
			if (memberMap.isEmpty())
				response.getWriter().append(String.format("<script> alert('[%s]는 존재하지않는 계정입니다.'); location.replace('login'); </script>", memberId));
			
			if (!((String) memberMap.get("memberPassword")).equals(memberPw))
				response.getWriter().append("<script> alert('비밀번호가 일치하지 않습니다.'); location.replace('login'); </script>");
			
			HttpSession session = request.getSession();
			session.setAttribute("loginMemberNumber", memberMap.get("memberNumber"));
			session.setAttribute("loginMemberId", memberMap.get("memberId"));
			
			response.getWriter().append(String.format("<script> alert('%s님 로그인을 환영합니다.'); location.replace('../home/main'); </script>", memberId));

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
  	
