package es.jortri.generadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Codpostales;

@Repository
public interface CodpostalesRepository extends JpaRepository<Codpostales, Integer> {

	List<Codpostales> findByIdprovinciasAndInemunicipios(String idprovincias, String inemunicipios);
	
	Codpostales findFirstByOrderByIdAsc();
	
	Codpostales findFirstByOrderByIdDesc();		
	
}
