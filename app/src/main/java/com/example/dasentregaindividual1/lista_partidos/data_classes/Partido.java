package com.example.dasentregaindividual1.lista_partidos.data_classes;

public class Partido {

    private EquipoPartido equipoLocal;
    private EquipoPartido equipoVisitante;

    public Partido(EquipoPartido pEquipoLocal, EquipoPartido pEquipoVisitante) {
        this.equipoLocal = pEquipoLocal;
        this.equipoVisitante = pEquipoVisitante;
    }

    public EquipoPartido getEquipoLocal() {
        return equipoLocal;
    }

    public EquipoPartido getEquipoVisitante() {
        return equipoVisitante;
    }
}
