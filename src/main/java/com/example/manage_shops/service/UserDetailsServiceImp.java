package com.example.manage_shops.service;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.entity.*;
import com.example.manage_shops.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final AccountRepo accountRepo;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final RoleUserRepo roleUserRepo;

    private final ShopRepo shopRepo;

    private final UserDetailsImp userDetailsImp;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountRepo.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found for user name: " + userName));

        User user = userRepo.findByIdAccount(account.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for account: " + account.getUserName()));

        RoleUser roleUser = roleUserRepo.findByIdUser(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for user: " + user.getName()));

        Role role = roleRepo.findById(roleUser.getIdRole())
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for user as id role: " + roleUser.getIdRole()));

        if (!role.getRoleName().equals("ADMIN")) {
            shopRepo.findById(user.getIdShop())
                    .orElseThrow(() -> new UsernameNotFoundException("Shop not found for user: " +user.getName()));
        }
        return userDetailsImp.build(account.getUserName(), account.getPassword(), user.getIdShop(), role.getRoleName());
    }
}
