package com.corebank.api.corebank.application.service.ledger;

import java.math.BigDecimal;

public interface LedgerService {

    void recordDeposit(Long accountId, BigDecimal amount);

    void recordWithdraw(Long accountId, BigDecimal amount);

    void recordTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

}