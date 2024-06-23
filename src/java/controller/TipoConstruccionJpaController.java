/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Construccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.TipoConstruccion;

/**
 *
 * @author User
 */
public class TipoConstruccionJpaController implements Serializable {

    public TipoConstruccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaCatastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoConstruccion tipoConstruccion) {
        if (tipoConstruccion.getConstruccionList() == null) {
            tipoConstruccion.setConstruccionList(new ArrayList<Construccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Construccion> attachedConstruccionList = new ArrayList<Construccion>();
            for (Construccion construccionListConstruccionToAttach : tipoConstruccion.getConstruccionList()) {
                construccionListConstruccionToAttach = em.getReference(construccionListConstruccionToAttach.getClass(), construccionListConstruccionToAttach.getIdConstruccion());
                attachedConstruccionList.add(construccionListConstruccionToAttach);
            }
            tipoConstruccion.setConstruccionList(attachedConstruccionList);
            em.persist(tipoConstruccion);
            for (Construccion construccionListConstruccion : tipoConstruccion.getConstruccionList()) {
                TipoConstruccion oldIdTipoOfConstruccionListConstruccion = construccionListConstruccion.getIdTipo();
                construccionListConstruccion.setIdTipo(tipoConstruccion);
                construccionListConstruccion = em.merge(construccionListConstruccion);
                if (oldIdTipoOfConstruccionListConstruccion != null) {
                    oldIdTipoOfConstruccionListConstruccion.getConstruccionList().remove(construccionListConstruccion);
                    oldIdTipoOfConstruccionListConstruccion = em.merge(oldIdTipoOfConstruccionListConstruccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoConstruccion tipoConstruccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoConstruccion persistentTipoConstruccion = em.find(TipoConstruccion.class, tipoConstruccion.getIdTipo());
            List<Construccion> construccionListOld = persistentTipoConstruccion.getConstruccionList();
            List<Construccion> construccionListNew = tipoConstruccion.getConstruccionList();
            List<String> illegalOrphanMessages = null;
            for (Construccion construccionListOldConstruccion : construccionListOld) {
                if (!construccionListNew.contains(construccionListOldConstruccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Construccion " + construccionListOldConstruccion + " since its idTipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Construccion> attachedConstruccionListNew = new ArrayList<Construccion>();
            for (Construccion construccionListNewConstruccionToAttach : construccionListNew) {
                construccionListNewConstruccionToAttach = em.getReference(construccionListNewConstruccionToAttach.getClass(), construccionListNewConstruccionToAttach.getIdConstruccion());
                attachedConstruccionListNew.add(construccionListNewConstruccionToAttach);
            }
            construccionListNew = attachedConstruccionListNew;
            tipoConstruccion.setConstruccionList(construccionListNew);
            tipoConstruccion = em.merge(tipoConstruccion);
            for (Construccion construccionListNewConstruccion : construccionListNew) {
                if (!construccionListOld.contains(construccionListNewConstruccion)) {
                    TipoConstruccion oldIdTipoOfConstruccionListNewConstruccion = construccionListNewConstruccion.getIdTipo();
                    construccionListNewConstruccion.setIdTipo(tipoConstruccion);
                    construccionListNewConstruccion = em.merge(construccionListNewConstruccion);
                    if (oldIdTipoOfConstruccionListNewConstruccion != null && !oldIdTipoOfConstruccionListNewConstruccion.equals(tipoConstruccion)) {
                        oldIdTipoOfConstruccionListNewConstruccion.getConstruccionList().remove(construccionListNewConstruccion);
                        oldIdTipoOfConstruccionListNewConstruccion = em.merge(oldIdTipoOfConstruccionListNewConstruccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoConstruccion.getIdTipo();
                if (findTipoConstruccion(id) == null) {
                    throw new NonexistentEntityException("The tipoConstruccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoConstruccion tipoConstruccion;
            try {
                tipoConstruccion = em.getReference(TipoConstruccion.class, id);
                tipoConstruccion.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoConstruccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Construccion> construccionListOrphanCheck = tipoConstruccion.getConstruccionList();
            for (Construccion construccionListOrphanCheckConstruccion : construccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoConstruccion (" + tipoConstruccion + ") cannot be destroyed since the Construccion " + construccionListOrphanCheckConstruccion + " in its construccionList field has a non-nullable idTipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoConstruccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoConstruccion> findTipoConstruccionEntities() {
        return findTipoConstruccionEntities(true, -1, -1);
    }

    public List<TipoConstruccion> findTipoConstruccionEntities(int maxResults, int firstResult) {
        return findTipoConstruccionEntities(false, maxResults, firstResult);
    }

    private List<TipoConstruccion> findTipoConstruccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoConstruccion.class));
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

    public TipoConstruccion findTipoConstruccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoConstruccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoConstruccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoConstruccion> rt = cq.from(TipoConstruccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
