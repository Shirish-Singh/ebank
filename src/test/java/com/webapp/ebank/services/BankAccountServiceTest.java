package com.webapp.ebank.services;


import com.webapp.ebank.BaseTest;
import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.TransactionDetails;
import com.webapp.ebank.repository.AccountRepository;
import com.webapp.ebank.repository.BankUserRepository;
import com.webapp.ebank.services.enumerator.BankAccountTypes;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Slf4j
public class BankAccountServiceTest extends BaseTest {

    private final String IBAN_NUMBER = "IT60X0542811101000000123456";
    private final String IBAN_NUMBER2 = "IT70X0542811101000000123457";

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void NoAccountExistsDepositTest_Fail() {
        BigDecimal amount = new BigDecimal(10);
        try {
            TransactionDetails transactionDetails
                    = bankAccountService.deposit(IBAN_NUMBER, amount);
        } catch (RuntimeException exception) {
            throw exception;
        }
        Assert.fail("Test case should fail as no account exists");
    }

    @Test
    void depositTest_Success() {
        Account account = bankAccountService.createAccount(IBAN_NUMBER, BankAccountTypes.SAVING);
        BigDecimal amount = BigDecimal.valueOf(10,2);
        TransactionDetails transactionDetails
                = bankAccountService.deposit(IBAN_NUMBER, amount);
        Assert.assertEquals(transactionDetails.getTxAmount(), amount.toPlainString());
        Assert.assertEquals(transactionDetails.getTargetBankAccountId(), account.getId().toString());
    }

    @Test
    void getAccountBalanceTest_Success() {
        Account account = bankAccountService.createAccount(IBAN_NUMBER, BankAccountTypes.SAVING);
        BigDecimal amount = new BigDecimal(10);
        TransactionDetails transactionDetails
                = bankAccountService.deposit(IBAN_NUMBER, amount);
        BigDecimal balance = bankAccountService.getSavingsBalance(IBAN_NUMBER);
        Assert.assertEquals("10.00", balance.toPlainString());
    }

    @Test
    void transferTest_Success() {
        Account account1 = bankAccountService.createAccount(IBAN_NUMBER, BankAccountTypes.SAVING);
        Account account2 = bankAccountService.createAccount(IBAN_NUMBER2, BankAccountTypes.SAVING);
        BigDecimal amount = new BigDecimal(5);
        TransactionDetails transactionDetails
                = bankAccountService.deposit(IBAN_NUMBER, amount);
        bankAccountService.transfer(IBAN_NUMBER,IBAN_NUMBER2,amount);
        BigDecimal balance = bankAccountService.getSavingsBalance(IBAN_NUMBER2);
        Assert.assertEquals("5.00", balance.toPlainString());
    }

    @After
    void cleanUp(){
        accountRepository.deleteAll();
        bankUserRepository.deleteAll();
    }
}
