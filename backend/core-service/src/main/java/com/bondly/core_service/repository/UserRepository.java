package com.bondly.core_service.repository;

import com.bondly.core_service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    // Find user by email
    Optional<UserModel> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find user by username
    Optional<UserModel> findByUsername(String username);
}
