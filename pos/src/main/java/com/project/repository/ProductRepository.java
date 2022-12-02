package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.Product;
import java.sql.Connection;
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
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product");

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                products.add(new Product(id, name, price));
            }
            closeAll(rs, stmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


}
