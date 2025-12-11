package com.ecommerce.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.WebUser;

public interface WebUserRepository extends JpaRepository<WebUser, Integer> {

    Optional<WebUser> findByEmail(String email);

}
