package py.com.progweb.sfc.controller;

import py.com.progweb.sfc.entity.ConceptoUsoPuntos;
import py.com.progweb.sfc.service.ConceptoUsoPuntosDAO;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/conceptoUsoPuntos")
@Consumes("application/json")
@Produces("application/json")
public class ConceptoUsoPuntosRest {
    @Inject
    private ConceptoUsoPuntosDAO conceptoUsoPuntosDao;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(conceptoUsoPuntosDao.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam(value = "id") Integer id) {
        return Response.ok(conceptoUsoPuntosDao.obtenerPorId(id)).build();
    }
    @POST
    @Path("/")
    public Response crear(ConceptoUsoPuntos conceptoUsoPuntos) {
        this.conceptoUsoPuntosDao.guardar(conceptoUsoPuntos);
        return Response.ok().build();

    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam(value = "id") Integer id, ConceptoUsoPuntos conceptoUsoPuntos) {
        if(conceptoUsoPuntosDao.obtenerPorId(id) == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else{
            conceptoUsoPuntos.setConceptoUsoPuntosId(id);
            this.conceptoUsoPuntosDao.actualizar(conceptoUsoPuntos);
            return Response.ok().build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam(value = "id") Integer id){
        try {
            this.conceptoUsoPuntosDao.eliminarPorId(id);
            return Response.ok().build();
        }catch (Exception a) {
            return Response.serverError().build();
        }
    }

}
