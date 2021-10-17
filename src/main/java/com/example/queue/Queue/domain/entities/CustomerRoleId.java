package com.example.queue.Queue.domain.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRoleId implements Serializable {

    @JoinColumn(name = "role_id")
    private UUID roleId;

    @JoinColumn(name = "customer_id")
    private UUID customerId;
}