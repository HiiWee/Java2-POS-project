package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.controller.dto.GraphDto;
import com.project.controller.dto.SellProductInBillDto;
import com.project.domain.Sell;
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

    public void deleteBySellId(final long sellId) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from SELL_PRODUCT where sell_id = ?");
            preparedStatement.setLong(1, sellId);
            preparedStatement.executeUpdate();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GraphDto> fineBydays() {
        List<GraphDto> graphDtos = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select DATE_FORMAT(`date`, '%Y-%m-%d') `date`, sum(price * quantity)  total_price from\n"
                            + "sell_product sp join sell s on\n"
                            + "sp.sell_id = s.id\n"
                            + "group by DATE_FORMAT(`date`, '%Y-%m-%d')\n"
                            + "order by `date` desc");
            while (resultSet.next()) {
                int price = resultSet.getInt("total_price");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                graphDtos.add(new GraphDto(price, date));
            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return graphDtos;
    }

    public List<GraphDto> fineByMonth() {
        List<GraphDto> graphDtos = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select DATE_FORMAT(`date`, '%Y-%m') `date`, sum(price * quantity) total_price from "
                            + "sell_product sp join sell s on "
                            + "sp.sell_id = s.id "
                            + "group by DATE_FORMAT(`date`, '%Y-%m')\n"
                            + "order by `date` desc");
            while (resultSet.next()) {
                int price = resultSet.getInt("total_price");
                String parsedDate = resultSet.getString("date");
                LocalDate date = LocalDate.of(Integer.parseInt(parsedDate.substring(0, 4)), Integer.parseInt(parsedDate.substring(5, 7)), 1);
                graphDtos.add(new GraphDto(price, date));
            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return graphDtos;
    }
    public List<GraphDto> fineByQuantity() {
        List<GraphDto> graphDtos = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select product_name, sum(quantity) total_quantity from sell_product sp join sell s on sp.sell_id = s.id group by product_name order by `total_quantity` desc");
            while (resultSet.next()) {
                int quantity = resultSet.getInt("total_quantity");
                String productName=resultSet.getString("product_name");
                graphDtos.add(new GraphDto(productName, quantity));
            }
            closeAll(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return graphDtos;
    }
}
