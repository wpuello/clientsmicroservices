package org.clientes.Infraestructure.Exceptions;

import java.util.stream.Collectors;
import java.util.List;
import org.clientes.Infraestructure.Exceptions.ValidationExceptionMapper.ValidationError;
import jakarta.transaction.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {

    @Override
    public Response toResponse(RollbackException exception) {
        Throwable cause = exception.getCause();

        if (cause instanceof ConstraintViolationException violationEx) {

            List<ValidationError> errors = violationEx.getConstraintViolations()
                    .stream()
                    .map(v -> new ValidationError(
                            v.getPropertyPath().toString(),
                            v.getMessage()
                    ))
                    .collect(Collectors.toList());

            ErrorResponse errorResponse = new ErrorResponse(
                    "VALIDATION_ERROR",
                    "Error de validación en los datos enviados",
                    errors
            );

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
        }

        // Si no, devolvemos un error genérico (por seguridad)
        ErrorResponse errorResponse = new ErrorResponse(
                "TRANSACTION_ERROR",
                "Ocurrió un error inesperado al guardar los datos",
                null
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }

    public static class ErrorResponse {
        public String code;
        public String message;
        public List<ValidationError> details;

        public ErrorResponse(String code, String message, List<ValidationError> details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
    }

    public static class ValidationError {
        public String field;
        public String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
    
}
