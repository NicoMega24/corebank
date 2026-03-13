package com.corebank.api.corebank.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corebank.api.corebank.domain.model.LedgerEntry;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {
}