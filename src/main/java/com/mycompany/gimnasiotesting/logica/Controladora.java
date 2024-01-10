package com.mycompany.gimnasiotesting.logica;

import com.mycompany.gimnasiotesting.persistencia.ControladoraPersistencia;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardarMiembro(String nombre, String matricula, Enum estado, String apellido, String dni, String telefono, String sexo) {
        
        Miembro miembro = new Miembro();
        miembro.setNombre(nombre);
        miembro.setMatricula(matricula);
        miembro.setEstado(estado.name());
        miembro.setApellido(apellido);
        miembro.setDni(dni);
        miembro.setTelefono(telefono);
        miembro.setSexo(sexo);
       
        LocalDateTime fechaHoy = LocalDateTime.now();
        miembro.setFechaRegistro(fechaHoy);
        
        controlPersis.guardarMiembro(miembro);
    }

    public List<Miembro> traerMiembros() {
       return controlPersis.traerMiembros();
    }

    public void borrarMiembro(int num_miembro) {
        controlPersis.borrarMiembro(num_miembro);
    }

    public Miembro traerMiembro(int num_miembro) {
        return controlPersis.traerMiembro(num_miembro);
    }

    public void modificarMiembro(Miembro miembro, String nombre, String matricula, String apellido, String dni, 
            String telefono, String sexo) {
        
        miembro.setNombre(nombre);
        miembro.setMatricula(matricula);
        miembro.setApellido(apellido);
        miembro.setDni(dni);
        miembro.setTelefono(telefono);
        miembro.setSexo(sexo);
        
        controlPersis.modificarMiembro(miembro);
    }
    
    public List<Usuario> traerUsuarios() {
       return controlPersis.traerUsuarios();
    }

    public void guardarUsuario(String usuario, String contrasenia, String nombre) {
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setContrasena(contrasenia);
        user.setNombre(nombre);
        
        controlPersis.guardarUsuario(user);
    }

    public void borrarUsuario(int num_usuario) {
        controlPersis.borrarUsuario(num_usuario);
    }
    
    public Usuario traerUsuarioPorNombreYContrasenia(String nombreUsuario, String contrasenia) {
        return controlPersis.traerUsuarioPorNombreYContrasenia(nombreUsuario, contrasenia);
    }
    
    public Usuario traerUsuario(int num_usuario) {
        return controlPersis.traerUsuario(num_usuario);
    }

    public void modificarUsuario(Usuario usuario, String nombreUsuario, String contrasenia, String nombre) {
        
        usuario.setUsuario(nombreUsuario);
        usuario.setContrasena(contrasenia);
        usuario.setNombre(nombre);
        
       
        controlPersis.modificarUsuario(usuario);
    }

    public boolean validarUsuario(String nombreUsuario, String contrasenia) {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for(Usuario usuario: listaUsuarios){
            if(usuario.getUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasenia)){
                return true;
            }
        }
        
        return false;
    }
    
    public List<Membresia> traerMembresias() {
       return controlPersis.traerMembresias();
    }
    
    public void borrarMembresia(int num_membresia) {
        controlPersis.borrarMembresia(num_membresia);
    }

    public void guardarMembresia(String nombre, String duracion, Float precio, int cantDias) {
        Membresia membresia = new Membresia();
        membresia.setNombre(nombre);
        membresia.setDuracion(Integer.parseInt(duracion));
        membresia.setPrecio(precio);
        membresia.setDiasXSemana(cantDias);
        
        controlPersis.guardarMembresia(membresia);
    }

    public Membresia traerMembresia(int num_membresia) {
        return controlPersis.traerMembresia(num_membresia);
    }

    public void modificarMembresia(Membresia membresia, String nombre, int duracion, Float precio, int cantDias) {
        membresia.setNombre(nombre);
        membresia.setDuracion(duracion);
        membresia.setPrecio(precio);
        membresia.setDiasXSemana(cantDias);
       
        controlPersis.modificarMembresia(membresia);
    }

    public Membresia traerMembresiaPorNombre(String nombreMembresia) {
        List<Membresia> membresias = this.traerMembresias();
        
        for(Membresia mem : membresias){
            if(mem.getNombre().equals(nombreMembresia)){
                return mem;
            }
        }
        return null;
    }

    public void matchearMembresiaMiembro(Miembro miembro, Membresia membresiaSeleccionada, Usuario usuario) {
        miembro.setMiembro_membresia(membresiaSeleccionada);
        LocalDateTime fechaInicioMembresia = LocalDateTime.now();
        LocalDateTime fechaFinMembresia = fechaInicioMembresia.plusDays(membresiaSeleccionada.getDuracion());
        miembro.setFechaInicio(fechaInicioMembresia);
        miembro.setFechaFin(fechaFinMembresia);
        miembro.setEstado("ACTIVO");
        
        hacerPago(membresiaSeleccionada, usuario, miembro);
        
        membresiaSeleccionada.getMembresia_miembros().add(miembro);
        
        controlPersis.modificarMiembro(miembro);
    }

    public void hacerPago(Membresia membresiaSeleccionada, Usuario usuario, Miembro miembro) {
        Pago pago = new Pago();
        
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoy = LocalDateTime.now();

        // Formatear la fecha y el mes como un String "Año/Mes"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        String fechaFormateada = fechaHoy.format(formatter);
        
        pago.setFecha(fechaHoy);
        pago.setMatricula(fechaFormateada + "/" + miembro.getNombre());
        pago.setMonto(membresiaSeleccionada.getPrecio());
        pago.setPago_membresia(membresiaSeleccionada);
        pago.setPago_usuario(usuario);
        
        membresiaSeleccionada.getMembresia_pagos().add(pago);
        usuario.getUsuario_pagos().add(pago);
        
        controlPersis.guardarPago(pago);
        
    }
    
    public void hacerPagoVisitaUnica(Usuario usuario, String nombre, float precio) {
        Pago pago = new Pago();
        
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoy = LocalDateTime.now();

        // Formatear la fecha y el mes como un String "Año/Mes"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        String fechaFormateada = fechaHoy.format(formatter);
        
        pago.setFecha(fechaHoy);
        pago.setMatricula(fechaFormateada + "/" + nombre);
        pago.setMonto(precio);
        pago.setPago_usuario(usuario);

        usuario.getUsuario_pagos().add(pago);
        
        registrarVisitaUnica(usuario);
        
        controlPersis.guardarPago(pago);
        
    }
    
    public void registrarVisita(Usuario usuario, Membresia membresia, Miembro miembro) {
        Visita visita = new Visita();
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoy = LocalDateTime.now();
        
        visita.setFecha(fechaHoy);
        visita.setVisita_usuario(usuario);
        visita.setVisita_membresia(membresia);
        visita.setVisita_miembro(miembro);
        
        membresia.getMembresia_visitas().add(visita);
        miembro.getMiembro_visitas().add(visita);
        usuario.getUsuario_visitas().add(visita);
        
        controlPersis.guardarVisita(visita);
    }
    
    private void registrarVisitaUnica(Usuario usuario) {
        Visita visita = new Visita();
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoy = LocalDateTime.now();
        
        visita.setFecha(fechaHoy);
        visita.setVisita_usuario(usuario);
        
        controlPersis.guardarVisita(visita);
    }
    
    public List<Visita> traerVisitas(){
        return controlPersis.traerVisitas();
    }

    public List<Pago> traerPagos() {
        return controlPersis.traerPagos();
    }

    public float traerSumaDePagos() {
        List<Pago> listaPagos = traerPagos();
        float recaudacion = 0;
        
        for(Pago pago : listaPagos){
            recaudacion += pago.getMonto();
        }
        
        return recaudacion;
    }

    public float traerSumaDePagosMesActual() {
        YearMonth mesActual = YearMonth.now();

        // Obtener todos los pagos del mes actual
        List<Pago> pagosMesActual = traerPagosMesActual(mesActual);

        // Calcular la suma de pagos totales
        return calcularSumaDePagos(pagosMesActual);
    }
    
    public float traerSumaDeMembresiasMesActual() {
        YearMonth mesActual = YearMonth.now();

        // Obtener solo los pagos de membresías del mes actual
        List<Pago> pagosMembresiasMesActual = traerPagosMembresiasMesActual(mesActual);

        // Calcular la suma de pagos de membresías
        return calcularSumaDePagos(pagosMembresiasMesActual);
    }

    public float traerSumaDeVisitasUnicasMesActual() {
        YearMonth mesActual = YearMonth.now();

        // Obtener solo los pagos de visitas únicas del mes actual
        List<Pago> pagosVisitasUnicasMesActual = traerPagosVisitasUnicasMesActual(mesActual);

        // Calcular la suma de pagos de visitas únicas
        return calcularSumaDePagos(pagosVisitasUnicasMesActual);
    }
    
    private float calcularSumaDePagos(List<Pago> pagos) {
        float suma = 0;
        for (Pago pago : pagos) {
            suma += pago.getMonto();
        }
        return suma;
    }
    
    private List<Pago> traerPagosMesActual(YearMonth mes) {
        List<Pago> todosLosPagos = traerPagos();

        // Filtrar los pagos para obtener solo los del mes actual
        return todosLosPagos.stream()
                .filter(pago -> pago.getFecha().getYear() == mes.getYear() && pago.getFecha().getMonth() == mes.getMonth())
                .collect(Collectors.toList());
    }

    // Lógica para obtener pagos de membresías del mes actual
    private List<Pago> traerPagosMembresiasMesActual(YearMonth mes) {
        List<Pago> pagosMesActual = traerPagosMesActual(mes);

        // Filtrar los pagos para obtener solo los de membresías
        return pagosMesActual.stream()
                .filter(pago -> pago.getPago_membresia() != null)
                .collect(Collectors.toList());
    }

    // Lógica para obtener pagos de visitas únicas del mes actual
    private List<Pago> traerPagosVisitasUnicasMesActual(YearMonth mes) {
        List<Pago> pagosMesActual = traerPagosMesActual(mes);

        // Filtrar los pagos para obtener solo los de visitas únicas
        return pagosMesActual.stream()
                .filter(pago -> pago.getPago_membresia() == null)
                .collect(Collectors.toList());
    }
    
    public List<HistorialVentas> traerHistorialVentas() {
        List<HistorialVentas> historialVentas = new ArrayList<>();
        YearMonth mesActual = YearMonth.now();

        // Obtener datos para cada mes del historial
        for (int i = 0; i < 12; i++) {
            YearMonth mes = mesActual.minusMonths(i);
            List<Pago> pagosMesActual = traerPagosMesActual(mes);
            
            if(pagosMesActual != null){
                float recaudacionTotal = calcularSumaDePagos(pagosMesActual);
                float recaudacionMembresia = calcularSumaDePagos(traerPagosMembresiasMesActual(mes));
                float recaudacionVisitaUnica = calcularSumaDePagos(traerPagosVisitasUnicasMesActual(mes));
                int nuevosMiembros = contarNuevosMiembros(pagosMesActual);

                HistorialVentas historial = new HistorialVentas();
                historial.setMesAnio(mes);
                historial.setRecaudacionTotal(recaudacionTotal);
                historial.setRecaudacionMembresia(recaudacionMembresia);
                historial.setRecaudacionVisitaUnica(recaudacionVisitaUnica);
                historial.setNuevosMiembrosRegistrados(nuevosMiembros);

                historialVentas.add(historial);
            }
        }

        return historialVentas;
    }
    
    private int contarNuevosMiembros(List<Pago> pagos) {    
        List<String> matriculasNuevosMiembros = new ArrayList<>();

        // Filtrar los pagos que corresponden a nuevas membresías y obtener las matrículas únicas
        for (Pago pago : pagos) {
            if (pago.getPago_membresia() != null) {
                matriculasNuevosMiembros.add(pago.getMatricula());
            }
        }

        return matriculasNuevosMiembros.size();
    }
    
    public String traerMembresiaMasComprada() {
        List<Pago> listaPagos = traerPagos();

        // Mapa para almacenar el recuento de cada membresía
        Map<String, Integer> recuentoMembresias = new HashMap<>();

        // Contar la frecuencia de cada membresía en la lista de pagos
        for (Pago pago : listaPagos) {
            if(pago.getPago_membresia() != null){
                String nombreMembresia = pago.getPago_membresia().getNombre();
                recuentoMembresias.put(nombreMembresia, recuentoMembresias.getOrDefault(nombreMembresia, 0) + 1);
            }
        }

        // Encontrar la membresía con el recuento máximo
        String membresiaMasComprada = " ";
        int recuentoMaximo = 0;

        for (Map.Entry<String, Integer> entry : recuentoMembresias.entrySet()) {
            String nombreMembresia = entry.getKey();
            int recuento = entry.getValue();

            if (recuento > recuentoMaximo) {
                recuentoMaximo = recuento;
                membresiaMasComprada = nombreMembresia;
            }
        }

        return membresiaMasComprada;      
        
    }

    public String traerUsuarioMasPagos() {
        List<Pago> listaPagos = traerPagos();

        // Mapa para almacenar el recuento de cada membresía
        Map<String, Integer> recuentoUsuarios = new HashMap<>();

        // Contar la frecuencia de cada membresía en la lista de pagos
        for (Pago pago : listaPagos) {
            String nombreUsuario = pago.getPago_usuario().getUsuario();
            recuentoUsuarios.put(nombreUsuario, recuentoUsuarios.getOrDefault(nombreUsuario, 0) + 1);
        }

        // Encontrar la membresía con el recuento máximo
        String usuarioMasPagos = " ";
        int recuentoMaximo = 0;

        for (Map.Entry<String, Integer> entry : recuentoUsuarios.entrySet()) {
            String nombreUsuario = entry.getKey();
            int recuento = entry.getValue();

            if (recuento > recuentoMaximo) {
                recuentoMaximo = recuento;
                usuarioMasPagos = nombreUsuario;
            }
        }

        return usuarioMasPagos;
    }

    public int obtenerDiasVisitados(Miembro miembro) {
        int contadorVisitas = 0;

        List<Visita> listaVisitas = traerVisitas();  // Supongo que tienes un método para obtener todas las visitas

        if(listaVisitas != null){         
            for (Visita visita : listaVisitas) {
                LocalDateTime fechaVisita = visita.getFecha();

                // Comprobar si la visita pertenece a la semana actual
                if (esSemanaActual(fechaVisita) && visita.getVisita_miembro() != null) {
                    if(visita.getVisita_miembro().getMatricula().equals(miembro.getMatricula())){
                        contadorVisitas++;
                    }      
                }
            }
        }
        
        return contadorVisitas;
    }
    
    private boolean esSemanaActual(LocalDateTime fecha) {
        LocalDateTime inicioSemana = LocalDateTime.now().with(DayOfWeek.MONDAY).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime finSemana = inicioSemana.plusDays(6);

        return fecha.isAfter(inicioSemana) && fecha.isBefore(finSemana.plusDays(1));
    }
    
}