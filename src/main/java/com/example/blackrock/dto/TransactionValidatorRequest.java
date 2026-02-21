package com.example.blackrock.dto;

import java.util.List;

public class TransactionValidatorRequest {
    private double wage;

    private List<Transaction> transactions;

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
