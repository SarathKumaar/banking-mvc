// Controller Layer
package com.banking.controller;

import com.banking.model.BankAccount;
import com.banking.service.BankAccountService;
import java.math.BigDecimal;
import java.util.List;

public class BankAccountController {
    private final BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    public void createAccount(String accountNumber, String holderName, BankAccount.AccountType type) {
        BankAccount account = new BankAccount(accountNumber, holderName, type);
        accountService.createAccount(account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    public List<BankAccount> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
    }

    public void closeAccount(String accountNumber) {
        accountService.closeAccount(accountNumber);
    }
}