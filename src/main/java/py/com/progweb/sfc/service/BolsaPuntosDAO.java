package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.BolsaPuntos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    private ReglaAsignacionPuntosDAO reglaAsignacionPuntosDAO;
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;
    @Inject
    private ClienteDAO clienteDAO;

    public void guardar(BolsaPuntos entidad) {
        this.em.persist(entidad);
    }
    public void eliminar(BolsaPuntos entidad) {
        this.em.remove(entidad);
    }
    public BolsaPuntos obtenerPorId(Integer id){
        return this.em.find(BolsaPuntos.class, id);
    }
    public void eliminarPorId(Integer id) {
        BolsaPuntos bolsa = obtenerPorId(id);
        eliminar(bolsa);
    }
    public void actualizar(BolsaPuntos entidad) {
        this.em.merge(entidad);
    }
    public List<BolsaPuntos> listar(){
        Query q = this.em.createQuery("select p from BolsaPuntos p");
        List<BolsaPuntos> bolsaPuntos = (List<BolsaPuntos>) q.getResultList();
        return bolsaPuntos;
    }
    public List<BolsaPuntos> obtenerPorIdCliente(Integer idCliente){
        List<BolsaPuntos> bolsaPuntos = null;
        Query q = null;
        q = this.em.createQuery("select b from BolsaPuntos b where b.clienteId.clienteId = :param");
        q.setParameter("param", idCliente);

        bolsaPuntos = (List<BolsaPuntos>) q.getResultList();

        return bolsaPuntos;
    }
    public List<BolsaPuntos> obtenerPorRangoPuntos(Integer rangoInicio, Integer rangoFin){
        Query q = this.em.createQuery("select p from BolsaPuntos p");
        List<BolsaPuntos> bolsasPuntos = (List<BolsaPuntos>) q.getResultList();
        List<BolsaPuntos> result = new ArrayList<>();
        for (BolsaPuntos bolsaPuntos : bolsasPuntos) {
            if(bolsaPuntos.getPuntajeAsignado() >= rangoInicio && bolsaPuntos.getPuntajeAsignado() <= rangoFin){
                result.add(bolsaPuntos);
            }
        }

        return result;
    }
    public void calcBolsaPuntos(Integer idCliente, Integer monto) throws Exception {
        try {
            Integer puntos = reglaAsignacionPuntosDAO.obtenerEquivalenciaPuntos(monto);
            BolsaPuntos bolsa = new BolsaPuntos();
            bolsa.setClienteId(clienteDAO.obtenerPorId(idCliente));
            bolsa.setMontoOperacion(monto);
            bolsa.setPuntajeAsignado(puntos);
            bolsa.setSaldoPuntos(puntos);
            bolsa.setFechaAsignacionPuntaje(new Date());
            bolsa.setFechaCaducidadPuntaje(vencimientoPuntosDAO.calculaFechaVencimiento(new Date()));
            guardar(bolsa);
        } catch (Exception e) {
            throw e;
        }
    }

}
