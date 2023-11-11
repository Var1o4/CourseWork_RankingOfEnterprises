package com.example.demo4.Database.DAO;
import java.sql.*;
import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;

public class CbDataDAO {
    public int insertCbRate(double nominalRate, double inflationRate, double realRate) throws SQLException {
        JDBС.connect();

        try {
            String cbRateSql = "INSERT INTO cb_rate (nominal_rate, inflation_rate, real_rate) VALUES (?, ?, ?)";
            PreparedStatement cbRateStatement = JDBС.connection.prepareStatement(cbRateSql, PreparedStatement.RETURN_GENERATED_KEYS);
            cbRateStatement.setDouble(1, nominalRate);
            cbRateStatement.setDouble(2, inflationRate);
            cbRateStatement.setDouble(3, realRate);

            cbRateStatement.executeUpdate();

            ResultSet generatedKeys = cbRateStatement.getGeneratedKeys();
            int cbRateId = 0;
            if (generatedKeys.next()) {
                cbRateId = generatedKeys.getInt(1);
                System.out.println(cbRateId);
            }

            cbRateStatement.close();
            return cbRateId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }


    public void deleteCbRateById(int cbRateId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM cb_rate WHERE cb_rate_id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, cbRateId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }

}
