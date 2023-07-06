package ar.edu.utn.frc.tup.lciii.Ajedrez.Piezas;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Color;
import ar.edu.utn.frc.tup.lciii.Ajedrez.EstadoPieza;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Posicion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pieza {

    private Posicion posicion;
    private Color colorPieza;
    private EstadoPieza estado;
    private String icono;
    private NombrePiezas nombre;
    private Integer valor;

    public abstract boolean validarMovimiento(Posicion origen, Posicion destino, Color color, Boolean casillaVacia, List<Pieza> piezasBlancas, List<Pieza> piezasNegras);
    public abstract List<Posicion> posiblesMovimientos(Posicion posicionActual);
    public abstract boolean verificarCaminoLibre(Posicion origen, Posicion destino, List<Pieza> piezasBlancas, List<Pieza> piezasNegras);
}