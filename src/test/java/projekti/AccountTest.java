/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import javax.transaction.Transactional;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import projekti.service.AccountService;

/**
 *
 * @author Ilmari-Jalmari
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest

public class AccountTest {
     @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;
    @Test
    @Transactional
    public void AccountServiceWorks() {

        Account account = new Account();
        account.setAccountname("oikeanimi");
        account.setUsername("testi");
        account.setPassword("testi");
       
        account = accountRepository.save(account);
    
       
        Long testAccountId = account.getId();
        
        Account testAccount = accountService.getAccount(testAccountId);
        
        assertTrue (testAccount.getAccountname().equals("oikeanimi"));
        assertTrue (testAccount.getUsername().equals("testi"));
        
    }
}
