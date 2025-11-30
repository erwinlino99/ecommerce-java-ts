package com.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.backend.models.NewWebUser;

public interface NewWebUserRepository extends JpaRepository<NewWebUser, Integer> {
    //NO TENGO NI IDEA DE QUE ES ESTO ->
    NewWebUser findByEmail(String email);
}
