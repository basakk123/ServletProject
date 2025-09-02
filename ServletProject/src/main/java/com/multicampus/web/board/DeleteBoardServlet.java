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

public class DeleteBoardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> DeleteBoardServlet 실행");

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
