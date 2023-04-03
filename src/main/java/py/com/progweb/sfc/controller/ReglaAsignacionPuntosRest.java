package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.entity.ReglaAsignacionPuntos;
import py.com.progweb.sfc.service.ReglaAsignacionPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/reglaAsignacionPuntos")
@Consumes("application/json")
@Produces("application/json")
public class ReglaAsignacionPuntosRest {
    @Inject
    private ReglaAsignacionPuntosDAO reglaAsignacionPuntosDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(reglaAsignacionPuntosDAO.listar()).build();
    }
    @GET
    @Path("/{id}")
    public Response obtener(@PathParam(value = "id") Integer id) {
        return Response.ok(reglaAsignacionPuntosDAO.obtenerPorId(id)).build();
    }
    @GET
    @Path("/equivalencia")
    public Response obtenerEquivalenciaPuntos(@QueryParam(value = "monto") Integer monto) throws Exception {
        try {
            return Response.ok(this.reglaAsignacionPuntosDAO.obtenerEquivalenciaPuntos(monto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @POST
    @Path("/")
    public Response crear(ReglaAsignacionPuntos cliente) {
        this.reglaAsignacionPuntosDAO.guardar(cliente);
        return Response.status(Response.Status.CREATED).build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam(value = "id") Integer id) {
        this.reglaAsignacionPuntosDAO.eliminarPorId(id);
        return Response.ok().build();
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam(value = "id") Integer id,ReglaAsignacionPuntos regla) {
        if(reglaAsignacionPuntosDAO.obtenerPorId(id)==null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else {
            regla.setReglaAsignacionPuntosId(id);
            this.reglaAsignacionPuntosDAO.actualizar(regla);
            return Response.ok().build();
        }
    }

}
