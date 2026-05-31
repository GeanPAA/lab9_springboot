package com.tecsup.demo_Security_JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecsup.demo_Security_JWT.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}