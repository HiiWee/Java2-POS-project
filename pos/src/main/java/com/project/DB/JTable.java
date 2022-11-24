package com.project.DB;

import com.project.utils.DButil;
import com.project.view.management.ManagementEditPage;
import com.project.view.management.ManagementPage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class JTable {
    ManagementPage managementPage = ManagementPage.getInstance();
    ManagementEditPage managementEditPage = ManagementEditPage.getInstance();
    private static final JTable instance = new JTable();

    public static JTable getInstance() {
        return instance;
    }

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public void select() {
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
                e2.printStackTrace();
            }
        }
    }

    public void insert() {
        String query = "insert into product(id,name,price)" + "values(?,?,?)";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
            pstmt.setString(1,managementEditPage.getjTextFieldNumber().getText());
            pstmt.setString(2, managementEditPage.getjTextFieldStuffName().getText());
            pstmt.setString(3, managementEditPage.getjTextFieldStuffPrice().getText());
            int insert = pstmt.executeUpdate();
            System.out.println("1성공 " + insert);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                DButil.disConnect();
            }catch (Exception ee){
                ee.printStackTrace();
        }
    }

}

    public void drop() {
        DefaultTableModel tableModel = (DefaultTableModel) managementPage.table.getModel();
        int row = managementPage.table.getSelectedRow();
        if (row < 0) {
            return;
        }
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
                DButil.disConnect();
            } catch (Exception i) {
            }
        }
        tableModel.removeRow(row);
    }
}
