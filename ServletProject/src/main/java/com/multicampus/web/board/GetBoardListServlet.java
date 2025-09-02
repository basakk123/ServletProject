package com.multicampus.web.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardListServlet extends HttpServlet {
	
	private String author;
	private String createDate;
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		System.out.println("서블릿 작성자 : " + author);
//		// 로컬 파라미터 정보 추출
//		author = config.getInitParameter("author");
//		createDate = config.getInitParameter("createDate");
//		System.out.println("서블릿 작성자 : " + author);
//		System.out.println("서블릿 작성일 : " + createDate);
//	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> GetBoardListServlet 실행");
		
		// 글로벌 파라미터 정보 추출
		ServletContext context = getServletContext();
		String appName = context.getInitParameter("appName");
		System.out.println("애플리케이션 이름 : " + appName);
		
		// 1. DB 연동 처리
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardList = dao.getBoardList();
		
		// 3. 검색 결과를 이용한 응답 화면 구성
		// 출력 스트림을 획득하기 전에 인코딩을 설정한다.
		response.setContentType("text/html; charset=UTF-8");
		
		// HTTP 응답 프로토콜 Body와 연결된 출력 스트림을 획득한다.
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>게시글 목록</title></head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>게시글 목록2</h1>");
		out.println("<hr>");
		
		out.println("<table border='1' cellpadding='0' cellspacing='0' width='800'>");
		out.println("<tr>");
		out.println("<th bgcolor='orange' width='100'>번호</th>");
		out.println("<th bgcolor='orange' width='400'>제목</th>");
		out.println("<th bgcolor='orange' width='100'>작성자</th>");
		out.println("<th bgcolor='orange' width='100'>등록일</th>");
		out.println("<th bgcolor='orange' width='100'>조회수</th>");
		out.println("<tr>");
		
		for (BoardVO board : boardList) {
			out.println("<tr>");
			out.println("<td>" + board.getSeq() + "</td>");
			out.println("<td><a href='getBoard.do?seq=" + board.getSeq() + "'>" + board.getTitle() + "</a></td>");
			out.println("<td>" + board.getWriter() + "</td>");
			out.println("<td>" + board.getRegDate() + "</td>");
			out.println("<td>" + board.getCnt() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
		out.println("<hr>");

		out.println("<a href='insertBoard.html'>글 등록 화면으로 이동</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
		// 출력 스트림을 닫는다.
		out.close();
	}

}






