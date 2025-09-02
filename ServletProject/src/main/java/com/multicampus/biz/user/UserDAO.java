package com.multicampus.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.multicampus.biz.common.JDBCUtil;

public class UserDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // SQL 문
    private final String USER_INSERT = "INSERT INTO USERS(ID, PASSWORD, NAME, ROLE) VALUES(?, ?, ?, ?)";
    private final String USER_UPDATE = "UPDATE USERS SET PASSWORD = ?, NAME = ?, ROLE = ? WHERE ID = ?";
    private final String USER_DELETE = "DELETE FROM USERS WHERE ID = ?";
    private final String USER_GET    = "SELECT * FROM USERS WHERE ID = ? AND PASSWORD = ?";
    private final String USER_LIST   = "SELECT * FROM USERS ORDER BY ID";

    // 사용자 등록
    public void insertUser(UserVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_INSERT);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, vo.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 사용자 수정
    public void updateUser(UserVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_UPDATE);
            stmt.setString(1, vo.getPassword());
            stmt.setString(2, vo.getName());
            stmt.setString(3, vo.getRole());
            stmt.setString(4, vo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 사용자 삭제
    public void deleteUser(UserVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_DELETE);
            stmt.setString(1, vo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 로그인 시 사용자 조회
    public UserVO getUser(UserVO vo) {
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

    // 전체 사용자 목록 조회
    public List<UserVO> getUserList() {
        List<UserVO> userList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                UserVO user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return userList;
    }
}
