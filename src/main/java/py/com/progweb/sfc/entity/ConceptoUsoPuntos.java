/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.com.progweb.sfc.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "concepto_uso_puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoUsoPuntos.findAll", query = "SELECT c FROM ConceptoUsoPuntos c"),
    @NamedQuery(name = "ConceptoUsoPuntos.findByConceptoUsoPuntosId", query = "SELECT c FROM ConceptoUsoPuntos c WHERE c.conceptoUsoPuntosId = :conceptoUsoPuntosId"),
    @NamedQuery(name = "ConceptoUsoPuntos.findByPuntosRequerido", query = "SELECT c FROM ConceptoUsoPuntos c WHERE c.puntosRequerido = :puntosRequerido"),
    @NamedQuery(name = "ConceptoUsoPuntos.findByDesConcepto", query = "SELECT c FROM ConceptoUsoPuntos c WHERE c.desConcepto = :desConcepto")})
public class ConceptoUsoPuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "concepto_uso_puntos_id")
    private Integer conceptoUsoPuntosId;
    @Column(name = "puntos_requerido")
    private Integer puntosRequerido;
    @Size(max = 2147483647)
    @Column(name = "des_concepto")
    private String desConcepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoUsoPuntosId", fetch = FetchType.EAGER)
    private List<CabeceraUsoPuntos> cabeceraUsoPuntosList;

    public ConceptoUsoPuntos() {
    }

    public ConceptoUsoPuntos(Integer conceptoUsoPuntosId) {
        this.conceptoUsoPuntosId = conceptoUsoPuntosId;
    }

    public Integer getConceptoUsoPuntosId() {
        return conceptoUsoPuntosId;
    }

    public void setConceptoUsoPuntosId(Integer conceptoUsoPuntosId) {
        this.conceptoUsoPuntosId = conceptoUsoPuntosId;
    }

    public Integer getPuntosRequerido() {
        return puntosRequerido;
    }

    public void setPuntosRequerido(Integer puntosRequerido) {
        this.puntosRequerido = puntosRequerido;
    }

    public String getDesConcepto() {
        return desConcepto;
    }

    public void setDesConcepto(String desConcepto) {
        this.desConcepto = desConcepto;
    }

    @XmlTransient
    public List<CabeceraUsoPuntos> getCabeceraUsoPuntosList() {
        return cabeceraUsoPuntosList;
    }

    public void setCabeceraUsoPuntosList(List<CabeceraUsoPuntos> cabeceraUsoPuntosList) {
        this.cabeceraUsoPuntosList = cabeceraUsoPuntosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptoUsoPuntosId != null ? conceptoUsoPuntosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConceptoUsoPuntos)) {
            return false;
        }
        ConceptoUsoPuntos other = (ConceptoUsoPuntos) object;
        if ((this.conceptoUsoPuntosId == null && other.conceptoUsoPuntosId != null) || (this.conceptoUsoPuntosId != null && !this.conceptoUsoPuntosId.equals(other.conceptoUsoPuntosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.progweb.sfc.entity.ConceptoUsoPuntos[ conceptoUsoPuntosId=" + conceptoUsoPuntosId + " ]";
    }
    
}
