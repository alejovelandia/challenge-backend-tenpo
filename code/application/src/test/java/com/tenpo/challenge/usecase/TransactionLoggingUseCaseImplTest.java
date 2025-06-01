package com.tenpo.challenge.usecase;

import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.TransactionHistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.tenpo.challenge.util.MockDtoObjects.mockPaginatedTransactionHistory;
import static com.tenpo.challenge.util.MockDtoObjects.mockTransactionHistory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionLoggingUseCaseImplTest {

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @InjectMocks
    private TransactionLoggingUseCaseImpl transactionHistoryUseCase;

    @Test
    void save_ShouldAsyncSaveTransactionAsynchronous() {
        final TransactionHistory dto = mockTransactionHistory();

        when(this.transactionHistoryRepository.save(dto)).thenReturn(dto);

        this.transactionHistoryUseCase.asyncSave(dto);

        verify(this.transactionHistoryRepository).save(any(TransactionHistory.class));
    }

    @Test
    void getAll_ShouldReturnTransactionHistory() {
        final int offset = 0;
        final int limit = 10;
        final PaginatedTransactionHistory dto = mockPaginatedTransactionHistory();

        when(this.transactionHistoryRepository.getAllPaginated(offset, limit)).thenReturn(dto);

        final PaginatedTransactionHistory result = this.transactionHistoryUseCase.getAllPaginated(offset, limit);

        assertFalse(result.getData().isEmpty());
        assertEquals(dto, result);
        verify(this.transactionHistoryRepository).getAllPaginated(offset, limit);
    }
}
