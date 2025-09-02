package com.multicampus.web.user;

import java.io.IOException;

import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
		
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> LoginServlet 실행");
		
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
			// 로그인 성공 시, 글 목록 화면으로 이동
			response.sendRedirect("getBoardList.do");
		} else {
			// 로그인 실패 시, 로그인 화면으로 되돌아간다.
			response.sendRedirect("login.html");
		}
	}
	
}
