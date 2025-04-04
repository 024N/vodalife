package com.vodafone.vodalife.model;

public class BillRecord {
    private String month; // Fatura ayı (YYYY-MM formatında saklanacak)
    private String type; // Fatura türü (su, elektrik)
    private double value; // Tüketim miktarı
    private String unit; // Birim (örneğin, kWh, m3)
    private double cost; // Fatura maliyeti

    public BillRecord(String month, String type, double value, String unit, double cost) {
        this.month = month;
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.cost = cost;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}