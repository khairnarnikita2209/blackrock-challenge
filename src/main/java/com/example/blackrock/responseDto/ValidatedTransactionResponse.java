package com.example.blackrock.responseDto;

import com.example.blackrock.dto.InvalidTransaction;
import com.example.blackrock.dto.Transaction;

import java.util.List;

public class ValidatedTransactionResponse {
    private List<Transaction> valid;
    private List<InvalidTransaction> invalid;

    public ValidatedTransactionResponse(List<Transaction> valid, List<InvalidTransaction> invalid) {
        this.valid = valid;
        this.invalid = invalid;
    }

    public List<Transaction> getValid() {
        return valid;
    }

    public void setValid(List<Transaction> valid) {
        this.valid = valid;
    }

    public List<InvalidTransaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<InvalidTransaction> invalid) {
        this.invalid = invalid;
    }
}
