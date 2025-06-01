package com.tenpo.challenge.util;

import com.tenpo.challenge.dto.NextPageInfo;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;

import java.util.List;

public class MockDtoObjects {

    public static final String ENDPOINT = "/mock-endpoint";
    public static final String PARAMETERS = "param1=value1&param2=value2";
    public static final String RESPONSE = "{\"result\": 42}";

    public static final Long ID = 1L;
    public static final java.time.LocalDateTime CREATION_DATE = java.time.LocalDateTime.now();

    public static TransactionHistory mockTransactionHistory() {
        return TransactionHistory.builder()
                .id(ID)
                .creationDate(CREATION_DATE)
                .endpoint(ENDPOINT)
                .parameters(PARAMETERS)
                .response(RESPONSE)
                .build();
    }

    public static NextPageInfo mockNextPageInfo() {
        return NextPageInfo.builder()
                .offset(1)
                .limit(10)
                .total(5)
                .build();
    }

    public static PaginatedTransactionHistory mockPaginatedTransactionHistory() {
        return PaginatedTransactionHistory.builder()
                .data(List.of(mockTransactionHistory()))
                .nextPageInfo(mockNextPageInfo())
                .build();
    }
}
