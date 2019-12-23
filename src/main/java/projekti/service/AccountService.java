package projekti.service;

/**
 *
 * @author Teemu-Petteri Remes, teemu.petteri.remes@gmail.com
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekti.Account;
import projekti.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account findByUserName(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account findByAccountName(String accountname) {
        return accountRepository.findByAccountname(accountname);
    }
    
    public void saveAccount(Account a) {
        accountRepository.save(a);
    }

    public void newAccount(String accountname, String username, String password) {
        Account a = new Account();
        a.setAccountname(accountname);
        a.setUsername(username);
        a.setPassword(passwordEncoder.encode(password));
        saveAccount(a);
    }

    public List<Account> list() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.getOne(id);
    }

}
