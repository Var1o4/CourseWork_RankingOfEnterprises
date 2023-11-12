package com.example.demo4.Database.DAO;


import java.sql.*;
import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;



public class CompanyDataDAO {
    public int insertCompany(String companyName, String result, int userId) throws SQLException {
        JDBС.connect();

        try {
            String companySql = "INSERT INTO company (company_name, result, user_id) VALUES (?, ?, ?)";
            PreparedStatement companyStatement = JDBС.connection.prepareStatement(companySql, PreparedStatement.RETURN_GENERATED_KEYS);
            companyStatement.setString(1, companyName);
            companyStatement.setString(2, result);
            companyStatement.setInt(3, userId);

            companyStatement.executeUpdate();

            ResultSet generatedKeys = companyStatement.getGeneratedKeys();
            int companyId = 0;
            if (generatedKeys.next()) {
                companyId = generatedKeys.getInt(1);
                System.out.println(companyId);
            }

            companyStatement.close();
            return companyId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }

    public void deleteCompanyById(int companyId) throws SQLException {
        JDBС.connect();

        try {
            String deleteSql = "DELETE FROM company WHERE company_id = ?";
            PreparedStatement deleteStatement = JDBС.connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, companyId);

            deleteStatement.executeUpdate();

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }


    public void updateCompanyResult(int companyId, String newResult) throws SQLException {
        JDBС.connect();

        try {
            String updateSql = "UPDATE company SET result = ? WHERE company_id = ?";
            PreparedStatement updateStatement = JDBС.connection.prepareStatement(updateSql);
            int maxLength = 250; // Максимальная длина столбца 'result'

            if (newResult.length() > maxLength) {
                newResult = newResult.substring(0, maxLength);
            }
            updateStatement.setString(1, newResult);
            updateStatement.setInt(2, companyId);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Company result updated successfully.");
            } else {
                System.out.println("Failed to update company result. Company not found.");
            }

            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
    }


}
