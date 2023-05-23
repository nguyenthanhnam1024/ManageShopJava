package com.example.manage_shops.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class UserDetailsImp implements UserDetails {
    private String userName;

    private String passWord;

    private int shopId;

    private Collection<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public UserDetailsImp build(String userName, String passWord, int shopId, String roleName) {
        this.userName = userName;
        this.passWord = passWord;
        this.shopId = shopId;
        this.authorities.add(new SimpleGrantedAuthority(roleName));
        return this;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
