package com.example.queue.Queue.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRole implements Serializable {

    @EmbeddedId
    private CustomerRoleId customerRoleId;

    @GeneratedValue
    private UUID id;

    @ManyToOne
    @MapsId("roleId")
    @JsonIgnore
    private Role role;

    @ManyToOne
    @MapsId("customerId")
    @JsonIgnore
    private Customer customer;
}
