package com.example.blackrock.services;

import com.example.blackrock.dto.*;
import com.example.blackrock.responseDto.FilteredtransactionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TransactionFilterService {
    public FilteredtransactionResponse filterTransactionsMethod(TransactionFilterRequest request){
        List<KRule> k = request.getK();
        List<PRule> p = request.getP();
        List<QRule> q = request.getQ();

        List<FilteredValidTransaction> valid = new ArrayList<>();
        List<FilteredInvalidTransaction> invalid = new ArrayList<>();

        Set<String> seen = new HashSet<>();

        for(Transaction txn: request.getTransactions()){
            LocalDateTime txnDate = txn.getDate();

            if(txn.getAmount()<0){
                invalid.add(new FilteredInvalidTransaction(txn.getDate(), txn.getAmount(),"Negative amounts are not allowed"));
                continue;
            }

            String transactionIdentifier = txn.getDate() + "|" + txn.getAmount();
            if(seen.contains(transactionIdentifier)){
                invalid.add(new FilteredInvalidTransaction(txn.getDate(), txn.getAmount(),"Duplicate Transaction"));
                continue;
            }else{
                seen.add(transactionIdentifier);            }


            boolean liesInQWindow = isDateInAnyWindow(txnDate,request.getQ());
            if(liesInQWindow)
                continue;

            boolean liesInKWindow = isDateInAnyWindow(txnDate,request.getK());
            if(liesInKWindow){
                double value = txn.getAmount();
                int rounded = (int) (Math.ceil(value / 100.0) * 100);
                double ceiling = rounded;
                double remanent = ceiling - txn.getAmount();
                valid.add(new FilteredValidTransaction(txn.getDate(),txn.getAmount(),ceiling,remanent,true));
            }
        }

        return new FilteredtransactionResponse(valid,invalid);

    }

    public boolean isDuplicate(List<Transaction> transactions, Transaction txn){
        boolean seen = false;

        Iterator<Transaction> itr = transactions.iterator();
        while (itr.hasNext()){
            Transaction transaction = itr.next();
            if(transaction.equals(txn)){
                if(seen){
                    itr.remove();
                    return true;
                }
                seen = true;
            }
        }

        return seen;
    }

    public static boolean isDateInAnyWindow(
            LocalDateTime date,
            List<? extends TimeWindow> rules) {

        if (date == null || rules == null || rules.isEmpty()) {
            return false;
        }

        return rules.stream()
                .anyMatch(rule -> isWithinWindow(date, rule));
    }

    public static boolean isWithinWindow(LocalDateTime date, TimeWindow window) {
        if (date == null || window == null) return false;

        return !date.isBefore(window.getStart())
                && !date.isAfter(window.getEnd());
    }

}
