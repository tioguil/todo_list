package com.example.queue.Queue.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="todo")
@Getter
@Setter
public class Todo implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private LocalDate todoDate;
    private boolean finished;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="customer_id", referencedColumnName="id")
    @JsonManagedReference
    private Customer customer;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
