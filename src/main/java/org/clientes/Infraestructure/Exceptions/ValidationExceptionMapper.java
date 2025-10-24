package org.clientes.Infraestructure.Exceptions;

import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ValidationError> errors = exception.getConstraintViolations()
                .stream()
                .map(v -> new ValidationError(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                ))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR DE VALIDACION",
                "Se encontraron errores de validación",
                errors
        );

        return Response.status(Response.Status.BAD_REQUEST)
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
