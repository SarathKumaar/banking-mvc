package com.banking;

import com.banking.controller.BankAccountController;
import com.banking.dao.BankAccountDAO;
import com.banking.dao.BankAccountDAOImpl;
import com.banking.model.BankAccount;
import com.banking.service.BankAccountService;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize components
            BankAccountDAO accountDAO = new BankAccountDAOImpl();
            BankAccountService accountService = new BankAccountService(accountDAO);
            BankAccountController accountController = new BankAccountController(accountService);

            // Test account creation
            System.out.println("Creating new account...");
            accountController.createAccount("1001", "John Doe", BankAccount.AccountType.CHECKING);
            
            // Test deposit
            System.out.println("Depositing $1000...");
            accountController.deposit("1001", new BigDecimal("1000.00"));
            
            // Test account retrieval
            BankAccount account = accountController.getAccount("1001");
            System.out.println("Account holder: " + account.getAccountHolderName());
            System.out.println("Current balance: $" + account.getBalance());
            
            // Test withdrawal
            System.out.println("Withdrawing $500...");
            accountController.withdraw("1001", new BigDecimal("500.00"));
            
            // Check final balance
            account = accountController.getAccount("1001");
            System.out.println("Final balance: $" + account.getBalance());

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}