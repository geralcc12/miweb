/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Construccion;
import model.Propiedad;
import model.TipoConstruccion;

/**
 *
 * @author User
 */
public class ConstruccionJpaController implements Serializable {

    public ConstruccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaCatastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Construccion construccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad idPropiedad = construccion.getIdPropiedad();
            if (idPropiedad != null) {
                idPropiedad = em.getReference(idPropiedad.getClass(), idPropiedad.getIdPropiedad());
                construccion.setIdPropiedad(idPropiedad);
            }
            TipoConstruccion idTipo = construccion.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
                construccion.setIdTipo(idTipo);
            }
            em.persist(construccion);
            if (idPropiedad != null) {
                idPropiedad.getConstruccionList().add(construccion);
                idPropiedad = em.merge(idPropiedad);
            }
            if (idTipo != null) {
                idTipo.getConstruccionList().add(construccion);
                idTipo = em.merge(idTipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Construccion construccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Construccion persistentConstruccion = em.find(Construccion.class, construccion.getIdConstruccion());
            Propiedad idPropiedadOld = persistentConstruccion.getIdPropiedad();
            Propiedad idPropiedadNew = construccion.getIdPropiedad();
            TipoConstruccion idTipoOld = persistentConstruccion.getIdTipo();
            TipoConstruccion idTipoNew = construccion.getIdTipo();
            if (idPropiedadNew != null) {
                idPropiedadNew = em.getReference(idPropiedadNew.getClass(), idPropiedadNew.getIdPropiedad());
                construccion.setIdPropiedad(idPropiedadNew);
            }
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
                construccion.setIdTipo(idTipoNew);
            }
            construccion = em.merge(construccion);
            if (idPropiedadOld != null && !idPropiedadOld.equals(idPropiedadNew)) {
                idPropiedadOld.getConstruccionList().remove(construccion);
                idPropiedadOld = em.merge(idPropiedadOld);
            }
            if (idPropiedadNew != null && !idPropiedadNew.equals(idPropiedadOld)) {
                idPropiedadNew.getConstruccionList().add(construccion);
                idPropiedadNew = em.merge(idPropiedadNew);
            }
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getConstruccionList().remove(construccion);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getConstruccionList().add(construccion);
                idTipoNew = em.merge(idTipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = construccion.getIdConstruccion();
                if (findConstruccion(id) == null) {
                    throw new NonexistentEntityException("The construccion with id " + id + " no longer exists.");
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
            Construccion construccion;
            try {
                construccion = em.getReference(Construccion.class, id);
                construccion.getIdConstruccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The construccion with id " + id + " no longer exists.", enfe);
            }
            Propiedad idPropiedad = construccion.getIdPropiedad();
            if (idPropiedad != null) {
                idPropiedad.getConstruccionList().remove(construccion);
                idPropiedad = em.merge(idPropiedad);
            }
            TipoConstruccion idTipo = construccion.getIdTipo();
            if (idTipo != null) {
                idTipo.getConstruccionList().remove(construccion);
                idTipo = em.merge(idTipo);
            }
            em.remove(construccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Construccion> findConstruccionEntities() {
        return findConstruccionEntities(true, -1, -1);
    }

    public List<Construccion> findConstruccionEntities(int maxResults, int firstResult) {
        return findConstruccionEntities(false, maxResults, firstResult);
    }

    private List<Construccion> findConstruccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Construccion.class));
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

    public Construccion findConstruccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Construccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstruccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Construccion> rt = cq.from(Construccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
