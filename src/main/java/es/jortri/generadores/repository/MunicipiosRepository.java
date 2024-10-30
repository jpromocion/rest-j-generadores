package es.jortri.generadores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Municipios;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Integer> {

	List<Municipios> findByIdprovincias(String idprovincias);
	
	Municipios findFirstByOrderByIdAsc();
	
	Municipios findFirstByOrderByIdDesc();	
	
	Municipios findFirstByIdprovinciasAndCodigoine(String idprovincias, String codigoine);
	
	
}
