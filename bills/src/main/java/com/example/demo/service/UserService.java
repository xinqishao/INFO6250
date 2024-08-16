package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}