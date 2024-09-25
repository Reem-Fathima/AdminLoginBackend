package com.ust.project.sustainability.service;


import com.ust.project.sustainability.config.JwtUtil;
import com.ust.project.sustainability.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String loginUser(String email) {
        Optional<User> user = userRepository.findByEmailId(email);
        if (user.isPresent()) {
            return jwtUtil.generateToken(email);
        }
        return null;
    }

    public boolean isUserRegistered(String email) {

        return userRepository.findByEmailId(email).isPresent();
    }
}