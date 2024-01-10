package com.mycompany.gimnasiotesting.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gimnasiotesting.logica.Membresia;
import com.mycompany.gimnasiotesting.logica.Miembro;
import com.mycompany.gimnasiotesting.logica.Visita;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MiembroJpaController implements Serializable {

    public MiembroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public MiembroJpaController() {
        emf = Persistence.createEntityManagerFactory("GimnasioPU");
    }    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Miembro miembro) {
        if (miembro.getMiembro_visitas() == null) {
            miembro.setMiembro_visitas(new ArrayList<Visita>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membresia miembro_membresia = miembro.getMiembro_membresia();
            if (miembro_membresia != null) {
                miembro_membresia = em.getReference(miembro_membresia.getClass(), miembro_membresia.getId());
                miembro.setMiembro_membresia(miembro_membresia);
            }
            List<Visita> attachedMiembro_visitas = new ArrayList<Visita>();
            for (Visita miembro_visitasVisitaToAttach : miembro.getMiembro_visitas()) {
                miembro_visitasVisitaToAttach = em.getReference(miembro_visitasVisitaToAttach.getClass(), miembro_visitasVisitaToAttach.getId());
                attachedMiembro_visitas.add(miembro_visitasVisitaToAttach);
            }
            miembro.setMiembro_visitas(attachedMiembro_visitas);
            em.persist(miembro);
            if (miembro_membresia != null) {
                miembro_membresia.getMembresia_miembros().add(miembro);
                miembro_membresia = em.merge(miembro_membresia);
            }
            for (Visita miembro_visitasVisita : miembro.getMiembro_visitas()) {
                Miembro oldVisita_miembroOfMiembro_visitasVisita = miembro_visitasVisita.getVisita_miembro();
                miembro_visitasVisita.setVisita_miembro(miembro);
                miembro_visitasVisita = em.merge(miembro_visitasVisita);
                if (oldVisita_miembroOfMiembro_visitasVisita != null) {
                    oldVisita_miembroOfMiembro_visitasVisita.getMiembro_visitas().remove(miembro_visitasVisita);
                    oldVisita_miembroOfMiembro_visitasVisita = em.merge(oldVisita_miembroOfMiembro_visitasVisita);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Miembro miembro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Miembro persistentMiembro = em.find(Miembro.class, miembro.getId());
            Membresia miembro_membresiaOld = persistentMiembro.getMiembro_membresia();
            Membresia miembro_membresiaNew = miembro.getMiembro_membresia();
            List<Visita> miembro_visitasOld = persistentMiembro.getMiembro_visitas();
            List<Visita> miembro_visitasNew = miembro.getMiembro_visitas();
            if (miembro_membresiaNew != null) {
                miembro_membresiaNew = em.getReference(miembro_membresiaNew.getClass(), miembro_membresiaNew.getId());
                miembro.setMiembro_membresia(miembro_membresiaNew);
            }
            List<Visita> attachedMiembro_visitasNew = new ArrayList<Visita>();
            for (Visita miembro_visitasNewVisitaToAttach : miembro_visitasNew) {
                miembro_visitasNewVisitaToAttach = em.getReference(miembro_visitasNewVisitaToAttach.getClass(), miembro_visitasNewVisitaToAttach.getId());
                attachedMiembro_visitasNew.add(miembro_visitasNewVisitaToAttach);
            }
            miembro_visitasNew = attachedMiembro_visitasNew;
            miembro.setMiembro_visitas(miembro_visitasNew);
            miembro = em.merge(miembro);
            if (miembro_membresiaOld != null && !miembro_membresiaOld.equals(miembro_membresiaNew)) {
                miembro_membresiaOld.getMembresia_miembros().remove(miembro);
                miembro_membresiaOld = em.merge(miembro_membresiaOld);
            }
            if (miembro_membresiaNew != null && !miembro_membresiaNew.equals(miembro_membresiaOld)) {
                miembro_membresiaNew.getMembresia_miembros().add(miembro);
                miembro_membresiaNew = em.merge(miembro_membresiaNew);
            }
            for (Visita miembro_visitasOldVisita : miembro_visitasOld) {
                if (!miembro_visitasNew.contains(miembro_visitasOldVisita)) {
                    miembro_visitasOldVisita.setVisita_miembro(null);
                    miembro_visitasOldVisita = em.merge(miembro_visitasOldVisita);
                }
            }
            for (Visita miembro_visitasNewVisita : miembro_visitasNew) {
                if (!miembro_visitasOld.contains(miembro_visitasNewVisita)) {
                    Miembro oldVisita_miembroOfMiembro_visitasNewVisita = miembro_visitasNewVisita.getVisita_miembro();
                    miembro_visitasNewVisita.setVisita_miembro(miembro);
                    miembro_visitasNewVisita = em.merge(miembro_visitasNewVisita);
                    if (oldVisita_miembroOfMiembro_visitasNewVisita != null && !oldVisita_miembroOfMiembro_visitasNewVisita.equals(miembro)) {
                        oldVisita_miembroOfMiembro_visitasNewVisita.getMiembro_visitas().remove(miembro_visitasNewVisita);
                        oldVisita_miembroOfMiembro_visitasNewVisita = em.merge(oldVisita_miembroOfMiembro_visitasNewVisita);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = miembro.getId();
                if (findMiembro(id) == null) {
                    throw new NonexistentEntityException("The miembro with id " + id + " no longer exists.");
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
            Miembro miembro;
            try {
                miembro = em.getReference(Miembro.class, id);
                miembro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The miembro with id " + id + " no longer exists.", enfe);
            }
            Membresia miembro_membresia = miembro.getMiembro_membresia();
            if (miembro_membresia != null) {
                miembro_membresia.getMembresia_miembros().remove(miembro);
                miembro_membresia = em.merge(miembro_membresia);
            }
            List<Visita> miembro_visitas = miembro.getMiembro_visitas();
            for (Visita miembro_visitasVisita : miembro_visitas) {
                miembro_visitasVisita.setVisita_miembro(null);
                miembro_visitasVisita = em.merge(miembro_visitasVisita);
            }
            em.remove(miembro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Miembro> findMiembroEntities() {
        return findMiembroEntities(true, -1, -1);
    }

    public List<Miembro> findMiembroEntities(int maxResults, int firstResult) {
        return findMiembroEntities(false, maxResults, firstResult);
    }

    private List<Miembro> findMiembroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Miembro.class));
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

    public Miembro findMiembro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Miembro.class, id);
        } finally {
            em.close();
        }
    }

    public int getMiembroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Miembro> rt = cq.from(Miembro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
