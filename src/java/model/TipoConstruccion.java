/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "tipo_construccion")
@NamedQueries({
    @NamedQuery(name = "TipoConstruccion.findAll", query = "SELECT t FROM TipoConstruccion t"),
    @NamedQuery(name = "TipoConstruccion.findByIdTipo", query = "SELECT t FROM TipoConstruccion t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoConstruccion.findByNombre", query = "SELECT t FROM TipoConstruccion t WHERE t.nombre = :nombre")})
public class TipoConstruccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private List<Construccion> construccionList;

    public TipoConstruccion() {
    }

    public TipoConstruccion(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TipoConstruccion(Integer idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Construccion> getConstruccionList() {
        return construccionList;
    }

    public void setConstruccionList(List<Construccion> construccionList) {
        this.construccionList = construccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConstruccion)) {
            return false;
        }
        TipoConstruccion other = (TipoConstruccion) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoConstruccion[ idTipo=" + idTipo + " ]";
    }
    
}
