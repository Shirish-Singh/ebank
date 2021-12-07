package com.webapp.ebank.services.enumerator;

public enum BankAccountTypes {
    SAVING,CHECKING,LOAN;

    public static String getDefault() {
        return SAVING.name();
    }
}
