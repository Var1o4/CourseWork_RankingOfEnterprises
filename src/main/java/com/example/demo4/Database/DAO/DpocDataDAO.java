package com.example.demo4.Database.DAO;


import java.sql.*;
import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;


public class DpocDataDAO {

    public int insertDpocData(double netProductionWorkingCapital, double amountOfProduction, double dpoc) throws SQLException {
        JDBС.connect();

        try {
            String dpocSql = "INSERT INTO dpoc_data (net_production_working_capital, amount_of_production, dpoc) VALUES (?, ?, ?)";
            PreparedStatement dpocStatement = JDBС.connection.prepareStatement(dpocSql, PreparedStatement.RETURN_GENERATED_KEYS);
            dpocStatement.setDouble(1, netProductionWorkingCapital);
            dpocStatement.setDouble(2, amountOfProduction);
            dpocStatement.setDouble(3, dpoc);

            dpocStatement.executeUpdate();

            ResultSet generatedKeys = dpocStatement.getGeneratedKeys();
            int dpocDataId = 0;
            if (generatedKeys.next()) {
                dpocDataId = generatedKeys.getInt(1);
                System.out.println(dpocDataId);
            }

            dpocStatement.close();
            return dpocDataId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }

    public void deleteDpocDataById(int dpocDataId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM dpoc_data WHERE dpoc_data_id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, dpocDataId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }
}
