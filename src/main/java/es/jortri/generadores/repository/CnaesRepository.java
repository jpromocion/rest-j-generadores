package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.jortri.generadores.model.Cnaes;


public interface CnaesRepository extends JpaRepository<Cnaes, Integer> {

	Cnaes findFirstByOrderByIdAsc();
	
	Cnaes findFirstByOrderByIdDesc();	
	
}
