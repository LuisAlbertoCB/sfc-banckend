/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.com.progweb.sfc.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "detalle_uso_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleUsoPuntos.findAll", query = "SELECT d FROM DetalleUsoPuntos d"),
    @NamedQuery(name = "DetalleUsoPuntos.findByDetalleUsoPuntosId", query = "SELECT d FROM DetalleUsoPuntos d WHERE d.detalleUsoPuntosId = :detalleUsoPuntosId"),
    @NamedQuery(name = "DetalleUsoPuntos.findByPuntajeUtilizado", query = "SELECT d FROM DetalleUsoPuntos d WHERE d.puntajeUtilizado = :puntajeUtilizado")})
public class DetalleUsoPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_uso_puntos_id")
    private Integer detalleUsoPuntosId;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @JoinColumn(name = "bolsa_puntos_id", referencedColumnName = "bolsa_puntos_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private BolsaPuntos bolsaPuntosId;
    @JoinColumn(name = "cabecera_uso_puntos_id", referencedColumnName = "cabecera_uso_puntos_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CabeceraUsoPuntos cabeceraUsoPuntosId;

    public DetalleUsoPuntos() {
    }

    public DetalleUsoPuntos(Integer detalleUsoPuntosId) {
        this.detalleUsoPuntosId = detalleUsoPuntosId;
    }

    public Integer getDetalleUsoPuntosId() {
        return detalleUsoPuntosId;
    }

    public void setDetalleUsoPuntosId(Integer detalleUsoPuntosId) {
        this.detalleUsoPuntosId = detalleUsoPuntosId;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public BolsaPuntos getBolsaPuntosId() {
        return bolsaPuntosId;
    }

    public void setBolsaPuntosId(BolsaPuntos bolsaPuntosId) {
        this.bolsaPuntosId = bolsaPuntosId;
    }

    public CabeceraUsoPuntos getCabeceraUsoPuntosId() {
        return cabeceraUsoPuntosId;
    }

    public void setCabeceraUsoPuntosId(CabeceraUsoPuntos cabeceraUsoPuntosId) {
        this.cabeceraUsoPuntosId = cabeceraUsoPuntosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleUsoPuntosId != null ? detalleUsoPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleUsoPuntos)) {
            return false;
        }
        DetalleUsoPuntos other = (DetalleUsoPuntos) object;
        if ((this.detalleUsoPuntosId == null && other.detalleUsoPuntosId != null) || (this.detalleUsoPuntosId != null && !this.detalleUsoPuntosId.equals(other.detalleUsoPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.DetalleUsoPuntos[ detalleUsoPuntosId=" + detalleUsoPuntosId + " ]";
    }
    
}
