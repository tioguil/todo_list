package com.example.queue.Queue.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    public String passwordEncoder(String password) {
        return this.bCryptPasswordEncoder.encode(password);
    }

    public boolean passwordMatch(String rawPassword, String encodedPassword){
        return this.bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }
}