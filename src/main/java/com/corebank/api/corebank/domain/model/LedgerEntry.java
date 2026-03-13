package com.corebank.api.corebank.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="ledger_entries")
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy="ledgerEntry", cascade=CascadeType.ALL)
    private List<LedgerLine> lines;

    public LedgerEntry(String reference, List<LedgerLine> lines) {
        this.reference = reference;
        this.lines = lines;
        this.createdAt = LocalDateTime.now();
    }

}