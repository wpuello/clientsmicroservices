package org.clientes.Applications.Services;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Domain.Entities.Clients.Clients;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientMapperService {
    
    public ResponseCreateClientDTO toResponseDTO(Clients client) {
        if (client == null) return null;

        ResponseCreateClientDTO dto = new ResponseCreateClientDTO();
        dto.setId(client.id);
        dto.setName(client.name);
        dto.setBirthDate(client.birthDate);
        dto.setNumCTA(client.numCTA);
        dto.setGender(client.gender != null ? client.gender.name : null);
        dto.setStatus(client.status != null ? client.status.name : null);
        dto.setCountry(client.country != null ? client.country.name : null);

        return dto;
    }
    
}
