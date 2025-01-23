package org.devgurus.grpcimplementation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FraudStatusResponse {
    private boolean fraudTransaction;
    private int pastTransactionCountGenerated;
    private String message;
}
