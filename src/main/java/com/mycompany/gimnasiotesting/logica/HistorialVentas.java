package com.mycompany.gimnasiotesting.logica;

import java.time.YearMonth;

public class HistorialVentas {
    private YearMonth mesAnio;
    private float recaudacionTotal;
    private float recaudacionMembresia;
    private float recaudacionVisitaUnica;
    private int nuevosMiembrosRegistrados;

    public HistorialVentas() {
    }

    public HistorialVentas(YearMonth mesAnio, float recaudacionTotal, float recaudacionMembresia, float recaudacionVisitaUnica, int nuevosMiembrosRegistrados) {
        this.mesAnio = mesAnio;
        this.recaudacionTotal = recaudacionTotal;
        this.recaudacionMembresia = recaudacionMembresia;
        this.recaudacionVisitaUnica = recaudacionVisitaUnica;
        this.nuevosMiembrosRegistrados = nuevosMiembrosRegistrados;
    }

    public YearMonth getMesAnio() {
        return mesAnio;
    }

    public void setMesAnio(YearMonth mesAnio) {
        this.mesAnio = mesAnio;
    }

    public float getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(float recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }

    public float getRecaudacionMembresia() {
        return recaudacionMembresia;
    }

    public void setRecaudacionMembresia(float recaudacionMembresia) {
        this.recaudacionMembresia = recaudacionMembresia;
    }

    public float getRecaudacionVisitaUnica() {
        return recaudacionVisitaUnica;
    }

    public void setRecaudacionVisitaUnica(float recaudacionVisitaUnica) {
        this.recaudacionVisitaUnica = recaudacionVisitaUnica;
    }

    public int getNuevosMiembrosRegistrados() {
        return nuevosMiembrosRegistrados;
    }

    public void setNuevosMiembrosRegistrados(int nuevosMiembrosRegistrados) {
        this.nuevosMiembrosRegistrados = nuevosMiembrosRegistrados;
    }
    
    
}
