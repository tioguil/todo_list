package com.example.queue.Queue.mapper;

import com.example.queue.Queue.domain.DTOs.CustomerDto;
import com.example.queue.Queue.domain.DTOs.TodoDto;
import com.example.queue.Queue.domain.DTOs.security.CustomerAuthenticatedDto;
import com.example.queue.Queue.domain.entities.Customer;
import com.example.queue.Queue.domain.entities.Todo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto convertToDto(Customer customer);

    @Mapping(target = "customer.todos", ignore = true)
    Todo todoDtoToTodo(TodoDto todoDto);

    Customer convertToEntity(CustomerDto dto);

    List<CustomerDto> convertToList(Iterable<Customer> customers);

    @Mapping(target = "customer.todos", ignore = true)
    TodoDto todoToTodoDto(Todo todo);

    @BeanMapping(ignoreUnmappedSourceProperties = "authorities")
    @Mapping(source = "id", target = "id", qualifiedBy = ConvertStringToUUID.class)
    CustomerDto  customerAuthToDto(CustomerAuthenticatedDto authenticatedDto);


    @ConvertStringToUUID
    public static UUID convertStringToUUID(String id) {
        return UUID.fromString(id);
    }


    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface ConvertStringToUUID {}
}