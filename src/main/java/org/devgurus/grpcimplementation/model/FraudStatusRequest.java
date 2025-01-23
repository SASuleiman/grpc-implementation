package org.devgurus.grpcimplementation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FraudStatusRequest {
    private double transactionAmount;
    private int randomTransactionRequest;
}
