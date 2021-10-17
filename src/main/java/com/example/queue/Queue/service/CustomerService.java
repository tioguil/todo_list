package com.example.queue.Queue.service;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.entities.Customer;
import com.example.queue.Queue.gateway.repository.CustomerRepository;
import com.example.queue.Queue.mapper.CustomerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public CustomerDto save(CustomerDto customerDTO){
        var customer = mapper.convertToEntity(customerDTO);
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
