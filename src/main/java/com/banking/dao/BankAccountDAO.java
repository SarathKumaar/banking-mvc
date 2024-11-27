// DAO Interface
package com.banking.dao;

import com.banking.model.BankAccount;
import java.util.List;

public interface BankAccountDAO {
    void createAccount(BankAccount account);
    BankAccount getAccount(String accountNumber);
    List<BankAccount> getAllAccounts();
    void updateAccount(BankAccount account);
    void deleteAccount(String accountNumber);
}