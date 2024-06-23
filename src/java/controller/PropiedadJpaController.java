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
import model.Usuario;
import model.Construccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Impuesto;
import model.Propiedad;

/**
 *
 * @author User
 */
public class PropiedadJpaController implements Serializable {

    public PropiedadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaCatastroPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Propiedad propiedad) {
        if (propiedad.getConstruccionList() == null) {
            propiedad.setConstruccionList(new ArrayList<Construccion>());
        }
        if (propiedad.getImpuestoList() == null) {
            propiedad.setImpuestoList(new ArrayList<Impuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario propietarioId = propiedad.getPropietarioId();
            if (propietarioId != null) {
                propietarioId = em.getReference(propietarioId.getClass(), propietarioId.getIdUser());
                propiedad.setPropietarioId(propietarioId);
            }
            List<Construccion> attachedConstruccionList = new ArrayList<Construccion>();
            for (Construccion construccionListConstruccionToAttach : propiedad.getConstruccionList()) {
                construccionListConstruccionToAttach = em.getReference(construccionListConstruccionToAttach.getClass(), construccionListConstruccionToAttach.getIdConstruccion());
                attachedConstruccionList.add(construccionListConstruccionToAttach);
            }
            propiedad.setConstruccionList(attachedConstruccionList);
            List<Impuesto> attachedImpuestoList = new ArrayList<Impuesto>();
            for (Impuesto impuestoListImpuestoToAttach : propiedad.getImpuestoList()) {
                impuestoListImpuestoToAttach = em.getReference(impuestoListImpuestoToAttach.getClass(), impuestoListImpuestoToAttach.getIdImpuesto());
                attachedImpuestoList.add(impuestoListImpuestoToAttach);
            }
            propiedad.setImpuestoList(attachedImpuestoList);
            em.persist(propiedad);
            if (propietarioId != null) {
                propietarioId.getPropiedadList().add(propiedad);
                propietarioId = em.merge(propietarioId);
            }
            for (Construccion construccionListConstruccion : propiedad.getConstruccionList()) {
                Propiedad oldIdPropiedadOfConstruccionListConstruccion = construccionListConstruccion.getIdPropiedad();
                construccionListConstruccion.setIdPropiedad(propiedad);
                construccionListConstruccion = em.merge(construccionListConstruccion);
                if (oldIdPropiedadOfConstruccionListConstruccion != null) {
                    oldIdPropiedadOfConstruccionListConstruccion.getConstruccionList().remove(construccionListConstruccion);
                    oldIdPropiedadOfConstruccionListConstruccion = em.merge(oldIdPropiedadOfConstruccionListConstruccion);
                }
            }
            for (Impuesto impuestoListImpuesto : propiedad.getImpuestoList()) {
                Propiedad oldIdPropiedadOfImpuestoListImpuesto = impuestoListImpuesto.getIdPropiedad();
                impuestoListImpuesto.setIdPropiedad(propiedad);
                impuestoListImpuesto = em.merge(impuestoListImpuesto);
                if (oldIdPropiedadOfImpuestoListImpuesto != null) {
                    oldIdPropiedadOfImpuestoListImpuesto.getImpuestoList().remove(impuestoListImpuesto);
                    oldIdPropiedadOfImpuestoListImpuesto = em.merge(oldIdPropiedadOfImpuestoListImpuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Propiedad propiedad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Propiedad persistentPropiedad = em.find(Propiedad.class, propiedad.getIdPropiedad());
            Usuario propietarioIdOld = persistentPropiedad.getPropietarioId();
            Usuario propietarioIdNew = propiedad.getPropietarioId();
            List<Construccion> construccionListOld = persistentPropiedad.getConstruccionList();
            List<Construccion> construccionListNew = propiedad.getConstruccionList();
            List<Impuesto> impuestoListOld = persistentPropiedad.getImpuestoList();
            List<Impuesto> impuestoListNew = propiedad.getImpuestoList();
            List<String> illegalOrphanMessages = null;
            for (Construccion construccionListOldConstruccion : construccionListOld) {
                if (!construccionListNew.contains(construccionListOldConstruccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Construccion " + construccionListOldConstruccion + " since its idPropiedad field is not nullable.");
                }
            }
            for (Impuesto impuestoListOldImpuesto : impuestoListOld) {
                if (!impuestoListNew.contains(impuestoListOldImpuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Impuesto " + impuestoListOldImpuesto + " since its idPropiedad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (propietarioIdNew != null) {
                propietarioIdNew = em.getReference(propietarioIdNew.getClass(), propietarioIdNew.getIdUser());
                propiedad.setPropietarioId(propietarioIdNew);
            }
            List<Construccion> attachedConstruccionListNew = new ArrayList<Construccion>();
            for (Construccion construccionListNewConstruccionToAttach : construccionListNew) {
                construccionListNewConstruccionToAttach = em.getReference(construccionListNewConstruccionToAttach.getClass(), construccionListNewConstruccionToAttach.getIdConstruccion());
                attachedConstruccionListNew.add(construccionListNewConstruccionToAttach);
            }
            construccionListNew = attachedConstruccionListNew;
            propiedad.setConstruccionList(construccionListNew);
            List<Impuesto> attachedImpuestoListNew = new ArrayList<Impuesto>();
            for (Impuesto impuestoListNewImpuestoToAttach : impuestoListNew) {
                impuestoListNewImpuestoToAttach = em.getReference(impuestoListNewImpuestoToAttach.getClass(), impuestoListNewImpuestoToAttach.getIdImpuesto());
                attachedImpuestoListNew.add(impuestoListNewImpuestoToAttach);
            }
            impuestoListNew = attachedImpuestoListNew;
            propiedad.setImpuestoList(impuestoListNew);
            propiedad = em.merge(propiedad);
            if (propietarioIdOld != null && !propietarioIdOld.equals(propietarioIdNew)) {
                propietarioIdOld.getPropiedadList().remove(propiedad);
                propietarioIdOld = em.merge(propietarioIdOld);
            }
            if (propietarioIdNew != null && !propietarioIdNew.equals(propietarioIdOld)) {
                propietarioIdNew.getPropiedadList().add(propiedad);
                propietarioIdNew = em.merge(propietarioIdNew);
            }
            for (Construccion construccionListNewConstruccion : construccionListNew) {
                if (!construccionListOld.contains(construccionListNewConstruccion)) {
                    Propiedad oldIdPropiedadOfConstruccionListNewConstruccion = construccionListNewConstruccion.getIdPropiedad();
                    construccionListNewConstruccion.setIdPropiedad(propiedad);
                    construccionListNewConstruccion = em.merge(construccionListNewConstruccion);
                    if (oldIdPropiedadOfConstruccionListNewConstruccion != null && !oldIdPropiedadOfConstruccionListNewConstruccion.equals(propiedad)) {
                        oldIdPropiedadOfConstruccionListNewConstruccion.getConstruccionList().remove(construccionListNewConstruccion);
                        oldIdPropiedadOfConstruccionListNewConstruccion = em.merge(oldIdPropiedadOfConstruccionListNewConstruccion);
                    }
                }
            }
            for (Impuesto impuestoListNewImpuesto : impuestoListNew) {
                if (!impuestoListOld.contains(impuestoListNewImpuesto)) {
                    Propiedad oldIdPropiedadOfImpuestoListNewImpuesto = impuestoListNewImpuesto.getIdPropiedad();
                    impuestoListNewImpuesto.setIdPropiedad(propiedad);
                    impuestoListNewImpuesto = em.merge(impuestoListNewImpuesto);
                    if (oldIdPropiedadOfImpuestoListNewImpuesto != null && !oldIdPropiedadOfImpuestoListNewImpuesto.equals(propiedad)) {
                        oldIdPropiedadOfImpuestoListNewImpuesto.getImpuestoList().remove(impuestoListNewImpuesto);
                        oldIdPropiedadOfImpuestoListNewImpuesto = em.merge(oldIdPropiedadOfImpuestoListNewImpuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = propiedad.getIdPropiedad();
                if (findPropiedad(id) == null) {
                    throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.");
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
            Propiedad propiedad;
            try {
                propiedad = em.getReference(Propiedad.class, id);
                propiedad.getIdPropiedad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The propiedad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Construccion> construccionListOrphanCheck = propiedad.getConstruccionList();
            for (Construccion construccionListOrphanCheckConstruccion : construccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propiedad (" + propiedad + ") cannot be destroyed since the Construccion " + construccionListOrphanCheckConstruccion + " in its construccionList field has a non-nullable idPropiedad field.");
            }
            List<Impuesto> impuestoListOrphanCheck = propiedad.getImpuestoList();
            for (Impuesto impuestoListOrphanCheckImpuesto : impuestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Propiedad (" + propiedad + ") cannot be destroyed since the Impuesto " + impuestoListOrphanCheckImpuesto + " in its impuestoList field has a non-nullable idPropiedad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario propietarioId = propiedad.getPropietarioId();
            if (propietarioId != null) {
                propietarioId.getPropiedadList().remove(propiedad);
                propietarioId = em.merge(propietarioId);
            }
            em.remove(propiedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Propiedad> findPropiedadEntities() {
        return findPropiedadEntities(true, -1, -1);
    }

    public List<Propiedad> findPropiedadEntities(int maxResults, int firstResult) {
        return findPropiedadEntities(false, maxResults, firstResult);
    }

    private List<Propiedad> findPropiedadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Propiedad.class));
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

    public Propiedad findPropiedad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Propiedad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPropiedadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Propiedad> rt = cq.from(Propiedad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
