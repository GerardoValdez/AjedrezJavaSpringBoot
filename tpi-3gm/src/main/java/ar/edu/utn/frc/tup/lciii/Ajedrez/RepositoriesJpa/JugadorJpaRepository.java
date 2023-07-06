package ar.edu.utn.frc.tup.lciii.Ajedrez.RepositoriesJpa;

import ar.edu.utn.frc.tup.lciii.Ajedrez.Entities.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorJpaRepository extends JpaRepository<JugadorEntity,Long> {

    JugadorEntity findByNombre(String nombre);
}
