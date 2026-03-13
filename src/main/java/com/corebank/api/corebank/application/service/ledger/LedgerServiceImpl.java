package com.corebank.api.corebank.application.service.ledger;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corebank.api.corebank.domain.enums.LedgerType;
import com.corebank.api.corebank.domain.model.LedgerEntry;
import com.corebank.api.corebank.domain.model.LedgerLine;
import com.corebank.api.corebank.infrastructure.persistence.LedgerEntryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {

    private final LedgerEntryRepository ledgerEntryRepository;

    @Override
    @Transactional
    public void recordDeposit(Long accountId, BigDecimal amount) {

        LedgerLine credit = new LedgerLine(accountId, amount, LedgerType.CREDIT);

        LedgerEntry entry = new LedgerEntry(
                "DEPOSIT",
                List.of(credit)
        );

        credit.setLedgerEntry(entry);

        ledgerEntryRepository.save(entry);
    }

    @Override
    @Transactional
    public void recordWithdraw(Long accountId, BigDecimal amount) {

        LedgerLine debit = new LedgerLine(accountId, amount, LedgerType.DEBIT);

        LedgerEntry entry = new LedgerEntry(
                "WITHDRAW",
                List.of(debit)
        );

        debit.setLedgerEntry(entry);

        ledgerEntryRepository.save(entry);
    }

    @Override
    @Transactional
    public void recordTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        LedgerLine debit = new LedgerLine(fromAccountId, amount, LedgerType.DEBIT);
        LedgerLine credit = new LedgerLine(toAccountId, amount, LedgerType.CREDIT);

        LedgerEntry entry = new LedgerEntry(
                "TRANSFER",
                List.of(debit, credit)
        );

        debit.setLedgerEntry(entry);
        credit.setLedgerEntry(entry);

        ledgerEntryRepository.save(entry);
    }
}