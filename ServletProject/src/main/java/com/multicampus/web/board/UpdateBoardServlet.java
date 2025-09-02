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

public class UpdateBoardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> UpdateBoardServlet 실행");

		// 1. 사용자 입력정보 추출
		String title = request.getParameter("title");
		String seq = request.getParameter("seq");
		String content = request.getParameter("content");

		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setSeq(Integer.parseInt(seq));
		vo.setContent(content);

		BoardDAO dao = new BoardDAO();
		dao.updateBoard(vo);

		// 3. 화면 이동
		// 글 수정 성공 후, 글 목록 화면으로 이동
		response.sendRedirect("getBoardList.do");
	}
}
