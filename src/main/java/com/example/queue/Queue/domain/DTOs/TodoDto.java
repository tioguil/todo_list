package com.example.queue.Queue.domain.DTOs;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TodoDto implements Serializable {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;

    @JsonProperty
    private Date todoDate;

    @JsonProperty
    private boolean finished;

    @JsonProperty
    @JsonIgnoreProperties(value = "todos")
//    @JsonBackReference
    private CustomerDto customer;

    @JsonProperty
    private LocalDateTime createdAt;
    @JsonProperty
    private LocalDateTime updatedAt;
}
