package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.backend.models.WebUserRole;

public interface WebUserRoleRepository extends JpaRepository<WebUserRole, Integer> {

}
