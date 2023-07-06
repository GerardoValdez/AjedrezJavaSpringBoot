package ar.edu.utn.frc.tup.lciii.Ajedrez.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDTO {
    private String nombre;
    private int partidasGanadas;
}