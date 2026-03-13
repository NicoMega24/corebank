package com.corebank.api.corebank.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long accountId;

    @Column(nullable=false, unique=true)
    private String cardNumber;

    @Column(nullable=false)
    private String cardHolder;

    @Column(nullable=false)
    private String cvv;

    private LocalDate expirationDate;

    private boolean active;

    private LocalDateTime createdAt;

    public Card(Long accountId, String cardNumber, String cardHolder, String cvv, LocalDate expirationDate) {
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public void block() {
        this.active = false;
    }

}