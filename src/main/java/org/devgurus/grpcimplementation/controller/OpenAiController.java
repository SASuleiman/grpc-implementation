package org.devgurus.grpcimplementation.controller;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.devgurus.grpcimplementation.OpenAiRequest;
import org.devgurus.grpcimplementation.OpenAiResponse;
import org.devgurus.grpcimplementation.OpenAiServiceGrpc;
import org.devgurus.grpcimplementation.model.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.

@GrpcService
public class OpenAiController extends OpenAiServiceGrpc.OpenAiServiceImplBase {

    private final ChatClient chatClient;

    public OpenAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @Override
    public void ask(OpenAiRequest request, StreamObserver<OpenAiResponse> responseObserver) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(request.getQuestion())
                .call()
                .entity(ChatResponse.class);
        OpenAiResponse response = OpenAiResponse.newBuilder()
                .setMessage(chatResponse.response())
                        .setConclusion(chatResponse.conclusion()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
