package com.example.queue.Queue.gateway.controller;

import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.DTOs.security.CustomerAuthenticatedDto;
import com.example.queue.Queue.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto dto, Authentication authentication){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TodoDto>> findAll(Authentication authentication){
        CustomerAuthenticatedDto dto = (CustomerAuthenticatedDto) authentication.getPrincipal();
        return ResponseEntity.ok(service.findAll(dto.getId()));
    }
}
