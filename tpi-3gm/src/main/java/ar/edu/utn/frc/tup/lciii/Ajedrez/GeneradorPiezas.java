package ar.edu.utn.frc.tup.lciii.Ajedrez;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas.*;

import java.util.ArrayList;
import java.util.List;

public class GeneradorPiezas {

    public static List<Pieza> generarBlancas(){
        List<Pieza> piezasBlancas = new ArrayList<>();

        Posicion posicionTorre1 = new Posicion(0, 0);
        Torre t1 = new Torre(posicionTorre1, Color.BLANCAS,EstadoPieza.JUGANDO,"♖", NombrePiezas.TORRE, 5);
        piezasBlancas.add(t1);
        Posicion posicionCaballo1 = new Posicion(0, 1);
        Caballo c1 = new Caballo(posicionCaballo1, Color.BLANCAS,EstadoPieza.JUGANDO,"♘", NombrePiezas.CABALLO, 3);
        piezasBlancas.add(c1);
        Posicion posicionAlfil1 = new Posicion(0, 2);
        Alfil a1 = new Alfil(posicionAlfil1, Color.BLANCAS,EstadoPieza.JUGANDO,"♗", NombrePiezas.ALFIL,3);
        piezasBlancas.add(a1);
        Posicion posicionReina = new Posicion(0, 3);
        Reina reina = new Reina(posicionReina, Color.BLANCAS,EstadoPieza.JUGANDO,"♕", NombrePiezas.REINA, 9);
        piezasBlancas.add(reina);
        Posicion posicionRey = new Posicion(0, 4);
        Rey rey = new Rey(posicionRey, Color.BLANCAS,EstadoPieza.JUGANDO,"♔", NombrePiezas.REY, 0);
        piezasBlancas.add(rey);
        Posicion posicionAlfil2 = new Posicion(0, 5);
        Alfil a2 = new Alfil(posicionAlfil2, Color.BLANCAS,EstadoPieza.JUGANDO,"♗", NombrePiezas.ALFIL, 3);
        piezasBlancas.add(a2);
        Posicion posicionCaballo2 = new Posicion(0, 6);
        Caballo c2 = new Caballo(posicionCaballo2, Color.BLANCAS,EstadoPieza.JUGANDO,"♘", NombrePiezas.CABALLO, 3);
        piezasBlancas.add(c2);
        Posicion posicionTorre2 = new Posicion(0, 7);
        Torre t2 = new Torre(posicionTorre2, Color.BLANCAS,EstadoPieza.JUGANDO,"♖", NombrePiezas.TORRE, 5);
        piezasBlancas.add(t2);

        for (int i = 0; i < 8; i++) {
            Posicion posicionPeon = new Posicion(1, i);
            Peon peon = new Peon(posicionPeon, Color.BLANCAS,EstadoPieza.JUGANDO,"♙", NombrePiezas.PEON, 1);

            piezasBlancas.add(peon);
        }

        return piezasBlancas;
    }

    public static List<Pieza> generarNegras(){
        List<Pieza> piezasNegras = new ArrayList<>();

        Posicion posicionTorre1 = new Posicion(7, 0);
        Torre t1 = new Torre(posicionTorre1, Color.NEGRAS,EstadoPieza.JUGANDO,"♜", NombrePiezas.TORRE, 5);
        piezasNegras.add(t1);
        Posicion posicionCaballo1 = new Posicion(7, 1);
        Caballo c1 = new Caballo(posicionCaballo1, Color.NEGRAS,EstadoPieza.JUGANDO,"♞", NombrePiezas.CABALLO, 3);
        piezasNegras.add(c1);
        Posicion posicionAlfil1 = new Posicion(7, 2);
        Alfil a1 = new Alfil(posicionAlfil1, Color.NEGRAS,EstadoPieza.JUGANDO,"♝", NombrePiezas.ALFIL, 3);
        piezasNegras.add(a1);
        Posicion posicionReina = new Posicion(7, 3);
        Reina reina = new Reina(posicionReina, Color.NEGRAS,EstadoPieza.JUGANDO,"♛", NombrePiezas.REINA, 9);
        piezasNegras.add(reina);
        Posicion posicionRey = new Posicion(7, 4);
        Rey rey = new Rey(posicionRey, Color.NEGRAS,EstadoPieza.JUGANDO,"♚", NombrePiezas.REY, 0);
        piezasNegras.add(rey);
        Posicion posicionAlfil2 = new Posicion(7, 5);
        Alfil a2 = new Alfil(posicionAlfil2, Color.NEGRAS,EstadoPieza.JUGANDO,"♝", NombrePiezas.ALFIL, 3);
        piezasNegras.add(a2);
        Posicion posicionCaballo2 = new Posicion(7, 6);
        Caballo c2 = new Caballo(posicionCaballo2, Color.NEGRAS,EstadoPieza.JUGANDO,"♞", NombrePiezas.CABALLO, 3);
        piezasNegras.add(c2);
        Posicion posicionTorre2 = new Posicion(7, 7);
        Torre t2 = new Torre(posicionTorre2, Color.NEGRAS,EstadoPieza.JUGANDO,"♜", NombrePiezas.TORRE, 5);
        piezasNegras.add(t2);

        for (int i = 0; i < 8; i++) {
            Posicion posicionPeon = new Posicion(6, i);
            Peon peon = new Peon(posicionPeon, Color.NEGRAS,EstadoPieza.JUGANDO,"♟", NombrePiezas.PEON, 1);

            piezasNegras.add(peon);
        }

        return piezasNegras;
    }
}