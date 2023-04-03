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
@Table(name = "bolsa_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BolsaPuntos.findAll", query = "SELECT b FROM BolsaPuntos b"),
    @NamedQuery(name = "BolsaPuntos.findByBolsaPuntosId", query = "SELECT b FROM BolsaPuntos b WHERE b.bolsaPuntosId = :bolsaPuntosId"),
    @NamedQuery(name = "BolsaPuntos.findByFechaAsignacionPuntaje", query = "SELECT b FROM BolsaPuntos b WHERE b.fechaAsignacionPuntaje = :fechaAsignacionPuntaje"),
    @NamedQuery(name = "BolsaPuntos.findByFechaCaducidadPuntaje", query = "SELECT b FROM BolsaPuntos b WHERE b.fechaCaducidadPuntaje = :fechaCaducidadPuntaje"),
    @NamedQuery(name = "BolsaPuntos.findByPuntajeAsignado", query = "SELECT b FROM BolsaPuntos b WHERE b.puntajeAsignado = :puntajeAsignado"),
    @NamedQuery(name = "BolsaPuntos.findByPuntajeUtilizado", query = "SELECT b FROM BolsaPuntos b WHERE b.puntajeUtilizado = :puntajeUtilizado"),
    @NamedQuery(name = "BolsaPuntos.findBySaldoPuntos", query = "SELECT b FROM BolsaPuntos b WHERE b.saldoPuntos = :saldoPuntos"),
    @NamedQuery(name = "BolsaPuntos.findByMontoOperacion", query = "SELECT b FROM BolsaPuntos b WHERE b.montoOperacion = :montoOperacion")})
public class BolsaPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bolsa_puntos_id")
    private Integer bolsaPuntosId;
    @Column(name = "fecha_asignacion_puntaje")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAsignacionPuntaje;
    @Column(name = "fecha_caducidad_puntaje")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaCaducidadPuntaje;
    @Column(name = "puntaje_asignado")
    private Integer puntajeAsignado;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Column(name = "saldo_puntos")
    private Integer saldoPuntos;
    @Column(name = "monto_operacion")
    private Integer montoOperacion;
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente clienteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bolsaPuntosId", fetch = FetchType.EAGER)
    private List<DetalleUsoPuntos> detalleUsoPuntosList;

    public BolsaPuntos() {
    }

    public BolsaPuntos(Integer bolsaPuntosId) {
        this.bolsaPuntosId = bolsaPuntosId;
    }

    public Integer getBolsaPuntosId() {
        return bolsaPuntosId;
    }

    public void setBolsaPuntosId(Integer bolsaPuntosId) {
        this.bolsaPuntosId = bolsaPuntosId;
    }

    public Date getFechaAsignacionPuntaje() {
        return fechaAsignacionPuntaje;
    }

    public void setFechaAsignacionPuntaje(Date fechaAsignacionPuntaje) {
        this.fechaAsignacionPuntaje = fechaAsignacionPuntaje;
    }

    public Date getFechaCaducidadPuntaje() {
        return fechaCaducidadPuntaje;
    }

    public void setFechaCaducidadPuntaje(Date fechaCaducidadPuntaje) {
        this.fechaCaducidadPuntaje = fechaCaducidadPuntaje;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    @XmlTransient
    public List<DetalleUsoPuntos> getDetalleUsoPuntosList() {
        return detalleUsoPuntosList;
    }

    public void setDetalleUsoPuntosList(List<DetalleUsoPuntos> detalleUsoPuntosList) {
        this.detalleUsoPuntosList = detalleUsoPuntosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bolsaPuntosId != null ? bolsaPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BolsaPuntos)) {
            return false;
        }
        BolsaPuntos other = (BolsaPuntos) object;
        if ((this.bolsaPuntosId == null && other.bolsaPuntosId != null) || (this.bolsaPuntosId != null && !this.bolsaPuntosId.equals(other.bolsaPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.BolsaPuntos[ bolsaPuntosId=" + bolsaPuntosId + " ]";
    }
    
}
