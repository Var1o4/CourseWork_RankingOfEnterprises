package com.example.demo4.Database.DAO;

import java.sql.*;
import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;


public class EquityDataDAO {
    public int insertEquityLevel(double equity, double totalAssets, double equityLevel) throws SQLException {
        JDBС.connect();

        try {
            String equityLevelSql = "INSERT INTO equity_level (equity, totalAssets, equity_level) VALUES (?, ?, ?)";
            PreparedStatement equityLevelStatement = JDBС.connection.prepareStatement(equityLevelSql, PreparedStatement.RETURN_GENERATED_KEYS);
            equityLevelStatement.setDouble(1, equity);
            equityLevelStatement.setDouble(2, totalAssets);
            equityLevelStatement.setDouble(3, equityLevel);

            equityLevelStatement.executeUpdate();

            ResultSet generatedKeys = equityLevelStatement.getGeneratedKeys();
            int equityId = 0;
            if (generatedKeys.next()) {
                equityId = generatedKeys.getInt(1);
                System.out.println(equityId);
            }

            equityLevelStatement.close();
            return equityId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }

    public void deleteEquityLevelById(int equityId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM equity_level WHERE equity_id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, equityId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }


}
