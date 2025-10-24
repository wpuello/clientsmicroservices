package org.clientes.Infraestructure.Seeders.Persistence;

import org.clientes.Domain.Entities.Country;
import org.clientes.Domain.Enums.CountryEnum;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CountrySeeder {

    void onStart(@Observes StartupEvent ev) {
        seed();
    }

    @Transactional
    public void seed() {
        
        if (Country.count() == 0) {
            for (CountryEnum countryEnum : CountryEnum.values()) {
                persistCountry(countryEnum.name());
            }
            System.out.println("Países creados correctamente");
        } else {
            System.out.println("Países ya existen");
        }
    }

    private void persistCountry(String name) {
        Country country = new Country();
        country.name = name;
        country.persist();
    }
}
