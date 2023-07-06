package ar.edu.utn.frc.tup.lciii.Ajedrez;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas.Pieza;
import lombok.Data;

import java.util.List;

@Data
public class Tablero {

    private String[][] tablero;
    private static final Integer ROWS = 8;
    private static final Integer COLUMNS = 8;

    public Tablero(){
        this.tablero = new String[ROWS][COLUMNS];
    }

    public void limpiarTablero() {

        for(int f = 0; f < ROWS; f++) {
            for(int c = 0; c < COLUMNS; c++) {
                this.tablero[f][c] = "⼺";
            }
        }
    }

    public void colocarPiezas(List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        limpiarTablero();
        colocarPiezasEnTablero(piezasBlancas);
        colocarPiezasEnTablero(piezasNegras);
    }

    private void colocarPiezasEnTablero(List<Pieza> piezas) {
        for (Pieza pieza : piezas) {
            Posicion posicion = pieza.getPosicion();
            int fila = posicion.getFila();
            int columna = posicion.getColumna();

            if (fila >= 0 && fila < ROWS && columna >= 0 && columna < COLUMNS) {
                if (pieza.getEstado().equals(EstadoPieza.JUGANDO)) {
                    tablero[fila][columna] = pieza.getIcono();
                } else {
                    tablero[fila][columna] = "⼺";
                }
            }
        }
    }

    public boolean casillaEstaVacia(Posicion posicion) {
        return this.tablero[posicion.getFila()][posicion.getColumna()].equals("⼺");
    }
}