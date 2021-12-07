package com.webapp.ebank.services;


import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.TransactionDetails;
import com.webapp.ebank.services.enumerator.BankAccountTypes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface BankAccountService {

    TransactionDetails deposit(String iBan, BigDecimal amount);

    TransactionDetails withdraw(String iban, BigDecimal amount);

    Account createAccount(String iBan, BankAccountTypes bankAccountType );

    BigDecimal getSavingsBalance(String iBan);

    void transfer(String iban_number, String iban_number2, BigDecimal amount);
}
