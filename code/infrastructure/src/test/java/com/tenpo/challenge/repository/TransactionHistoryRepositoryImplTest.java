package com.tenpo.challenge.repository;

import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import com.tenpo.challenge.repository.mapper.TransactionHistoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.tenpo.challenge.util.MockDtoObjects.mockTransactionHistory;
import static com.tenpo.challenge.util.MockEntityObjects.mockTransactionHistoryEntity;
import static com.tenpo.challenge.util.MockEntityObjects.mockTransactionHistoryEntityPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionHistoryRepositoryImplTest {

    @Mock
    private TransactionHistoryJpa transactionHistoryJpa;

    @Mock
    private TransactionHistoryMapper transactionHistoryMapper;

    @InjectMocks
    private TransactionHistoryRepositoryImpl transactionHistoryRepository;

    @Test
    void save_ShouldReturnMappedSavedEntity() {
        final TransactionHistoryEntity entity = mockTransactionHistoryEntity();
        final TransactionHistory dto = mockTransactionHistory();

        when(this.transactionHistoryMapper.toEntity(dto)).thenReturn(entity);
        when(this.transactionHistoryJpa.save(entity)).thenReturn(entity);
        when(this.transactionHistoryMapper.toDomain(entity)).thenReturn(dto);

        final TransactionHistory result = this.transactionHistoryRepository.save(dto);

        assertEquals(dto, result);
        verify(this.transactionHistoryMapper).toEntity(dto);
        verify(this.transactionHistoryJpa).save(entity);
        verify(this.transactionHistoryMapper).toDomain(entity);
    }

    @Test
    void getAll_ShouldReturnMappedFindAll() {
        final int offset = 0;
        final int limit = 10;
        final Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").ascending());
        final TransactionHistoryEntity entity = mockTransactionHistoryEntity();
        final TransactionHistory dto = mockTransactionHistory();
        final Page<TransactionHistoryEntity> page = mockTransactionHistoryEntityPage();

        when(this.transactionHistoryJpa.findAll(pageable)).thenReturn(page);
        when(this.transactionHistoryMapper.toDomainList(page.getContent())).thenReturn(List.of(dto));

        final PaginatedTransactionHistory result = this.transactionHistoryRepository.getAllPaginated(offset, limit);

        assertEquals(dto, result.getData().getFirst());
        assertEquals(0, result.getNextPageInfo().getOffset());
        assertEquals(1, result.getNextPageInfo().getLimit());
        assertEquals(1, result.getNextPageInfo().getTotal());
        verify(this.transactionHistoryJpa).findAll(pageable);
        verify(this.transactionHistoryMapper).toDomainList(List.of(entity));
    }
}
