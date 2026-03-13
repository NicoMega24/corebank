package com.corebank.api.corebank.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corebank.api.corebank.application.service.account.AccountService;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.web.dto.account.TransferRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // CREATE ACCOUNT
    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestParam String accountNumber,
            @RequestParam Long customerId,
            @RequestParam CurrencyEnum currency,
            @RequestParam AccountTypeEnum accountType
    ) {

        Account account = accountService.createAccount(
                accountNumber,
                customerId,
                currency,
                accountType
        );

        return ResponseEntity.ok(account);
    }

    // GET ACCOUNT
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // DEPOSIT
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(
            @PathVariable Long id,
            @RequestParam BigDecimal amount
    ) {

        accountService.deposit(id, amount);
        return ResponseEntity.ok().build();
    }

    // WITHDRAW
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(
            @PathVariable Long id,
            @RequestParam BigDecimal amount
    ) {

        accountService.withdraw(id, amount);
        return ResponseEntity.ok().build();
    }

    // TRANSFER
    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(
            @RequestBody @Valid TransferRequestDTO request
    ) {

        String transactionGroupId = UUID.randomUUID().toString();

        accountService.transfer(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount(),
                transactionGroupId
        );

        return ResponseEntity.ok().build();
    }

    // BLOCK
    @PatchMapping("/{id}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable Long id) {

        accountService.blockAccount(id);
        return ResponseEntity.ok().build();
    }

    // ACTIVATE
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateAccount(@PathVariable Long id) {

        accountService.activateAccount(id);
        return ResponseEntity.ok().build();
    }

    // CLOSE
    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> closeAccount(@PathVariable Long id) {

        accountService.closedAccount(id);
        return ResponseEntity.ok().build();
    }

}