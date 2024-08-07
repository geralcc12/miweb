/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.TipoUsuario;
import model.Propiedad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

/**
 *
 * @author USER
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Sistema_CatastralPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getPropiedadList() == null) {
            usuario.setPropiedadList(new ArrayList<Propiedad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
                usuario.setIdTipo(idTipo);
            }
            List<Propiedad> attachedPropiedadList = new ArrayList<Propiedad>();
            for (Propiedad propiedadListPropiedadToAttach : usuario.getPropiedadList()) {
                propiedadListPropiedadToAttach = em.getReference(propiedadListPropiedadToAttach.getClass(), propiedadListPropiedadToAttach.getIdPropiedad());
                attachedPropiedadList.add(propiedadListPropiedadToAttach);
            }
            usuario.setPropiedadList(attachedPropiedadList);
            em.persist(usuario);
            if (idTipo != null) {
                idTipo.getUsuarioList().add(usuario);
                idTipo = em.merge(idTipo);
            }
            for (Propiedad propiedadListPropiedad : usuario.getPropiedadList()) {
                Usuario oldPropietarioIdOfPropiedadListPropiedad = propiedadListPropiedad.getPropietarioId();
                propiedadListPropiedad.setPropietarioId(usuario);
                propiedadListPropiedad = em.merge(propiedadListPropiedad);
                if (oldPropietarioIdOfPropiedadListPropiedad != null) {
                    oldPropietarioIdOfPropiedadListPropiedad.getPropiedadList().remove(propiedadListPropiedad);
                    oldPropietarioIdOfPropiedadListPropiedad = em.merge(oldPropietarioIdOfPropiedadListPropiedad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUser());
            TipoUsuario idTipoOld = persistentUsuario.getIdTipo();
            TipoUsuario idTipoNew = usuario.getIdTipo();
            List<Propiedad> propiedadListOld = persistentUsuario.getPropiedadList();
            List<Propiedad> propiedadListNew = usuario.getPropiedadList();
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
                usuario.setIdTipo(idTipoNew);
            }
            List<Propiedad> attachedPropiedadListNew = new ArrayList<Propiedad>();
            for (Propiedad propiedadListNewPropiedadToAttach : propiedadListNew) {
                propiedadListNewPropiedadToAttach = em.getReference(propiedadListNewPropiedadToAttach.getClass(), propiedadListNewPropiedadToAttach.getIdPropiedad());
                attachedPropiedadListNew.add(propiedadListNewPropiedadToAttach);
            }
            propiedadListNew = attachedPropiedadListNew;
            usuario.setPropiedadList(propiedadListNew);
            usuario = em.merge(usuario);
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getUsuarioList().remove(usuario);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getUsuarioList().add(usuario);
                idTipoNew = em.merge(idTipoNew);
            }
            for (Propiedad propiedadListOldPropiedad : propiedadListOld) {
                if (!propiedadListNew.contains(propiedadListOldPropiedad)) {
                    propiedadListOldPropiedad.setPropietarioId(null);
                    propiedadListOldPropiedad = em.merge(propiedadListOldPropiedad);
                }
            }
            for (Propiedad propiedadListNewPropiedad : propiedadListNew) {
                if (!propiedadListOld.contains(propiedadListNewPropiedad)) {
                    Usuario oldPropietarioIdOfPropiedadListNewPropiedad = propiedadListNewPropiedad.getPropietarioId();
                    propiedadListNewPropiedad.setPropietarioId(usuario);
                    propiedadListNewPropiedad = em.merge(propiedadListNewPropiedad);
                    if (oldPropietarioIdOfPropiedadListNewPropiedad != null && !oldPropietarioIdOfPropiedadListNewPropiedad.equals(usuario)) {
                        oldPropietarioIdOfPropiedadListNewPropiedad.getPropiedadList().remove(propiedadListNewPropiedad);
                        oldPropietarioIdOfPropiedadListNewPropiedad = em.merge(oldPropietarioIdOfPropiedadListNewPropiedad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUser();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo.getUsuarioList().remove(usuario);
                idTipo = em.merge(idTipo);
            }
            List<Propiedad> propiedadList = usuario.getPropiedadList();
            for (Propiedad propiedadListPropiedad : propiedadList) {
                propiedadListPropiedad.setPropietarioId(null);
                propiedadListPropiedad = em.merge(propiedadListPropiedad);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public boolean comprobarIngreso(String nombreUsuario, String contrasena) {
    List<Usuario> listaUsuarios = findUsuarioEntities();
    boolean ingreso = false;

    for (Usuario usu : listaUsuarios) {
        if (usu.getNombreUsuario().equals(nombreUsuario) && usu.getContrasena().equals(contrasena)) {
            ingreso = true;
            break;
        }
    }

    return ingreso;
}

    
}
