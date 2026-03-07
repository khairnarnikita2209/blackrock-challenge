package com.example.blackrock.services;

import com.example.blackrock.dto.*;
import com.example.blackrock.responseDto.ReturnCalculationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateNpsReturnsService {

    private static final double NPS_RATE = 0.0711;

    public ReturnCalculationResponse calculateNpsReturns(CalculateRetunsRequest request){
        System.out.println("NPS return calculation service called");
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

            double finalAmount = invested * Math.pow((1 + NPS_RATE), years);

            double realValue = finalAmount /
                    Math.pow((1 + request.getInflation()), years);

            double profit = realValue - invested;

            double taxBenefit = calculateTaxBenefit(invested, request.getWage());

            results.add(new SavingsCalculation(
                    kRule.getStart(),
                    kRule.getEnd(),
                    invested,
                    profit,
                    taxBenefit
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

        for(QRule q : request.getQ()){
            if(date.isAfter(q.getStart()) || date.isEqual(q.getStart())){
                if(date.isBefore(q.getEnd()) || date.isEqual(q.getEnd())){
                    rem = q.getFixed();
                }
            }
        }

        for(PRule p : request.getP()){
            if(date.isAfter(p.getStart()) || date.isEqual(p.getStart())){
                if(date.isBefore(p.getEnd()) || date.isEqual(p.getEnd())){
                    rem += p.getExtra();
                }
            }
        }

        return rem;
    }

    private double calculateTaxBenefit(double invested, double wage){

        double annualIncome = wage * 12;

        double deduction = Math.min(
                invested,
                Math.min(annualIncome * 0.10, 200000)
        );

        double originalTax = calculateTax(annualIncome);
        double reducedTax = calculateTax(annualIncome - deduction);

        return originalTax - reducedTax;
    }

    private double calculateTax(double income){

        if(income <= 700000) return 0;

        if(income <= 1000000)
            return (income - 700000) * 0.10;

        if(income <= 1200000)
            return 30000 + (income - 1000000) * 0.15;

        if(income <= 1500000)
            return 60000 + (income - 1200000) * 0.20;

        return 120000 + (income - 1500000) * 0.30;
    }
}