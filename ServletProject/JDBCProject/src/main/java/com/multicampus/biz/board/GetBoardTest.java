package com.multicampus.biz.board;

public class GetBoardTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		
		// 글 상세 조회
		dao.getBoard(1);
	}
}






