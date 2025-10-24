package org.clientes.Applications.Dto.Clients;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ResponseCreateClientDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String numCTA;
    private String status;
    private String country;
}
