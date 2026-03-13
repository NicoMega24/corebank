package com.corebank.api.corebank.domain.model;

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
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String documentNumber;

    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

    public Customer(String firstName, String lastName, String email, String documentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.documentNumber = documentNumber;
        this.createdAt = LocalDateTime.now();
    }

}