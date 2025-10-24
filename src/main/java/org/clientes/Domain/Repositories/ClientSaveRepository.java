package org.clientes.Domain.Repositories;

import org.clientes.Domain.Entities.Clients.Clients;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientSaveRepository implements PanacheRepository<Clients> {

    public Clients findByNumCTA(String numCTA) {
        return find("numCTA", numCTA).firstResult();
    }
    
}
