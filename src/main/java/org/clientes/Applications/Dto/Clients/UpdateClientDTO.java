package org.clientes.Applications.Dto.Clients;

import java.time.LocalDate;
import lombok.Data;


@Data
public class UpdateClientDTO {
    public String name;
    public LocalDate birthDate;
    public Long genderId;
    public String numCTA;
}
