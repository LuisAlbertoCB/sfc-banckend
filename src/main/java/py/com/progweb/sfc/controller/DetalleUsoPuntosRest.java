package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.service.DetalleUsoPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/detalleUsoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class DetalleUsoPuntosRest {
    @Inject
    DetalleUsoPuntosDAO detalleUsoPuntosDAO;
    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(detalleUsoPuntosDAO.listar()).build();
    }


}
