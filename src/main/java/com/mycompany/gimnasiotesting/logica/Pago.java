package com.mycompany.gimnasiotesting.logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pago implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String matricula;
    
    @ManyToOne    //@JoinColumn(name = "id_membresia")
    private Membresia pago_membresia;
    
    @ManyToOne    //@JoinColumn(name = "id_usuario")
    private Usuario pago_usuario;
    
    private LocalDateTime fecha;
    private float monto;

    public Pago() {
    }

    public Pago(int id, String matricula, Membresia pago_membresia, Usuario pago_usuario, LocalDateTime fecha, float monto) {
        this.id = id;
        this.matricula = matricula;
        this.pago_membresia = pago_membresia;
        this.pago_usuario = pago_usuario;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Membresia getPago_membresia() {
        return pago_membresia;
    }

    public void setPago_membresia(Membresia pago_membresia) {
        this.pago_membresia = pago_membresia;
    }

    public Usuario getPago_usuario() {
        return pago_usuario;
    }

    public void setPago_usuario(Usuario pago_usuario) {
        this.pago_usuario = pago_usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    

    
    
    
}
