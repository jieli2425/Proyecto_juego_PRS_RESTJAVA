package edu.fje.daw2.proyecto_prs_restjava;

import java.util.HashMap;
import java.util.Map;

public class Opciones {
    private final Map<String, Map<String, String>> reglas;

    public Opciones() {
        reglas = new HashMap<>();
        reglas.put("piedra", new HashMap<>());
        reglas.put("papel", new HashMap<>());
        reglas.put("tijera", new HashMap<>());

        reglas.get("piedra").put("piedra", "empate");
        reglas.get("piedra").put("papel", "perdido");
        reglas.get("piedra").put("tijera", "victoria");

        reglas.get("papel").put("papel", "empate");
        reglas.get("papel").put("tijera", "perdido");
        reglas.get("papel").put("piedra", "victoria");

        reglas.get("tijera").put("tijera", "empate");
        reglas.get("tijera").put("piedra", "perdido");
        reglas.get("tijera").put("papel", "victoria");
    }

    public String evaluarResultado(String eleccion1, String eleccion2) {
        if (!reglas.containsKey(eleccion1) || !reglas.containsKey(eleccion2)) {
            throw new IllegalArgumentException("Movimiento inválido");
        }
        return reglas.get(eleccion1).get(eleccion2);
    }

    public boolean esMovimientoValido(String movimiento) {
        return reglas.containsKey(movimiento);
    }
}
