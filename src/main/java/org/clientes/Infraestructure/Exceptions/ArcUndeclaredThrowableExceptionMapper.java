package org.clientes.Infraestructure.Exceptions;

import java.util.stream.Collectors;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import jakarta.transaction.RollbackException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import java.util.List;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ArcUndeclaredThrowableExceptionMapper implements ExceptionMapper<ArcUndeclaredThrowableException> {

    @Override
    public Response toResponse(ArcUndeclaredThrowableException exception) {

        Throwable cause = exception.getCause();

        while (cause != null) {
            if (cause instanceof ConstraintViolationException violationEx) {
                return handleConstraintViolation(violationEx);
            }
            if (cause instanceof RollbackException rollbackEx && rollbackEx.getCause() instanceof ConstraintViolationException inner) {
                return handleConstraintViolation(inner);
            }
            cause = cause.getCause();
        }

        // Si no encontramos una violación de validación, devolvemos error genérico
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("ERROR INTERNO", "Error inesperado en el servidor"))
                .build();
    }

    private Response handleConstraintViolation(ConstraintViolationException ex) {
        List<ValidationError> errors = ex.getConstraintViolations().stream()
                .map(v -> new ValidationError(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
                "ERROR DE VALIDACION",
                "Error de validación en los datos enviados",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }

   
    public static class ErrorResponse {
        public String code;
        public String message;
        public List<ValidationError> details;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

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
