package com.example.blackrock.dto;

import java.util.List;

public class CalculateRetunsRequest {
    private Integer age;
    private Double wage;
    private Double inflation;
    private List<KRule> k;
    private List<PRule> p;
    private List<QRule> q;

    public CalculateRetunsRequest(Integer age, Double wage, Double inflation, List<KRule> k, List<PRule> p, List<QRule> q, List<Transaction> transactions) {
        this.age = age;
        this.wage = wage;
        this.inflation = inflation;
        this.k = k;
        this.p = p;
        this.q = q;
        this.transactions = transactions;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public Double getInflation() {
        return inflation;
    }

    public void setInflation(Double inflation) {
        this.inflation = inflation;
    }

    public List<KRule> getK() {
        return k;
    }

    public void setK(List<KRule> k) {
        this.k = k;
    }

    public List<PRule> getP() {
        return p;
    }

    public void setP(List<PRule> p) {
        this.p = p;
    }

    public List<QRule> getQ() {
        return q;
    }

    public void setQ(List<QRule> q) {
        this.q = q;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private List<Transaction> transactions;
}
