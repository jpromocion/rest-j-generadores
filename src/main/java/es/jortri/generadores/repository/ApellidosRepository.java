package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Apellidos;

@Repository
public interface ApellidosRepository extends JpaRepository<Apellidos, Integer> {

	Apellidos findFirstByOrderByIdAsc();
	
	Apellidos findFirstByOrderByIdDesc();
	
}
