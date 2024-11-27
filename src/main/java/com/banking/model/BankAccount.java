package com.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private AccountType accountType;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private String status; // ACTIVE, FROZEN, CLOSED

    public enum AccountType {
        CHECKING, SAVINGS, BUSINESS
    }

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
