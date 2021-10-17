package com.example.queue.Queue.mapper;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.entities.Customer;
import com.example.queue.Queue.domain.entities.Todo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo todoDtoToTodo(TodoDto dto);

    TodoDto todoToTodoDto(Todo todo);

    List<TodoDto> convertToDtoList(Iterable<Todo> todos);

    @Mapping(target = "todos", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = "password")
    CustomerDto customerToCustomerDto(Customer customer);
}
