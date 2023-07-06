package ar.edu.utn.frc.tup.lciii.Ajedrez;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas.NombrePiezas;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas.Pieza;


import ar.edu.utn.frc.tup.lciii.Ajedrez.Services.JugadorServicio;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ar.edu.utn.frc.tup.lciii.Ajedrez.GeneradorPiezas.generarBlancas;
import static ar.edu.utn.frc.tup.lciii.Ajedrez.GeneradorPiezas.generarNegras;

@Data
@NoArgsConstructor
public class JuegoAjedrez {

    private JugadorServicio jugadorServicio;
    private Scanner scan = new Scanner(System.in);
    private Jugador jugadorblancas;
    private Jugador jugadorNegras;
    private List<Pieza> piezasBlancas;
    private List<Pieza> piezasNegras;
    private Tablero tablero;
    private Color turnoActual;

    private static final String COORDENADA_REGEX = "[0-7]{1} [0-7]{1}";

    public JuegoAjedrez(Jugador jugadorBlancas, Jugador jugadorNegras, JugadorServicio jugadorServicio) {
        this.jugadorblancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        this.piezasBlancas = generarBlancas();
        this.piezasNegras = generarNegras();
        this.tablero = new Tablero();
        this.tablero.limpiarTablero();
        this.tablero.colocarPiezas(this.piezasBlancas, this.piezasNegras);
        this.turnoActual = Color.BLANCAS;
        this.jugadorServicio = jugadorServicio;
    }

    public void cambiarTurno() {
        if (turnoActual == Color.BLANCAS) {
            turnoActual = Color.NEGRAS;
        } else {
            turnoActual = Color.BLANCAS;
        }
    }

    public Boolean obtenerMovimientoJugador() {
        Posicion posicionMovimiento = null;

        do {
            System.out.println("Mueven las " + getTurnoActual());
            System.out.println("Selecione la pieza que deseas mover mediante una coordenada\n" +
                    "con formato de dos numeros enteros entre 0 y 7 separados\n" +
                    "por un espacio en blanco.");
            String piezaSeleccionada = scan.nextLine();

            if (validarCoordenada(piezaSeleccionada)) {

                if (validarSeleccion(piezaSeleccionada)) {
                    System.out.println("\nIndique la posición donde quiere moverla mediante una coordenada\n" +
                            "con formato de dos numeros enteros entre 0 y 7 separados\n" +
                            "por un espacio en blanco.");
                    Pieza piezaEnJuego = detectarPieza(piezaSeleccionada);
                    String movimientoSeleccionado = scan.nextLine();

                    if (validarCoordenada(movimientoSeleccionado)) {
                        String[] coordenadasMovimiento = movimientoSeleccionado.split(" ");
                        posicionMovimiento = new Posicion(Integer.parseInt(coordenadasMovimiento[0]), Integer.parseInt(coordenadasMovimiento[1]));

                        if (validarPosicion(posicionMovimiento, piezaEnJuego)) {
                            ejecutarMovimiento(posicionMovimiento, piezaEnJuego);
                            return true;

                        } else{
                            System.out.println();
                            System.out.println("No se puede mover la pieza a esa posición.");
                            System.out.println();
                            return false;
                        }
                    } else{
                        System.out.println();
                        System.out.println("Datos ingresados de manera incorrecta.");
                        System.out.println();
                        return false;
                    }
                } else{
                    System.out.println();
                    System.out.println("No se puede mover la pieza a esa posición.");
                    System.out.println();
                    return false;
                }
            } else{
                System.out.println();
                System.out.println("Datos ingresados de manera incorrecta.");
                System.out.println();
                return false;
            }

        } while (posicionMovimiento == null);
    }

    private Boolean validarCoordenada(String coordenada) {
        Pattern pattern = Pattern.compile(COORDENADA_REGEX);
        if (pattern.matcher(coordenada).matches()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validarPosicion(Posicion posicionAValidar, Pieza piezaEnJuego) {
        Boolean casillaVacia = this.tablero.casillaEstaVacia(posicionAValidar);

        Boolean condicionA = piezaEnJuego.validarMovimiento(piezaEnJuego.getPosicion(), posicionAValidar, piezaEnJuego.getColorPieza(), casillaVacia, piezasBlancas, piezasNegras);
        Boolean condicionB = piezaEnJuego.verificarCaminoLibre(piezaEnJuego.getPosicion(), posicionAValidar, piezasBlancas, piezasNegras);
        return condicionA && condicionB;
    }

    private Boolean iteradorDePiezasBool (List<Pieza> listaDePiezas, Posicion posPieza) {
        Iterator<Pieza> iterador =  listaDePiezas.iterator();
        while (iterador.hasNext()){
            Pieza pieza = iterador.next();
            if (posPieza.getFila() == pieza.getPosicion().getFila() && posPieza.getColumna() == pieza.getPosicion().getColumna()) {
                return true;
            }
        }
        return false;
    }

    private Pieza iteradorDePiezas(List<Pieza> listaDePiezas, Posicion posPieza) {
        Iterator<Pieza> iterador =  listaDePiezas.iterator();
        while (iterador.hasNext()){
            Pieza pieza = iterador.next();
            if (posPieza.getFila() == pieza.getPosicion().getFila() && posPieza.getColumna() == pieza.getPosicion().getColumna()) {
                return pieza;
            }
        }
        throw new Error("No tienes esa pieza en juego");
    }

    private Pieza iteradorDePiezasEnemigas(List<Pieza> listaDePiezas, Posicion posPieza) {
        Iterator<Pieza> iterador =  listaDePiezas.iterator();
        while (iterador.hasNext()){
            Pieza pieza = iterador.next();
            if (posPieza.getFila() == pieza.getPosicion().getFila() && posPieza.getColumna() == pieza.getPosicion().getColumna()) {
                return pieza;
            }
        }
        return null;
    }

    private Pieza detectarPieza(String posicionAValidar) {
        String[] coorPieza = posicionAValidar.split(" ");
        Posicion posPieza = new Posicion(Integer.parseInt(coorPieza[0]), Integer.parseInt(coorPieza[1]));
           if (this.turnoActual.equals(Color.BLANCAS)) {
              return this.iteradorDePiezas(this.piezasBlancas, posPieza);
           } else {
              return this.iteradorDePiezas(this.piezasNegras, posPieza);
           }
    }

    private boolean validarSeleccion(String seleccion) {
        String[] coordenadas = seleccion.split(" ");
        Posicion seleccionAValidar = new Posicion(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));
        if (this.turnoActual.equals(Color.BLANCAS)) {
            return this.iteradorDePiezasBool(this.piezasBlancas, seleccionAValidar);
        } else {
            return this.iteradorDePiezasBool(this.piezasNegras, seleccionAValidar);
        }
    }

    public void ejecutarMovimiento(Posicion posicionMovimiento, Pieza piezaEnJuego) {
        List<Pieza> piezasEnemigas = getPiezasEnemigas();
        Pieza piezaEnemiga = iteradorDePiezasEnemigas(piezasEnemigas, posicionMovimiento);

        if (piezaEnemiga != null) {
            piezaEnemiga.setEstado(EstadoPieza.COMIDO);
        }

        List<Pieza> piezasBlancasEnJuego = obtenerPiezasBlancasEnJuego();
        List<Pieza> piezasNegrasEnJuego = obtenerPiezasNegrasEnJuego();
        piezaEnJuego.setPosicion(posicionMovimiento);
        tablero.limpiarTablero();
        tablero.colocarPiezas(piezasBlancasEnJuego, piezasNegrasEnJuego);
        System.out.println("Movimiento realizado");
    }

    private List<Pieza> getPiezasEnemigas() {
        return (turnoActual.equals(Color.BLANCAS)) ? piezasNegras : piezasBlancas;
    }

    private List<Pieza> obtenerPiezasBlancasEnJuego() {
        return piezasBlancas.stream()
                .filter(pieza -> pieza.getEstado().equals(EstadoPieza.JUGANDO))
                .collect(Collectors.toList());
    }

    private List<Pieza> obtenerPiezasNegrasEnJuego() {
        return piezasNegras.stream()
                .filter(pieza -> pieza.getEstado().equals(EstadoPieza.JUGANDO))
                .collect(Collectors.toList());
    }

    public boolean terminoElJuego() {
        Pieza rey = encontrarRey();
        if (jaqueMate()) {
            jugadorServicio.sumarPartidaGanada(obtenerJugador());
            System.out.println("La partida ha finalizado, las " + this.turnoActual + " ganan");
            return true;
        } else if (reyEstaEnJaque(rey)) {
            System.out.println("JAQUE!");
            cambiarTurno();
            return false;
        } else {
            cambiarTurno();
            return false;
        }
    }

    public void imprimirResultados() {

        System.out.println("Resultado");
    }

    public void mensajeDespedida() {
        System.out.println("Gracias por haber jugado! Nos vemos en la próxima");
    }

    public void dibujarTablero() {
        StringBuilder sb = new StringBuilder();
        sb.append("    0    1   2    3    4   5    6    7   \n");
        sb.append("  --------------------------------------\n");
        for (int f = 0; f < 8; f++) {
            sb.append(f + " |");
            for (int c = 0; c < 8; c++) {
                sb.append(" " + tablero.getTablero()[f][c] + "\u001B[0m" + " ");
                sb.append("|");
            }
            sb.append("  " + f + "\n");
            sb.append("  --------------------------------------\n");
        }
        sb.append("    0    1   2    3    4   5    6    7   \n");
        System.out.println(sb);
    }

    private boolean reyEstaEnJaque(Pieza rey) {
        List<Pieza> piezasEnemigo = this.turnoActual == Color.BLANCAS ? this.piezasBlancas : this.piezasNegras;

        for (Pieza pieza : piezasEnemigo) {
            List<Posicion> movimientosPieza = pieza.posiblesMovimientos(pieza.getPosicion());
            if (movimientosPieza.contains(rey.getPosicion())) {

                return true;
            }
        }
        return false;
    }

    private Pieza piezaQueHaceJaque(Pieza rey) {
        List<Pieza> piezasEnemigo = this.turnoActual == Color.BLANCAS ? this.piezasBlancas : this.piezasNegras;

        for (Pieza pieza : piezasEnemigo) {
            List<Posicion> movimientosPieza = pieza.posiblesMovimientos(pieza.getPosicion());
            if (movimientosPieza.contains(rey.getPosicion())) {

                return pieza;
            }
        }
        return null;
    }

    private Pieza encontrarRey(){
        List<Pieza> listaPiezas = this.turnoActual == Color.BLANCAS ? this.piezasNegras : this.piezasBlancas;

        for (Pieza pieza : listaPiezas) {
            if (pieza.getNombre() == NombrePiezas.REY) {
                return pieza;
            }
        }
        return null;
    }

    private boolean jaqueDespMovimiento(Pieza rey, Posicion posiblePosicion){
        Posicion posicionRey = rey.getPosicion();
        rey.setPosicion(posiblePosicion);
        Boolean esJaque = reyEstaEnJaque(rey);
        rey.setPosicion(posicionRey);
        return esJaque;
    }

    private boolean piezaBloqueaJaque(Pieza pieza, Posicion posiblePosicion) {
        Pieza rey = encontrarRey();
        Posicion posicionPieza = pieza.getPosicion();
        pieza.setPosicion(posiblePosicion);
        Boolean bloqueaJaque = !reyEstaEnJaque(rey);
        pieza.setPosicion(posicionPieza);
        return bloqueaJaque;
    }

    private boolean jaqueMate(){
        Pieza rey = encontrarRey();
        List<Posicion> movimientosRey = rey.posiblesMovimientos(rey.getPosicion());
        List<Pieza> piezasPropias = this.turnoActual == Color.BLANCAS ? this.piezasNegras : this.piezasBlancas;

        if (!reyEstaEnJaque(rey)) {
            return false;
        }

        for (Posicion mov : movimientosRey) {
            Boolean vacia = this.tablero.casillaEstaVacia(mov);
            if (vacia) {
                if (!jaqueDespMovimiento(rey, mov)) {
                    return false;
                }
            }
        }

        for (Pieza pieza : piezasPropias) {
            List<Posicion> movimientosPieza = pieza.posiblesMovimientos(pieza.getPosicion());
            for (Posicion movimiento : movimientosPieza) {
                Boolean vacia = this.tablero.casillaEstaVacia(movimiento);
                if (vacia) {
                    if (piezaBloqueaJaque(pieza, movimiento)) {
                        return false;
                    }
                }
            }
        }

        Pieza piezaAtaque = piezaQueHaceJaque(rey);
        piezasPropias.remove(rey);

        for (Pieza pieza : piezasPropias) {
            List<Posicion> movimientosPieza = pieza.posiblesMovimientos(pieza.getPosicion());
            for (Posicion movimiento : movimientosPieza) {
                if (movimiento.equals(piezaAtaque.getPosicion())) {
                    return false;
                }
            }
        }
        piezasPropias.add(rey);
        return true;
    }

    public Jugador obtenerJugador(){
        return turnoActual == Color.BLANCAS ? jugadorblancas: jugadorNegras;
    }
}