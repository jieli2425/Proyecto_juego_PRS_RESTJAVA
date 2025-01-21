package edu.fje.daw2.proyecto_prs_restjava;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/")
public class Juego {

    private static final ConcurrentHashMap<String, Partida> partidas = new ConcurrentHashMap<>();
    private static final Opciones opciones = new Opciones();

    @POST
    @Path("/iniciarJoc/{idPartida}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarPartida(@PathParam("idPartida") String idPartida, @FormParam("nombre") String nombre) {
        if (idPartida == null || nombre == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos: idPartida o nombre").build();
        }

        if (!partidas.containsKey(idPartida)) {
            if ("jugador1".equals(nombre)) {
                partidas.put(idPartida, new Partida(idPartida, nombre));
                return Response.ok().entity("Partida creada. Esperando jugador 2.").build();
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Partida no encontrada").build();
        }

        Partida partida = partidas.get(idPartida);
        if (partida.getJugador2() == null && "jugador2".equals(nombre)) {
            partida.setJugador2(nombre);
            partida.setEstado("en curso");
            return Response.ok().entity("Jugador 2 registrado. ¡Comienza el juego!").build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("La partida ya está completa o no puedes unirte como este jugador").build();
    }

    @PUT
    @Path("/moureJugador/{idPartida}/{jugador}/{eleccion}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response realizarMovimiento(@PathParam("idPartida") String idPartida,
                                       @PathParam("jugador") String jugador, @PathParam("eleccion") String eleccion) {
        if (!opciones.esMovimientoValido(eleccion)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Movimiento inválido").build();
        }

        Partida partida = partidas.get(idPartida);
        if (partida == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Partida no encontrada").build();
        }

        if (!jugador.equals(partida.getTurno())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No es tu turno").build();
        }

        if ("jugador1".equals(jugador)) {
            partida.setEleccion1(eleccion);
        } else {
            partida.setEleccion2(eleccion);
        }

        partida.setTurno("jugador1".equals(jugador) ? "jugador2" : "jugador1");

        if (partida.getEleccion1() != null && partida.getEleccion2() != null) {
            String resultado = opciones.evaluarResultado(partida.getEleccion1(), partida.getEleccion2());
            partida.setEstado(resultado);
            partida.setEleccion1(null);
            partida.setEleccion2(null);
            return Response.ok("Resultado: " + resultado).build();
        }

        return Response.ok("Esperando al otro jugador...").build();
    }

    @GET
    @Path("/consultarEstatPartida/{idPartida}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarEstadoPartida(@PathParam("idPartida") String idPartida) {
        Partida partida = partidas.get(idPartida);
        if (partida == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Partida no encontrada").build();
        }

        return Response.ok(partida).build();
    }

    @DELETE
    @Path("/acabarJoc/{idPartida}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response finalizarPartida(@PathParam("idPartida") String idPartida) {
        if (partidas.remove(idPartida) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Partida no encontrada").build();
        }
        return Response.ok("Partida eliminada con éxito").build();
    }
}
