package com.mycompany.gimnasiotesting.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gimnasiotesting.logica.Membresia;
import com.mycompany.gimnasiotesting.logica.Pago;
import com.mycompany.gimnasiotesting.logica.Usuario;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PagoJpaController() {
        emf = Persistence.createEntityManagerFactory("GimnasioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membresia pago_membresia = pago.getPago_membresia();
            if (pago_membresia != null) {
                pago_membresia = em.getReference(pago_membresia.getClass(), pago_membresia.getId());
                pago.setPago_membresia(pago_membresia);
            }
            Usuario pago_usuario = pago.getPago_usuario();
            if (pago_usuario != null) {
                pago_usuario = em.getReference(pago_usuario.getClass(), pago_usuario.getId());
                pago.setPago_usuario(pago_usuario);
            }
            em.persist(pago);
            if (pago_membresia != null) {
                pago_membresia.getMembresia_pagos().add(pago);
                pago_membresia = em.merge(pago_membresia);
            }
            if (pago_usuario != null) {
                pago_usuario.getUsuario_pagos().add(pago);
                pago_usuario = em.merge(pago_usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getId());
            Membresia pago_membresiaOld = persistentPago.getPago_membresia();
            Membresia pago_membresiaNew = pago.getPago_membresia();
            Usuario pago_usuarioOld = persistentPago.getPago_usuario();
            Usuario pago_usuarioNew = pago.getPago_usuario();
            if (pago_membresiaNew != null) {
                pago_membresiaNew = em.getReference(pago_membresiaNew.getClass(), pago_membresiaNew.getId());
                pago.setPago_membresia(pago_membresiaNew);
            }
            if (pago_usuarioNew != null) {
                pago_usuarioNew = em.getReference(pago_usuarioNew.getClass(), pago_usuarioNew.getId());
                pago.setPago_usuario(pago_usuarioNew);
            }
            pago = em.merge(pago);
            if (pago_membresiaOld != null && !pago_membresiaOld.equals(pago_membresiaNew)) {
                pago_membresiaOld.getMembresia_pagos().remove(pago);
                pago_membresiaOld = em.merge(pago_membresiaOld);
            }
            if (pago_membresiaNew != null && !pago_membresiaNew.equals(pago_membresiaOld)) {
                pago_membresiaNew.getMembresia_pagos().add(pago);
                pago_membresiaNew = em.merge(pago_membresiaNew);
            }
            if (pago_usuarioOld != null && !pago_usuarioOld.equals(pago_usuarioNew)) {
                pago_usuarioOld.getUsuario_pagos().remove(pago);
                pago_usuarioOld = em.merge(pago_usuarioOld);
            }
            if (pago_usuarioNew != null && !pago_usuarioNew.equals(pago_usuarioOld)) {
                pago_usuarioNew.getUsuario_pagos().add(pago);
                pago_usuarioNew = em.merge(pago_usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pago.getId();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Membresia pago_membresia = pago.getPago_membresia();
            if (pago_membresia != null) {
                pago_membresia.getMembresia_pagos().remove(pago);
                pago_membresia = em.merge(pago_membresia);
            }
            Usuario pago_usuario = pago.getPago_usuario();
            if (pago_usuario != null) {
                pago_usuario.getUsuario_pagos().remove(pago);
                pago_usuario = em.merge(pago_usuario);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
