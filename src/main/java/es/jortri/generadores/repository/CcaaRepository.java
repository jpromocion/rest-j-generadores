package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.jortri.generadores.model.Ccaa;

@Repository
public interface CcaaRepository extends JpaRepository<Ccaa, String> {

}
