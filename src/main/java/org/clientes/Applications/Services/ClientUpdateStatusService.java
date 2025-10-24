package org.clientes.Applications.Services;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Repositories.ClientUpdateStatusRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientUpdateStatusService {

    @Inject
    ClientUpdateStatusRepository clientStatusRepository;

    @Inject
    ClientMapperService clientMapperService;

    @Transactional
    public ResponseCreateClientDTO activateClient(Long id) {
        Clients client = clientStatusRepository.findById(id);
        if (client == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }

        if ("ACTIVE".equalsIgnoreCase(client.status.name)) {
            throw new RuntimeException("El cliente ya está activo");
        }

        clientStatusRepository.changeStatus(id, "ACTIVE");
        clientStatusRepository.getEntityManager().flush();
        clientStatusRepository.getEntityManager().refresh(client);

        return clientMapperService.toResponseDTO(client);
    }

    @Transactional
    public ResponseCreateClientDTO deactivateClient(Long id) {
        Clients client = clientStatusRepository.findById(id);
        if (client == null) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }

        if ("INACTIVE".equalsIgnoreCase(client.status.name)) {
            throw new RuntimeException("El cliente ya está inactivo");
        }

        clientStatusRepository.changeStatus(id, "INACTIVE");
        clientStatusRepository.getEntityManager().flush();
        clientStatusRepository.getEntityManager().refresh(client);

        return clientMapperService.toResponseDTO(client);
    }
}
