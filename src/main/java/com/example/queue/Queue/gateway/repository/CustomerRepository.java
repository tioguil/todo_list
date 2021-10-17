package com.example.queue.Queue.gateway.repository;

import com.example.queue.Queue.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByEmail(String email);
}
