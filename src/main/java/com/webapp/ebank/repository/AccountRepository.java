package com.webapp.ebank.repository;

import com.webapp.ebank.entities.Account;
import com.webapp.ebank.entities.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountByIbanNumberAndAccountType(String iban,String accountType);
}
