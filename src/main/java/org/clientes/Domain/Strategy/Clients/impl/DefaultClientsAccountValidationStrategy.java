package org.clientes.Domain.Strategy.Clients.impl;

import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Strategy.Clients.ClientAccountValidationStrategy;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefaultClientsAccountValidationStrategy implements ClientAccountValidationStrategy {

@Override
    public void validate(Clients client) {
        // No reliza validacion
    }

    
}
