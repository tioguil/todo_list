package com.example.queue.Queue.gateway.controller;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.DTOs.security.CustomerAuthenticatedDto;
import com.example.queue.Queue.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto dto, Authentication authentication){
        CustomerAuthenticatedDto customerAuthenticatedDto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        CustomerDto customer = new CustomerDto();
        customer.setId(UUID.fromString(customerAuthenticatedDto.getId()));
        dto.setCustomer(customer);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TodoDto>> findAll(Authentication authentication){
        CustomerAuthenticatedDto dto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        return ResponseEntity.ok(service.findAll(dto.getId()));
    }
}
