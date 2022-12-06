package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.controller.dto.TotalSellProductDto;
import com.project.domain.SellProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SellProductRepository {
    private static final SellProductRepository instance = new SellProductRepository();

    public static SellProductRepository getInstance() {
        return instance;
    }

    private SellProductRepository() {
    }

    public List<TotalSellProductDto> findAllByTotalSellProduct() {
        List<TotalSellProductDto> totalSellProducts = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select sell_id, `date`, sum(price * quantity) total_price, sum(quantity) total_quantity "
                            + "from sell_product sp "
                            + "join sell s on sp.sell_id = s.id "
                            + "group by sell_id, `date`\n"
                            + "order by `date` desc;");

            while (resultSet.next()) {
                Long sellId = resultSet.getLong("sell_id");
                String parsedDate = resultSet.getString("date").substring(0, 10);
                LocalDate date = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int totalPrice = resultSet.getInt("total_price");
                long totalQuantity = resultSet.getLong("total_quantity");
                totalSellProducts.add(new TotalSellProductDto(sellId, date, totalPrice, totalQuantity));
            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalSellProducts;
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
