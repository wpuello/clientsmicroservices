package org.clientes.Infraestructure.Seeders.Persistence;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class DatabaseSeeder {

    @Inject CountrySeeder countrySeeder;
    @Inject GenderSeeder genderSeeder;
    @Inject StatusSeeder statusSeeder;

    void onStart(@Observes StartupEvent ev) {
        countrySeeder.seed();
        genderSeeder.seed();
        statusSeeder.seed();
    }
}
