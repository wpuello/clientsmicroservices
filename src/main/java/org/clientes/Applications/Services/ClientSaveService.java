package org.clientes.Applications.Services;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Exceptions.Clients.DuplicateAccountException;
import org.clientes.Domain.Repositories.ClientSaveRepository;
import org.clientes.Domain.Strategy.Clients.ClientAccountValidationFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientSaveService {

    @Inject
    ClientMapperService clientMapperService;

    @Inject
    ClientSaveRepository clientRepository;

    @Inject
    ClientAccountValidationFactory clientvalidationFactory;

    @Transactional
    public ResponseCreateClientDTO saveClient(Clients client) {

       if (clientRepository.find("numCTA", client.numCTA).firstResultOptional().isPresent()) {
        throw new DuplicateAccountException(client.numCTA);
        }

        // Valida según país que sea Digitado
        clientvalidationFactory.getStrategy(client).validate(client);

        clientRepository.persist(client);
        return clientMapperService.toResponseDTO(client);
    }
    
}
