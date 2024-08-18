package com.example.demo.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.User;
import com.example.demo.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("producer").isEmpty()) {
            User producer = new User();
            producer.setUsername("producer");
            producer.setPassword("password");  // Use plain text or implement custom encoding
            producer.setRoles(Set.of("ROLE_PRODUCER"));
            userRepository.save(producer);
        }

        if (userRepository.findByUsername("consumer").isEmpty()) {
            User consumer = new User();
            consumer.setUsername("consumer");
            consumer.setPassword("password");  // Use plain text or implement custom encoding
            consumer.setRoles(Set.of("ROLE_CONSUMER"));
            userRepository.save(consumer);
        }
    }
}
