package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.SellProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SellProductRepository {
    private static final SellProductRepository instance = new SellProductRepository();

    public static SellProductRepository getInstance() {
        return instance;
    }

    private SellProductRepository() {
    }

    public void saveAll(final List<SellProduct> sellProducts) {
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into SELL_PRODUCT(product_name, quantity, price, product_id, sell_id)"
                            + "values(?, ?, ?, ?, ?)");
            for (SellProduct sellProduct : sellProducts) {
                saveToBatch(preparedStatement, sellProduct);
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToBatch(final PreparedStatement preparedStatement, final SellProduct sellProduct)
            throws SQLException {
        preparedStatement.setString(1, sellProduct.getProductName());
        preparedStatement.setLong(2, sellProduct.getQuantity());
        preparedStatement.setInt(3, sellProduct.getPrice());
        preparedStatement.setLong(4, sellProduct.getProductId());
        preparedStatement.setLong(5, sellProduct.getSellId());
        preparedStatement.addBatch();
        preparedStatement.clearParameters();
    }
}
