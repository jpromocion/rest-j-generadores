package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Tipovias;

@Repository
public interface TipoviasRepository extends JpaRepository<Tipovias, Integer> {

}
