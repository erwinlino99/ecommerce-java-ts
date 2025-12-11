package com.ecommerce.backend.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.WebUser;
import com.ecommerce.backend.repositories.WebUserRepository;

@Service
public class WebUserDetailsService implements UserDetailsService {

    private final WebUserRepository webUserRepo;

    public WebUserDetailsService(WebUserRepository webUserRepo) {
        this.webUserRepo = webUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        WebUser user = webUserRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}