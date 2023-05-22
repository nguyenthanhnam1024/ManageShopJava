package com.example.manage_shops.config;

import com.example.manage_shops.exception.AuthExceptionEntryPoint;
import com.example.manage_shops.jwt.AuthFilter;
import com.example.manage_shops.service.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImp userDetailsServiceImp;

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(this.bCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private final AuthFilter authFilter;

    private final AuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authExceptionEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/security/login", "/security/register", "/security/logout", "/security/getShopList").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
