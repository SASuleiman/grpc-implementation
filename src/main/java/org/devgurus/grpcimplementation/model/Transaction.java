package org.devgurus.grpcimplementation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Transaction {
    private LocalDateTime timestamp;
    private  double amount;


}
