package com.example.managershop.service_ipm;

import com.example.managershop.entity.Account;
import com.example.managershop.repository.AccountRepo;
import com.example.managershop.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceIpm implements AccountService {
    private final AccountRepo accountRepo;

    public AccountServiceIpm(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public List<Account> searchAccountByKeyword(String keyword) {
        return accountRepo.searchAccountByKeyword(keyword);
    }
}
