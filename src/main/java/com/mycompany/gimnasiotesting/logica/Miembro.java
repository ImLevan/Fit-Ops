package com.mycompany.gimnasiotesting.logica;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Miembro implements Serializable {
    
    public enum Estado{
        ACTIVO, VENCIDO, SIN_MEMBRESIA
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String matricula;
    private String nombre;
    private String estado;
    private String apellido;
    private String dni;
    private String telefono;
    private String sexo;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    
    @ManyToOne //@JoinColumn(name = "id_membresia")
    private Membresia miembro_membresia;
    
    /*@JoinColumn(name = "id_miembro") @IndexColumn(name = "idx_miembro")*/
    @OneToMany(mappedBy = "visita_miembro")
    private List<Visita> miembro_visitas;
    
    
    public Miembro() {
    }

    public Miembro(int id, String matricula, String nombre, String estado, String apellido, String dni, String telefono, String sexo, LocalDateTime fechaRegistro, LocalDateTime fechaInicio, LocalDateTime fechaFin, Membresia miembro_membresia, List<Visita> miembro_visitas) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.estado = estado;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.sexo = sexo;
        this.fechaRegistro = fechaRegistro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.miembro_membresia = miembro_membresia;
        this.miembro_visitas = miembro_visitas;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Membresia getMiembro_membresia() {
        return miembro_membresia;
    }

    public void setMiembro_membresia(Membresia miembro_membresia) {
        this.miembro_membresia = miembro_membresia;
    }

    public List<Visita> getMiembro_visitas() {
        return miembro_visitas;
    }

    public void setMiembro_visitas(List<Visita> miembro_visitas) {
        this.miembro_visitas = miembro_visitas;
    }

    

    
    
    
}
