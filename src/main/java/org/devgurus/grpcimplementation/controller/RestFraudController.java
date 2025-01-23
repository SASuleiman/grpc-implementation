package org.devgurus.grpcimplementation.controller;

import lombok.RequiredArgsConstructor;
import org.devgurus.grpcimplementation.model.FraudStatusRequest;
import org.devgurus.grpcimplementation.model.FraudStatusResponse;
import org.devgurus.grpcimplementation.model.RestFraudCheckResponse;
import org.devgurus.grpcimplementation.service.CheckFraudStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fraud")
public class RestFraudController {



    private final CheckFraudStatus checkFraudStatus;

    @PostMapping("/check")
    public ResponseEntity<RestFraudCheckResponse> fraudCheck(@RequestParam double amount) {
        try {
            // Business logic
            FraudStatusRequest request = new FraudStatusRequest(amount, 20000);
            FraudStatusResponse response = checkFraudStatus.doFraudCheck(request);

            RestFraudCheckResponse r = RestFraudCheckResponse.builder()
                    .fraudTransaction(response.isFraudTransaction())
                    .message(response.getMessage())
                    .pastTransactionCountGenerated(response.getPastTransactionCountGenerated()).build();

            return ResponseEntity.ok(r);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new RestFraudCheckResponse(false,0, e.getMessage()));
        }
    }

}
