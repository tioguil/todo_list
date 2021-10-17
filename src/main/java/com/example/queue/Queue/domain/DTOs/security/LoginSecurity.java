package com.example.queue.Queue.domain.DTOs.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginSecurity {
    private String email;
    private String password;
}
