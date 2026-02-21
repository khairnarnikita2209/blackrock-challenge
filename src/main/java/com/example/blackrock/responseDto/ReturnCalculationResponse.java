package com.example.blackrock.responseDto;

import com.example.blackrock.dto.SavingsCalculation;

import java.util.List;

public class ReturnCalculationResponse {

    private Double totalTransactionAmount;

    private Double totalCeiling;
    private List<SavingsCalculation> savingBytes;

    public Double getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(Double totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public Double getTotalCeiling() {
        return totalCeiling;
    }

    public void setTotalCeiling(Double totalCeiling) {
        this.totalCeiling = totalCeiling;
    }

    public List<SavingsCalculation> getSavingBytes() {
        return savingBytes;
    }

    public void setSavingBytes(List<SavingsCalculation> savingBytes) {
        this.savingBytes = savingBytes;
    }

    public ReturnCalculationResponse(Double totalTransactionAmount, Double totalCeiling, List<SavingsCalculation> savingBytes) {
        this.totalTransactionAmount = totalTransactionAmount;
        this.totalCeiling = totalCeiling;
        this.savingBytes = savingBytes;
    }
}
