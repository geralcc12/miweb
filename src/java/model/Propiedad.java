/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "propiedad")
@NamedQueries({
    @NamedQuery(name = "Propiedad.findAll", query = "SELECT p FROM Propiedad p"),
    @NamedQuery(name = "Propiedad.findByIdPropiedad", query = "SELECT p FROM Propiedad p WHERE p.idPropiedad = :idPropiedad"),
    @NamedQuery(name = "Propiedad.findByCodigo", query = "SELECT p FROM Propiedad p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Propiedad.findByDireccion", query = "SELECT p FROM Propiedad p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Propiedad.findByCiudad", query = "SELECT p FROM Propiedad p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Propiedad.findByProvincia", query = "SELECT p FROM Propiedad p WHERE p.provincia = :provincia"),
    @NamedQuery(name = "Propiedad.findByPais", query = "SELECT p FROM Propiedad p WHERE p.pais = :pais"),
    @NamedQuery(name = "Propiedad.findByValor", query = "SELECT p FROM Propiedad p WHERE p.valor = :valor")})
public class Propiedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_propiedad")
    private Integer idPropiedad;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "provincia")
    private String provincia;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropiedad")
    private List<Construccion> construccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPropiedad")
    private List<Impuesto> impuestoList;
    @JoinColumn(name = "propietario_id", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Usuario propietarioId;

    public Propiedad() {
    }

    public Propiedad(Integer idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Propiedad(Integer idPropiedad, String codigo, String direccion, String ciudad, String provincia, String pais, BigDecimal valor) {
        this.idPropiedad = idPropiedad;
        this.codigo = codigo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.valor = valor;
    }

    public Integer getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Integer idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Construccion> getConstruccionList() {
        return construccionList;
    }

    public void setConstruccionList(List<Construccion> construccionList) {
        this.construccionList = construccionList;
    }

    public List<Impuesto> getImpuestoList() {
        return impuestoList;
    }

    public void setImpuestoList(List<Impuesto> impuestoList) {
        this.impuestoList = impuestoList;
    }

    public Usuario getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Usuario propietarioId) {
        this.propietarioId = propietarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropiedad != null ? idPropiedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propiedad)) {
            return false;
        }
        Propiedad other = (Propiedad) object;
        if ((this.idPropiedad == null && other.idPropiedad != null) || (this.idPropiedad != null && !this.idPropiedad.equals(other.idPropiedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Propiedad[ idPropiedad=" + idPropiedad + " ]";
    }
    
}
