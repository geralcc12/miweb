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
import model.Impuesto;
import model.Propiedad;

/**
 *
 * @author USER
 */
public class ImpuestoJpaController implements Serializable {

    public ImpuestoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Sistema_CatastralPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Impuesto impuesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad idPropiedad = impuesto.getIdPropiedad();
            if (idPropiedad != null) {
                idPropiedad = em.getReference(idPropiedad.getClass(), idPropiedad.getIdPropiedad());
                impuesto.setIdPropiedad(idPropiedad);
            }
            em.persist(impuesto);
            if (idPropiedad != null) {
                idPropiedad.getImpuestoList().add(impuesto);
                idPropiedad = em.merge(idPropiedad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Impuesto impuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Impuesto persistentImpuesto = em.find(Impuesto.class, impuesto.getIdImpuesto());
            Propiedad idPropiedadOld = persistentImpuesto.getIdPropiedad();
            Propiedad idPropiedadNew = impuesto.getIdPropiedad();
            if (idPropiedadNew != null) {
                idPropiedadNew = em.getReference(idPropiedadNew.getClass(), idPropiedadNew.getIdPropiedad());
                impuesto.setIdPropiedad(idPropiedadNew);
            }
            impuesto = em.merge(impuesto);
            if (idPropiedadOld != null && !idPropiedadOld.equals(idPropiedadNew)) {
                idPropiedadOld.getImpuestoList().remove(impuesto);
                idPropiedadOld = em.merge(idPropiedadOld);
            }
            if (idPropiedadNew != null && !idPropiedadNew.equals(idPropiedadOld)) {
                idPropiedadNew.getImpuestoList().add(impuesto);
                idPropiedadNew = em.merge(idPropiedadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = impuesto.getIdImpuesto();
                if (findImpuesto(id) == null) {
                    throw new NonexistentEntityException("The impuesto with id " + id + " no longer exists.");
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
            Impuesto impuesto;
            try {
                impuesto = em.getReference(Impuesto.class, id);
                impuesto.getIdImpuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The impuesto with id " + id + " no longer exists.", enfe);
            }
            Propiedad idPropiedad = impuesto.getIdPropiedad();
            if (idPropiedad != null) {
                idPropiedad.getImpuestoList().remove(impuesto);
                idPropiedad = em.merge(idPropiedad);
            }
            em.remove(impuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Impuesto> findImpuestoEntities() {
        return findImpuestoEntities(true, -1, -1);
    }

    public List<Impuesto> findImpuestoEntities(int maxResults, int firstResult) {
        return findImpuestoEntities(false, maxResults, firstResult);
    }

    private List<Impuesto> findImpuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Impuesto.class));
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

    public Impuesto findImpuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Impuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getImpuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Impuesto> rt = cq.from(Impuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
