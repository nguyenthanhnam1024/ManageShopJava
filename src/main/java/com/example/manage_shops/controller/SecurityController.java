package com.example.manage_shops.controller;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.request.RequestLogin;
import com.example.manage_shops.request.RequestRegister;
import com.example.manage_shops.entity.Shop;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/security")
@AllArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;
    private final Commons commons;

    @GetMapping("/getShopList")
    public List<Shop> getShopList() {
        return securityService.getShopList();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody RequestLogin requestLogin) {
        String error = securityService.errorCheckAccountMap(requestLogin);
        if (error != null) {
            return ResponseEntity.status(1000).body(error);
        }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getUserName(),
                            requestLogin.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwt((UserDetailsImp) authentication.getPrincipal());
        response.addHeader("Authorization", "Bearer " + jwt);
        return ResponseEntity.ok(securityService.responseLogin(requestLogin.getUserName()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RequestRegister requestRegister, BindingResult result) {
        Map<String, String> errors = securityService.errorCheckRequestRegisterMap(requestRegister);
        if (result.hasErrors() || !errors.isEmpty()) {
            Map<String, String> allError = commons.handleExceptionInBindingResult(result);
            allError.putAll(errors);
            return ResponseEntity.status(1000).body(allError);
        }
        securityService.registerUser(requestRegister);
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
