package py.com.progweb.sfc.service;

import py.com.progweb.sfc.entity.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    public void guardar(Cliente cliente) {
        this.em.persist(cliente);
    }
    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
    }
    public void eliminar(Cliente cliente) {
        this.em.remove(cliente);
    }
    public void eliminarPorId(Integer id) {
        Cliente cliente = this.em.find(Cliente.class, id);
        this.em.remove(cliente);
    }
    public Cliente obtenerPorId(Integer id){
        return this.em.find(Cliente.class, id);
    }
    public List<Cliente> listar(){
        Query q = this.em.createQuery("select p from Cliente p");
        List<Cliente> clientes = (List<Cliente>) q.getResultList();
        return clientes;
    }
    public Object obtenerClientesPorParametro(String nombre, String apellido, String fechaNacimiento) {
        List<Cliente> clientes = null;
        Query q = null;
        if(nombre != null && !nombre.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.nombre like :param");
            q.setParameter("param", "%"+nombre+"%");
        } else if(apellido != null && !apellido.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.apellido like :param");
            q.setParameter("param", "%"+apellido+"%");
        } else if(fechaNacimiento != null && !fechaNacimiento.equals("")) {
            q = this.em.createQuery("select p from Cliente p where to_char(p.fechaNacimiento, 'yyyy-MM-dd') like :param");
            q.setParameter("param", fechaNacimiento);
        } else {
            q = this.em.createQuery("select p from Cliente p");
        }
        clientes = (List<Cliente>) q.getResultList();

        return clientes;
    }
}
