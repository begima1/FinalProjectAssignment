package com.example.demo;

import java.util.Date;

public class Income {
    private int incomeId;
    private int userId;
    private double amount;
    private String source;
    private Date date;


    public Income() {}

    public Income(int incomeId, int userId, double amount, String source, Date date) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    public Income(int i, double amount, java.sql.Date date) {
    }


    public int getIncomeId() { return incomeId; }
    public void setIncomeId(int incomeId) { this.incomeId = incomeId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
