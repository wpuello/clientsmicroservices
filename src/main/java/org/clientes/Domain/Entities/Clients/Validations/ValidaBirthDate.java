package org.clientes.Domain.Entities.Clients.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = BirthDateValida.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidaBirthDate {
        String message() default "La fecha de nacimiento debe estar entre 1990 y la fecha actual";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
