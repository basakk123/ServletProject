package com.multicampus.web.board;

import java.io.IOException;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteBoardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> DeleteBoardServlet 실행");
		
		// 0. 세션 체크
		// 브라우저와 매핑된 세션 객체를 획득한다.(있으면 재사용, 없으면 새롭게 생성)
		HttpSession session = request.getSession();
		
		// 브라우저와 매핑된 세션에 userId 정보가 있다면 과거에 로그인을 했던 브라우저인거다.
		if(session.getAttribute("userId") == null) {
			response.sendRedirect("login.html");
		} else {

		// 1. 사용자 입력정보 추출
		String seq = request.getParameter("seq");

		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));

		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(vo);

		// 3. 화면 이동
		// 글 삭제 성공 후, 글 목록 화면으로 이동
		response.sendRedirect("getBoardList.do");
		}
	}
}
