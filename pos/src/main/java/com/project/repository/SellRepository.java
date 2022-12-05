package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.SeatProduct;
import com.project.domain.Sell;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
