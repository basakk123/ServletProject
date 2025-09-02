package com.multicampus.biz.board;

public class UpdateBoardTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		
		// 글 수정
		BoardVO vo = new BoardVO();
		vo.setContent("---내용 수정");	
		vo.setTitle("---제목 수정");
		vo.setSeq(2);	
		dao.updateBoard(vo);
		
		// 글 목록 검색
		dao.getBoardList();
	}
}
