package com.example.queue.Queue.domain.DTOs;

import com.example.queue.Queue.domain.entities.Customer;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TodoDto {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;

    @JsonProperty
    private LocalDate todoDate;

    @JsonProperty
    private boolean finished;

    @JsonProperty
    @JsonIgnoreProperties(value = "todos")
//    @JsonBackReference
    private CustomerDto customer;

    @JsonProperty
    private LocalDate createdAt;
    @JsonProperty
    private LocalDate updatedAt;
}
