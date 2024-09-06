package es.jortri.generadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Provincias;

@Repository
public interface ProvinciasRepository extends JpaRepository<Provincias, String> {

	List<Provincias> findByIdccaa(String idccaa);
	
}
