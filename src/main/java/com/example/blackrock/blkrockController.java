package com.example.blackrock;

import com.example.blackrock.dto.CalculateRetunsRequest;
import com.example.blackrock.dto.TransactionFilterRequest;
import com.example.blackrock.dto.TransactionParseRequest;
import com.example.blackrock.dto.TransactionValidatorRequest;
import com.example.blackrock.responseDto.FilteredtransactionResponse;
import com.example.blackrock.responseDto.ValidatedTransactionResponse;
import com.example.blackrock.services.*;
import com.example.blackrock.responseDto.ParsedTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blackrock.challenge/v1")
public class blkrockController {
    @Autowired
    ParseTransactionService parseTransactionService;

    @Autowired
    TransactionsValidatorService transactionsValidatorService;

    @Autowired
    TransactionFilterService transactionFilterService;

    @Autowired
    CalculateNpsReturnsService calculateNpsReturnsService;

    @Autowired
    CalculateIndexReturnsService calculateIndexReturnsService;

    @PostMapping("/transactions:parse")
    public List<ParsedTransactionResponse> parseTransaction(@RequestBody List<TransactionParseRequest> transactions){
        return parseTransactionService.parseInputTransaction(transactions);
    }

    @PostMapping("/transactions:validator")
    public ValidatedTransactionResponse validateTransacion(@RequestBody TransactionValidatorRequest transactions){
        return transactionsValidatorService.validateTransactionMethod(transactions);
    }

    @PostMapping("/transactions:filter")
    public FilteredtransactionResponse filterTransactions(@RequestBody TransactionFilterRequest request){
        return transactionFilterService.filterTransactionsMethod(request);
    }
    @PostMapping("/returns:nps")
    public void calculateNpsReturns(@RequestBody CalculateRetunsRequest request){
        calculateNpsReturnsService.calculateNpsReturns(request);
    }

    @PostMapping("/returns:index")
    public void calculateIndexReturns(@RequestBody CalculateRetunsRequest request){
        calculateIndexReturnsService.calculateIndexReturns(request);
    }
}
