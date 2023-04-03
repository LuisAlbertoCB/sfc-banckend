package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.DetalleUsoPuntos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DetalleUsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    private CabeceraUsoPuntosDAO cabeceraUsoPuntosDAO;
    public void guardar(DetalleUsoPuntosDAO entidad) {
        this.em.persist(entidad);
    }
    public void eliminar(DetalleUsoPuntosDAO entidad) {
        this.em.remove(entidad);
    }
    public DetalleUsoPuntosDAO obtenerPorId(Integer id){
        return this.em.find(DetalleUsoPuntosDAO.class, id);
    }
    public void eliminarPorId(Integer id) {
        DetalleUsoPuntosDAO detalle = obtenerPorId(id);
        eliminar(detalle);
    }
    public void actualizar(DetalleUsoPuntosDAO entidad) {
        this.em.merge(entidad);
    }
    public List<DetalleUsoPuntosDAO> listar(){
        Query q = this.em.createQuery("select p from DetalleUsoPuntos p");
        List<DetalleUsoPuntosDAO> detalleUsoPuntoDAOS = (List<DetalleUsoPuntosDAO>) q.getResultList();
        return detalleUsoPuntoDAOS;
    }
    //verificar
    public List<DetalleUsoPuntos> obtenerDetallesPorCanje(Integer idCanje){
        Query q = this.em.createQuery("select d from DetalleUsoPuntos d where d.detalleUsoPuntosId = :param");
        q.setParameter("param", idCanje);

        return (List<DetalleUsoPuntos>) q.getResultList();
    }
    public void guardarDetalles(Integer idCanje, List<DetalleUsoPuntos> detalles){
        for (DetalleUsoPuntos det : detalles) {
            det.setCabeceraUsoPuntosId(cabeceraUsoPuntosDAO.obtenerPorId(idCanje));
            this.em.persist(det);
        }
    }

}
