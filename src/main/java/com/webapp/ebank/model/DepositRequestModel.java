package com.webapp.ebank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequestModel {
    private String iban;
    private BigDecimal amount;
}
