package com.corebank.api.corebank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String ownerName;

    private Double balance;

    public Account() {

    }

    public Account(String ownerName, Double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public Long getId() {
        return  id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
