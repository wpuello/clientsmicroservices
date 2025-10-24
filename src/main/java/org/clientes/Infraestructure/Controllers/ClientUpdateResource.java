package org.clientes.Infraestructure.Controllers;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Applications.Dto.Clients.UpdateClientDTO;
import org.clientes.Applications.Services.ClientSaveService;
import org.clientes.Applications.Services.ClientUpdateService;
import org.clientes.Domain.Entities.Clients.Clients;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path("/v1/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Update Clients", description = "Actualizar Clientes")
public class ClientUpdateResource {
    
    @Inject
    ClientUpdateService clientUpdateService;

    @PUT
    @Path("/update/{id}")
    @Operation(summary = "Actualizar cliente solo Activos", description = "Actualiza los datos principales de un cliente que ya existe y que esten Activos")
    @RequestBody(
        description = "Datos Cliente Actualizar",
        required = true,
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            examples = {
                @ExampleObject(
                    name = "Ejemplo de request update",
                    value = """
                    {
                      "name": "William Puello",
                      "birthDate": "1988-03-17",
                      "gender": { "id": 51 },
                      "numCTA": "005123456789012"
                    }
                    """
                )
            }
        )
    )
    @APIResponse(
        responseCode = "200",
        description = "Cliente Activo actualizado correctamente",
        content = @Content(schema = @Schema(implementation = ResponseCreateClientDTO.class))
    )
     @APIResponse(
        responseCode = "400",
        description = "Request Invalido, verifique Nro de Cuenta o Datos Suministrador"
    )
    public Response updateClient(@PathParam("id") Long id, UpdateClientDTO dto) {
        ResponseCreateClientDTO updated = clientUpdateService.updateClient(id, dto);
        return Response.ok(updated).build();
    }

}
