package com.corebank.api.corebank.application.service.transaction;

import java.util.List;

import com.corebank.api.corebank.domain.model.Transaction;

public interface TransactionService {

    Transaction register(Transaction transaction);

    List<Transaction> getTransactionsByAccountId(Long accountId);

}
