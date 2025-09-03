package com.multicampus.web.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetBoardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> GetBoardServlet 실행");
		
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
		BoardVO board = dao.getBoard(vo);
		
		// 3. 검색 결과를 이용한 응답 화면 구성
		// 출력 스트림을 획득하기 전에 인코딩을 설정한다.
		response.setContentType("text/html; charset=UTF-8");
		
		// HTTP 응답 프로토콜 Body와 연결된 출력 스트림을 획득한다.
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>게시글 상세</title></head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>게시글 상세</h1>");
		out.println("<h3>" + session.getAttribute("userName") + "님 환영합니다.</h3>");
		out.println("<a href='logout.do'>LOGOUT</a>");
		out.println("<hr>");
		
		out.println("<form action='updateBoard.do' method='get'>");
		out.println("<input type='hidden' name='seq' value='" + board.getSeq() + "'/>");
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='500'>");
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='100'>제목</td>");
		out.println("<td><input type='text' name='title' value='" + board.getTitle() + "'/></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='100'>작성자</td>");
		out.println("<td>" + board.getWriter() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='100'>내용</td>");
		out.println("<td><textarea name='content' cols='50' rows='10'>" + board.getContent() + "</textarea></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='100'>등록일</td>");
		out.println("<td>" + board.getRegDate() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td bgcolor='orange' width='100'>조회수</td>");
		out.println("<td>" + board.getCnt() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan='2' align='center'><input type='submit' value='글수정'/></td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		
		out.println("<hr>");

		out.println("<a href='insertBoard.html'>글등록</a>&nbsp;&nbsp;&nbsp;");
		
		String userRole = (String) session.getAttribute("userRole");
		if(userRole.equals("Admin")) {
			out.println("<a href='deleteBoard.do?seq=" + board.getSeq() + "'>글삭제</a>&nbsp;&nbsp;&nbsp;");
		}
		
		out.println("<a href='getBoardList.do'>글목록</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		// 출력 스트림을 닫는다.
		out.close();
		}
	}
}






