package com.example.queue.Queue.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConstants {

    @Value("${security.SECRET}")
    public String SECRET;
    @Value("${security.HEADER_FIELD}")
    public String HEADER_FIELD;
    @Value("${security.EXPIRATION_TOKEN}")
    public Long  EXPIRATION_TOKEN;
}