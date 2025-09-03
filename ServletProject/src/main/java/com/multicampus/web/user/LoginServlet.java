package com.multicampus.web.user;

import java.io.IOException;

import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> LoginServlet 실행");
		
		// 글로벌 파라미터 정보 추출
		ServletContext context = getServletContext();
		String appName = context.getInitParameter("appName");
		System.out.println("애플리케이션 이름 : " + appName);
		
		// 1. 사용자 입력정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);
		
		// 3. 화면 이동
		if(user != null) {
			// 로그인 성공 시, 세션에 userId 정보를 저장하고 글 목록 화면으로 이동
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 60 * 3);
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userRole", user.getRole());
			
			response.sendRedirect("getBoardList.do");
		} else {
			// 로그인 실패 시, 로그인 화면으로 되돌아간다.
			response.sendRedirect("login.html");
		}
	}
	
}
