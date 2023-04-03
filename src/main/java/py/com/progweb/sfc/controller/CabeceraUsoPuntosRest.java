package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.service.CabeceraUsoPuntosDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/usoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class CabeceraUsoPuntosRest {
    @Inject
    private CabeceraUsoPuntosDAO cabeceraUsoPuntosDAO;
    @GET
    @Path("/")
    public Response listarUsoPuntos(){
        return Response.ok(cabeceraUsoPuntosDAO.listar()).build();
    }

    @POST
    @Path("/utilizar")
    public Response utilizarPuntos(@QueryParam("idCliente") Integer idCliente,
                                  @QueryParam("idConceptoUsoPuntos") Integer idConceptoUsoPuntos) throws Exception {
        try
        {
            cabeceraUsoPuntosDAO.agregarUsoPuntos(idCliente, idConceptoUsoPuntos);
        }
        catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return Response.ok().build();
    }

    @GET
    @Path("/filtro")
    public Response filtro( @QueryParam("idConcepto") Integer idConcepto,
                            @QueryParam("fechaUso") String fechaUso,
                            @QueryParam("idCliente") Integer idCliente) throws Exception {
        if (idConcepto != null) {
            return Response.ok(cabeceraUsoPuntosDAO.obtenerUsoPuntosPorConceptoUso(idConcepto)).build();
        }else if (fechaUso != null && !fechaUso.isEmpty()){
            return Response.ok(cabeceraUsoPuntosDAO.obtenerUsoPuntosPorFechaCanje(fechaUso)).build();
        } else if(idCliente != null){ {
            return Response.ok(cabeceraUsoPuntosDAO.obtenerUsoPuntosPorIdCliente(idCliente)).build();
        }
        } else {
            return  Response.ok("Enviar parametro de filtro").build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id){
        cabeceraUsoPuntosDAO.eliminarPorId(id);
        return Response.ok().build();
    }

}
