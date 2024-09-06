package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Bancos;

@Repository
public interface BancosRepository extends JpaRepository<Bancos, Integer> {

	Bancos findFirstByCodigo(String codigo);
	
}
