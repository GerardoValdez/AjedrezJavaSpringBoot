package ar.edu.utn.frc.tup.lciii.Ajedrez.controllers;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Jugador;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Services.JugadorServicio;
import ar.edu.utn.frc.tup.lciii.Ajedrez.dto.JugadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui.html
@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/jugadores")
public class JugadorController {
    @Autowired
    JugadorServicio jugadorServicio;

    @GetMapping("/getAllJugadores")
    public ResponseEntity<List<JugadorDTO>> getAllUser(){
        try {
            var jugadores = jugadorServicio.getAllUsers();
                return ResponseEntity.status(HttpStatus.OK).body(jugadores);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}