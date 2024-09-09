package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.NombresEmpresas;

@Repository
public interface NombresEmpresasRepository extends JpaRepository<NombresEmpresas, Integer> {

	NombresEmpresas findFirstByOrderByIdAsc();
	
	NombresEmpresas findFirstByOrderByIdDesc();
	
}
