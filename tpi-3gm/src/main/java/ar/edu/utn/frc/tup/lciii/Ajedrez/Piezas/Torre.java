package ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Color;
import ar.edu.utn.frc.tup.lciii.Ajedrez.EstadoPieza;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Posicion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
public class Torre extends Pieza {

    private int[][] movimientosTorre = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1}
    };

    @Builder
    public Torre(Posicion posicion, Color colorPieza, EstadoPieza estado, String icono, NombrePiezas nombre, Integer valor) {
        super(posicion, colorPieza, estado, icono, nombre, valor);
    }

    private boolean casillaValida(Posicion posicion) {
        String coordenada = posicion.getFila() + " " + posicion.getColumna();
        Pattern pattern = Pattern.compile("[0-7]{1} [0-7]{1}");
        return pattern.matcher(coordenada).matches();
    }

    private boolean iteradorDePiezasBool(List<Pieza> listaDePiezas, Posicion posPieza) {
        for (Pieza pieza : listaDePiezas) {
            if (Objects.equals(posPieza.getFila(), pieza.getPosicion().getFila()) && Objects.equals(posPieza.getColumna(), pieza.getPosicion().getColumna())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Posicion> posiblesMovimientos(Posicion posicionActual) {
        List<Posicion> movimientos = new ArrayList<>();

        for (int[] coordenada : movimientosTorre) {
            int fila = posicionActual.getFila() + coordenada[0];
            int columna = posicionActual.getColumna() + coordenada[1];
            Posicion posicion = new Posicion(fila, columna);
            while (casillaValida(posicion)) {
                movimientos.add(posicion);
                fila += coordenada[0];
                columna += coordenada[1];
                posicion = new Posicion(fila, columna);
            }
        }
        return movimientos;
    }

    @Override
    public boolean validarMovimiento(Posicion origen, Posicion destino, Color color, Boolean casillaVacia, List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        List<Posicion> posiblesMovimientos = posiblesMovimientos(origen);
        if (posiblesMovimientos.contains(destino) && casillaVacia) {
            return true;
        }
        if (posiblesMovimientos.contains(destino) && !casillaVacia) {
            if (color == Color.BLANCAS && iteradorDePiezasBool(piezasNegras, destino)) {
                return true;
            }
            if (color == Color.NEGRAS && iteradorDePiezasBool(piezasBlancas, destino)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verificarCaminoLibre(Posicion origen, Posicion destino, List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        int diferenciaFilas = destino.getFila() - origen.getFila();
        int diferenciaColumnas = destino.getColumna() - origen.getColumna();

        int incrementoFila = (diferenciaFilas != 0) ? diferenciaFilas / Math.abs(diferenciaFilas) : 0;
        int incrementoColumna = (diferenciaColumnas != 0) ? diferenciaColumnas / Math.abs(diferenciaColumnas) : 0;

        int filaActual = origen.getFila() + incrementoFila;
        int columnaActual = origen.getColumna() + incrementoColumna;

        while (filaActual != destino.getFila() || columnaActual != destino.getColumna()) {
            Posicion posicionActual = new Posicion(filaActual, columnaActual);
            if (iteradorDePiezasBool(piezasBlancas, posicionActual) || iteradorDePiezasBool(piezasNegras, posicionActual)) {
                return false;
            }
            filaActual += incrementoFila;
            columnaActual += incrementoColumna;
        }
        return true;
    }
}