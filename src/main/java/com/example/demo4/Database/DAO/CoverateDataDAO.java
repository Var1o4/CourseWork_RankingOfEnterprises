package com.example.demo4.Database.DAO;


import java.sql.*;
import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;



public class CoverateDataDAO {
    public int insertCoverageRatio(double equity, double totalAssets, double coverageRatio) throws SQLException {
        JDBС.connect();

        try {
            String coverageRatioSql = "INSERT INTO coverate_ratio (equity, totalAssets, coverate_ratio) VALUES (?, ?, ?)";
            PreparedStatement coverageRatioStatement = JDBС.connection.prepareStatement(coverageRatioSql, PreparedStatement.RETURN_GENERATED_KEYS);
            coverageRatioStatement.setDouble(1, equity);
            coverageRatioStatement.setDouble(2, totalAssets);
            coverageRatioStatement.setDouble(3, coverageRatio);

            coverageRatioStatement.executeUpdate();

            ResultSet generatedKeys = coverageRatioStatement.getGeneratedKeys();
            int coverageRatioId = 0;
            if (generatedKeys.next()) {
                coverageRatioId = generatedKeys.getInt(1);
                System.out.println(coverageRatioId);
            }

            coverageRatioStatement.close();
            return coverageRatioId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }

    public void deleteCoverageRatioById(int coverageRatioId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM coverate_ratio WHERE coverate_ratio_id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, coverageRatioId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }



}
