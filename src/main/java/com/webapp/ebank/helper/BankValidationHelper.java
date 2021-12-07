package com.webapp.ebank.helper;

import org.apache.commons.validator.routines.IBANValidator;

import java.math.BigDecimal;

import static com.webapp.ebank.config.EbankConfiguration.MINIMUM_DEPOSIT_THRESHOLD;

public class BankValidationHelper {
    public static void validateIban(String iban) {
        Boolean isIbanValid = IBANValidator.DEFAULT_IBAN_VALIDATOR.isValid(iban);
        if (isIbanValid == null || !isIbanValid.booleanValue()) {
            throw new RuntimeException("Invalid Iban number.");
        }
    }

    public static void validateDepositAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(MINIMUM_DEPOSIT_THRESHOLD) == -1) {
            throw new RuntimeException("Deposit amount is invalid or less then minimum threshold amount.");
        }
    }

    public static void validateDeposit(String iban, BigDecimal
            amount) {
        validateIban(iban);
        validateDepositAmount(amount);
    }
}
