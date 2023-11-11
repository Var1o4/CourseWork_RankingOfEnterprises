package com.example.demo4.Database.DAO;

import java.sql.*;

import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;

public class RoeDataDAO {


        public int insertRoeData(double netIncome, double equity, double roe) throws SQLException {
            JDBС.connect();

            try {
                String roeSql = "INSERT INTO roe_data (net_income, equity, roe) VALUES (?, ?, ?)";
                PreparedStatement roeStatement = JDBС.connection.prepareStatement(roeSql, PreparedStatement.RETURN_GENERATED_KEYS);
                roeStatement.setDouble(1, netIncome);
                roeStatement.setDouble(2, equity);
                roeStatement.setDouble(3, roe);

                roeStatement.executeUpdate();

                ResultSet generatedKeys = roeStatement.getGeneratedKeys();
                int roeId = 0;
                if (generatedKeys.next()) {
                    roeId = generatedKeys.getInt(1);
                    System.out.println(roeId);
                }

                roeStatement.close();
                return roeId;


            } catch (SQLException e) {
                e.printStackTrace();
            }

            JDBС.close();
            return 0;

        }

    public void deleteRoeDataById(int roeId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM roe_data WHERE id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, roeId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }

    public static void updateRoeData(int roeId, double netIncome, double equity, double roe) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")) {
            String sql = "UPDATE roe_data SET net_income = ?, equity = ?, roe = ? WHERE roe_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, netIncome);
                statement.setDouble(2, equity);
                statement.setDouble(3, roe);
                statement.setInt(4, roeId);

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Запись успешно обновлена. roe_id = " + roeId);
                } else {
                    System.out.println("Запись с roe_id = " + roeId + " не найдена.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}