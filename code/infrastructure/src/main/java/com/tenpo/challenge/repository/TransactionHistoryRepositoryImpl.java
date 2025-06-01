package com.tenpo.challenge.repository;

import com.tenpo.challenge.dto.NextPageInfo;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import com.tenpo.challenge.repository.mapper.TransactionHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionHistoryRepositoryImpl implements TransactionHistoryRepository {

    private final TransactionHistoryJpa transactionHistoryJpa;

    private final TransactionHistoryMapper transactionHistoryMapper;

    @Override
    public TransactionHistory save(TransactionHistory transactionHistory) {
        final TransactionHistoryEntity result = this.transactionHistoryJpa.save(
                this.transactionHistoryMapper.toEntity(transactionHistory));
        return this.transactionHistoryMapper.toDomain(result);
    }

    @Override
    public PaginatedTransactionHistory getAllPaginated(final Integer offset, final Integer limit) {
        final Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").ascending());
        final Page<TransactionHistoryEntity> resultList = this.transactionHistoryJpa.findAll(pageable);

        return this.buildPaginatedTransactionHistory(resultList);
    }

    private PaginatedTransactionHistory buildPaginatedTransactionHistory(final Page<TransactionHistoryEntity> page) {
        return PaginatedTransactionHistory.builder()
                .data(this.transactionHistoryMapper.toDomainList(page.getContent()))
                .nextPageInfo(NextPageInfo.builder()
                        .offset(page.hasNext() ? page.getNumber() + 1 : page.getNumber())
                        .limit(page.getNumberOfElements())
                        .total(page.getTotalPages())
                        .build())
                .build();
    }
}
