package com.ust.project.sustainability.controller;


import com.ust.project.sustainability.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, Object> userInfo) {
        System.out.println(userInfo.get("email"));
        String email = (String) userInfo.get("email");


        boolean isRegistered = authService.isUserRegistered(email);

        if (isRegistered) {
            String jwt = authService.loginUser(email);
            return ResponseEntity.ok(Map.of("jwt", jwt, "user", userInfo));
        } else {

            return ResponseEntity.status(404).body(Map.of("message", "User not registered"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> signupData) {

        return ResponseEntity.ok("User registered successfully");
    }
}
