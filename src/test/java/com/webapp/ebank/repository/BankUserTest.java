package com.webapp.ebank.repository;

import com.webapp.ebank.BaseTest;
import com.webapp.ebank.entities.BankUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class BankUserTest extends BaseTest {

    @Autowired BankUserRepository bankUserRepository;

    @Test
    void createBankUserTest(){
        BankUser bankUser
                 = new BankUser();
        bankUser.setName("John");
        bankUser.setLastName("K");
        bankUser.setEmail("j@j.com");
        bankUser.setMobile("999911119");
        bankUser.setStatus("ACTIVE");
        BankUser persistedEntity = bankUserRepository.save(bankUser);
        Assert.assertEquals(bankUser.getName(),persistedEntity.getName());
    }

}
