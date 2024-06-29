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
@Table(name = "impuesto")
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i"),
    @NamedQuery(name = "Impuesto.findByIdImpuesto", query = "SELECT i FROM Impuesto i WHERE i.idImpuesto = :idImpuesto"),
    @NamedQuery(name = "Impuesto.findByAnio", query = "SELECT i FROM Impuesto i WHERE i.anio = :anio"),
    @NamedQuery(name = "Impuesto.findByMonto", query = "SELECT i FROM Impuesto i WHERE i.monto = :monto"),
    @NamedQuery(name = "Impuesto.findByPagado", query = "SELECT i FROM Impuesto i WHERE i.pagado = :pagado"),
    @NamedQuery(name = "Impuesto.findByFechaPago", query = "SELECT i FROM Impuesto i WHERE i.fechaPago = :fechaPago")})
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_impuesto")
    private Integer idImpuesto;
    @Basic(optional = false)
    @Column(name = "anio")
    @Temporal(TemporalType.DATE)
    private Date anio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "pagado")
    private Boolean pagado;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @JoinColumn(name = "id_propiedad", referencedColumnName = "id_propiedad")
    @ManyToOne(optional = false)
    private Propiedad idPropiedad;

    public Impuesto() {
    }

    public Impuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Impuesto(Integer idImpuesto, Date anio, BigDecimal monto) {
        this.idImpuesto = idImpuesto;
        this.anio = anio;
        this.monto = monto;
    }

    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Propiedad getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Propiedad idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpuesto != null ? idImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idImpuesto == null && other.idImpuesto != null) || (this.idImpuesto != null && !this.idImpuesto.equals(other.idImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Impuesto[ idImpuesto=" + idImpuesto + " ]";
    }
    
}
