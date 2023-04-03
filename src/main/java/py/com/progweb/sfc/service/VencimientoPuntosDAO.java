package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class VencimientoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar(VencimientoPuntos entidad) {
        this.em.persist(entidad);
    }
    public void eliminar(VencimientoPuntos entidad) {
        this.em.remove(entidad);
    }
    public VencimientoPuntos obtenerPorId(Integer id){
        return this.em.find(VencimientoPuntos.class, id);
    }
    public void eliminarPorId(Integer id) {
        VencimientoPuntos vencimiento = obtenerPorId(id);
        eliminar(vencimiento);
    }
    public void actualizar(VencimientoPuntos entidad) {
        this.em.merge(entidad);
    }
    public List<VencimientoPuntos> listar(){
        Query q = this.em.createQuery("select p from VencimientoPuntos p");
        List<VencimientoPuntos> vencimientoPuntos = (List<VencimientoPuntos>) q.getResultList();
        return vencimientoPuntos;
    }
    public VencimientoPuntos obtenerVencimiento(Integer idVencimiento){
        return this.em.find(VencimientoPuntos.class, idVencimiento);
    }

    public Date calculaFechaVencimiento(Date today) throws Exception{
        Query q = this.em.createQuery("select b from VencimientoPuntos b");
        List<VencimientoPuntos> vencimientos = q.getResultList();
        for(VencimientoPuntos venc : vencimientos) {
            if(venc.getFechaIniValidez().before(today) && venc.getFechaFinValidez().after(today)) {
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                c.add(Calendar.DATE, venc.getDuracionPuntaje());
                return c.getTime();
            }
        }
        throw new Exception("No hay fecha de vencimiento disponible");
    }
}
