package com.tenpo.challenge.usecase;

import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;

public interface TransactionLoggingUseCase {

    /**
     * Saves a transaction history record async.
     *
     * @param transactionHistory the transaction history to save
     */
    void asyncSave(final TransactionHistory transactionHistory);

    /**
     * Retrieves all paginated transaction history.
     *
     * @param offset pagination current page number
     * @param limit the maximum number of records per page
     *
     * @return a paginated list of transaction history records with next page info
     */
    PaginatedTransactionHistory getAllPaginated(final Integer offset, final Integer limit);
}
