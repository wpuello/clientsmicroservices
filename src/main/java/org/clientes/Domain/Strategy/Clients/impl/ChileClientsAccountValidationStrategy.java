package org.clientes.Domain.Strategy.Clients.impl;

import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Strategy.Clients.ClientAccountValidationStrategy;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ChileClientsAccountValidationStrategy implements ClientAccountValidationStrategy {

    @Override
    public void validate(Clients client) {
        if (!client.numCTA.startsWith("003")) {
            throw new RuntimeException("El número de cuenta debería de iniciar con '003' para clientes de Chile.");
        }
    }
    
}
