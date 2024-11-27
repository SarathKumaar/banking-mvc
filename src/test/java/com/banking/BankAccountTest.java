package com.banking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.banking.model.BankAccount;

class BankAccountTest {
    @Test
    void testBankAccountCreation() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        
        assertEquals("1001", account.getAccountNumber());
        assertEquals("John Doe", account.getAccountHolderName());
        assertEquals(BankAccount.AccountType.CHECKING, account.getAccountType());
        assertEquals("ACTIVE", account.getStatus());
        assertEquals(0, account.getBalance().intValue());
    }
}