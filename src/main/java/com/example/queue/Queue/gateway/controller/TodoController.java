package com.example.queue.Queue.gateway.controller;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.DTOs.security.CustomerAuthenticatedDto;
import com.example.queue.Queue.mapper.CustomerMapper;
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
    private final CustomerMapper mapper;

    public TodoController(TodoService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/save")
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto dto, Authentication authentication){
        CustomerAuthenticatedDto customerAuthenticatedDto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        dto.setCustomer(mapper.customerAuthToDto(customerAuthenticatedDto));
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TodoDto>> findAll(Authentication authentication){
        CustomerAuthenticatedDto dto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        return ResponseEntity.ok(service.findAll(dto.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<TodoDto> update(@RequestBody TodoDto dto, Authentication authentication){
        CustomerAuthenticatedDto customerAuthenticatedDto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        dto.setCustomer(mapper.customerAuthToDto(customerAuthenticatedDto));
        return ResponseEntity.ok(service.update(dto));
    }
}
