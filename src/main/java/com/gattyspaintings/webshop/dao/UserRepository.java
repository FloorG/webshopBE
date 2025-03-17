package com.gattyspaintings.webshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.gattyspaintings.webshop.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
