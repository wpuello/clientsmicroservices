package org.clientes.Domain.Entities.Clients;

import java.time.LocalDate;

import org.clientes.Domain.Entities.Gender;
import org.clientes.Domain.Entities.Status;
import org.clientes.Domain.Entities.Country;
import org.clientes.Domain.Entities.Clients.Validations.ValidaBirthDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Clients extends PanacheEntity {

    @Column(length = 255, nullable = false)
    public String name;

    @ValidaBirthDate //Creo mi propia anotación: Valido Primero que no acepte fechas futuras y segundo que La fecha de nacimiento debe estar entre 1990 y la fecha actual
    @Column(nullable = false)
    public LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    public Gender gender;

    @Column(length = 15, nullable = false, unique = true)
    @Size(min = 12, max = 15)
    @Pattern(regexp = "^[0-9]+$")
    public String numCTA ;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    public Status status;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    public Country country;

    @Column(nullable = true)
    public LocalDate activatedDate;

    @Column(nullable = true)
    public LocalDate inactivatedDate;

}
