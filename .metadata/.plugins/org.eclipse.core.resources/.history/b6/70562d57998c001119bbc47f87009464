package com.aman.expensetracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.expensetracker.entity.User;
import com.aman.expensetracker.repository.UserRepository;
import com.aman.expensetracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}