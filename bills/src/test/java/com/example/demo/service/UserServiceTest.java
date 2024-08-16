package com.example.demo.service;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("testpassword"));
        user.setRoles(Set.of("ROLE_USER"));
        
        try {
            User savedUser = userService.saveUser(user);
            assertNotNull(savedUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to save user: " + e.getMessage());
        }
    }
}
