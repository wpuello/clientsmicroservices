package org.clientes.Domain.Exceptions.Clients;

public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(String numCTA) {
        super("El número de cuenta ya existe para otro cliente: " + numCTA);
    }
}
