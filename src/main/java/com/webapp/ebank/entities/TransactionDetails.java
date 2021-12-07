package com.webapp.ebank.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Data
@Table(name="transaction_details")
@Entity(name = "TransactionDetails")
public class TransactionDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String srcBankAccountId;
    private String targetBankAccountId;
    private String txAmount;
}
