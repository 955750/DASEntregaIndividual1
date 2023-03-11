package com.example.dasentregaindividual1.data.database.modelos;

public class Partido {

    private EquipoPartido[] equipos;
    private String fecha;
    private String hora;

    public Partido(
        EquipoPartido[] pEquipos,
        String pFecha,
        String pHora
    ) {
        this.equipos = pEquipos;
        this.fecha = pFecha;
        this.hora = pHora;
    }

    public EquipoPartido[] getEquipos() {
        return this.equipos;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getHora() {
        return this.hora;
    }
}
