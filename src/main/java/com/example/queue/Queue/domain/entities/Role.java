package com.example.queue.Queue.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "role")
    private List<CustomerRole> customerRoles;
}