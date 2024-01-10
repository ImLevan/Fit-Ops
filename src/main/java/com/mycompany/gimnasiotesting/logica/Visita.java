package com.mycompany.gimnasiotesting.logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  
    private int id;
    
    @ManyToOne   //@JoinColumn(name = "id_miembro")
    private Miembro visita_miembro;
    
    @ManyToOne    //@JoinColumn(name = "id_usuario")
    private Usuario visita_usuario;
    
    @ManyToOne    //@JoinColumn(name = "id_membresia")
    private Membresia visita_membresia;
    
    private LocalDateTime fecha;

    public Visita() {
    }

    public Visita(int id, Miembro visita_miembro, Usuario visita_usuario, Membresia visita_membresia, LocalDateTime fecha) {
        this.id = id;
        this.visita_miembro = visita_miembro;
        this.visita_usuario = visita_usuario;
        this.visita_membresia = visita_membresia;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Miembro getVisita_miembro() {
        return visita_miembro;
    }

    public void setVisita_miembro(Miembro visita_miembro) {
        this.visita_miembro = visita_miembro;
    }

    public Usuario getVisita_usuario() {
        return visita_usuario;
    }

    public void setVisita_usuario(Usuario visita_usuario) {
        this.visita_usuario = visita_usuario;
    }

    public Membresia getVisita_membresia() {
        return visita_membresia;
    }

    public void setVisita_membresia(Membresia visita_membresia) {
        this.visita_membresia = visita_membresia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    
    
    
    
}
