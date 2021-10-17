package com.example.queue.Queue.domain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonBackReference
    @OneToMany(mappedBy ="customer")
    private Set<Todo> todos = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<CustomerRole> customerRoles = new HashSet<>();
}