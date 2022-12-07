package com.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pos";
    public static final String USER = "";
    public static final String PASSWORD = "";

    private JDBCUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void closeAll(
            final ResultSet resultSet,
            final Statement statement,
            final Connection connection
    ) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void closeAll(final PreparedStatement preparedStatement, final Connection connection)
            throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
