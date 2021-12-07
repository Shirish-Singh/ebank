package com.webapp.ebank.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name="bank_user")
@Entity(name = "BankUser")
public class BankUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String mobile;
    private String status;
}
