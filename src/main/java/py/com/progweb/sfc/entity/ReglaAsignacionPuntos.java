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
@Table(name = "regla_asignacion_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReglaAsignacionPuntos.findAll", query = "SELECT r FROM ReglaAsignacionPuntos r"),
    @NamedQuery(name = "ReglaAsignacionPuntos.findByReglaAsignacionPuntosId", query = "SELECT r FROM ReglaAsignacionPuntos r WHERE r.reglaAsignacionPuntosId = :reglaAsignacionPuntosId"),
    @NamedQuery(name = "ReglaAsignacionPuntos.findByLimiteMax", query = "SELECT r FROM ReglaAsignacionPuntos r WHERE r.limiteMax = :limiteMax"),
    @NamedQuery(name = "ReglaAsignacionPuntos.findByLimiteMin", query = "SELECT r FROM ReglaAsignacionPuntos r WHERE r.limiteMin = :limiteMin"),
    @NamedQuery(name = "ReglaAsignacionPuntos.findByMontoEquivalencia", query = "SELECT r FROM ReglaAsignacionPuntos r WHERE r.montoEquivalencia = :montoEquivalencia")})
public class ReglaAsignacionPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "regla_asignacion_puntos_id")
    private Integer reglaAsignacionPuntosId;
    @Column(name = "limite_max")
    private Integer limiteMax;
    @Column(name = "limite_min")
    private Integer limiteMin;
    @Column(name = "monto_equivalencia")
    private Integer montoEquivalencia;
    @JoinColumn(name = "vencimiento_puntos_id", referencedColumnName = "vencimiento_puntos_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private VencimientoPuntos vencimientoPuntosId;

    public ReglaAsignacionPuntos() {
    }

    public ReglaAsignacionPuntos(Integer reglaAsignacionPuntosId) {
        this.reglaAsignacionPuntosId = reglaAsignacionPuntosId;
    }

    public Integer getReglaAsignacionPuntosId() {
        return reglaAsignacionPuntosId;
    }

    public void setReglaAsignacionPuntosId(Integer reglaAsignacionPuntosId) {
        this.reglaAsignacionPuntosId = reglaAsignacionPuntosId;
    }

    public Integer getLimiteMax() {
        return limiteMax;
    }

    public void setLimiteMax(Integer limiteMax) {
        this.limiteMax = limiteMax;
    }

    public Integer getLimiteMin() {
        return limiteMin;
    }

    public void setLimiteMin(Integer limiteMin) {
        this.limiteMin = limiteMin;
    }

    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    public VencimientoPuntos getVencimientoPuntosId() {
        return vencimientoPuntosId;
    }

    public void setVencimientoPuntosId(VencimientoPuntos vencimientoPuntosId) {
        this.vencimientoPuntosId = vencimientoPuntosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reglaAsignacionPuntosId != null ? reglaAsignacionPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReglaAsignacionPuntos)) {
            return false;
        }
        ReglaAsignacionPuntos other = (ReglaAsignacionPuntos) object;
        if ((this.reglaAsignacionPuntosId == null && other.reglaAsignacionPuntosId != null) || (this.reglaAsignacionPuntosId != null && !this.reglaAsignacionPuntosId.equals(other.reglaAsignacionPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.ReglaAsignacionPuntos[ reglaAsignacionPuntosId=" + reglaAsignacionPuntosId + " ]";
    }
    
}
