package com.multicampus.biz.board;

public class DeleteBoardTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		
		// 글 삭제
		dao.deleteBoard(2);
		
		// 글 목록 검색
		dao.getBoardList();
	}
}






