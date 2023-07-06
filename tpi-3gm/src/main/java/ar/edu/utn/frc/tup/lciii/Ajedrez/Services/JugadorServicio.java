package ar.edu.utn.frc.tup.lciii.Ajedrez.Services;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Jugador;
import ar.edu.utn.frc.tup.lciii.Ajedrez.dto.JugadorDTO;

import java.util.List;

public interface JugadorServicio {

    Jugador createJugador(Jugador jugador);
    Jugador getJugador(Jugador jugador);
    void sumarPartidaGanada(Jugador jugador);
    List<JugadorDTO> getAllUsers();
}