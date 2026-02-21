package com.example.blackrock.dto;

import java.time.LocalDateTime;

public class InvalidTransaction extends Transaction{
    private String message;

    public InvalidTransaction(LocalDateTime date, double amount, double ceiling, double remenant, String message) {
        super(date,amount, ceiling,remenant);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
