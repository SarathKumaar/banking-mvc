package com.banking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.banking.controller.BankAccountController;
import com.banking.dao.BankAccountDAO;
import com.banking.dao.BankAccountDAOImpl;
import com.banking.model.BankAccount;
import com.banking.service.BankAccountService;
import java.math.BigDecimal;

class BankAccountControllerTest {
    private BankAccountController accountController;

    @BeforeEach
    void setUp() {
        BankAccountDAO accountDAO = new BankAccountDAOImpl();
        BankAccountService accountService = new BankAccountService(accountDAO);
        accountController = new BankAccountController(accountService);
    }

    @Test
    void testCreateAndRetrieveAccount() {
        accountController.createAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        BankAccount account = accountController.getAccount("1001");
        
        assertEquals("John Doe", account.getAccountHolderName());
        assertEquals(BankAccount.AccountType.CHECKING, account.getAccountType());
    }

    @Test
    void testDepositAndWithdraw() {
        accountController.createAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
        
        accountController.deposit("1001", new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), accountController.getAccount("1001").getBalance());
        
        accountController.withdraw("1001", new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), accountController.getAccount("1001").getBalance());
    }
}