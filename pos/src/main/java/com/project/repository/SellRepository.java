package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.controller.dto.SellProductInBillDto;
import com.project.controller.dto.TotalSellProductDto;
import com.project.domain.SeatProduct;
import com.project.domain.Sell;
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

public class SellRepository {
    private static final SellRepository instance = new SellRepository();

    public static SellRepository getInstance() {
        return instance;
    }

    private SellRepository() {
    }

    public Sell findRecentAdded() {
        Sell sell = null;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select *  from sell "
                            + "where member_id = 1"
                            + " order by date desc LIMIT 1");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String parsedDate = resultSet.getString("date").substring(0, 10);
                LocalDate date = LocalDate.parse(parsedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long memberId = resultSet.getLong("member_id");
                sell = new Sell(id, date, memberId);
            }

            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sell;
    }


    public Sell save(final Sell sell) {
        try {
            Connection connection = getConnection();
            System.out.println("pass");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into SELL(member_id)"
                            + "values(?)");
            preparedStatement.setLong(1, sell.getMemberId());
            preparedStatement.executeUpdate();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findRecentAdded();
    }

    public List<SellProductInBillDto> findAllById(final Long sellId) {
        List<SellProductInBillDto> sellProductInBillDtos = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select product_name, quantity, price from SELL_PRODUCT"
                            + " where sell_id = ?");
            preparedStatement.setLong(1, sellId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                if (productName == null) {
                    productName = "판매하지 않는 품목";
                }
                long quantity = resultSet.getLong("quantity");
                int price = resultSet.getInt("price");
                sellProductInBillDtos.add(new SellProductInBillDto(productName, quantity, price));
            }
            resultSet.close();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sellProductInBillDtos;
    }
}
