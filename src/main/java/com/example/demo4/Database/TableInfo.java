package com.example.demo4.Database;

import java.sql.*;
import java.time.LocalDate;

import com.example.demo4.Database.JDBС;
import com.example.demo4.User.BCrypt;
import com.example.demo4.Database.IdStorage;


public class TableInfo {
    private int roeTableId;
    private int cbTableId;
    private int equityTableId;
    private int coverateTableId;
    private int dpoTableId;
    private int dpocTableId;
    private int companyTableId;

    // Геттеры и сеттеры для каждого поля


    public void setRoeTableId(int roeTableId) {
        this.roeTableId = roeTableId;
    }

    public void setCbTableId(int cbTableId) {
        this.cbTableId = cbTableId;
    }

    public void setCompanyTableId(int companyTableId) {
        this.companyTableId = companyTableId;
    }

    public void setCoverateTableId(int coverateTableId) {
        this.coverateTableId = coverateTableId;
    }

    public void setDpocTableId(int dpocTableId) {
        this.dpocTableId = dpocTableId;
    }

    public void setDpoTableId(int dpoTableId) {
        this.dpoTableId = dpoTableId;
    }

    public void setEquityTableId(int equityTableId) {
        this.equityTableId = equityTableId;
    }

    public int getCbTableId() {
        return cbTableId;
    }

    public int getCompanyTableId() {
        return companyTableId;
    }

    public int getCoverateTableId() {
        return coverateTableId;
    }

    public int getDpocTableId() {
        return dpocTableId;
    }

    public int getDpoTableId() {
        return dpoTableId;
    }

    public int getEquityTableId() {
        return equityTableId;
    }

    public int getRoeTableId() {
        return roeTableId;
    }

    public boolean isRoeTableCreated() {
        return roeTableId != 0;
    }

    public boolean isCbTableCreated() {
        return cbTableId != 0;
    }

    public boolean isEquityTableCreated() {
        return equityTableId != 0;
    }

    public boolean isCoverateTableCreated() {
        return coverateTableId != 0;
    }

    public boolean isDpoTableCreated() {
        return dpoTableId != 0;
    }

    public boolean isDpocTableCreated() {
        return dpocTableId != 0;
    }

    public boolean isCompanyTableCreated() {
        return companyTableId != 0;
    }


    public boolean areAllTablesCreated() {
        return roeTableId != 0 && cbTableId != 0 && equityTableId != 0 && coverateTableId != 0 &&
            dpoTableId != 0 && dpocTableId != 0 && companyTableId != 0;
    }


    public void createSystemIndicator() throws SQLException {
        JDBС.connect();
        String query = "INSERT INTO system_indicators (roe_data_id, cb_rate_id, equity_level_id, coverate_ratio_id, " +
                "dpo_data_id, dpoc_data_id, year, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = JDBС.connection.prepareStatement(query)) {
            statement.setInt(1, roeTableId);
            statement.setInt(2, cbTableId);
            statement.setInt(3, equityTableId);
            statement.setInt(4, coverateTableId);
            statement.setInt(5, dpoTableId);
            statement.setInt(6, dpocTableId);
            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear();
            statement.setInt(7, year);
            statement.setInt(8, companyTableId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("System indicator created successfully.");
            } else {
                System.out.println("Failed to create system indicator.");
            }
        } finally {
            JDBС.close();
        }
    }

    public double getDoubleById(String tableName,String keyName, String columnName,  int keyValue) throws SQLException {
        JDBС.connect();
        double roeValue = 0.0;

        try {
            String selectSql = "SELECT " + columnName + " FROM " + tableName + " WHERE " + keyName + " = ?";
            PreparedStatement selectStatement = JDBС.connection.prepareStatement(selectSql);
            selectStatement.setInt(1, keyValue);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                roeValue = resultSet.getDouble(columnName);
            }

            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return roeValue;
    }


    public String getStringById(String tableName, String columnName, String keyName, int keyValue) throws SQLException {
        JDBС.connect();
        String roeValue = null;

        try {
            String selectSql = "SELECT " + columnName + " FROM " + tableName + " WHERE " + keyName + " = ?";
            PreparedStatement selectStatement = JDBС.connection.prepareStatement(selectSql);
            selectStatement.setInt(1, keyValue);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                roeValue = resultSet.getString(columnName);
            }

            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBС.close();
        return roeValue;
    }
}