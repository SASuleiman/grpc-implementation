package org.devgurus.grpcimplementation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestFraudCheckRequest {
    private double transactionAmount;
    private int randomTransactionRequest;
}
