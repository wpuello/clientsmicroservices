package org.clientes.Applications.Services;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Applications.Dto.Clients.UpdateClientDTO;
import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Repositories.ClientUpdateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientUpdateService {

    @Inject
    ClientMapperService clientMapperService;

    @Inject
    ClientUpdateRepository clientUpdateRepository;


    @Transactional
    public ResponseCreateClientDTO updateClient(Long id, UpdateClientDTO dto) {
        Clients existing = clientUpdateRepository.findById(id);

        if (existing == null) {
            throw new RuntimeException("Cliente no encontrado con el ID: " + id);
        }

        if (!"ACTIVE".equalsIgnoreCase(existing.status.name)) {
        throw new RuntimeException("Solo se pueden actualizar clientes con estado ACTIVO");
        }

        // Validar duplicado de numCTA
        if (dto.numCTA != null && clientUpdateRepository.existsByNumCTAExcludingId(dto.numCTA, id)) {
            throw new RuntimeException("El número de cuenta ya existe: " + dto.numCTA);
        }

        clientUpdateRepository.updateClientPartial(
                id,
                dto.name,
                dto.numCTA,
                dto.genderId,
                dto.birthDate
        );

        // Muestra Resultados
        clientUpdateRepository.getEntityManager().flush();
        clientUpdateRepository.getEntityManager().clear();
        Clients updated = clientUpdateRepository.findById(id);

        return clientMapperService.toResponseDTO(updated);
    }
    
}
