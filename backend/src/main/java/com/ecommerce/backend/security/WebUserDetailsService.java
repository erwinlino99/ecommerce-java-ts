package com.ecommerce.backend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.repositories.CpUserRepository;
import com.ecommerce.backend.repositories.WebUserRepository;
import com.ecommerce.backend.util.UseLogger;

@Service
public class WebUserDetailsService implements UserDetailsService {

    private final WebUserRepository webUserRepo;
    private final CpUserRepository cpUserRepo;

    public WebUserDetailsService(WebUserRepository webUserRepo, CpUserRepository cpUserRepo) {
        this.webUserRepo = webUserRepo;
        this.cpUserRepo = cpUserRepo;
    }

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UseLogger.info("Intentando cargar usuario por email: ", email);

    var cpUserOpt = cpUserRepo.findByEmail(email);
    if (cpUserOpt.isPresent()) {
        var user = cpUserOpt.get();
        UseLogger.info("CP User encontrado: ", user.getEmail());

        String roleName = (user.getRole() != null) ? user.getRole().getName() : "ROLE_ADMIN";

        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(roleName) // Esto acepta "ROLE_ADMIN" sin quejarse
            .build();
    }

    var webUserOpt = webUserRepo.findByEmail(email);
    if (webUserOpt.isPresent()) {
        var user = webUserOpt.get();
        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities("ROLE_CLIENT") // Siempre con prefijo ROLE_
            .build();
    }

    throw new UsernameNotFoundException("Usuario no encontrado");
}
}