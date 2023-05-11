package com.example.manage_shops.service;

import com.example.manage_shops.config.UserDetailsImp;
import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.Role;
import com.example.manage_shops.entity.RoleUser;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.repository.RoleUserRepo;
import com.example.manage_shops.repository.UserRepo;
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

    private final UserDetailsImp userDetailsImp;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountRepo.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found for username: " + userName));

        User user = userRepo.findByIdAccount(account.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for account: " + account.getUserName()));

        RoleUser roleUser = roleUserRepo.findByIdUser(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("RoleUser not found for user: " + user.getName()));

        Role role = roleRepo.findById(roleUser.getIdRole())
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for roleUser as id role : " + roleUser.getIdRole()));

        return userDetailsImp.build(account.getUserName(), account.getPassword(), user.getIdShop(), role.getRoleName());
    }
}
