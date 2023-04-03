package py.com.progweb.sfc.service;

import py.com.progweb.sfc.emailConf.Email;
import py.com.progweb.sfc.entity.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class CabeceraUsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    private DetalleUsoPuntosDAO detalleUsoPuntosDAO;
    @Inject
    private ClienteDAO clienteDAO;
    @Inject
    private ConceptoUsoPuntosDAO conceptoUsoPuntosDAO;
    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    private final Email email = new Email();

    public void guardar(CabeceraUsoPuntos entidad) {
        this.em.persist(entidad);
    }
    public void eliminar(CabeceraUsoPuntos entidad) {
        this.em.remove(entidad);
    }
    public CabeceraUsoPuntos obtenerPorId(Integer id){
        return this.em.find(CabeceraUsoPuntos.class, id);
    }
    public void eliminarPorId(Integer id) {
        CabeceraUsoPuntos cabecera = obtenerPorId(id);
        this.eliminar(cabecera);
    }
    public void actualizar(CabeceraUsoPuntos entidad) {
        this.em.merge(entidad);
    }
    public List<CabeceraUsoPuntos> listar(){
        Query q = this.em.createQuery("select p from CabeceraUsoPuntos p");
        List<CabeceraUsoPuntos> cabeceraUsoPuntos = (List<CabeceraUsoPuntos>) q.getResultList();
        return cabeceraUsoPuntos;
    }

    public CabeceraUsoPuntos obtenerUsoPuntosPorId(Integer id){
        return this.em.find(CabeceraUsoPuntos.class, id);
    }

    public void agregarUsoPuntos(Integer idCliente, Integer idConceptoCanje) throws Exception {
        Cliente cliente = clienteDAO.obtenerPorId(idCliente);
        ConceptoUsoPuntos conceptoUsoPuntos = conceptoUsoPuntosDAO.obtenerConceptoCanje(idConceptoCanje);
        List<BolsaPuntos> bolsasCliente = bolsaPuntosDAO.obtenerPorIdCliente(idCliente);
        List<DetalleUsoPuntos> detalles = new ArrayList<>();

        if (conceptoUsoPuntos != null) {
            int puntajeAUtilizar = conceptoUsoPuntos.getPuntosRequerido();

            if (Boolean.TRUE.equals(puedeUtilizarPuntos(bolsasCliente, puntajeAUtilizar))
                    && !bolsasCliente.isEmpty()) {

                CabeceraUsoPuntos usoPuntos = new CabeceraUsoPuntos();

                usoPuntos.setClienteId(clienteDAO.obtenerPorId(idCliente));
                usoPuntos.setConceptoUsoPuntosId(conceptoUsoPuntosDAO.obtenerPorId(idConceptoCanje));

                utilizarPuntosDeBolsas(bolsasCliente, puntajeAUtilizar, detalles);

                usoPuntos.setPuntajeUtilizado(conceptoUsoPuntos.getPuntosRequerido());
                usoPuntos.setFechaUso(new Date());

                this.em.persist(usoPuntos);
                this.em.flush();

                detalleUsoPuntosDAO.guardarDetalles(usoPuntos.getCabeceraUsoPuntosId(), detalles);

                try {
                    email.EnviarEmailConfirmacion(cliente,usoPuntos.getPuntajeUtilizado(),conceptoUsoPuntos.getDesConcepto(),usoPuntos.getFechaUso());
                } catch (IOException e) {
                    System.out.println("Error al enviar el email");
                }

            } else {
                System.out.println("No tiene suficientes puntos");
            }
        } else{
            System.out.println("No existe el concepto "+ idConceptoCanje);
        }
    }

    private Boolean puedeUtilizarPuntos(List<BolsaPuntos> bolsasCliente, Integer puntajeAUtilizar) {

        int totalPuntos = 0;
        for (BolsaPuntos bolsa : bolsasCliente) {
            if(bolsa.getFechaCaducidadPuntaje().after(new Date())) {
                totalPuntos = totalPuntos + bolsa.getSaldoPuntos();
            }
        }
        if (totalPuntos >= puntajeAUtilizar){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    private List<DetalleUsoPuntos> utilizarPuntosDeBolsas(List<BolsaPuntos> bolsasCliente, Integer puntajeAUtilizar, List<DetalleUsoPuntos> detalles){
        for (int i = 0 ; i < bolsasCliente.size() ; i++) {
            DetalleUsoPuntos detalleCanje = new DetalleUsoPuntos();
            if (bolsasCliente.get(i).getFechaCaducidadPuntaje().after(new Date())
                    && bolsasCliente.get(i).getSaldoPuntos() > 0) {
                if (bolsasCliente.get(i).getSaldoPuntos() <= puntajeAUtilizar) {
                    puntajeAUtilizar = puntajeAUtilizar - bolsasCliente.get(i).getSaldoPuntos();
                    bolsasCliente.get(i).setPuntajeUtilizado(bolsasCliente.get(i).getSaldoPuntos());
                    bolsasCliente.get(i).setSaldoPuntos(0);
                    detalleCanje.setBolsaPuntosId(bolsaPuntosDAO.obtenerPorId(bolsasCliente.get(i).getBolsaPuntosId()));
                    detalleCanje.setPuntajeUtilizado(bolsasCliente.get(i).getPuntajeUtilizado());
                }
                if (bolsasCliente.get(i).getSaldoPuntos() > puntajeAUtilizar && puntajeAUtilizar > 0) {
                    bolsasCliente.get(i).setPuntajeUtilizado(puntajeAUtilizar);
                    bolsasCliente.get(i).setSaldoPuntos(bolsasCliente.get(i).getSaldoPuntos() - puntajeAUtilizar);
                    detalleCanje.setBolsaPuntosId(bolsaPuntosDAO.obtenerPorId(bolsasCliente.get(i).getBolsaPuntosId()));
                    detalleCanje.setPuntajeUtilizado(bolsasCliente.get(i).getPuntajeUtilizado());
                    puntajeAUtilizar = 0;
                }
                if(detalleCanje.getPuntajeUtilizado()!= 0){
                    detalles.add(detalleCanje);
                }
            }
            bolsaPuntosDAO.actualizar(bolsasCliente.get(i));
        }
        return detalles;
    }

    public List<CabeceraUsoPuntos> obtenerUsoPuntosPorConceptoUso(Integer idConcepto){

        Query q = this.em.createQuery("select c from CabeceraUsoPuntos c where c.conceptoUsoPuntosId.conceptoUsoPuntosId = :param");
        q.setParameter("param", idConcepto);

        return (List<CabeceraUsoPuntos>)q.getResultList();
    }

    public List<CabeceraUsoPuntos> obtenerUsoPuntosPorIdCliente(Integer idCliente){

        Query q = this.em.createQuery("select c from CabeceraUsoPuntos c where c.clienteId.clienteId = :param");
        q.setParameter("param", idCliente);

        return (List<CabeceraUsoPuntos>)q.getResultList();
    }
    public List<CabeceraUsoPuntos> obtenerUsoPuntosPorFechaCanje(String fechaCanje){

        Query q = this.em.createQuery("select c from CabeceraUsoPuntos c where to_char(c.fechaUso, 'YYYY-MM-dd') like :param");
        q.setParameter("param", fechaCanje);

        return (List<CabeceraUsoPuntos>)q.getResultList();
    }

}
