package com.example.queue.Queue.service;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.entities.Customer;
import com.example.queue.Queue.domain.entities.CustomerRole;
import com.example.queue.Queue.domain.entities.CustomerRoleId;
import com.example.queue.Queue.exceptions.ObjectNotFoundException;
import com.example.queue.Queue.gateway.repository.CustomerRepository;
import com.example.queue.Queue.gateway.repository.RoleRepository;
import com.example.queue.Queue.mapper.CustomerMapper;
import com.example.queue.Queue.service.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final String defaultRole;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper mapper, PasswordEncoder passwordEncoder,
                           RoleRepository repository,
                           @Value("${security.DEFAULT_ROLE}") String defaultRole) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = repository;
        this.defaultRole = defaultRole;
    }

    @Transactional
    public CustomerDto save(CustomerDto customerDTO){
        var customer = mapper.convertToEntity(customerDTO);

        customer.setPassword(passwordEncoder.passwordEncoder(customerDTO.getPassword()));

        roleRepository.findByName(defaultRole).ifPresentOrElse(role -> {
            var customerRole = new CustomerRole();

            var customerRoleId = new CustomerRoleId();
            customerRoleId.setRoleId(role.getId());
            customerRoleId.setCustomerId(customer.getId());
            customerRole.setCustomerRoleId(customerRoleId);

            customerRole.setRole(role);
            customerRole.setCustomer(customer);
            Set<CustomerRole> roles = Set.of(customerRole);

            customer.setCustomerRoles(roles);
        }, ()-> {
            throw new ObjectNotFoundException("Role não encontrada");
        });

        return  mapper.convertToDto(customerRepository.saveAndFlush(customer));
    }

    @Transactional
    public List<CustomerDto> findAll(){
        Iterable<Customer> customers = customerRepository.findAll();
        return mapper.convertToList(customers);
    }

    public CustomerDto find(String id) {
        var OptionalCustomer = customerRepository.findById(UUID.fromString(id));

        if(OptionalCustomer.isPresent())
            return mapper.convertToDto(OptionalCustomer.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
