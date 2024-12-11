package org.devgurus.grpcimplementation.controller;

import org.devgurus.grpcimplementation.dto.RestGreetingRequest;
import org.devgurus.grpcimplementation.dto.RestGreetingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RestTestController {

    @PostMapping("/rest/greet")
    public ResponseEntity<RestGreetingResponse> greetings(@RequestBody RestGreetingRequest request) {
        String name = request.getName();
        String message = "Hello, " + name + "! Welcome to REST with Spring Boot...";

        // Build the response
        RestGreetingResponse response = RestGreetingResponse.builder()
                .message(message)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
