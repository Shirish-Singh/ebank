package com.webapp.ebank.services.impl;


import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.TransactionDetails;
import com.webapp.ebank.helper.BankValidationHelper;
import com.webapp.ebank.repository.AccountRepository;
import com.webapp.ebank.services.AccountManagementService;
import com.webapp.ebank.services.BankAccountService;
import com.webapp.ebank.services.enumerator.BankAccountTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements
        BankAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountManagementService accountManagementService;

    @Override
    public TransactionDetails deposit(String iban, BigDecimal amount) {
        BankValidationHelper.validateDeposit(iban,amount);
        Account account = getAccount(iban,BankAccountTypes.getDefault());
        return accountManagementService.deposit(account, amount);
    }

    @Override
    public TransactionDetails withdraw(String iban, BigDecimal amount) {
        Account account = getAccount(iban,BankAccountTypes.getDefault());
        return accountManagementService.withdraw(account, amount);
    }

    @Override
    public Account createAccount(String iBan, BankAccountTypes bankAccountType) {
        Account account = new Account();
        account.setIbanNumber(iBan);
        account.setAccountType(bankAccountType.name());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @Override
    public BigDecimal getSavingsBalance(String iBan) {
        return  getAccount(iBan,BankAccountTypes.SAVING.name()).getBalance();
    }

    @Override
    @Transactional
    public void transfer(String from_iban_number, String to_iban_number2, BigDecimal amount) {
        Optional<Account> account1 = accountRepository.findAccountByIbanNumberAndAccountType(from_iban_number,BankAccountTypes.getDefault());
        Optional<Account> account2 = accountRepository.findAccountByIbanNumberAndAccountType(to_iban_number2,BankAccountTypes.getDefault());
        if(account1.isPresent() && account2.isPresent()) {
            accountManagementService.withdraw(account1.get(), amount);
            accountManagementService.deposit(account2.get(),amount);
        }
        //merge the transaction details
    }

    private Account getAccount(String iban,String accountType) {
        Optional<Account> account = accountRepository.findAccountByIbanNumberAndAccountType(iban,accountType);
        if (!account.isPresent()) {
            throw new RuntimeException("Account doesnt exists!");
        }
        return account.get();
    }

}
