package com.example.queue.Queue.domain.DTOs;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CustomerDto implements Serializable {

    @JsonProperty
    private UUID id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private LocalDateTime createdAt;
    @JsonProperty
    private LocalDateTime updatedAt;

    @JsonProperty
//    @JsonManagedReference
    private List<TodoDto> todos;
}
