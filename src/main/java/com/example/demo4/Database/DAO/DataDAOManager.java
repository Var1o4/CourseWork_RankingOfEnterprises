package com.example.demo4.Database.DAO;

import java.sql.SQLException;

public class DataDAOManager {
    RoeDataDAO roeDataDAO = new RoeDataDAO();
    CoverateDataDAO coverageRatioDataDAO = new CoverateDataDAO();
    DpoDataDAO dpoDataDAO = new DpoDataDAO();
    DpocDataDAO dpocDataDAO = new DpocDataDAO();
    CompanyDataDAO companyDAO = new CompanyDataDAO();

    EquityDataDAO equityDataDAO = new EquityDataDAO();

    CbDataDAO cbDataDAO = new CbDataDAO();




    // Добавьте необходимые методы и логику для работы с каждым DAO

    public int insertRoeData(double netIncome, double averageEquity, double roe) throws SQLException {
        return roeDataDAO.insertRoeData(netIncome, averageEquity, roe);
    }

    public int insertCBData(double netIncome, double averageEquity, double real_rate) throws SQLException {
        return cbDataDAO.insertCbRate(netIncome, averageEquity, real_rate);
    }

    public int insertCoverageRatioData(double equity, double totalAssets, double coverageRatio) throws SQLException {
        return  coverageRatioDataDAO.insertCoverageRatio(equity, totalAssets, coverageRatio);
    }
    public int insertEquity(double equity, double totalAssets, double equityRatio) throws SQLException {
        return  equityDataDAO.insertEquityLevel(equity, totalAssets, equityRatio);
    }
    public int insertDpoData(double costOfGoodsSold, double averageAccountsPayable, double nkaz,  double dpo) throws SQLException {
        return dpoDataDAO.insertDpoData(costOfGoodsSold, averageAccountsPayable, nkaz ,  dpo);
    }

    public int insertDpocData(double netProductionWorkingCapital, double amountOfProduction, double dpoc) throws SQLException {
        return dpocDataDAO.insertDpocData(netProductionWorkingCapital, amountOfProduction, dpoc);
    }

    public int insertCompany(String companyName, String result, int userId) throws SQLException {
        return companyDAO.insertCompany(companyName, result, userId);
    }


}