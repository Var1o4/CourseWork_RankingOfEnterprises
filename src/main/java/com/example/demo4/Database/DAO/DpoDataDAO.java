package com.example.demo4.Database.DAO;


import java.sql.*;

import com.example.demo4.Database.JDBС;


public class DpoDataDAO {

    public int insertDpoData(double kz, double nzak, double netRevenue, double dpo) throws SQLException {
        JDBС.connect();

        try {
            String dpoSql = "INSERT INTO dpo_data (kz, nzak, net_revenue, dpo) VALUES (?, ?, ?, ?)";
            PreparedStatement dpoStatement = JDBС.connection.prepareStatement(dpoSql, PreparedStatement.RETURN_GENERATED_KEYS);
            dpoStatement.setDouble(1, kz);
            dpoStatement.setDouble(2, nzak);
            dpoStatement.setDouble(3, netRevenue);
            dpoStatement.setDouble(4, dpo);

            dpoStatement.executeUpdate();

            ResultSet generatedKeys = dpoStatement.getGeneratedKeys();
            int dpoDataId = 0;
            if (generatedKeys.next()) {
                dpoDataId = generatedKeys.getInt(1);
                System.out.println(dpoDataId);
            }

            dpoStatement.close();
            return dpoDataId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return 0;
    }
}
