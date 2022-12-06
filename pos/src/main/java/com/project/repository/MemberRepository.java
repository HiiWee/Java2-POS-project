package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberRepository {
    private MemberRepository() {
    }

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    public String findPasswordByAuth() {
        String password = null;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select password from member where auth='ROLE_ADMIN'");
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

    public void changePassword(String changedPassword) {
        PreparedStatement psmt = null;
        String query = "update member set password = ? where auth='ROLE_ADMIN'";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1, changedPassword);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
