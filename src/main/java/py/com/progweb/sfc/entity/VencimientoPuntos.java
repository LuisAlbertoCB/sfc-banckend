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
@Table(name = "vencimiento_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VencimientoPuntos.findAll", query = "SELECT v FROM VencimientoPuntos v"),
    @NamedQuery(name = "VencimientoPuntos.findByVencimientoPuntosId", query = "SELECT v FROM VencimientoPuntos v WHERE v.vencimientoPuntosId = :vencimientoPuntosId"),
    @NamedQuery(name = "VencimientoPuntos.findByFechaFinValidez", query = "SELECT v FROM VencimientoPuntos v WHERE v.fechaFinValidez = :fechaFinValidez"),
    @NamedQuery(name = "VencimientoPuntos.findByFechaIniValidez", query = "SELECT v FROM VencimientoPuntos v WHERE v.fechaIniValidez = :fechaIniValidez"),
    @NamedQuery(name = "VencimientoPuntos.findByDuracionPuntaje", query = "SELECT v FROM VencimientoPuntos v WHERE v.duracionPuntaje = :duracionPuntaje")})
public class VencimientoPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vencimiento_puntos_id")
    private Integer vencimientoPuntosId;
    @Column(name = "fecha_fin_validez")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFinValidez;
    @Column(name = "fecha_ini_validez")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaIniValidez;
    @Column(name = "duracion_puntaje")
    private Integer duracionPuntaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vencimientoPuntosId", fetch = FetchType.EAGER)
    private List<ReglaAsignacionPuntos> reglaAsignacionPuntosList;

    public VencimientoPuntos() {
    }

    public VencimientoPuntos(Integer vencimientoPuntosId) {
        this.vencimientoPuntosId = vencimientoPuntosId;
    }

    public Integer getVencimientoPuntosId() {
        return vencimientoPuntosId;
    }

    public void setVencimientoPuntosId(Integer vencimientoPuntosId) {
        this.vencimientoPuntosId = vencimientoPuntosId;
    }

    public Date getFechaFinValidez() {
        return fechaFinValidez;
    }

    public void setFechaFinValidez(Date fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }

    public Date getFechaIniValidez() {
        return fechaIniValidez;
    }

    public void setFechaIniValidez(Date fechaIniValidez) {
        this.fechaIniValidez = fechaIniValidez;
    }

    public Integer getDuracionPuntaje() {
        return duracionPuntaje;
    }

    public void setDuracionPuntaje(Integer duracionPuntaje) {
        this.duracionPuntaje = duracionPuntaje;
    }

    @XmlTransient
    public List<ReglaAsignacionPuntos> getReglaAsignacionPuntosList() {
        return reglaAsignacionPuntosList;
    }

    public void setReglaAsignacionPuntosList(List<ReglaAsignacionPuntos> reglaAsignacionPuntosList) {
        this.reglaAsignacionPuntosList = reglaAsignacionPuntosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vencimientoPuntosId != null ? vencimientoPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VencimientoPuntos)) {
            return false;
        }
        VencimientoPuntos other = (VencimientoPuntos) object;
        if ((this.vencimientoPuntosId == null && other.vencimientoPuntosId != null) || (this.vencimientoPuntosId != null && !this.vencimientoPuntosId.equals(other.vencimientoPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.VencimientoPuntos[ vencimientoPuntosId=" + vencimientoPuntosId + " ]";
    }
    
}
