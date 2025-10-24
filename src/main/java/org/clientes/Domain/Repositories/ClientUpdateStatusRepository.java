package org.clientes.Domain.Repositories;

import java.time.LocalDate;

import org.clientes.Domain.Entities.Clients.Clients;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ClientUpdateStatusRepository implements PanacheRepository<Clients> {

    public boolean changeStatus(Long id, String newStatus) {
        Clients client = findById(id);
        if (client == null) {
            return false;
        }

        client.status.name = newStatus;

        if ("ACTIVE".equalsIgnoreCase(newStatus)) {
            client.activatedDate = LocalDate.now();
            client.inactivatedDate = null;
        } else if ("INACTIVE".equalsIgnoreCase(newStatus)) {
            client.inactivatedDate = LocalDate.now();
        }

        persist(client);
        return true;
    }
    
}
