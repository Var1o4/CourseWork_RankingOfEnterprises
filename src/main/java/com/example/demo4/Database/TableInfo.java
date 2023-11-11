package com.example.demo4.Database;

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

    public int getCbTableId(int i) {
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
}