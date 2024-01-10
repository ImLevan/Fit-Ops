package com.mycompany.gimnasiotesting.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String usuario;
    private String contrasena;
    private String nombre;
    
    @OneToMany(mappedBy = "visita_usuario")      /*@JoinColumn(name = "id_usuario")@IndexColumn(name = "idx_usuario") */ 
    private List<Visita> usuario_visitas;
    
    @OneToMany(mappedBy = "pago_usuario")     /*@JoinColumn(name = "id_usuario")@IndexColumn(name = "idx_usuario")*/
    private List<Pago> usuario_pagos;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String contrasena, String nombre, List<Visita> usuario_visitas, List<Pago> usuario_pagos) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.usuario_visitas = usuario_visitas;
        this.usuario_pagos = usuario_pagos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Visita> getUsuario_visitas() {
        return usuario_visitas;
    }

    public void setUsuario_visitas(List<Visita> usuario_visitas) {
        this.usuario_visitas = usuario_visitas;
    }

    public List<Pago> getUsuario_pagos() {
        return usuario_pagos;
    }

    public void setUsuario_pagos(List<Pago> usuario_pagos) {
        this.usuario_pagos = usuario_pagos;
    }

    
    
    
}
