package com.webapp.ebank.services.impl;

import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.TransactionDetails;
import com.webapp.ebank.repository.AccountRepository;
import com.webapp.ebank.services.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountManagementImpl implements
        AccountManagementService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional
    public TransactionDetails deposit(Account account, BigDecimal amount) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTargetBankAccountId(account.getId().toString());
        transactionDetails.setTxAmount(amount.toPlainString());
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return transactionDetails;
    }

    @Override
    public TransactionDetails withdraw(Account account, BigDecimal amount) {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setSrcBankAccountId(account.getId().toString());
        transactionDetails.setTxAmount(amount.toPlainString());
        synchronized (account) {
            if(amount.compareTo(BigDecimal.ZERO)==0){
                return transactionDetails;
            }
            if(account.getBalance().compareTo(amount)<0){
               throw new RuntimeException("Not Enough Balance");
            }
            account.setBalance(account.getBalance().subtract(amount));
            amount = BigDecimal.ZERO;
        }
        return transactionDetails;
    }
}
