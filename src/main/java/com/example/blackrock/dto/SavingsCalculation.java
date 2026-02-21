package com.example.blackrock.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class SavingsCalculation {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    private Double amount;
    private Double profit;

    public SavingsCalculation(LocalDateTime start, LocalDateTime end, Double amount, Double profit, Double taxBenefit) {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.profit = profit;
        this.taxBenefit = taxBenefit;
    }

    private Double taxBenefit;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTaxBenefit() {
        return taxBenefit;
    }

    public void setTaxBenefit(Double taxBenefit) {
        this.taxBenefit = taxBenefit;
    }
}
