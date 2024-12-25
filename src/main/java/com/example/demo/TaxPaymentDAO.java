package com.example.demo;

import java.sql.Connection;
import java.util.List;

public class TaxPaymentDAO {
    public TaxPaymentDAO(Connection connection) {
    }

    public List<TaxPayment> getAllTaxPayments() {
        return List.of();
    }

    public void addTaxPayment(TaxPayment taxPayment) {
    }
}
