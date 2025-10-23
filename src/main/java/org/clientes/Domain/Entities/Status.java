package org.clientes.Domain.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Status extends PanacheEntity {
     public String name;
}
