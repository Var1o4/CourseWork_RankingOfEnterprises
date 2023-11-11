package com.example.demo4.Database;

public class IdStorage {
    private static int roeDataId;
    private static int cbRateId;
    private static int equityLevelId;
    private static int coverateRatioId;
    private static int dpoDataId;
    private static int dpocDataId;
    private static int companyId;

    public static int getRoeDataId() {
        return roeDataId;
    }

    public static void setRoeDataId(int roeDataId) {
        IdStorage.roeDataId = roeDataId;
    }

    public static int getCbRateId() {
        return cbRateId;
    }

    public static void setCbRateId(int cbRateId) {
        IdStorage.cbRateId = cbRateId;
    }

    public static int getEquityLevelId() {
        return equityLevelId;
    }

    public static void setEquityLevelId(int equityLevelId) {
        IdStorage.equityLevelId = equityLevelId;
    }

    public static int getCoverateRatioId() {
        return coverateRatioId;
    }

    public static void setCoverateRatioId(int coverateRatioId) {
        IdStorage.coverateRatioId = coverateRatioId;
    }

    public static int getDpoDataId() {
        return dpoDataId;
    }

    public static void setDpoDataId(int dpoDataId) {
        IdStorage.dpoDataId = dpoDataId;
    }

    public static int getDpocDataId() {
        return dpocDataId;
    }

    public static void setDpocDataId(int dpocDataId) {
        IdStorage.dpocDataId = dpocDataId;
    }

    public static int getCompanyId() {
        return companyId;
    }

    public static void setCompanyId(int companyId) {
        IdStorage.companyId = companyId;
    }
}