package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.Product;
import com.project.view.management.ManagementAddFrame;
import com.project.view.management.ManagementEditFrame;
import com.project.view.management.ManagementFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.table.DefaultTableModel;

public class ProductRepository {
    private static final ProductRepository instance = new ProductRepository();
    private final List<Product> productList = new ArrayList<>();
    private final ManagementFrame managementFrame = ManagementFrame.getInstance();
    private final ManagementAddFrame managementAddFrame = ManagementAddFrame.getInstance();
    private final ManagementEditFrame managementEditFrame=ManagementEditFrame.getInstance();

    public static ProductRepository getInstance() {
        return instance;
    }

    private PreparedStatement psmt = null;

    private ProductRepository() {
    }

    /**
     * 전체 상품 조회
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id,name,price from product");

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                products.add(product);
            }
            closeAll(rs, stmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void drop() {
        DefaultTableModel tableModel = (DefaultTableModel) managementFrame.table.getModel();
        int row = managementFrame.table.getSelectedRow();
        if (row < 0) {
            return;
        }
        String query = "delete from product where id=?";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, Long.parseLong(String.valueOf(managementFrame.table.getValueAt(row, 0))));
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableModel.removeRow(row);
    }

    public void update() {
        String query = "update product set name=?, price=? where id=?";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1, managementEditFrame.getjTextFieldStuffName().getText());
            psmt.setString(2, managementEditFrame.getjTextFieldStuffPrice().getText());
            psmt.setString(3, managementEditFrame.getjTextFieldNumber().getText());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        String query = "insert into product(name,price) values(?,?)";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            for (Product i : productList) {
                psmt.setString(1, i.getName());
                psmt.setInt(2, i.getPrice());
                psmt.executeUpdate();
            }
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveList() {
        Product product = new Product();
        product.setName(managementAddFrame.getjTextFieldStuffName().getText());
        product.setPrice(Integer.parseInt(managementAddFrame.getjTextFieldStuffPrice().getText()));
        productList.add(product);
        System.out.println(productList);
    }

    public void clearList() {
        productList.clear();
    }
}
