package com.example.blackrock.services;

import com.example.blackrock.dto.*;
import com.example.blackrock.responseDto.ReturnCalculationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateIndexReturnsService {

    private static final double INDEX_RATE = 0.1449;

    public ReturnCalculationResponse calculateIndexReturns(CalculateRetunsRequest request){

        List<Transaction> transactions = request.getTransactions();
        List<SavingsCalculation> results = new ArrayList<>();

        double totalAmount = 0;
        double totalCeiling = 0;

        for(Transaction t : transactions){
            totalAmount += t.getAmount();
            totalCeiling += t.getCeiling();
        }

        int years = getInvestmentYears(request.getAge());

        for(KRule kRule : request.getK()){

            double invested = 0;

            for(Transaction t : transactions){

                LocalDateTime date = t.getDate();

                if(date.isAfter(kRule.getStart()) || date.isEqual(kRule.getStart())){
                    if(date.isBefore(kRule.getEnd()) || date.isEqual(kRule.getEnd())){
                        invested += calculateRemanent(t, request);
                    }
                }

            }

            double finalAmount = invested * Math.pow((1 + INDEX_RATE), years);

            double realValue = finalAmount /
                    Math.pow((1 + request.getInflation()), years);

            double profit = realValue - invested;

            results.add(new SavingsCalculation(
                    kRule.getStart(),
                    kRule.getEnd(),
                    invested,
                    profit,
                    0.0
            ));
        }

        return new ReturnCalculationResponse(
                totalAmount,
                totalCeiling,
                results
        );
    }

    private int getInvestmentYears(Integer age){
        if(age >= 60) return 5;
        return 60 - age;
    }

    private double calculateRemanent(Transaction t, CalculateRetunsRequest request){

        double rem = t.getRemanent();
        LocalDateTime date = t.getDate();

        // Apply q rules
        for(QRule q : request.getQ()){
            if(date.isAfter(q.getStart()) || date.isEqual(q.getStart())){
                if(date.isBefore(q.getEnd()) || date.isEqual(q.getEnd())){
                    rem = q.getFixed();
                }
            }
        }

        // Apply p rules
        for(PRule p : request.getP()){
            if(date.isAfter(p.getStart()) || date.isEqual(p.getStart())){
                if(date.isBefore(p.getEnd()) || date.isEqual(p.getEnd())){
                    rem += p.getExtra();
                }
            }
        }

        return rem;
    }
}