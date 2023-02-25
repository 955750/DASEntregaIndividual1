package com.example.dasentregaindividual1.lista_partidos.data_classes;

public class EquipoPartido {

    private int escudo;
    private String nombre;
    private String rachaUltimosPartidos;
    private int puntos;

    public EquipoPartido(int pEscudo, String pNombre, String pRachaUltimosPartidos, int pPuntos) {
        this.escudo = pEscudo;
        this.nombre = pNombre;
        this.rachaUltimosPartidos = pRachaUltimosPartidos;
        this.puntos = pPuntos;
    }

    public int getEscudo() {
        return escudo;
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
}
