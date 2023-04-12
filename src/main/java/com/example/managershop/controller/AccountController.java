package com.example.managershop.controller;

import com.example.managershop.entity.Account;
import com.example.managershop.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping("save")
    public void saveAccount(@Valid @RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @PutMapping("/update")
    public void updateAccount(@Valid @RequestBody Account account) {
        accountService.updateAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("/searchByKeyword")
    public List<Account> searchAccountByKeyword(@RequestParam("keyword") String keyword) {
        return accountService.searchAccountByKeyword(keyword);
    }
}
