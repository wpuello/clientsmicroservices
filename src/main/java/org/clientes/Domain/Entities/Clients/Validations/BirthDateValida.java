package org.clientes.Domain.Entities.Clients.Validations;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BirthDateValida implements ConstraintValidator<ValidaBirthDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return true;
        LocalDate minDate = LocalDate.of(1990, 1, 1);
        LocalDate today = LocalDate.now();
        return !value.isBefore(minDate) && !value.isAfter(today);
    }

    
}
