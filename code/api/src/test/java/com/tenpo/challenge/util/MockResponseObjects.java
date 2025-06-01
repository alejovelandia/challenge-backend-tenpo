package com.tenpo.challenge.util;

import com.tenpo.api.model.NextPageInfo;
import com.tenpo.api.model.PaginatedTransactionResponse;
import com.tenpo.api.model.Transaction;

import java.time.OffsetDateTime;
import java.util.List;

public class MockResponseObjects {

    public static Transaction mockTransactionResponse() {
        final Transaction response = new Transaction();
        response.setId(1L);
        response.setEndpoint("/mock-endpoint");
        response.setParameters("param1=value1&param2=value2");
        response.setResponse("{\"result\": 42}");
        response.setCreationDate(OffsetDateTime.now());
        return response;
    }

    public static NextPageInfo mockNextPageInfoResponse() {
        final NextPageInfo nextPageInfo = new NextPageInfo();
        nextPageInfo.setOffset(1);
        nextPageInfo.setLimit(10);
        nextPageInfo.setTotal(5);
        return nextPageInfo;
    }

    public static PaginatedTransactionResponse mockPaginatedTransactionResponse() {
        final PaginatedTransactionResponse response = new PaginatedTransactionResponse();
        response.setData(List.of(mockTransactionResponse()));
        response.setNextPageInfo(mockNextPageInfoResponse());
        return response;
    }
}
