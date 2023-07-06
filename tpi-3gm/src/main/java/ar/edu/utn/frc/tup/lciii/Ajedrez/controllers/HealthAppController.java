package ar.edu.utn.frc.tup.lciii.Ajedrez.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthAppController {
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
