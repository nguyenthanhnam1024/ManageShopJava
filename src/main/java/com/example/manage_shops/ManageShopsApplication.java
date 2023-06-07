package com.example.manage_shops;

import com.example.manage_shops.entity.*;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.repository.RoleUserRepo;
import com.example.manage_shops.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@SpringBootApplication
public class ManageShopsApplication {
	private final AccountRepo accountRepo;
	private final UserRepo userRepo;
	private final RoleUserRepo roleUserRepo;
	private final RoleRepo roleRepo;

	@Autowired
	public ManageShopsApplication(AccountRepo accountRepo, UserRepo userRepo, RoleUserRepo roleUserRepo, RoleRepo roleRepo) {
		this.accountRepo = accountRepo;
		this.userRepo = userRepo;
		this.roleUserRepo = roleUserRepo;
		this.roleRepo = roleRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ManageShopsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("POST", "PUT", "GET", "DELETE")
						.allowedHeaders("Authorization", "Content-Type")
						.exposedHeaders("Authorization");
			}
		};
	}

	@PostConstruct
	@Transactional
	public void initialize() {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String encodedPassword = bc.encode("admin");
		Account account = new Account(1L, "admin", encodedPassword);
		accountRepo.save(account);
	    User user = new User(1L, 0, 1L, "admin", 20, "admin@gmail.com", "012345678910", "vietnam");
		userRepo.save(user);
		Role role = new Role(1, "ADMIN");
		roleRepo.save(role);
		IdRoleUser idRoleUser = new IdRoleUser(1L, 1);
		RoleUser roleUser = new RoleUser();
		roleUser.setIdUser(idRoleUser.getIdUser());
		roleUser.setIdRole(idRoleUser.getIdRole());
		roleUserRepo.save(roleUser);
	}
}
