package ar.edu.utn.frc.tup.lciii.Ajedrez;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {

    private String nombre;
    private Color color;
    private Integer partidasGanadas;
    private Integer puntos;
}