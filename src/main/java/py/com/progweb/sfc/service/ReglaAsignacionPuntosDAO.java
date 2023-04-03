package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.ReglaAsignacionPuntos;
import py.com.progweb.sfc.entity.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class ReglaAsignacionPuntosDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    public void guardar(ReglaAsignacionPuntos entidad) {
        this.em.persist(entidad);
    }
    public void eliminar(ReglaAsignacionPuntos entidad) {
        this.em.remove(entidad);
    }
    public ReglaAsignacionPuntos obtenerPorId(Integer id){
        return this.em.find(ReglaAsignacionPuntos.class, id);
    }
    public void eliminarPorId(Integer id) {
        ReglaAsignacionPuntos regla = obtenerPorId(id);
        eliminar(regla);
    }
    public void actualizar(ReglaAsignacionPuntos entidad) {
        this.em.merge(entidad);
    }
    public List<ReglaAsignacionPuntos> listar(){
        Query q = this.em.createQuery("select p from ReglaAsignacionPuntos p");
        List<ReglaAsignacionPuntos> reglaAsignacionPuntos = (List<ReglaAsignacionPuntos>) q.getResultList();
        return reglaAsignacionPuntos;
    }
    public Integer obtenerEquivalenciaPuntos(Integer montoTotal) throws Exception {
        Query q = this.em.createQuery("select p from ReglaAsignacionPuntos p");
        List<ReglaAsignacionPuntos> reglas = (List<ReglaAsignacionPuntos>) q.getResultList();
        Date today = new Date();
        for(ReglaAsignacionPuntos regla : reglas) {
            if(regla.getLimiteMax() == null && regla.getLimiteMin() == null) {
                VencimientoPuntos vencimiento = regla.getVencimientoPuntosId();
                if(vencimiento.getFechaIniValidez().before(today) && vencimiento.getFechaFinValidez().after(today)) {
                    return montoTotal/regla.getMontoEquivalencia();
                }
            }
            else if(montoTotal <= regla.getLimiteMax() && montoTotal >= regla.getLimiteMin()) {
                VencimientoPuntos vencimiento = regla.getVencimientoPuntosId();
                if(vencimiento.getFechaIniValidez().before(today) && vencimiento.getFechaFinValidez().after(today)) {
                    return montoTotal/regla.getMontoEquivalencia();
                }
            }
        }
        throw new Exception("Regla inexistente");
    }
}
