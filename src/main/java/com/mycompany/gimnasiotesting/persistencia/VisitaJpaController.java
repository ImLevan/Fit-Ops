package com.mycompany.gimnasiotesting.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gimnasiotesting.logica.Miembro;
import com.mycompany.gimnasiotesting.logica.Usuario;
import com.mycompany.gimnasiotesting.logica.Membresia;
import com.mycompany.gimnasiotesting.logica.Visita;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VisitaJpaController implements Serializable {

    public VisitaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public VisitaJpaController() {
        emf = Persistence.createEntityManagerFactory("GimnasioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Visita visita) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Miembro visita_miembro = visita.getVisita_miembro();
            if (visita_miembro != null) {
                visita_miembro = em.getReference(visita_miembro.getClass(), visita_miembro.getId());
                visita.setVisita_miembro(visita_miembro);
            }
            Usuario visita_usuario = visita.getVisita_usuario();
            if (visita_usuario != null) {
                visita_usuario = em.getReference(visita_usuario.getClass(), visita_usuario.getId());
                visita.setVisita_usuario(visita_usuario);
            }
            Membresia visita_membresia = visita.getVisita_membresia();
            if (visita_membresia != null) {
                visita_membresia = em.getReference(visita_membresia.getClass(), visita_membresia.getId());
                visita.setVisita_membresia(visita_membresia);
            }
            em.persist(visita);
            if (visita_miembro != null) {
                visita_miembro.getMiembro_visitas().add(visita);
                visita_miembro = em.merge(visita_miembro);
            }
            if (visita_usuario != null) {
                visita_usuario.getUsuario_visitas().add(visita);
                visita_usuario = em.merge(visita_usuario);
            }
            if (visita_membresia != null) {
                visita_membresia.getMembresia_visitas().add(visita);
                visita_membresia = em.merge(visita_membresia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Visita visita) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Visita persistentVisita = em.find(Visita.class, visita.getId());
            Miembro visita_miembroOld = persistentVisita.getVisita_miembro();
            Miembro visita_miembroNew = visita.getVisita_miembro();
            Usuario visita_usuarioOld = persistentVisita.getVisita_usuario();
            Usuario visita_usuarioNew = visita.getVisita_usuario();
            Membresia visita_membresiaOld = persistentVisita.getVisita_membresia();
            Membresia visita_membresiaNew = visita.getVisita_membresia();
            if (visita_miembroNew != null) {
                visita_miembroNew = em.getReference(visita_miembroNew.getClass(), visita_miembroNew.getId());
                visita.setVisita_miembro(visita_miembroNew);
            }
            if (visita_usuarioNew != null) {
                visita_usuarioNew = em.getReference(visita_usuarioNew.getClass(), visita_usuarioNew.getId());
                visita.setVisita_usuario(visita_usuarioNew);
            }
            if (visita_membresiaNew != null) {
                visita_membresiaNew = em.getReference(visita_membresiaNew.getClass(), visita_membresiaNew.getId());
                visita.setVisita_membresia(visita_membresiaNew);
            }
            visita = em.merge(visita);
            if (visita_miembroOld != null && !visita_miembroOld.equals(visita_miembroNew)) {
                visita_miembroOld.getMiembro_visitas().remove(visita);
                visita_miembroOld = em.merge(visita_miembroOld);
            }
            if (visita_miembroNew != null && !visita_miembroNew.equals(visita_miembroOld)) {
                visita_miembroNew.getMiembro_visitas().add(visita);
                visita_miembroNew = em.merge(visita_miembroNew);
            }
            if (visita_usuarioOld != null && !visita_usuarioOld.equals(visita_usuarioNew)) {
                visita_usuarioOld.getUsuario_visitas().remove(visita);
                visita_usuarioOld = em.merge(visita_usuarioOld);
            }
            if (visita_usuarioNew != null && !visita_usuarioNew.equals(visita_usuarioOld)) {
                visita_usuarioNew.getUsuario_visitas().add(visita);
                visita_usuarioNew = em.merge(visita_usuarioNew);
            }
            if (visita_membresiaOld != null && !visita_membresiaOld.equals(visita_membresiaNew)) {
                visita_membresiaOld.getMembresia_visitas().remove(visita);
                visita_membresiaOld = em.merge(visita_membresiaOld);
            }
            if (visita_membresiaNew != null && !visita_membresiaNew.equals(visita_membresiaOld)) {
                visita_membresiaNew.getMembresia_visitas().add(visita);
                visita_membresiaNew = em.merge(visita_membresiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = visita.getId();
                if (findVisita(id) == null) {
                    throw new NonexistentEntityException("The visita with id " + id + " no longer exists.");
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
            Visita visita;
            try {
                visita = em.getReference(Visita.class, id);
                visita.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The visita with id " + id + " no longer exists.", enfe);
            }
            Miembro visita_miembro = visita.getVisita_miembro();
            if (visita_miembro != null) {
                visita_miembro.getMiembro_visitas().remove(visita);
                visita_miembro = em.merge(visita_miembro);
            }
            Usuario visita_usuario = visita.getVisita_usuario();
            if (visita_usuario != null) {
                visita_usuario.getUsuario_visitas().remove(visita);
                visita_usuario = em.merge(visita_usuario);
            }
            Membresia visita_membresia = visita.getVisita_membresia();
            if (visita_membresia != null) {
                visita_membresia.getMembresia_visitas().remove(visita);
                visita_membresia = em.merge(visita_membresia);
            }
            em.remove(visita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Visita> findVisitaEntities() {
        return findVisitaEntities(true, -1, -1);
    }

    public List<Visita> findVisitaEntities(int maxResults, int firstResult) {
        return findVisitaEntities(false, maxResults, firstResult);
    }

    private List<Visita> findVisitaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Visita.class));
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

    public Visita findVisita(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Visita.class, id);
        } finally {
            em.close();
        }
    }

    public int getVisitaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Visita> rt = cq.from(Visita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
