package com.webapp.ebank.repository;

import com.webapp.ebank.entities.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, UUID> {
}
