package com.example.queue.Queue.service;

import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.gateway.repository.TodoRepository;
import com.example.queue.Queue.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository repository;
    private final TodoMapper mapper;

    public TodoService(TodoRepository repository, TodoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TodoDto save(TodoDto dto){
        var todo = mapper.todoDtoToTodo(dto);
        return mapper.todoToTodoDto(repository.save(todo));
    }

    public List<TodoDto> findAll(String customerId) {
        UUID id = UUID.fromString(customerId);
        return mapper.convertToDtoList(repository.findByCustomerId(id));
    }
}
