package com.example.queue.Queue.configuration.security.filters;

import com.example.queue.Queue.configuration.security.SecurityConstants;
import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.security.CustomerSecurityDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecurityConstants securityConstants;

    public AuthenticateFilter(AuthenticationManager authenticationManager, SecurityConstants securityConstants){
        this.authenticationManager = authenticationManager;
        this.securityConstants = securityConstants;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            var customerDto = new ObjectMapper().readValue(request.getInputStream(), CustomerDto.class);

            System.out.println(customerDto.getPassword());

            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Autenticação realizada com sucesso! Montando response com token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomerSecurityDto customer = (CustomerSecurityDto) authResult.getPrincipal();

        var customerSecurityDto = new ObjectMapper().findAndRegisterModules().writeValueAsString(customer);

        String token = Jwts
                .builder()
                .setSubject(customer.getEmail())
                .claim("customer", customerSecurityDto)
                .setExpiration(new Date(System.currentTimeMillis() + securityConstants.EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, securityConstants.SECRET)
                .compact();

        response.addHeader(securityConstants.HEADER_FIELD, token);
    }

}
