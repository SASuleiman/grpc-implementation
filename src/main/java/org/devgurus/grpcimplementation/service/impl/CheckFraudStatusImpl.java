package org.devgurus.grpcimplementation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.devgurus.grpcimplementation.model.FraudStatusRequest;
import org.devgurus.grpcimplementation.model.FraudStatusResponse;
import org.devgurus.grpcimplementation.model.Transaction;
import org.devgurus.grpcimplementation.service.CheckFraudStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class CheckFraudStatusImpl implements CheckFraudStatus {

    @Override
    public FraudStatusResponse doFraudCheck(FraudStatusRequest fraudStatusRequest) {
        try {
            log.info("Generating Transactions...");
            List<Transaction> transactions = generateTransactionList(fraudStatusRequest.getRandomTransactionRequest());
            log.info("{}, Transactions generated.",transactions.size());
            List<Double> pastAmountList = new ArrayList<>();
            transactions.forEach(transaction -> {
                pastAmountList.add(transaction.getAmount());
            });
            boolean isFraudTransaction = detectFraud(fraudStatusRequest.getTransactionAmount(), pastAmountList);
            return new FraudStatusResponse(isFraudTransaction,transactions.size(),"Fraud Check Complete");

        } catch (Exception ex) {
            log.error("An unexpected error occurred.. {}",ex.getMessage());
            return new FraudStatusResponse(false,0,"An unexpected error occurred");
        }
    }

    private List<Transaction> generateTransactionList(int count) {
        List<Transaction> transactions = new ArrayList<>(count);
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // Generate random timestamp in the past 5 years
            LocalDateTime timestamp = LocalDateTime.now()
                    .minusDays(random.nextInt(1825)) // 1825 days = 5 years
                    .minusHours(random.nextInt(24))
                    .minusMinutes(random.nextInt(60))
                    .minusSeconds(random.nextInt(60));

            // Generate random transaction amount between 1 and 20,000
            double amount = 1 + (random.nextDouble() * 19_999);

            transactions.add(new Transaction(timestamp, amount));
        }

        return transactions;

    }

    public boolean detectFraud(double transactionAmount, List<Double> pastTransactions) {
        double average = pastTransactions.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double standardDeviation = Math.sqrt(pastTransactions.stream()
                .mapToDouble(tx -> Math.pow(tx - average, 2))
                .average().orElse(0));

        // Flag as fraud if the transaction deviates by more than 3 standard deviations
        boolean isFraud = Math.abs(transactionAmount - average) > (3 * standardDeviation);
        if (isFraud) {
            log.info("Fraud detected!");
        } else {
            log.info("Not a Fraud Transaction");
        }
        log.info("Transaction Amount: " + transactionAmount + ", Average: " + average + ", Standard Deviation: " + standardDeviation);
        return isFraud;
    }
}
