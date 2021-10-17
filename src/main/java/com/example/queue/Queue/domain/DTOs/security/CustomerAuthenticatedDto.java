package com.example.queue.Queue.domain.DTOs.security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonDeserialize(using = CustomerAuthenticatedSerializer.class)
public class CustomerAuthenticatedDto {

    private String id;
    private String password;
    private String name;
    private String email;
    private List<GrantedAuthority> authorities = new ArrayList<>();
}
