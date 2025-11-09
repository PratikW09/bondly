package com.bondly.core_service.controller;

import com.bondly.core_service.model.UserModel;
import com.bondly.core_service.service.UserService;
import com.bondly.core_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Base URL for all user-related APIs
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel user) {
        UserModel createdUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // ✅ Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
    }

    // ✅ Get all users
    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // ✅ Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        UserModel user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
