package com.tenpo.challenge.repository.mapper;

import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import org.junit.jupiter.api.Test;

import static com.tenpo.challenge.util.MockDtoObjects.mockTransactionHistory;
import static com.tenpo.challenge.util.MockEntityObjects.mockTransactionHistoryEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransactionHistoryMapperTest {

    private final TransactionHistoryMapper mapper = new TransactionHistoryMapperImpl();

    @Test
    void toDomain_ShouldMapAllFieldsCorrectly() {
        final TransactionHistoryEntity entity = mockTransactionHistoryEntity();

        final TransactionHistory result = mapper.toDomain(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getCreationDate(), result.getCreationDate());
        assertEquals(entity.getEndpoint(), result.getEndpoint());
        assertEquals(entity.getParameters(), result.getParameters());
        assertEquals(entity.getResponse(), result.getResponse());
    }

    @Test
    void toDomain_ShouldReturnNull_WhenEntityIsNull() {
        final TransactionHistory result = mapper.toDomain(null);
        assertNull(result);
    }

    @Test
    void toEntity_ShouldMapAllFieldsCorrectly() {
        final TransactionHistory dto = mockTransactionHistory();

        TransactionHistoryEntity result = mapper.toEntity(dto);

        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCreationDate(), result.getCreationDate());
        assertEquals(dto.getEndpoint(), result.getEndpoint());
        assertEquals(dto.getParameters(), result.getParameters());
        assertEquals(dto.getResponse(), result.getResponse());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenDtoIsNull() {
        TransactionHistoryEntity result = mapper.toEntity(null);
        assertNull(result);
    }
}