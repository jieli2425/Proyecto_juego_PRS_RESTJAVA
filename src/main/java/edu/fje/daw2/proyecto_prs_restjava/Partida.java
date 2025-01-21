package edu.fje.daw2.proyecto_prs_restjava;

public class Partida {
    private String idPartida;
    private String jugador1;
    private String jugador2;
    private String eleccion1;
    private String eleccion2;
    private String estado;
    private String turno;

    public Partida(String idPartida, String jugador1) {
        this.idPartida = idPartida;
        this.jugador1 = jugador1;
        this.estado = "esperando";
        this.turno = "jugador1";
    }

    // Getters y Setters
    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getEleccion1() {
        return eleccion1;
    }

    public void setEleccion1(String eleccion1) {
        this.eleccion1 = eleccion1;
    }

    public String getEleccion2() {
        return eleccion2;
    }

    public void setEleccion2(String eleccion2) {
        this.eleccion2 = eleccion2;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
