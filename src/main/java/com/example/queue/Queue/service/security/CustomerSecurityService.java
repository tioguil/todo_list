package com.example.queue.Queue.service.security;

import com.example.queue.Queue.domain.DTOs.security.CustomerSecurityDto;
import com.example.queue.Queue.gateway.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerSecurityService implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomerSecurityService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var customerOptional = repository.findCustomerByEmail(email);

        if (customerOptional.isEmpty())
            return null;

        var customer = customerOptional.get();

        System.out.println(customer.getPassword());

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new CustomerSecurityDto(
                customer.getId().toString(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getName(),
                authorityList,
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
