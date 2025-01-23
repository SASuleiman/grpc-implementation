package org.devgurus.grpcimplementation.controller;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.devgurus.grpcimplementation.FraudCheckRequest;
import org.devgurus.grpcimplementation.FraudCheckResponse;
import org.devgurus.grpcimplementation.FraudCheckServiceGrpc;
import org.devgurus.grpcimplementation.model.FraudStatusRequest;
import org.devgurus.grpcimplementation.model.FraudStatusResponse;
import org.devgurus.grpcimplementation.service.CheckFraudStatus;

@GrpcService
@RequiredArgsConstructor
public class GrpcFraudController extends FraudCheckServiceGrpc.FraudCheckServiceImplBase {

    private final CheckFraudStatus checkFraudStatus;

    @Override
    public void fraudCheck(FraudCheckRequest request, StreamObserver<FraudCheckResponse> responseObserver) {

        try {
            // business logic
            FraudStatusResponse fraudStatusResponse = checkFraudStatus.doFraudCheck(new FraudStatusRequest(request.getAmount(), 20000));
            FraudCheckResponse response = FraudCheckResponse.newBuilder()
                    .setMessage(fraudStatusResponse.getMessage())
                    .setFraudTransaction(fraudStatusResponse.isFraudTransaction())
                    .setPastTransactionCountGenerated(fraudStatusResponse.getPastTransactionCountGenerated())
                    .build();

            // return response
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
