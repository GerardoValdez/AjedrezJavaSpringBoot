package ar.edu.utn.frc.tup.lciii.Ajedrez;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Services.JugadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.regex.Pattern;

@Configuration
public class PartidaAjedrez {

    private JugadorServicio jugadorServicio;
    private Scanner scan = new Scanner(System.in);
    private static boolean blancas = false; //bandera
    private static final String SI_NO_REGEX = "[sSnN]";

    @Autowired
    public PartidaAjedrez(JugadorServicio jugadorServicio) {
        this.jugadorServicio = jugadorServicio;
    }

    public void mensajeBienvenida() {
        System.out.println("Bienvenidos al juego del ajedrez\n");
     }

    public Jugador crearNuevoJugador() {
        if (!blancas) {
            System.out.println("Primero las blancas. ¿Cuál es tu nombre?");
            blancas = true;
            String nombre = scan.nextLine();
            Jugador jugador = new Jugador(nombre, Color.BLANCAS, 0, 39);
            return jugadorServicio.getJugador(jugador);
        } else {
            System.out.println("Jugarás con las negras. ¿Cuál es tu nombre?");
            String nombre = scan.nextLine();
            Jugador jugador = new Jugador(nombre, Color.NEGRAS, 0, 39);
            return jugadorServicio.getJugador(jugador);
        }
    }

    public Boolean jugarDeNuevo() {
        Boolean respuesta = null;
        do {
            System.out.println("¿Quieres volver a jugar? (s/n)");
            String input = scan.nextLine();
            respuesta = respuestaSiNo(input);
        } while (respuesta == null);
        return respuesta;
    }

    private static Boolean respuestaSiNo(String input) {
        Pattern pattern = Pattern.compile(SI_NO_REGEX);
        if (pattern.matcher(input).matches()) {
            if (input.toLowerCase().equals("s"))
                return true;
            else
                return false;
        }
        else {
            System.out.println("Respuesta incorrecta");
            return null;
        }
    }
}