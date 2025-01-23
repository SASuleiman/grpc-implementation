package org.devgurus.grpcimplementation.service;

import org.devgurus.grpcimplementation.model.FraudStatusRequest;
import org.devgurus.grpcimplementation.model.FraudStatusResponse;

public interface CheckFraudStatus {
    FraudStatusResponse doFraudCheck(FraudStatusRequest fraudStatusRequest);
}
