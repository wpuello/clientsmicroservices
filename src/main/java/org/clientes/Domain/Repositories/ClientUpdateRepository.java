package org.clientes.Domain.Repositories;


import org.clientes.Domain.Entities.Clients.Clients;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientUpdateRepository implements PanacheRepository<Clients> {

    public boolean existsByNumCTAExcludingId(String numCTA, Long excludeId) {
        return find("numCTA = ?1 and id <> ?2", numCTA, excludeId)
                .firstResultOptional()
                .isPresent();
    }

    public boolean isClientActive(Long id) {
    return find("id = ?1 and status.name = ?2", id, "ACTIVE")
            .firstResultOptional()
            .isPresent();
}

    public void updateClientPartial(Long id, String name, String numCTA, Long genderId, java.time.LocalDate birthDate) {
        StringBuilder query = new StringBuilder("UPDATE Clients c SET ");
        boolean hasPrevious = false;

        if (name != null) {
            query.append("c.name = :name");
            hasPrevious = true;
        }
        if (numCTA != null) {
            if (hasPrevious) query.append(", ");
            query.append("c.numCTA = :numCTA");
            hasPrevious = true;
        }
        if (genderId != null) {
            if (hasPrevious) query.append(", ");
            query.append("c.gender.id = :genderId");
            hasPrevious = true;
        }
        if (birthDate != null) {
            if (hasPrevious) query.append(", ");
            query.append("c.birthDate = :birthDate");
        }

        query.append(" WHERE c.id = :id");

        var q = getEntityManager().createQuery(query.toString());
        q.setParameter("id", id);

        if (name != null) q.setParameter("name", name);
        if (numCTA != null) q.setParameter("numCTA", numCTA);
        if (genderId != null) q.setParameter("genderId", genderId);
        if (birthDate != null) q.setParameter("birthDate", birthDate);

        q.executeUpdate();
    }
    
}
