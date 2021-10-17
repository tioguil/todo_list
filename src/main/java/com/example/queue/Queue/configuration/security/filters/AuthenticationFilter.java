package com.example.queue.Queue.configuration.security.filters;


import com.example.queue.Queue.configuration.security.SecurityConstants;

import com.example.queue.Queue.domain.DTOs.security.CustomerAuthenticatedDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFilter extends BasicAuthenticationFilter {

    private final SecurityConstants securityConstants;

    public AuthenticationFilter(AuthenticationManager authenticationManager, SecurityConstants securityConstants) {
        super(authenticationManager);
        this.securityConstants = securityConstants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String token =  request.getHeader(securityConstants.HEADER_FIELD);

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) throws JsonProcessingException {

        String token = request.getHeader(securityConstants.HEADER_FIELD);
        if(token == null) return null;

        var customerJson = (String) Jwts.parser().setSigningKey(securityConstants.SECRET).parseClaimsJws(token)
                .getBody().get("customer");

        var customer =  new ObjectMapper().findAndRegisterModules().readValue(customerJson, CustomerAuthenticatedDto.class);

        return new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());

    }
}
