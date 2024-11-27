// DAO Implementation
package com.banking.dao;

import com.banking.model.BankAccount;
import com.banking.exception.AccountNotFoundException;
import java.util.*;

public class BankAccountDAOImpl implements BankAccountDAO {
    // Using HashMap as in-memory database for demonstration
    private final Map<String, BankAccount> accountsDB = new HashMap<>();

    @Override
    public void createAccount(BankAccount account) {
        accountsDB.put(account.getAccountNumber(), account);
    }

    @Override
    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accountsDB.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accountsDB.values());
    }

    @Override
    public void updateAccount(BankAccount account) {
        if (!accountsDB.containsKey(account.getAccountNumber())) {
            throw new AccountNotFoundException("Account not found: " + account.getAccountNumber());
        }
        accountsDB.put(account.getAccountNumber(), account);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        if (!accountsDB.containsKey(accountNumber)) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        accountsDB.remove(accountNumber);
    }
}