package com.example.queue.Queue.gateway.controller;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto dto){
        System.out.println(dto.getName());
        return ResponseEntity.ok(service.save(dto));
    }

    @PreAuthorize("hasRole('ROLE_ADM')")
    @GetMapping("/findAll")
    public ResponseEntity<List<CustomerDto>> findAll(){
        var customers = service.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerDto> find(@PathVariable String id){
        return ResponseEntity.ok(service.find(id));
    }
}
