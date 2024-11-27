// Service Layer
package com.banking.service;

import com.banking.dao.BankAccountDAO;
import com.banking.model.BankAccount;
import com.banking.exception.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.List;

public class BankAccountService {
    private final BankAccountDAO accountDAO;

    public BankAccountService(BankAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void createAccount(BankAccount account) {
        validateAccount(account);
        accountDAO.createAccount(account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    public List<BankAccount> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        BankAccount account = accountDAO.getAccount(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        accountDAO.updateAccount(account);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        BankAccount account = accountDAO.getAccount(accountNumber);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account: " + accountNumber);
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountDAO.updateAccount(account);
    }

    public void closeAccount(String accountNumber) {
        BankAccount account = accountDAO.getAccount(accountNumber);
        account.setStatus("CLOSED");
        accountDAO.updateAccount(account);
    }

    private void validateAccount(BankAccount account) {
        if (account.getAccountHolderName() == null || account.getAccountHolderName().trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be empty");
        }
        if (account.getAccountNumber() == null || account.getAccountNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (account.getAccountType() == null) {
            throw new IllegalArgumentException("Account type must be specified");
        }
    }
}