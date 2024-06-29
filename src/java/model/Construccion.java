/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "construccion")
@NamedQueries({
    @NamedQuery(name = "Construccion.findAll", query = "SELECT c FROM Construccion c"),
    @NamedQuery(name = "Construccion.findByIdConstruccion", query = "SELECT c FROM Construccion c WHERE c.idConstruccion = :idConstruccion"),
    @NamedQuery(name = "Construccion.findByArea", query = "SELECT c FROM Construccion c WHERE c.area = :area"),
    @NamedQuery(name = "Construccion.findByPisos", query = "SELECT c FROM Construccion c WHERE c.pisos = :pisos"),
    @NamedQuery(name = "Construccion.findByMaterial", query = "SELECT c FROM Construccion c WHERE c.material = :material"),
    @NamedQuery(name = "Construccion.findByAnioConstruccion", query = "SELECT c FROM Construccion c WHERE c.anioConstruccion = :anioConstruccion")})
public class Construccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_construccion")
    private Integer idConstruccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "area")
    private BigDecimal area;
    @Basic(optional = false)
    @Column(name = "pisos")
    private int pisos;
    @Basic(optional = false)
    @Column(name = "material")
    private String material;
    @Basic(optional = false)
    @Column(name = "anio_construccion")
    @Temporal(TemporalType.DATE)
    private Date anioConstruccion;
    @JoinColumn(name = "id_propiedad", referencedColumnName = "id_propiedad")
    @ManyToOne(optional = false)
    private Propiedad idPropiedad;
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    @ManyToOne(optional = false)
    private TipoConstruccion idTipo;

    public Construccion() {
    }

    public Construccion(Integer idConstruccion) {
        this.idConstruccion = idConstruccion;
    }

    public Construccion(Integer idConstruccion, BigDecimal area, int pisos, String material, Date anioConstruccion) {
        this.idConstruccion = idConstruccion;
        this.area = area;
        this.pisos = pisos;
        this.material = material;
        this.anioConstruccion = anioConstruccion;
    }

    public Integer getIdConstruccion() {
        return idConstruccion;
    }

    public void setIdConstruccion(Integer idConstruccion) {
        this.idConstruccion = idConstruccion;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public int getPisos() {
        return pisos;
    }

    public void setPisos(int pisos) {
        this.pisos = pisos;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Date getAnioConstruccion() {
        return anioConstruccion;
    }

    public void setAnioConstruccion(Date anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }

    public Propiedad getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Propiedad idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public TipoConstruccion getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoConstruccion idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstruccion != null ? idConstruccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Construccion)) {
            return false;
        }
        Construccion other = (Construccion) object;
        if ((this.idConstruccion == null && other.idConstruccion != null) || (this.idConstruccion != null && !this.idConstruccion.equals(other.idConstruccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Construccion[ idConstruccion=" + idConstruccion + " ]";
    }
    
}
