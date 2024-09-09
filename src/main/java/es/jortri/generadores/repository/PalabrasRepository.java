package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Palabras;

@Repository
public interface PalabrasRepository extends JpaRepository<Palabras, Integer> {

	Palabras findFirstByOrderByIdAsc();
	
	Palabras findFirstByOrderByIdDesc();
	
	
}
