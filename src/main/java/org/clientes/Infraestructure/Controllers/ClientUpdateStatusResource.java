package org.clientes.Infraestructure.Controllers;

import org.clientes.Applications.Dto.Clients.ResponseCreateClientDTO;
import org.clientes.Applications.Services.ClientUpdateStatusService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/v1/clients/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Client Status", description = "Activar o desactivar clientes")
public class ClientUpdateStatusResource {

    @Inject
    ClientUpdateStatusService clientStatusService;

    @PUT
    @Path("/activate/{id}")
    @Operation(summary = "Activar cliente", description = "Activa un cliente y registra la fecha de activación")
    @APIResponse(
        responseCode = "200",
        description = "Cliente activado correctamente",
        content = @Content(schema = @Schema(implementation = ResponseCreateClientDTO.class))
    )
    @APIResponse(
        responseCode = "400",
        description = "Error al activar el cliente"
    )
    public Response activateClient(@PathParam("id") Long id) {
        clientStatusService.activateClient(id);
         return Response.ok("{\"message\": \"Cliente activado correctamente\"}")
               .type(MediaType.APPLICATION_JSON)
               .build();
    }

    @PUT
    @Path("/deactivate/{id}")
    @Operation(summary = "Desactivar cliente", description = "Desactiva un cliente y registra la fecha de inactivación")
    @APIResponse(
        responseCode = "200",
        description = "Cliente desactivado correctamente",
        content = @Content(schema = @Schema(implementation = ResponseCreateClientDTO.class))
    )
    @APIResponse(
        responseCode = "400",
        description = "Error al desactivar el cliente"
    )
    public Response deactivateClient(@PathParam("id") Long id) {
        clientStatusService.deactivateClient(id);
        return Response.ok("{\"message\": \"Cliente desactivado correctamente\"}")
               .type(MediaType.APPLICATION_JSON)
               .build();
    }
    
    
}
