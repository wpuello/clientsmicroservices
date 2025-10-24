package org.clientes.Domain.Strategy.Clients;

import org.clientes.Domain.Entities.Clients.Clients;
import org.clientes.Domain.Strategy.Clients.impl.ChileClientsAccountValidationStrategy;
import org.clientes.Domain.Strategy.Clients.impl.DefaultClientsAccountValidationStrategy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientAccountValidationFactory {

    @Inject
    ChileClientsAccountValidationStrategy chileclientStrategy;

    @Inject
    DefaultClientsAccountValidationStrategy defaultclientStrategy;

    public ClientAccountValidationStrategy getStrategy(Clients client) {
        if (client.country != null && 
            client.country.name != null &&
            client.country.name.equalsIgnoreCase("Chile")) {
            return chileclientStrategy;
        }
        return defaultclientStrategy;
    }
}
