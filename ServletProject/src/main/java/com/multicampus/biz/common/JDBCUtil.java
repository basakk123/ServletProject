package com.multicampus.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 1단계 : 드라이버 클래스 객체 생성 및 등록
			DriverManager.registerDriver(new org.h2.Driver());
			
			// 2단계 : DBMS와 Connection 연결
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa" , "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		// 6단계 : 연결 해제
					try {
						if(rs != null)
							rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						rs = null;
					}
					
					try {
						if(stmt != null)
							stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						stmt = null;
					}
					
					try {
						if(conn != null && !conn.isClosed())
							conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						conn = null;
					}
	}
	
	public static void close(Statement stmt, Connection conn) {
		// 6단계 : 연결 해제	
					try {
						if(stmt != null)
							stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						stmt = null;
					}
					
					try {
						if(conn != null && !conn.isClosed())
							conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						conn = null;
					}
	}
}
