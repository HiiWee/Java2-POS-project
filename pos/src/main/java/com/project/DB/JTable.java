package com.project.DB;

import com.project.table.Product;
import com.project.utils.DButil;
import com.project.view.management.ManagementEditPage;
import com.project.view.management.ManagementPage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class JTable {
    private static final List<Product> productList = new ArrayList<>();
    private final Product product = new Product();
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
                managementPage.tableModel.addRow(new Object[]{rs.getInt("id")
                        , rs.getString("name"), rs.getInt("price")});
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

    public void insert() {// TODO: 2022-11-25 id 값 사용 하지 않고 해보기 및 리스트 이용해서 한번에 DB접근 구현
        String query = "insert into product(name,price) values(?,?)";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
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
            pstmt.setLong(1, Long.parseLong(String.valueOf(managementPage.table.getValueAt(row, 0))));
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

    public void update() {// TODO: 2022-11-25 id 값 접근하지 않을 예정
        String query = "update product set name=?, price=? where id=?";
        try {
            DButil.connect();
            pstmt = DButil.connection.prepareStatement(query);
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

    public void saveList() {// TODO: 2022-11-25 리스트 이용해 보자
        product.setName(managementEditPage.getjTextFieldStuffName().getText());
        product.setPrice(Integer.parseInt(managementEditPage.getjTextFieldStuffPrice().getText()));
        productList.add(product);
        System.out.println(product);
    }

    public void clearList() {
        productList.clear();
    }
}
