package com.example.blackrock.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ParsedTransactionResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public long getAmount() {
        return (long) amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCeiling() {
        return (long) ceiling;
    }

    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    public long getRemanent() {
        return (long) remanent;
    }

    public void setRemanent(double remanent) {
        this.remanent = remanent;
    }

    private double amount;

    private double ceiling;

    private double remanent;

    public ParsedTransactionResponse(LocalDateTime date, double amount, double ceiling, double remanent) {
        this.date = date;
        this.amount = amount;
        this.ceiling = ceiling;
        this.remanent = remanent;
    }
}
