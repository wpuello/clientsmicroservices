package org.clientes.Infraestructure.Seeders.Persistence;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.clientes.Domain.Entities.Status;
import org.clientes.Domain.Enums.StatusEnum;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StatusSeeder {

    void onStart(@Observes StartupEvent ev) {
        seed();
    }

    @Transactional
    public void seed() {
        System.out.println("Ejecutando StatusSeeder...");

        if (Status.count() == 0) {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                persistStatus(statusEnum.name());
            }
            System.out.println("Status creados correctamente por defecto");
        } else {
            System.out.println("Status ya existen");
        }
    }

    private void persistStatus(String name) {
        Status status = new Status();
        status.name = name;
        status.persist();
    }
}
