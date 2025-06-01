package com.tenpo.challenge.usecase;

import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionLoggingUseCaseImpl implements TransactionLoggingUseCase {

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    @Async
    public void asyncSave(TransactionHistory transactionHistory) {
        this.transactionHistoryRepository.save(transactionHistory);
    }

    @Override
    public PaginatedTransactionHistory getAllPaginated(final Integer offset, final Integer limit) {
        return this.transactionHistoryRepository.getAllPaginated(offset, limit);
    }
}
