package com.bondly.core_service;

import com.bondly.core_service.model.UserModel;
import com.bondly.core_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class Usertest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addNewUser() {
        UserModel user = UserModel.builder()
                .username("pratikwalale")
                .email("pratik@example.com")
                .password("securepassword")
                .bio("I am a backend developer")
                .interests(Arrays.asList("coding", "gaming", "reading"))
                .build();

        userRepository.save(user);

        System.out.println("✅ User saved successfully: " + user.getUsername());
    }
}
