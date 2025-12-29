package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.backend.models.CpIndex;

public interface CpIndexRepository extends JpaRepository<CpIndex, Integer> {
    
}
