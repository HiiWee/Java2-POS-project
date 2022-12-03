package com.project.repository;

import static com.project.repository.JDBCUtil.closeAll;
import static com.project.repository.JDBCUtil.getConnection;

import com.project.domain.SeatProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SeatProductRepository {
    private static final SeatProductRepository instance = new SeatProductRepository();

    private SeatProductRepository() {
    }

    public static SeatProductRepository getInstance() {
        return instance;
    }

    /**
     * 특정 테이블의 주문한 상품을 전부 삭제
     */
    public void deleteAllBySeatId(final Long seatId) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from SEAT_PRODUCT where seat_id = ?");
            preparedStatement.setLong(1, seatId);
            preparedStatement.executeUpdate();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 사용자가 선택한 물품들 테이블(SEAT)에 추가
     */
    public void saveSeatProductList(final List<SeatProduct> selectedProductList) {
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into SEAT_PRODUCT(quantity, price, product_id, product_name, seat_id)"
                            + "values(?, ?, ?, ?, ?)");
            for (SeatProduct seatProduct : selectedProductList) {
                saveToBatch(preparedStatement, seatProduct);
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            closeAll(preparedStatement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToBatch(final PreparedStatement preparedStatement, final SeatProduct seatProduct)
            throws SQLException {
        preparedStatement.setLong(1, seatProduct.getQuantity());
        preparedStatement.setInt(2, seatProduct.getPrice());
        preparedStatement.setLong(3, seatProduct.getProductId());
        preparedStatement.setString(4, seatProduct.getProductName());
        preparedStatement.setLong(5, seatProduct.getSeatId());
        preparedStatement.addBatch();
        preparedStatement.clearParameters();
    }

}
