package com.example.dasentregaindividual1.data.base_de_datos.modelos;

public class EquipoPartido {

    private int escudoId;
    private String nombre;
    private String rachaUltimosPartidos;
    private int puntos;
    private int local;

    public EquipoPartido(
        int pEscudo,
        String pNombre,
        String pRachaUltimosPartidos,
        int pPuntos,
        int pLocal
    ) {
        this.escudoId = pEscudo;
        this.nombre = pNombre;
        this.rachaUltimosPartidos = pRachaUltimosPartidos;
        this.puntos = pPuntos;
        this.local = pLocal;
    }

    public int getEscudoId() {
        return escudoId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRachaUltimosPartidos() {
        return rachaUltimosPartidos;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getLocal() {
        return local;
    }
}
