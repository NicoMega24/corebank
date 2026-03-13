package com.corebank.api.corebank.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corebank.api.corebank.domain.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
