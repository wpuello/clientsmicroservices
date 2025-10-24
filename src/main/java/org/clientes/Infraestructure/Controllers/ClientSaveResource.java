package org.clientes.Infraestructure.Controllers;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Applications.Services.ClientSaveService;
import org.clientes.Domain.Entities.Clients.Clients;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/v1/clients/save")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Save Clients", description = "Crear Clientes")
public class ClientSaveResource {

    @Inject
    ClientSaveService clientsaveService;

    @POST
    @Transactional
    @Operation(summary = "Crear Nuevo Cliente",description = "Este End Point se utiliza para crear un cliente nuevo y valida que El Nro de Cuenta no esté repetida")
    @RequestBody(
        description = "Datos Cliente",
        required = true,
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            examples = {
                @ExampleObject(
                    name = "Ejemplo de request",
                    value = """
                    {
                      "name": "William Puello",
                      "birthDate": "1988-03-17",
                      "gender": { "id": 51 },
                      "numCTA": "005123456789012",
                      "status": { "id": 51 },
                      "country": { "id": 51 }
                    }
                    """
                )
            }
        )
    )
    @APIResponse(
        responseCode = "201",
        description = "Cliente Creado Exitosamente",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = ResponseCreateClientDTO.class),
            examples = {
                @ExampleObject(
                    name = "Ejemplo response",
                    value = """
                    {
                      "id": 1,
                      "name": "William Puello",
                      "birthDate": "1988-03-17",
                      "gender": "Masculino",
                      "numCTA": "005123456789012",
                      "status": "Activo",
                      "country": "Colombia"
                    }
                    """
                )
            }
        )
    )
    @APIResponse(
        responseCode = "400",
        description = "Request Invalido, verifique Nro de Cuenta"
    )
    public Response createClient(Clients client) {
        ResponseCreateClientDTO response = clientsaveService.saveClient(client);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
}
