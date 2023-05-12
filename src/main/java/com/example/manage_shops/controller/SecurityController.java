package com.example.manage_shops.controller;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.JwtUtils;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/security")
@AllArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;
    private final Commons commons;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Map<String, String> mapError = securityService.errorCheckAccountMap(account);
        if (!mapError.isEmpty()) {
            return ResponseEntity.badRequest().body(mapError);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        account.getUserName(),
                        account.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwt((UserDetailsImp) authentication.getPrincipal());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, @Valid @RequestBody Account account, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(commons.handleExceptionInBindingResult(result));
        }
        securityService.registerUser(user, account);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication  != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("login failure");
    }
}
