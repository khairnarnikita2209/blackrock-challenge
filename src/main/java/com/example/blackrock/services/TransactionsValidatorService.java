package com.example.blackrock.services;

import com.example.blackrock.dto.InvalidTransaction;
import com.example.blackrock.dto.Transaction;
import com.example.blackrock.dto.TransactionValidatorRequest;
import com.example.blackrock.responseDto.ValidatedTransactionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsValidatorService {

    public ValidatedTransactionResponse validateTransactionMethod(TransactionValidatorRequest request){
        List<Transaction> valid = new ArrayList<>();
        List<InvalidTransaction> invalid = new ArrayList<>();

        request.getTransactions().forEach(transaction -> {
            if(transaction.getAmount() < 0){
                InvalidTransaction invalidTxn = new InvalidTransaction(transaction.getDate(), transaction.getAmount(), transaction.getCeiling(), transaction.getRemanent(), "Negative amounts are not allowed");
                invalid.add(invalidTxn);
            }else{
                valid.add(transaction);
            }
        });

        return new ValidatedTransactionResponse(valid,invalid);
    }
}
