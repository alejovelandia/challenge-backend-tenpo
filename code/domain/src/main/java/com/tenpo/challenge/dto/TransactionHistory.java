package com.tenpo.challenge.dto;

import lombok.*;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionHistory implements Serializable {

    Long id;

    java.time.LocalDateTime creationDate;

    String endpoint;

    String parameters;

    String response;
}
