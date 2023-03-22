package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Account;
import ptit.wibulord.webfilm.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountList(){
        return accountRepository.findAll();
    }
    public void addAccount(Account account){
        accountRepository.save(account);
    }
    public Account findAccountByUsername(String username){return accountRepository.findAccountByUsername(username);}
}
