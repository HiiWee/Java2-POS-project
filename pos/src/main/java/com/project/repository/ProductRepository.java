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
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                products.add(new Product(id, name, price));
            }
            closeAll(resultSet, statement, connection);
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
