package org.clientes.Domain.Strategy.Clients;

import org.clientes.Domain.Entities.Clients.Clients;

public interface ClientAccountValidationStrategy {
    void validate(Clients client);
}
