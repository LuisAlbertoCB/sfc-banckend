package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.entity.BolsaPuntos;
import py.com.progweb.sfc.service.BolsaPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/bolsaPuntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(bolsaPuntosDAO.listar()).build();
    }
    @GET
    @Path("/filtro")
    public Response filtro( @QueryParam("idCliente") Integer idCliente,
                            @QueryParam("rangoInicio") Integer rangoInicio,
                            @QueryParam("rangoFin") Integer rangoFin ) throws Exception {
        if (idCliente != null) {
            return Response.ok(bolsaPuntosDAO.obtenerPorIdCliente(idCliente)).build();
        }else if (rangoFin != null && rangoInicio != null){
            return  Response.ok(bolsaPuntosDAO.obtenerPorRangoPuntos(rangoInicio, rangoFin)).build();
        } else {
            return  Response.ok("Debe enviar un parametro de filtro").build();
        }
    }
    @POST
    @Path("/")
    public Response guardar(BolsaPuntos entidad) {
        bolsaPuntosDAO.guardar(entidad);
        return Response.ok().build();
    }
    @POST
    @Path("/cargarPuntos")
    public Response agregarPuntos(@QueryParam("idCliente") Integer idCliente,
                                  @QueryParam("monto") Integer monto) throws Exception {
        this.bolsaPuntosDAO.calcBolsaPuntos(idCliente, monto);
        return Response.ok().build();
    }
    @DELETE
    @Path("/")
    public Response eliminar(BolsaPuntos entidad) {
        bolsaPuntosDAO.eliminar(entidad);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        bolsaPuntosDAO.eliminarPorId(id);
        return Response.ok().build();
    }
    @PUT
    @Path("/")
    public Response actualizar (BolsaPuntos entidad) {
        bolsaPuntosDAO.actualizar(entidad);
        return Response.ok().build();
    }

}
