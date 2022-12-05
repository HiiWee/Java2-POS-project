package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProductRepository {
    private static final ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

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
                Product product = new Product(id, name, price);
                products.add(product);
            }
            closeAll(rs, stmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Long findById() {
        Long id = null;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id from product");

            while (rs.next()) {
                id = rs.getLong("id");
            }
            closeAll(rs, stmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public void dropById(Long getId) {
        PreparedStatement psmt = null;
        String query = "delete from product where id=?";
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setLong(1, getId);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product products) {
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
    }

    public void insert(Product products) {
        String query = "insert into product(name,price) values(?,?)";
        PreparedStatement psmt = null;
        try {
            Connection conn = getConnection();
            psmt = conn.prepareStatement(query);
            psmt.setString(1, products.getName());
            psmt.setInt(2, products.getPrice());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
