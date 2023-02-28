package com.example.dasentregaindividual1.lista_partidos.data_classes;

public class Partido {

    private EquipoPartido equipoLocal;
    private EquipoPartido equipoVisitante;
    private String fecha;
    private String hora;

    public Partido(
        EquipoPartido pEquipoLocal,
        EquipoPartido pEquipoVisitante,
        String pFecha,
        String pHora
    ) {
        this.equipoLocal = pEquipoLocal;
        this.equipoVisitante = pEquipoVisitante;
        this.fecha = pFecha;
        this.hora = pHora;
    }

    public EquipoPartido getEquipoLocal() {
        return this.equipoLocal;
    }

    public EquipoPartido getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getHora() {
        return this.hora;
    }
}
