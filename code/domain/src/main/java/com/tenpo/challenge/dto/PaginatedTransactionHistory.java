package com.tenpo.challenge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginatedTransactionHistory {

    List<TransactionHistory> data;

    NextPageInfo nextPageInfo;
}
