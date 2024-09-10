package es.jortri.generadores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.jortri.generadores.model.SecurityApiKeys;

public interface SecurityApìKeysRepository extends JpaRepository<SecurityApiKeys, Integer> {

	SecurityApiKeys findFirstByApikey(String apikey);	
	
}
