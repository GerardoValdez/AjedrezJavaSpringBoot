package ar.edu.utn.frc.tup.lciii.Ajedrez.Services.Imp;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Entities.JugadorEntity;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Jugador;
import ar.edu.utn.frc.tup.lciii.Ajedrez.RepositoriesJpa.JugadorJpaRepository;
import ar.edu.utn.frc.tup.lciii.Ajedrez.Services.JugadorServicio;
import ar.edu.utn.frc.tup.lciii.Ajedrez.dto.JugadorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorServicioImp implements JugadorServicio {

    @Autowired
    private JugadorJpaRepository jugadorJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelMapper mergerMapper;


    @Override
    public Jugador createJugador(Jugador jugador) {

        JugadorEntity jugadorEntity = modelMapper.map(jugador, JugadorEntity.class);
        JugadorEntity savedJugadorEntity= jugadorJpaRepository.save(jugadorEntity);
        Jugador savedJugador = modelMapper.map(savedJugadorEntity,Jugador.class);
        return savedJugador;
    }

    @Override
    public Jugador getJugador(Jugador jugador) {
       Optional<JugadorEntity> optionalJugadorEntity = Optional.ofNullable(jugadorJpaRepository.findByNombre(jugador.getNombre()));

        if(optionalJugadorEntity.isPresent()){
            JugadorEntity jugadorEntity = optionalJugadorEntity.get();
            return modelMapper.map(jugadorEntity,Jugador.class);
        }
        else
            return createJugador(jugador);
    }

    @Override
    public void sumarPartidaGanada(Jugador jugador) {
        JugadorEntity jugadorEntity = jugadorJpaRepository.findByNombre(jugador.getNombre());
        jugadorEntity.setPartidasGanadas(jugadorEntity.getPartidasGanadas() + 1);
        jugadorJpaRepository.save(jugadorEntity);
    }

    @Override
    public List<JugadorDTO> getAllUsers() {
        List<JugadorDTO> jugadores = new ArrayList<>();
        List<JugadorEntity> userEntities = jugadorJpaRepository.findAll();

        userEntities.forEach(jugadorEntity -> {JugadorDTO jugadorDTO = modelMapper.map(jugadorEntity, JugadorDTO.class);
            jugadores.add(jugadorDTO);
        });
        return jugadores;
    }
}