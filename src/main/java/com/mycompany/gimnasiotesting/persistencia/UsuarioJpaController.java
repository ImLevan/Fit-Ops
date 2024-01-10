package com.mycompany.gimnasiotesting.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gimnasiotesting.logica.Visita;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gimnasiotesting.logica.Pago;
import com.mycompany.gimnasiotesting.logica.Usuario;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("GimnasioPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getUsuario_visitas() == null) {
            usuario.setUsuario_visitas(new ArrayList<Visita>());
        }
        if (usuario.getUsuario_pagos() == null) {
            usuario.setUsuario_pagos(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Visita> attachedUsuario_visitas = new ArrayList<Visita>();
            for (Visita usuario_visitasVisitaToAttach : usuario.getUsuario_visitas()) {
                usuario_visitasVisitaToAttach = em.getReference(usuario_visitasVisitaToAttach.getClass(), usuario_visitasVisitaToAttach.getId());
                attachedUsuario_visitas.add(usuario_visitasVisitaToAttach);
            }
            usuario.setUsuario_visitas(attachedUsuario_visitas);
            List<Pago> attachedUsuario_pagos = new ArrayList<Pago>();
            for (Pago usuario_pagosPagoToAttach : usuario.getUsuario_pagos()) {
                usuario_pagosPagoToAttach = em.getReference(usuario_pagosPagoToAttach.getClass(), usuario_pagosPagoToAttach.getId());
                attachedUsuario_pagos.add(usuario_pagosPagoToAttach);
            }
            usuario.setUsuario_pagos(attachedUsuario_pagos);
            em.persist(usuario);
            for (Visita usuario_visitasVisita : usuario.getUsuario_visitas()) {
                Usuario oldVisita_usuarioOfUsuario_visitasVisita = usuario_visitasVisita.getVisita_usuario();
                usuario_visitasVisita.setVisita_usuario(usuario);
                usuario_visitasVisita = em.merge(usuario_visitasVisita);
                if (oldVisita_usuarioOfUsuario_visitasVisita != null) {
                    oldVisita_usuarioOfUsuario_visitasVisita.getUsuario_visitas().remove(usuario_visitasVisita);
                    oldVisita_usuarioOfUsuario_visitasVisita = em.merge(oldVisita_usuarioOfUsuario_visitasVisita);
                }
            }
            for (Pago usuario_pagosPago : usuario.getUsuario_pagos()) {
                Usuario oldPago_usuarioOfUsuario_pagosPago = usuario_pagosPago.getPago_usuario();
                usuario_pagosPago.setPago_usuario(usuario);
                usuario_pagosPago = em.merge(usuario_pagosPago);
                if (oldPago_usuarioOfUsuario_pagosPago != null) {
                    oldPago_usuarioOfUsuario_pagosPago.getUsuario_pagos().remove(usuario_pagosPago);
                    oldPago_usuarioOfUsuario_pagosPago = em.merge(oldPago_usuarioOfUsuario_pagosPago);
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
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Visita> usuario_visitasOld = persistentUsuario.getUsuario_visitas();
            List<Visita> usuario_visitasNew = usuario.getUsuario_visitas();
            List<Pago> usuario_pagosOld = persistentUsuario.getUsuario_pagos();
            List<Pago> usuario_pagosNew = usuario.getUsuario_pagos();
            List<Visita> attachedUsuario_visitasNew = new ArrayList<Visita>();
            for (Visita usuario_visitasNewVisitaToAttach : usuario_visitasNew) {
                usuario_visitasNewVisitaToAttach = em.getReference(usuario_visitasNewVisitaToAttach.getClass(), usuario_visitasNewVisitaToAttach.getId());
                attachedUsuario_visitasNew.add(usuario_visitasNewVisitaToAttach);
            }
            usuario_visitasNew = attachedUsuario_visitasNew;
            usuario.setUsuario_visitas(usuario_visitasNew);
            List<Pago> attachedUsuario_pagosNew = new ArrayList<Pago>();
            for (Pago usuario_pagosNewPagoToAttach : usuario_pagosNew) {
                usuario_pagosNewPagoToAttach = em.getReference(usuario_pagosNewPagoToAttach.getClass(), usuario_pagosNewPagoToAttach.getId());
                attachedUsuario_pagosNew.add(usuario_pagosNewPagoToAttach);
            }
            usuario_pagosNew = attachedUsuario_pagosNew;
            usuario.setUsuario_pagos(usuario_pagosNew);
            usuario = em.merge(usuario);
            for (Visita usuario_visitasOldVisita : usuario_visitasOld) {
                if (!usuario_visitasNew.contains(usuario_visitasOldVisita)) {
                    usuario_visitasOldVisita.setVisita_usuario(null);
                    usuario_visitasOldVisita = em.merge(usuario_visitasOldVisita);
                }
            }
            for (Visita usuario_visitasNewVisita : usuario_visitasNew) {
                if (!usuario_visitasOld.contains(usuario_visitasNewVisita)) {
                    Usuario oldVisita_usuarioOfUsuario_visitasNewVisita = usuario_visitasNewVisita.getVisita_usuario();
                    usuario_visitasNewVisita.setVisita_usuario(usuario);
                    usuario_visitasNewVisita = em.merge(usuario_visitasNewVisita);
                    if (oldVisita_usuarioOfUsuario_visitasNewVisita != null && !oldVisita_usuarioOfUsuario_visitasNewVisita.equals(usuario)) {
                        oldVisita_usuarioOfUsuario_visitasNewVisita.getUsuario_visitas().remove(usuario_visitasNewVisita);
                        oldVisita_usuarioOfUsuario_visitasNewVisita = em.merge(oldVisita_usuarioOfUsuario_visitasNewVisita);
                    }
                }
            }
            for (Pago usuario_pagosOldPago : usuario_pagosOld) {
                if (!usuario_pagosNew.contains(usuario_pagosOldPago)) {
                    usuario_pagosOldPago.setPago_usuario(null);
                    usuario_pagosOldPago = em.merge(usuario_pagosOldPago);
                }
            }
            for (Pago usuario_pagosNewPago : usuario_pagosNew) {
                if (!usuario_pagosOld.contains(usuario_pagosNewPago)) {
                    Usuario oldPago_usuarioOfUsuario_pagosNewPago = usuario_pagosNewPago.getPago_usuario();
                    usuario_pagosNewPago.setPago_usuario(usuario);
                    usuario_pagosNewPago = em.merge(usuario_pagosNewPago);
                    if (oldPago_usuarioOfUsuario_pagosNewPago != null && !oldPago_usuarioOfUsuario_pagosNewPago.equals(usuario)) {
                        oldPago_usuarioOfUsuario_pagosNewPago.getUsuario_pagos().remove(usuario_pagosNewPago);
                        oldPago_usuarioOfUsuario_pagosNewPago = em.merge(oldPago_usuarioOfUsuario_pagosNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
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

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Visita> usuario_visitas = usuario.getUsuario_visitas();
            for (Visita usuario_visitasVisita : usuario_visitas) {
                usuario_visitasVisita.setVisita_usuario(null);
                usuario_visitasVisita = em.merge(usuario_visitasVisita);
            }
            List<Pago> usuario_pagos = usuario.getUsuario_pagos();
            for (Pago usuario_pagosPago : usuario_pagos) {
                usuario_pagosPago.setPago_usuario(null);
                usuario_pagosPago = em.merge(usuario_pagosPago);
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

    public Usuario findUsuario(int id) {
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
    
}
