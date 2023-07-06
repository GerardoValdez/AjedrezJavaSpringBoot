package ar.edu.utn.frc.tup.lciii.Ajedrez.Entities;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "jugadores")
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Color color;
    @Column
    private String nombre;
    @Column
    private Integer partidasGanadas;
}