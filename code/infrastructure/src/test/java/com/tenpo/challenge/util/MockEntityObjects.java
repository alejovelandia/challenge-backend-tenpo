package com.tenpo.challenge.util;

import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

public class MockEntityObjects {


    public static TransactionHistoryEntity mockTransactionHistoryEntity() {
        TransactionHistoryEntity entity = new TransactionHistoryEntity();
        entity.setId(1L);
        entity.setCreationDate(LocalDateTime.of(2023, 1, 1, 12, 0));
        entity.setEndpoint("/mock-endpoint");
        entity.setParameters("param1=value1&param2=value2");
        entity.setResponse("{\"result\": 42}");
        return entity;
    }

    public static Page<TransactionHistoryEntity> mockTransactionHistoryEntityPage() {
        return new PageImpl<>(List.of(mockTransactionHistoryEntity()));
    }
}
