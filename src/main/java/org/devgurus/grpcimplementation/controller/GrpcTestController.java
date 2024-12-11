package org.devgurus.grpcimplementation.controller;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.devgurus.grpcimplementation.GreetingRequest;
import org.devgurus.grpcimplementation.GreetingResponse;
import org.devgurus.grpcimplementation.GreetingServiceGrpc;

@GrpcService
public class GrpcTestController extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        // Business logic for the greet method
        String name = request.getName();
        String message = "Hello, " + name + "! Welcome to gRPC with Spring Boot.";

        // Build the response
        GreetingResponse response = GreetingResponse.newBuilder()
                .setMessage(message)
                .build();

        // Send the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
