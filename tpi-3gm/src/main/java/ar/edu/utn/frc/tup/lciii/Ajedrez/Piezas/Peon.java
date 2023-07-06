package ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Color;
import ar.edu.utn.frc.tup.lciii.Ajedrez.EstadoPieza;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Posicion;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Peon extends Pieza {

    @Builder
    public Peon(Posicion posicion, Color colorPieza, EstadoPieza estado, String icono, NombrePiezas nombre, Integer valor) {
        super(posicion, colorPieza, estado, icono, nombre, valor);
    }

    private boolean iteradorDePiezasBool (List<Pieza> listaDePiezas, Posicion posPieza) {
        Iterator<Pieza> iterador =  listaDePiezas.iterator();
        while (iterador.hasNext()){
            Pieza pieza = iterador.next();
            if (posPieza.getFila() == pieza.getPosicion().getFila() && posPieza.getColumna() == pieza.getPosicion().getColumna()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Posicion> posiblesMovimientos(Posicion posicionActual){
        List<Posicion> movimientos = new ArrayList<>();
        return  movimientos;
    }

    @Override
    public boolean validarMovimiento(Posicion origen, Posicion destino, Color color, Boolean casillaVacia, List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        int direccionMovimiento = (color == Color.BLANCAS) ? 1 : -1;
        int filaSiguiente = origen.getFila() + direccionMovimiento;

        if (destino.getFila() == filaSiguiente && origen.getColumna() == destino.getColumna() && casillaVacia) {
            return true;
        }
        if (origen.getFila() == 1 && destino.getFila() == 3 && origen.getColumna() == destino.getColumna() && casillaVacia && color == Color.BLANCAS) {
            return true;
        }
        if (origen.getFila() == 6 && destino.getFila() == 4 && origen.getColumna() == destino.getColumna() && casillaVacia && color == Color.NEGRAS) {
            return true;
        }
        if (destino.getFila() == filaSiguiente && Math.abs(destino.getColumna() - origen.getColumna()) == 1 && !casillaVacia) {
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

        if (origen.getFila() == 1 && destino.getFila() == 3 && origen.getColumna() == destino.getColumna()) {
            Posicion casillaIntermedia = new Posicion(2, origen.getColumna());
            return !iteradorDePiezasBool(piezasBlancas, casillaIntermedia) && !iteradorDePiezasBool(piezasNegras, casillaIntermedia);
        }
        if (origen.getFila() == 6 && destino.getFila() == 4 && origen.getColumna() == destino.getColumna()) {
            Posicion casillaIntermedia = new Posicion(5, origen.getColumna());
            return !iteradorDePiezasBool(piezasBlancas, casillaIntermedia) && !iteradorDePiezasBool(piezasNegras, casillaIntermedia);
        }
        return true;
    }
}