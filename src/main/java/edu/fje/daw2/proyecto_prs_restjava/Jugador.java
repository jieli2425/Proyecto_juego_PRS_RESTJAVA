package edu.fje.daw2.proyecto_prs_restjava;

public class Jugador {
    private String nombre;

    public Jugador() {}

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
