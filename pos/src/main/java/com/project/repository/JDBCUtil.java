package com.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pos";
    public static final String USER = "hoseok";
    public static final String PASSWORD = "1234";

    private JDBCUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void closeAll(final ResultSet rs, final Statement stmt, final Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}
