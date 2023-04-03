package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.entity.Cliente;
import py.com.progweb.sfc.service.ClienteDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {

    @Inject
    ClienteDAO clienteDAO;

    @GET
    @Path("/filtro")
    public Response filtro(@QueryParam("nombre") String nombre,
                           @QueryParam("apellido") String apellido,
                           @QueryParam("fechaNacimiento") String fechaNacimiento) {
        if(nombre.isEmpty() && apellido.isEmpty() && fechaNacimiento.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();
        return Response.ok(clienteDAO.obtenerClientesPorParametro(nombre, apellido, fechaNacimiento)).build();
    }
    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(clienteDAO.listar()).build() ;
    }
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id){
        Cliente cliente = clienteDAO.obtenerPorId(id);
        if(cliente != null)
            return Response.ok(cliente).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        clienteDAO.eliminarPorId(id);
        return Response.ok().build();
    }
    @PUT
    @Path("/{id}")
    public Response actualizar (@PathParam("id")Integer id, Cliente entidad) {
        if(clienteDAO.obtenerPorId(id) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else {
            entidad.setClienteId(id);
            clienteDAO.actualizar(entidad);
            return Response.ok().build();
        }
    }
    @POST
    @Path("/")
    public Response guardar(Cliente entidad) {
        clienteDAO.guardar(entidad);
        return Response.ok().build();
    }
    @DELETE
    @Path("/")
    public Response eliminar(Cliente entidad) {
        clienteDAO.eliminar(entidad);
        return Response.ok().build();
    }


}
