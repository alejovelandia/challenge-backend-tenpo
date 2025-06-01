package com.tenpo.challenge.controller;

import com.tenpo.api.model.PaginatedTransactionResponse;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.mapper.TransactionMapper;
import com.tenpo.challenge.usecase.TransactionLoggingUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.tenpo.challenge.util.MockDtoObjects.mockPaginatedTransactionHistory;
import static com.tenpo.challenge.util.MockResponseObjects.mockPaginatedTransactionResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionLoggingUseCase transactionLoggingUseCase;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void transactionsHistory_ShouldReturnService_PaginatedTransactionsHistory() {
        final int offset = 0;
        final int limit = 10;
        final PaginatedTransactionHistory paginatedTransactionHistory = mockPaginatedTransactionHistory();
        final PaginatedTransactionResponse response = mockPaginatedTransactionResponse();

        when(this.transactionLoggingUseCase.getAllPaginated(offset, limit)).thenReturn(paginatedTransactionHistory);
        when(this.transactionMapper.toResponseDto(paginatedTransactionHistory)).thenReturn(response);

        final ResponseEntity<PaginatedTransactionResponse> result = this.transactionController.transactionsHistory(offset, limit);

        assertNotNull(result.getBody());
        assertEquals(response, result.getBody());
        verify(this.transactionLoggingUseCase).getAllPaginated(offset, limit);
    }
}
