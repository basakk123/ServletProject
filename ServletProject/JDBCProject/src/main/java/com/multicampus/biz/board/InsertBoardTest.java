package com.multicampus.biz.board;

public class InsertBoardTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		
		// 글 등록
		BoardVO vo = new BoardVO();
		vo.setContent("DTO 테스트 중입니다.");	
		vo.setTitle("테스터");
		vo.setWriter("DTO 테스트");			
		dao.insertBoard(vo);
		
		// 글 목록 검색
		dao.getBoardList();
	}
}
