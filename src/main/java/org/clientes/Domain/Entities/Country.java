package org.clientes.Domain.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Country extends PanacheEntity {
    
    @Column(nullable = false)
    public String name;
}
