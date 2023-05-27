package com.example.manage_shops.controller;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestAccount;
import com.example.manage_shops.service.AccountService;
import com.example.manage_shops.service.Commons;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final Commons commons;

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody RequestAccount requestAccount, BindingResult result) throws MyValidateException {
        Map<String, String> errors = commons.handleExceptionInBindingResult(result);
        if (!errors.isEmpty()) {
            return ResponseEntity.status(400).body(errors);
        }
        try {
            accountService.updateAccount(requestAccount);
        } catch (MyValidateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("can not update account");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteAccount(@RequestBody RequestAccount requestAccount) throws MyValidateException {
        try {
            accountService.deleteAccount(requestAccount);
        } catch (MyValidateException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("can not delete account");
        }
    }
}
