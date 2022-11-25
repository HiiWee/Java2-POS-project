package com.project.DB;

import com.project.utils.DButil;
import com.project.view.management.ManagementEditPage;
import com.project.view.management.ManagementPage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class JTable {
    private final ArrayList<String> product = new ArrayList<>();
    private final ManagementPage managementPage = ManagementPage.getInstance();
    private final ManagementEditPage managementEditPage = ManagementEditPage.getInstance();
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
            e.printStackTrace();
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
        String query = "insert into product(id,name,price) values(?,?,?)";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);

            //pstmt.setString(1, product.get(rs));
            //pstmt.setString(2, product.get());
            //pstmt.setString(3, product.get());

            pstmt.setString(1, managementEditPage.getjTextFieldNumber().getText());
            pstmt.setString(2, managementEditPage.getjTextFieldStuffName().getText());
            pstmt.setString(3, managementEditPage.getjTextFieldStuffPrice().getText());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                DButil.disConnect();
            } catch (Exception ee) {
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
            a.printStackTrace();
        } finally {
            try {
                pstmt.close();
                DButil.disConnect();
            } catch (Exception i) {
            }
        }
        tableModel.removeRow(row);
    }

    public void update() {
        String query = "update product set name=?, price=? where id=?";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
            pstmt.setString(1, managementEditPage.getjTextFieldStuffName().getText());
            pstmt.setString(2, managementEditPage.getjTextFieldStuffPrice().getText());
            pstmt.setString(3, managementEditPage.getjTextFieldNumber().getText());
            pstmt.executeUpdate();
        } catch (Exception o) {
            o.printStackTrace();
        } finally {
            try {
                pstmt.close();
                DButil.disConnect();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void saveList() {
        product.add(managementEditPage.getjTextFieldNumber().getText());
        product.add(managementEditPage.getjTextFieldStuffName().getText());
        product.add(managementEditPage.getjTextFieldStuffPrice().getText());
        System.out.println(product);
    }

    public void clearList() {
        product.clear();
    }
}
