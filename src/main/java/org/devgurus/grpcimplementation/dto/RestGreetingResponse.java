package org.devgurus.grpcimplementation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestGreetingResponse {
    private String message;
}
