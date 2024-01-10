package com.mycompany.gimnasiotesting.persistencia;

import com.mycompany.gimnasiotesting.logica.Membresia;
import com.mycompany.gimnasiotesting.logica.Miembro;
import com.mycompany.gimnasiotesting.logica.Pago;
import com.mycompany.gimnasiotesting.logica.Usuario;
import com.mycompany.gimnasiotesting.logica.Visita;
import com.mycompany.gimnasiotesting.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    MembresiaJpaController membresiaJpa = new MembresiaJpaController();
    MiembroJpaController miembroJpa = new MiembroJpaController();
    PagoJpaController pagoJpa = new PagoJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    VisitaJpaController visitaJpa = new VisitaJpaController();

    public void guardarMiembro(Miembro miembro) {
        
        miembroJpa.create(miembro);
    }

    public List<Miembro> traerMiembros() {
        return miembroJpa.findMiembroEntities();
    }

    public void borrarMiembro(int num_miembro) {
        try{
            Miembro miembro = miembroJpa.findMiembro(num_miembro);
            if(miembro.getMiembro_membresia() != null){     
                miembro.getMiembro_membresia().getMembresia_miembros().remove(miembro);
            }
            miembroJpa.destroy(num_miembro);
        } catch (NonexistentEntityException ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Miembro traerMiembro(int num_miembro) {
        return miembroJpa.findMiembro(num_miembro);
    }

    public void modificarMiembro(Miembro miembro) {
        try{
            miembroJpa.edit(miembro);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public void guardarUsuario(Usuario user) {
        usuarioJpa.create(user);
    }

    public void borrarUsuario(int num_usuario) {
        try{
            usuarioJpa.destroy(num_usuario);
        } catch (NonexistentEntityException ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int num_usuario) {
        return usuarioJpa.findUsuario(num_usuario);
    }

    public void modificarUsuario(Usuario usuario) {
        try{
            usuarioJpa.edit(usuario);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuarioPorNombreYContrasenia(String nombreUsuario, String contrasenia) {
        List<Usuario> listaUsuarios = usuarioJpa.findUsuarioEntities();
        
        for(Usuario usu: listaUsuarios){
            if(usu.getUsuario().equals(nombreUsuario) && usu.getContrasena().equals(contrasenia)){
                return usu;
            }
        }
        
        return null;
    }

    public List<Membresia> traerMembresias() {
        return membresiaJpa.findMembresiaEntities();
    }

    public void borrarMembresia(int num_membresia) {
        try{
            Membresia membresia = membresiaJpa.findMembresia(num_membresia);
            if(membresia.getMembresia_miembros() != null){     
                for(Miembro miembro : membresia.getMembresia_miembros()){
                    miembro.setMiembro_membresia(null);
                }
            }
            membresiaJpa.destroy(num_membresia);
        } catch (NonexistentEntityException ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarMembresia(Membresia membresia) {
        membresiaJpa.create(membresia);
    }

    public Membresia traerMembresia(int num_membresia) {
        return membresiaJpa.findMembresia(num_membresia);
    }

    public void modificarMembresia(Membresia membresia) {
        try{
            membresiaJpa.edit(membresia);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPago(Pago pago) {
        pagoJpa.create(pago);
    }

    public List<Pago> traerPagos() {
        return pagoJpa.findPagoEntities();
    }

    public void guardarVisita(Visita visita) {
        visitaJpa.create(visita);
    }

    public List<Visita> traerVisitas() {
        return visitaJpa.findVisitaEntities();
    }
}
