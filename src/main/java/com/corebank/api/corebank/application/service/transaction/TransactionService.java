package com.corebank.api.corebank.application.service.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.corebank.api.corebank.domain.model.Transaction;

public interface TransactionService {

    Transaction register(Transaction transaction);

    List<Transaction> getTransactionsByAccountId(Long accountId);

    Page<Transaction> getTransactions(Long accountId, Pageable pageable);

}
