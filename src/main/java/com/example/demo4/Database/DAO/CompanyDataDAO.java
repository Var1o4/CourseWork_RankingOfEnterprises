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

}
