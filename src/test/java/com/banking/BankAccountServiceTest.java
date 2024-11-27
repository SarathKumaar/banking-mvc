package com.banking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.banking.dao.BankAccountDAO;
import com.banking.dao.BankAccountDAOImpl;
import com.banking.model.BankAccount;
import com.banking.service.BankAccountService;
import com.banking.exception.InsufficientFundsException;
import java.math.BigDecimal;

class BankAccountServiceTest {
    private BankAccountService accountService;
    private BankAccountDAO accountDAO;

    @BeforeEach
    void setUp() {
        accountDAO = new BankAccountDAOImpl();
        accountService = new BankAccountService(accountDAO);
    }

    @Test
    void testCreateAccount() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        accountService.createAccount(account);
        
        BankAccount retrieved = accountService.getAccount("1001");
        assertEquals("John Doe", retrieved.getAccountHolderName());
    }

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        accountService.createAccount(account);
        
        accountService.deposit("1001", new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), accountService.getAccount("1001").getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        accountService.createAccount(account);
        
        accountService.deposit("1001", new BigDecimal("100.00"));
        accountService.withdraw("1001", new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), accountService.getAccount("1001").getBalance());
    }

    @Test
    void testInsufficientFundsWithdraw() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        accountService.createAccount(account);
        
        accountService.deposit("1001", new BigDecimal("50.00"));
        assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw("1001", new BigDecimal("100.00"));
        });
    }

    @Test
    void testCloseAccount() {
        BankAccount account = new BankAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        accountService.createAccount(account);
        
        accountService.closeAccount("1001");
        assertEquals("CLOSED", accountService.getAccount("1001").getStatus());
    }
}