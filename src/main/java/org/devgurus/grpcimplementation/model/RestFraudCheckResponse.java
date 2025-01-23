package org.devgurus.grpcimplementation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestFraudCheckResponse {
    private boolean fraudTransaction;
    private int pastTransactionCountGenerated;
    private String message;
}
