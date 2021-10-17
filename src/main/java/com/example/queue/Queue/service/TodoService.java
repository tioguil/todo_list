package com.example.queue.Queue.service;

import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.exceptions.ObjectNotFoundException;
import com.example.queue.Queue.gateway.repository.TodoRepository;
import com.example.queue.Queue.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
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

    @Cacheable(value = "todoCache", key = "#customerId")
    public List<TodoDto> findAll(String customerId) {
        log.info("Executado -> findAll");
        UUID id = UUID.fromString(customerId);
        return mapper.convertToDtoList(repository.findByCustomerId(id));
    }

    @CacheEvict(value="todoCache", key = "#dto.customer.id")
    public TodoDto update(TodoDto dto) {

        return repository.findById(dto.getId()).map(todo -> {

            var todoRepository = repository.save(mapper.todoDtoToTodo(dto));

            return mapper.todoToTodoDto(todoRepository);

        }).orElseThrow(() -> new ObjectNotFoundException("Todo não localizado"));
    }
}