package com.example.queue.Queue.service.security;

import com.example.queue.Queue.domain.DTOs.security.CustomerSecurityDto;
import com.example.queue.Queue.domain.entities.CustomerRole;
import com.example.queue.Queue.gateway.repository.CustomerRepository;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerSecurityService implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomerSecurityService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var customerOptional = repository.findCustomerByEmail(email);

        if (customerOptional.isEmpty())
            return null;

        var customer = customerOptional.get();

        List<GrantedAuthority> authorityList = new ArrayList<>();

        for(CustomerRole role : customer.getCustomerRoles()){
            authorityList.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }

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
