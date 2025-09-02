package com.multicampus.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.multicampus.biz.common.JDBCUtil;

// DAO(Data Access Object) : 실질적인 DB 연동 처리를 담당하는 클래스
public class BoardDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// BOARD 관련 SQL 명령어들
	private String board_insert = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?)";
	private String board_update = "update board set title = ?, content = ? where seq = ?";
	private String board_delete = "delete board where seq = ?";
	private String board_get    = "select * from board where seq = ?";
	private String board_list   = "select * from board order by seq desc";
	
	// CRUD 관련 메소드
	// 글 등록
	public void insertBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(board_insert);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(board_update);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 삭제
	public void deleteBoard(int seq) {		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(board_delete);
			stmt.setInt(1, seq);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 상세 조회
	public void getBoard(int seq) {	
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(board_get);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();	
			System.out.println("[ 게시글 상세 ]");
			if(rs.next()) {
				System.out.println(rs.getInt("SEQ"));
				System.out.println(rs.getString("TITLE"));
				System.out.println(rs.getString("WRITER"));
				System.out.println(rs.getString("CONTENT"));
				System.out.println(rs.getDate("REGDATE"));
				System.out.println(rs.getInt("CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	// 글 목록 검색
	public void getBoardList() {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(board_list);
			rs = stmt.executeQuery();
			System.out.println("[ 게시글 목록 ]");
			while(rs.next()) {
				System.out.print(rs.getInt("SEQ") + " : ");
				System.out.print(rs.getString("TITLE") + " : ");
				System.out.print(rs.getString("WRITER") + " : ");
				System.out.print(rs.getString("CONTENT") + " : ");
				System.out.print(rs.getDate("REGDATE") + " : ");
				System.out.print(rs.getInt("CNT") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}


