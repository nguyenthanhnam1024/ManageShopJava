package com.example.manage_shops.controller;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.entity.Account;
import com.example.manage_shops.jwt.JwtUtils;
import com.example.manage_shops.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/security")
@AllArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;

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
    public ResponseEntity<?> register() {
        return null;
    }
    @GetMapping("/random")
    public String test() {
        return "phai co jwt ms truy cap dc api nay";
    }
}
