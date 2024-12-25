package com.example.demo;

import java.util.Date;

public class TaxPayment {
    private int paymentId;
    private int userId;
    private double amountPaid;
    private Date paymentDate;


    public TaxPayment(int i, double amount, java.sql.Date date) {}

    public TaxPayment(int paymentId, int userId, double amountPaid, Date paymentDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
    }


    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
}
