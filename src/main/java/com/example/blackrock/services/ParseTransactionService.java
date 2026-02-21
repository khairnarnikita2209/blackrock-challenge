package com.example.blackrock.services;

import com.example.blackrock.dto.TransactionParseRequest;
import com.example.blackrock.responseDto.ParsedTransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParseTransactionService {

    public List<ParsedTransactionResponse> parseInputTransaction(List<TransactionParseRequest> transactions){

        List<ParsedTransactionResponse> result = transactions.stream().map(transaction -> {
            double amt = transaction.getAmount();
            System.out.println(amt);
            double value = amt;
            int rounded = (int) (Math.ceil(value / 100.0) * 100);
            double ceiling = rounded;
            double remanent = ceiling - amt;

            return new ParsedTransactionResponse(
                    transaction.getDate(),
                    amt, ceiling,remanent
            );
        }).toList();

        return result;
    }
}

