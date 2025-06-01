package com.tenpo.challenge.controller;

import com.tenpo.api.generated.TransactionApi;
import com.tenpo.api.model.PaginatedTransactionResponse;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.mapper.TransactionMapper;
import com.tenpo.challenge.usecase.TransactionLoggingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

    private final TransactionLoggingUseCase transactionLoggingUseCase;

    private final TransactionMapper transactionMapper;

    @Override
    public ResponseEntity<PaginatedTransactionResponse> transactionsHistory(final Integer offset, final Integer limit) {
        final PaginatedTransactionHistory paginatedTransactionHistory = this.transactionLoggingUseCase.getAllPaginated(offset, limit);
        final PaginatedTransactionResponse response = this.transactionMapper.toResponseDto(paginatedTransactionHistory);
        return ResponseEntity.ok(response);
    }
}