package com.bondly.core_service.service.impl;

import com.bondly.core_service.exception.ResourceNotFoundException;
import com.bondly.core_service.model.UserModel;
import com.bondly.core_service.repository.UserRepository;
import com.bondly.core_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel registerUser(UserModel user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel updateUser(Long id, UserModel updatedUser) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        // existingUser.setName(updatedUser.getName());
        // existingUser.setEmail(updatedUser.getEmail());
        // existingUser.setBio(updatedUser.getBio());
        // existingUser.setMood(updatedUser.getMood());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
