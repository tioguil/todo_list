package com.example.queue.Queue.gateway.repository;

import com.example.queue.Queue.domain.entities.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRole, UUID> {
}