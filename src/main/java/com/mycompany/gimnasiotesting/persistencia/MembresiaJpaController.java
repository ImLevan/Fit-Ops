package com.mycompany.gimnasiotesting.persistencia;

import com.mycompany.gimnasiotesting.logica.Membresia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gimnasiotesting.logica.Miembro;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gimnasiotesting.logica.Visita;
import com.mycompany.gimnasiotesting.logica.Pago;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MembresiaJpaController implements Serializable {

    public MembresiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public MembresiaJpaController() {
        emf = Persistence.createEntityManagerFactory("GimnasioPU");
    }    
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Membresia membresia) {
        if (membresia.getMembresia_miembros() == null) {
            membresia.setMembresia_miembros(new ArrayList<Miembro>());
        }
        if (membresia.getMembresia_visitas() == null) {
            membresia.setMembresia_visitas(new ArrayList<Visita>());
        }
        if (membresia.getMembresia_pagos() == null) {
            membresia.setMembresia_pagos(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Miembro> attachedMembresia_miembros = new ArrayList<Miembro>();
            for (Miembro membresia_miembrosMiembroToAttach : membresia.getMembresia_miembros()) {
                membresia_miembrosMiembroToAttach = em.getReference(membresia_miembrosMiembroToAttach.getClass(), membresia_miembrosMiembroToAttach.getId());
                attachedMembresia_miembros.add(membresia_miembrosMiembroToAttach);
            }
            membresia.setMembresia_miembros(attachedMembresia_miembros);
            List<Visita> attachedMembresia_visitas = new ArrayList<Visita>();
            for (Visita membresia_visitasVisitaToAttach : membresia.getMembresia_visitas()) {
                membresia_visitasVisitaToAttach = em.getReference(membresia_visitasVisitaToAttach.getClass(), membresia_visitasVisitaToAttach.getId());
                attachedMembresia_visitas.add(membresia_visitasVisitaToAttach);
            }
            membresia.setMembresia_visitas(attachedMembresia_visitas);
            List<Pago> attachedMembresia_pagos = new ArrayList<Pago>();
            for (Pago membresia_pagosPagoToAttach : membresia.getMembresia_pagos()) {
                membresia_pagosPagoToAttach = em.getReference(membresia_pagosPagoToAttach.getClass(), membresia_pagosPagoToAttach.getId());
                attachedMembresia_pagos.add(membresia_pagosPagoToAttach);
            }
            membresia.setMembresia_pagos(attachedMembresia_pagos);
            em.persist(membresia);
            for (Miembro membresia_miembrosMiembro : membresia.getMembresia_miembros()) {
                Membresia oldMiembro_membresiaOfMembresia_miembrosMiembro = membresia_miembrosMiembro.getMiembro_membresia();
                membresia_miembrosMiembro.setMiembro_membresia(membresia);
                membresia_miembrosMiembro = em.merge(membresia_miembrosMiembro);
                if (oldMiembro_membresiaOfMembresia_miembrosMiembro != null) {
                    oldMiembro_membresiaOfMembresia_miembrosMiembro.getMembresia_miembros().remove(membresia_miembrosMiembro);
                    oldMiembro_membresiaOfMembresia_miembrosMiembro = em.merge(oldMiembro_membresiaOfMembresia_miembrosMiembro);
                }
            }
            for (Visita membresia_visitasVisita : membresia.getMembresia_visitas()) {
                Membresia oldVisita_membresiaOfMembresia_visitasVisita = membresia_visitasVisita.getVisita_membresia();
                membresia_visitasVisita.setVisita_membresia(membresia);
                membresia_visitasVisita = em.merge(membresia_visitasVisita);
                if (oldVisita_membresiaOfMembresia_visitasVisita != null) {
                    oldVisita_membresiaOfMembresia_visitasVisita.getMembresia_visitas().remove(membresia_visitasVisita);
                    oldVisita_membresiaOfMembresia_visitasVisita = em.merge(oldVisita_membresiaOfMembresia_visitasVisita);
                }
            }
            for (Pago membresia_pagosPago : membresia.getMembresia_pagos()) {
                Membresia oldPago_membresiaOfMembresia_pagosPago = membresia_pagosPago.getPago_membresia();
                membresia_pagosPago.setPago_membresia(membresia);
                membresia_pagosPago = em.merge(membresia_pagosPago);
                if (oldPago_membresiaOfMembresia_pagosPago != null) {
                    oldPago_membresiaOfMembresia_pagosPago.getMembresia_pagos().remove(membresia_pagosPago);
                    oldPago_membresiaOfMembresia_pagosPago = em.merge(oldPago_membresiaOfMembresia_pagosPago);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Membresia membresia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membresia persistentMembresia = em.find(Membresia.class, membresia.getId());
            List<Miembro> membresia_miembrosOld = persistentMembresia.getMembresia_miembros();
            List<Miembro> membresia_miembrosNew = membresia.getMembresia_miembros();
            List<Visita> membresia_visitasOld = persistentMembresia.getMembresia_visitas();
            List<Visita> membresia_visitasNew = membresia.getMembresia_visitas();
            List<Pago> membresia_pagosOld = persistentMembresia.getMembresia_pagos();
            List<Pago> membresia_pagosNew = membresia.getMembresia_pagos();
            List<Miembro> attachedMembresia_miembrosNew = new ArrayList<Miembro>();
            for (Miembro membresia_miembrosNewMiembroToAttach : membresia_miembrosNew) {
                membresia_miembrosNewMiembroToAttach = em.getReference(membresia_miembrosNewMiembroToAttach.getClass(), membresia_miembrosNewMiembroToAttach.getId());
                attachedMembresia_miembrosNew.add(membresia_miembrosNewMiembroToAttach);
            }
            membresia_miembrosNew = attachedMembresia_miembrosNew;
            membresia.setMembresia_miembros(membresia_miembrosNew);
            List<Visita> attachedMembresia_visitasNew = new ArrayList<Visita>();
            for (Visita membresia_visitasNewVisitaToAttach : membresia_visitasNew) {
                membresia_visitasNewVisitaToAttach = em.getReference(membresia_visitasNewVisitaToAttach.getClass(), membresia_visitasNewVisitaToAttach.getId());
                attachedMembresia_visitasNew.add(membresia_visitasNewVisitaToAttach);
            }
            membresia_visitasNew = attachedMembresia_visitasNew;
            membresia.setMembresia_visitas(membresia_visitasNew);
            List<Pago> attachedMembresia_pagosNew = new ArrayList<Pago>();
            for (Pago membresia_pagosNewPagoToAttach : membresia_pagosNew) {
                membresia_pagosNewPagoToAttach = em.getReference(membresia_pagosNewPagoToAttach.getClass(), membresia_pagosNewPagoToAttach.getId());
                attachedMembresia_pagosNew.add(membresia_pagosNewPagoToAttach);
            }
            membresia_pagosNew = attachedMembresia_pagosNew;
            membresia.setMembresia_pagos(membresia_pagosNew);
            membresia = em.merge(membresia);
            for (Miembro membresia_miembrosOldMiembro : membresia_miembrosOld) {
                if (!membresia_miembrosNew.contains(membresia_miembrosOldMiembro)) {
                    membresia_miembrosOldMiembro.setMiembro_membresia(null);
                    membresia_miembrosOldMiembro = em.merge(membresia_miembrosOldMiembro);
                }
            }
            for (Miembro membresia_miembrosNewMiembro : membresia_miembrosNew) {
                if (!membresia_miembrosOld.contains(membresia_miembrosNewMiembro)) {
                    Membresia oldMiembro_membresiaOfMembresia_miembrosNewMiembro = membresia_miembrosNewMiembro.getMiembro_membresia();
                    membresia_miembrosNewMiembro.setMiembro_membresia(membresia);
                    membresia_miembrosNewMiembro = em.merge(membresia_miembrosNewMiembro);
                    if (oldMiembro_membresiaOfMembresia_miembrosNewMiembro != null && !oldMiembro_membresiaOfMembresia_miembrosNewMiembro.equals(membresia)) {
                        oldMiembro_membresiaOfMembresia_miembrosNewMiembro.getMembresia_miembros().remove(membresia_miembrosNewMiembro);
                        oldMiembro_membresiaOfMembresia_miembrosNewMiembro = em.merge(oldMiembro_membresiaOfMembresia_miembrosNewMiembro);
                    }
                }
            }
            for (Visita membresia_visitasOldVisita : membresia_visitasOld) {
                if (!membresia_visitasNew.contains(membresia_visitasOldVisita)) {
                    membresia_visitasOldVisita.setVisita_membresia(null);
                    membresia_visitasOldVisita = em.merge(membresia_visitasOldVisita);
                }
            }
            for (Visita membresia_visitasNewVisita : membresia_visitasNew) {
                if (!membresia_visitasOld.contains(membresia_visitasNewVisita)) {
                    Membresia oldVisita_membresiaOfMembresia_visitasNewVisita = membresia_visitasNewVisita.getVisita_membresia();
                    membresia_visitasNewVisita.setVisita_membresia(membresia);
                    membresia_visitasNewVisita = em.merge(membresia_visitasNewVisita);
                    if (oldVisita_membresiaOfMembresia_visitasNewVisita != null && !oldVisita_membresiaOfMembresia_visitasNewVisita.equals(membresia)) {
                        oldVisita_membresiaOfMembresia_visitasNewVisita.getMembresia_visitas().remove(membresia_visitasNewVisita);
                        oldVisita_membresiaOfMembresia_visitasNewVisita = em.merge(oldVisita_membresiaOfMembresia_visitasNewVisita);
                    }
                }
            }
            for (Pago membresia_pagosOldPago : membresia_pagosOld) {
                if (!membresia_pagosNew.contains(membresia_pagosOldPago)) {
                    membresia_pagosOldPago.setPago_membresia(null);
                    membresia_pagosOldPago = em.merge(membresia_pagosOldPago);
                }
            }
            for (Pago membresia_pagosNewPago : membresia_pagosNew) {
                if (!membresia_pagosOld.contains(membresia_pagosNewPago)) {
                    Membresia oldPago_membresiaOfMembresia_pagosNewPago = membresia_pagosNewPago.getPago_membresia();
                    membresia_pagosNewPago.setPago_membresia(membresia);
                    membresia_pagosNewPago = em.merge(membresia_pagosNewPago);
                    if (oldPago_membresiaOfMembresia_pagosNewPago != null && !oldPago_membresiaOfMembresia_pagosNewPago.equals(membresia)) {
                        oldPago_membresiaOfMembresia_pagosNewPago.getMembresia_pagos().remove(membresia_pagosNewPago);
                        oldPago_membresiaOfMembresia_pagosNewPago = em.merge(oldPago_membresiaOfMembresia_pagosNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = membresia.getId();
                if (findMembresia(id) == null) {
                    throw new NonexistentEntityException("The membresia with id " + id + " no longer exists.");
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
            Membresia membresia;
            try {
                membresia = em.getReference(Membresia.class, id);
                membresia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The membresia with id " + id + " no longer exists.", enfe);
            }
            List<Miembro> membresia_miembros = membresia.getMembresia_miembros();
            for (Miembro membresia_miembrosMiembro : membresia_miembros) {
                membresia_miembrosMiembro.setMiembro_membresia(null);
                membresia_miembrosMiembro = em.merge(membresia_miembrosMiembro);
            }
            List<Visita> membresia_visitas = membresia.getMembresia_visitas();
            for (Visita membresia_visitasVisita : membresia_visitas) {
                membresia_visitasVisita.setVisita_membresia(null);
                membresia_visitasVisita = em.merge(membresia_visitasVisita);
            }
            List<Pago> membresia_pagos = membresia.getMembresia_pagos();
            for (Pago membresia_pagosPago : membresia_pagos) {
                membresia_pagosPago.setPago_membresia(null);
                membresia_pagosPago = em.merge(membresia_pagosPago);
            }
            em.remove(membresia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Membresia> findMembresiaEntities() {
        return findMembresiaEntities(true, -1, -1);
    }

    public List<Membresia> findMembresiaEntities(int maxResults, int firstResult) {
        return findMembresiaEntities(false, maxResults, firstResult);
    }

    private List<Membresia> findMembresiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Membresia.class));
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

    public Membresia findMembresia(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Membresia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMembresiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Membresia> rt = cq.from(Membresia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
