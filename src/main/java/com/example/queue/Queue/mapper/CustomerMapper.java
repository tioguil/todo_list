package com.example.queue.Queue.mapper;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.entities.Customer;
import com.example.queue.Queue.domain.entities.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto convertToDto(Customer customer);

    @Mapping(target = "customer.todos", ignore = true)
    Todo todoDtoToTodo(TodoDto todoDto);

    Customer convertToEntity(CustomerDto dto);

    List<CustomerDto> convertToList(Iterable<Customer> customers);

    @Mapping(target = "customer.todos", ignore = true)
    TodoDto todoToTodoDto(Todo todo);
}