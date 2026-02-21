package com.example.blackrock.dto;

import java.util.List;

public class TransactionFilterRequest {
    private List<QRule> q;
    private List<PRule> p;
    private List<KRule> k;
    private Integer wage;
    private List<Transaction> transactions;

    public List<QRule> getQ() {
        return q;
    }

    public void setQ(List<QRule> q) {
        this.q = q;
    }

    public List<PRule> getP() {
        return p;
    }

    public void setP(List<PRule> p) {
        this.p = p;
    }

    public List<KRule> getK() {
        return k;
    }

    public void setK(List<KRule> k) {
        this.k = k;
    }

    public Integer getWage() {
        return wage;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
