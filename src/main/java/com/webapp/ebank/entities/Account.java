package com.webapp.ebank.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table(name="account")
@Entity(name = "Account")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String accountType;
    private String ibanNumber;
    private BigDecimal balance;
}
