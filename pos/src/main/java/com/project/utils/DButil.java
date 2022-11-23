package com.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DButil {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/pos?serverTimezone=UTC";
    private static final String id = "YuGwangun";
    private static final String password = "sun1213@@";
    private static Connection connection = null;


    public static Connection connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, id, password);
            if (connection != null) {
                System.out.println("연결 성공");
            } else {
                System.out.println("연결 오류");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "데이터 베이스가 연결 되지 않았습니다.",
                    "경고!!", JOptionPane.WARNING_MESSAGE);
            System.err.println("Connection ERROR! :" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static void disConnect() {
        try {
            if (connection != null) {
                System.out.println("Connection close");
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Connection closing Failed!:" + e.getMessage());
            e.printStackTrace();
        }
    }
    private DButil(){}
}
