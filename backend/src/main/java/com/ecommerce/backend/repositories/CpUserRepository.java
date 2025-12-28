package com.ecommerce.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.CpUser;

public interface CpUserRepository extends JpaRepository<CpUser, Integer> {
    Optional<CpUser> findByEmail(String email);
}
