package com.webapp.ebank.config;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public final class EbankConfiguration {

    public static final BigDecimal MINIMUM_DEPOSIT_THRESHOLD = BigDecimal.ONE;
}
