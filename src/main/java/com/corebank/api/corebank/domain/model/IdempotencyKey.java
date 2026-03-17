package com.corebank.api.corebank.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idempotency_keys")
public class IdempotencyKey {

    @Id
    private String id;

    private String endpoint;

    private LocalDateTime createdAt;

    protected IdempotencyKey() {}

    public IdempotencyKey(String id, String endpoint) {
        this.id = id;
        this.endpoint = endpoint;
        this.createdAt = LocalDateTime.now();
    }
}