package org.clientes.Infraestructure.Seeders.Persistence;
import org.clientes.Domain.Entities.Gender;
import org.clientes.Domain.Enums.GenderEnum;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;


@Startup
@ApplicationScoped
public class GenderSeeder {
   
    void onStart(@Observes StartupEvent ev) {
        seed();
    }

    @Transactional
    public void seed() {
        System.out.println("Ejecutando GenderSeeder...");

        if (Gender.count() == 0) {
            for (GenderEnum genderEnum : GenderEnum.values()) {
                persistGender(genderEnum.name());
            }
            System.out.println("Géneros creados correctamente por defecto");
        } else {
            System.out.println("Géneros ya existen");
        }
    }

    private void persistGender(String name) {
        Gender gender = new Gender();
        gender.name = name;
        gender.persist();
    }
}
