package com.corebank.api.corebank.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corebank.api.corebank.application.service.transaction.TransactionService;
import com.corebank.api.corebank.domain.model.Transaction;
import com.corebank.api.corebank.web.dto.transactions.TransactionResponse;
import com.corebank.api.corebank.web.mapper.TransactionMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

//     @GetMapping("/account/{accountId}")
//     public ResponseEntity<List<TransactionResponse>> getByAccount(
//             @PathVariable Long accountId) {

//         List<Transaction> transactions =
//                 transactionService.getTransactionsByAccountId(accountId);

//         List<TransactionResponse> response =
//                 transactionMapper.toResponseList(transactions);

//         return ResponseEntity.ok(response);
//     }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> getByAccount(
            @PathVariable Long accountId,
            Pageable pageable) {

        Page<Transaction> transactions =
                transactionService.getTransactions(accountId, pageable);

        Page<TransactionResponse> response =
                transactions.map(transactionMapper::toResponse);

        return ResponseEntity.ok(response);
    }
}
