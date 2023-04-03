/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.com.progweb.sfc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "cabecera_uso_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CabeceraUsoPuntos.findAll", query = "SELECT c FROM CabeceraUsoPuntos c"),
    @NamedQuery(name = "CabeceraUsoPuntos.findByCabeceraUsoPuntosId", query = "SELECT c FROM CabeceraUsoPuntos c WHERE c.cabeceraUsoPuntosId = :cabeceraUsoPuntosId"),
    @NamedQuery(name = "CabeceraUsoPuntos.findByPuntajeUtilizado", query = "SELECT c FROM CabeceraUsoPuntos c WHERE c.puntajeUtilizado = :puntajeUtilizado"),
    @NamedQuery(name = "CabeceraUsoPuntos.findByFechaUso", query = "SELECT c FROM CabeceraUsoPuntos c WHERE c.fechaUso = :fechaUso")})
public class CabeceraUsoPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cabecera_uso_puntos_id")
    private Integer cabeceraUsoPuntosId;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Column(name = "fecha_uso")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaUso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabeceraUsoPuntosId", fetch = FetchType.EAGER)
    private List<DetalleUsoPuntos> detalleUsoPuntosList;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente clienteId;
    @JoinColumn(name = "concepto_uso_puntos_id", referencedColumnName = "concepto_uso_puntos_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConceptoUsoPuntos conceptoUsoPuntosId;

    public CabeceraUsoPuntos() {
    }

    public CabeceraUsoPuntos(Integer cabeceraUsoPuntosId) {
        this.cabeceraUsoPuntosId = cabeceraUsoPuntosId;
    }

    public Integer getCabeceraUsoPuntosId() {
        return cabeceraUsoPuntosId;
    }

    public void setCabeceraUsoPuntosId(Integer cabeceraUsoPuntosId) {
        this.cabeceraUsoPuntosId = cabeceraUsoPuntosId;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(Date fechaUso) {
        this.fechaUso = fechaUso;
    }

    @XmlTransient
    public List<DetalleUsoPuntos> getDetalleUsoPuntosList() {
        return detalleUsoPuntosList;
    }

    public void setDetalleUsoPuntosList(List<DetalleUsoPuntos> detalleUsoPuntosList) {
        this.detalleUsoPuntosList = detalleUsoPuntosList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public ConceptoUsoPuntos getConceptoUsoPuntosId() {
        return conceptoUsoPuntosId;
    }

    public void setConceptoUsoPuntosId(ConceptoUsoPuntos conceptoUsoPuntosId) {
        this.conceptoUsoPuntosId = conceptoUsoPuntosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabeceraUsoPuntosId != null ? cabeceraUsoPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabeceraUsoPuntos)) {
            return false;
        }
        CabeceraUsoPuntos other = (CabeceraUsoPuntos) object;
        if ((this.cabeceraUsoPuntosId == null && other.cabeceraUsoPuntosId != null) || (this.cabeceraUsoPuntosId != null && !this.cabeceraUsoPuntosId.equals(other.cabeceraUsoPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.CabeceraUsoPuntos[ cabeceraUsoPuntosId=" + cabeceraUsoPuntosId + " ]";
    }
    
}
