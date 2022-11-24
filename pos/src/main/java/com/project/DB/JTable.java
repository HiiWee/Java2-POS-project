package com.project.DB;

import com.project.utils.DButil;
import com.project.view.management.ManagementPage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class JTable {
    ManagementPage managementPage = ManagementPage.getInstance();
    private static final JTable instance = new JTable();

    public static JTable getInstance() {
        return instance;
    }

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void Select() {
        String query = "select id, name, price from product";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                managementPage.tableModel.addRow(new Object[]{rs.getString("id")
                        , rs.getString("name"), rs.getString("price")});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                pstmt.close();
                DButil.disConnect();
            } catch (Exception e2) {
            }
        }
    }

    public void insert() {
    }

    public void drop() {
        DefaultTableModel tableModel = (DefaultTableModel) managementPage.table.getModel();
        int row = managementPage.table.getSelectedRow();
        if (row < 0)
            return;
        String query = "delete from product where id=?";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
            pstmt.setString(1, (String) tableModel.getValueAt(row, 0));
            pstmt.executeUpdate();
        } catch (Exception a) {
            System.out.println(a.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (Exception i) {}
        }
        tableModel.removeRow(row);
    }
}
