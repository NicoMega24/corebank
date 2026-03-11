package com.corebank.api.corebank.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corebank.api.corebank.application.service.account.AccountService;
import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.web.dto.account.AccountRequestDTO;
import com.corebank.api.corebank.web.dto.account.AccountResponseDTO;
import com.corebank.api.corebank.web.dto.account.DepositRequestDTO;
import com.corebank.api.corebank.web.dto.account.TransferRequestDTO;
import com.corebank.api.corebank.web.dto.account.WithdrawRequestDTO;
import com.corebank.api.corebank.web.mapper.AccountMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping //CREATE ACCOUNT
    public ResponseEntity<AccountResponseDTO> create(
        @Valid @RequestBody AccountRequestDTO request) {

        Account account = accountService.createAccount(
            request.accountNumber(),
            request.customerId(),
            request.currency(),
            request.accountType()
        );

        return ResponseEntity
                .status(201)
                .body(accountMapper.toResponse(account));
    }

    //GET ACCOUNT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long id) {
            
            Account account = accountService.getAccountById(id);
            return ResponseEntity.ok(accountMapper.toResponse(account));
    }

    //DEPOSIT
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponseDTO> deposit(
            @PathVariable Long id,
            @Valid @RequestBody DepositRequestDTO request) {

        accountService.deposit(id, request.amount());
        Account updatedAccount = accountService.getAccountById(id);

        return ResponseEntity.ok(accountMapper.toResponse(updatedAccount));

    }

    //WITHDRAW
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponseDTO> withdraw(
            @PathVariable Long id,
            @Valid @RequestBody WithdrawRequestDTO request) {
        
        accountService.withdraw(id, request.amount());
        Account updatedAccount = accountService.getAccountById(id);

        return ResponseEntity.ok(accountMapper.toResponse(updatedAccount));
    }

    //BLOCK ACCOUNT
    @PatchMapping("/{id}/block")
    public ResponseEntity<Void> block(@PathVariable Long id) {
        
        accountService.activateAccount(id);
        
        return ResponseEntity.noContent().build();
    }


    //ACTIVE ACCOUNT
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        
        accountService.activateAccount(id);
        
        return ResponseEntity.noContent().build();
    }


    //CLOSE AACOUNT
    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> close(@PathVariable Long id) {
        
        accountService.closedAccount(id);
        
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequestDTO request) {
        accountService.transfer(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount()
        );
        return ResponseEntity.ok().build();    
    }
}
