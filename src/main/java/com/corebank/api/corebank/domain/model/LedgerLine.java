package com.corebank.api.corebank.domain.model;

import java.math.BigDecimal;

import com.corebank.api.corebank.domain.enums.LedgerType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ledger_lines")
public class LedgerLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private LedgerType type;

    @ManyToOne
    @JoinColumn(name="ledger_entry_id")
    private LedgerEntry ledgerEntry;

    public LedgerLine(Long accountId, BigDecimal amount, LedgerType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public void setLedgerEntry(LedgerEntry ledgerEntry) {
        this.ledgerEntry = ledgerEntry;
    }

}