package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.Ajedrez.JuegoAjedrez;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Jugador;
import ar.edu.utn.frc.tup.lciii.Ajedrez.PartidaAjedrez;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Services.Imp.JugadorServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private JugadorServicioImp jugadorServicioImp;
    @Autowired
    private PartidaAjedrez partidaAjedrez;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Boolean jugarDeNuevo = true;
        Boolean partidaTerminada = null;
        Boolean realizoMovimiento = null;

        System.out.println("Trabajo Práctico Integrador - Grupo 15.\n");

        do {
            partidaAjedrez.mensajeBienvenida();
            Jugador jugador1 = partidaAjedrez.crearNuevoJugador();
            Jugador jugador2 = partidaAjedrez.crearNuevoJugador();
            JuegoAjedrez juegoAjedrez = new JuegoAjedrez(jugador1, jugador2, jugadorServicioImp);

            System.out.println("¡Comencemos a jugar!\n");

            juegoAjedrez.dibujarTablero();

            do {
                do {
                    realizoMovimiento = juegoAjedrez.obtenerMovimientoJugador();
                    juegoAjedrez.dibujarTablero();

                } while (!realizoMovimiento);

                partidaTerminada = juegoAjedrez.terminoElJuego();

            } while (!partidaTerminada);

            jugarDeNuevo = partidaAjedrez.jugarDeNuevo();
            juegoAjedrez.imprimirResultados();
            juegoAjedrez.mensajeDespedida();

        } while (jugarDeNuevo);
    }
}