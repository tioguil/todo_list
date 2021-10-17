package com.example.queue.Queue.configuration.security;

import com.example.queue.Queue.configuration.security.filters.AuthenticationFilter;
import com.example.queue.Queue.configuration.security.filters.AuthenticateFilter;
import com.example.queue.Queue.service.security.CustomerSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerSecurityService customerSecurity;
    @Autowired
    private SecurityConstants securityConstants;

    public static final String[] PUBLIC_ROUTES = {
            "/swagger-ui.html",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/webjars/**",
            "/customer/save"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_ROUTES).permitAll()
                .antMatchers("/**").hasRole("USER")
                .and()
                .addFilter(new AuthenticateFilter(authenticationManager(), securityConstants))
                .addFilter(new AuthenticationFilter(authenticationManager(), securityConstants));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerSecurity).passwordEncoder(new BCryptPasswordEncoder());
    }
}
