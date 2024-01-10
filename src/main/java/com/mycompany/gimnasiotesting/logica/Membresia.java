package com.mycompany.gimnasiotesting.logica;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Membresia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private int duracion;
    private float precio;
    private int diasXSemana;
    
    @OneToMany(mappedBy = "miembro_membresia")     /*@JoinColumn(name = "id_membresia") @IndexColumn(name = "idx")*/
    private List<Miembro> membresia_miembros;
    
    @OneToMany(mappedBy = "visita_membresia")     /*@JoinColumn(name = "id_membresia")@IndexColumn(name = "idx_membresia")*/
    private List<Visita> membresia_visitas;
    
    @OneToMany(mappedBy = "pago_membresia")     /*@JoinColumn(name = "id_membresia")@IndexColumn(name = "idx_membresia")*/
    private List<Pago> membresia_pagos;
    
    public Membresia() {
    }

    public Membresia(int id, String nombre, int duracion, float precio, int diasXSemana, List<Miembro> membresia_miembros, List<Visita> membresia_visitas, List<Pago> membresia_pagos) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
        this.diasXSemana = diasXSemana;
        this.membresia_miembros = membresia_miembros;
        this.membresia_visitas = membresia_visitas;
        this.membresia_pagos = membresia_pagos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getDiasXSemana() {
        return diasXSemana;
    }

    public void setDiasXSemana(int diasXSemana) {
        this.diasXSemana = diasXSemana;
    }

    public List<Miembro> getMembresia_miembros() {
        return membresia_miembros;
    }

    public void setMembresia_miembros(List<Miembro> membresia_miembros) {
        this.membresia_miembros = membresia_miembros;
    }

    public List<Visita> getMembresia_visitas() {
        return membresia_visitas;
    }

    public void setMembresia_visitas(List<Visita> membresia_visitas) {
        this.membresia_visitas = membresia_visitas;
    }

    public List<Pago> getMembresia_pagos() {
        return membresia_pagos;
    }

    public void setMembresia_pagos(List<Pago> membresia_pagos) {
        this.membresia_pagos = membresia_pagos;
    }

    

    

    

    
    
    
    
}
