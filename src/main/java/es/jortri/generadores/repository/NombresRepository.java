package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Nombres;

@Repository
public interface NombresRepository extends JpaRepository<Nombres, Integer> {
	
	Nombres findFirstByOrderByIdAsc();
	
	Nombres findFirstByOrderByIdDesc();
	
	
	Nombres findFirstByGeneroOrderByIdAsc(String genero);
	
	Nombres findFirstByGeneroOrderByIdDesc(String genero);	

}
