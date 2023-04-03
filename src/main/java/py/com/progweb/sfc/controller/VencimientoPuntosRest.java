package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.entity.VencimientoPuntos;
import py.com.progweb.sfc.service.VencimientoPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/vencimientoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosRest {
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(vencimientoPuntosDAO.listar()).build();
    }
    @GET
    @Path("/{id}")
    public Response obtenerVencimientoPuntosPorId(@PathParam("id") Integer id) {
        return Response.ok(vencimientoPuntosDAO.obtenerVencimiento(id)).build();
    }
    @POST
    @Path("/")
    public Response crear(VencimientoPuntos vencimientoPuntos) {
        this.vencimientoPuntosDAO.agregar(vencimientoPuntos);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam(value = "id") Integer id) {
        try {
            this.vencimientoPuntosDAO.eliminarPorId(id);
            return Response.ok().build();
        }catch (Exception e) {
            return Response.serverError().build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, VencimientoPuntos vencimientoPuntos) {
        if(vencimientoPuntosDAO.obtenerVencimiento(id)!=null){
            vencimientoPuntos.setVencimientoPuntosId(id);
            this.vencimientoPuntosDAO.actualizar(vencimientoPuntos);
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
