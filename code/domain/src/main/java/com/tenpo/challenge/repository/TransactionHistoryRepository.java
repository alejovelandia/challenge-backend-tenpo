package com.tenpo.challenge.repository;

import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;

public interface TransactionHistoryRepository {

    /**
     * Saves a transaction history record.
     *
     * @param transactionHistory the transaction history to save
     * @return the saved transaction history
     */
    TransactionHistory save(final TransactionHistory transactionHistory);

    /**
     * Retrieves all paginated transactions history.
     * @param offset pagination current page number
     * @param limit the maximum number of records to return per page
     *
     * @return a paginated list of transaction history records with next page info
     */
    PaginatedTransactionHistory getAllPaginated(final Integer offset, final Integer limit);
}
