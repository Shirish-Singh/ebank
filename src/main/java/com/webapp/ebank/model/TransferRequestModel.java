package com.webapp.ebank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestModel {
    private String ibanSource;
    private String ibanTarget;
    private BigDecimal amount;
}
