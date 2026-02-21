package com.example.blackrock.dto;

import java.time.LocalDateTime;

public class FilteredValidTransaction extends Transaction{
    private boolean inkPeriod;

    public boolean isInkPeriod() {
        return inkPeriod;
    }

    public void setInkPeriod(boolean inkPeriod) {
        this.inkPeriod = inkPeriod;
    }

    public FilteredValidTransaction(LocalDateTime date, Double amount, Double ceiling, Double remanent, boolean inkPeriod) {
        super(date, amount, ceiling, remanent);
        this.inkPeriod = inkPeriod;
    }
}
