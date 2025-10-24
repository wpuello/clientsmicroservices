package org.clientes.Infraestructure.Exceptions;

import org.clientes.Domain.Exceptions.Clients.DuplicateAccountException;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateAccountExceptionMapper implements ExceptionMapper<DuplicateAccountException> {

    @Override
    public Response toResponse(DuplicateAccountException exception) {
        ErrorResponse error = new ErrorResponse(
                "NRO DE CUENTA YA EXISTE",
                exception.getMessage()
        );

        return Response.status(Response.Status.CONFLICT) // 409 Conflict
                .entity(error)
                .build();
    }

    // Clase interna para la respuesta
    public static class ErrorResponse {
        public String code;
        public String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
    
    }
}