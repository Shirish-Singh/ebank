package com.webapp.ebank.services;

import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.TransactionDetails;

import java.math.BigDecimal;

public interface AccountManagementService {
    TransactionDetails deposit(Account account, BigDecimal
                     amount);

    TransactionDetails withdraw(Account account, BigDecimal
            amount);
}
