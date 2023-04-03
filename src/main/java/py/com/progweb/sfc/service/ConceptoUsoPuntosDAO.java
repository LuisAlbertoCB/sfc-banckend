package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.ConceptoUsoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ConceptoUsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void guardar(ConceptoUsoPuntos entidad) {
        this.em.persist(entidad);

    }
    public void eliminar(ConceptoUsoPuntos entidad) {
        this.em.remove(entidad);
    }
    public ConceptoUsoPuntos obtenerPorId(Integer id){
        return this.em.find(ConceptoUsoPuntos.class, id);
    }
    public void eliminarPorId(Integer id) {
        ConceptoUsoPuntos concepto = obtenerPorId(id);
        eliminar(concepto);
    }
    public void actualizar(ConceptoUsoPuntos entidad) {
        this.em.merge(entidad);
    }
    public List<ConceptoUsoPuntosDAO> listar(){
        Query q = this.em.createQuery("select p from ConceptoUsoPuntos p");
        List<ConceptoUsoPuntosDAO> conceptoUsoPuntoDAOS = (List<ConceptoUsoPuntosDAO>) q.getResultList();
        return conceptoUsoPuntoDAOS;
    }
    public ConceptoUsoPuntos obtenerConceptoCanje(Integer id) {return this.em.find(ConceptoUsoPuntos.class, id);}
}
