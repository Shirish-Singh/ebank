package com.webapp.ebank.controller;

import com.webapp.ebank.entities.TransactionDetails;
import com.webapp.ebank.model.DepositRequestModel;
import com.webapp.ebank.model.TransferRequestModel;
import com.webapp.ebank.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accouts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping(path = "/_deposit")
    public TransactionDetails deposit(@RequestBody DepositRequestModel depositRequestModel) {
        return bankAccountService.deposit(depositRequestModel.getIban(), depositRequestModel.getAmount());
    }

    @PostMapping(path = "/_transfer")
    public void transfer(@RequestBody TransferRequestModel transferRequestModel) {
        bankAccountService.transfer(transferRequestModel.getIbanSource(), transferRequestModel.getIbanTarget(), transferRequestModel.getAmount());
    }
}
