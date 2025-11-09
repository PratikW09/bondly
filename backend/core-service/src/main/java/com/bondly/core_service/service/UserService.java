package com.bondly.core_service.service;

import com.bondly.core_service.model.UserModel;
import java.util.List;
import java.util.Optional;

public interface UserService {

    // Create new user
    UserModel registerUser(UserModel user);

    // Get user by ID
    Optional<UserModel> getUserById(Long id);

    // Get user by email
    Optional<UserModel> getUserByEmail(String email);

    // Get all users (can be restricted later)
    List<UserModel> getAllUsers();

    // Update existing user details
    UserModel updateUser(Long id, UserModel updatedUser);

    // Delete user
    void deleteUser(Long id);
}
