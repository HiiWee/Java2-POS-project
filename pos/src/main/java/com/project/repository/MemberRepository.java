package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberRepository {
    private MemberRepository() {
    }
    private static final MemberRepository instance=new MemberRepository();
    public static MemberRepository getInstance(){
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
    /*public void chancePassword(){
        PreparedStatement psmt = null;
        String query = "update product set name=?, price=? where id=?";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1, products.getName());
            psmt.setInt(2, products.getPrice());
            psmt.setLong(3, products.getId());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
