package com.tenpo.challenge.mapper;

import com.tenpo.api.model.PaginatedTransactionResponse;
import com.tenpo.api.model.Transaction;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import org.junit.jupiter.api.Test;

import static com.tenpo.challenge.util.MockDtoObjects.mockPaginatedTransactionHistory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransactionMapperTest {

    private final TransactionMapper mapper = new TransactionMapperImpl();

    @Test
    void toResponseDto_ShouldMapAllFieldsCorrectly() {
        final PaginatedTransactionHistory domainDto = mockPaginatedTransactionHistory();

        final PaginatedTransactionResponse result = mapper.toResponseDto(domainDto);


        final Transaction transaction = result.getData().getFirst();
        final TransactionHistory domainTransaction = domainDto.getData().getFirst();

        assertEquals(domainTransaction.getId(), transaction.getId());
        assertEquals(domainTransaction.getCreationDate(), transaction.getCreationDate().toLocalDateTime());
        assertEquals(domainTransaction.getEndpoint(), transaction.getEndpoint());
        assertEquals(domainTransaction.getParameters(), transaction.getParameters());
        assertEquals(domainTransaction.getResponse(), transaction.getResponse());

        assertEquals(domainDto.getNextPageInfo().getOffset(), result.getNextPageInfo().getOffset());
        assertEquals(domainDto.getNextPageInfo().getLimit(), result.getNextPageInfo().getLimit());
        assertEquals(domainDto.getNextPageInfo().getTotal(), result.getNextPageInfo().getTotal());
    }

    @Test
    void toResponseDto_ShouldReturnNull_WhenEntityIsNull() {
        final PaginatedTransactionResponse result = mapper.toResponseDto(null);
        assertNull(result);
    }
}