package com.corebank.api.corebank.application.service.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corebank.api.corebank.domain.model.Transaction;
import com.corebank.api.corebank.infrastructure.persistence.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction register(Transaction transaction) {
       return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountIdOrderByCreatedAtDesc(accountId);
    }

    @Override
    public Page<Transaction> getTransactions(Long accountId, Pageable pageable) {
        return transactionRepository
                .findByAccountIdOrderByCreatedAtDesc(accountId, pageable);
    }

}
