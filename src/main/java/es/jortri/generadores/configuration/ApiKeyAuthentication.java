package es.jortri.generadores.configuration;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * Transformacion del API Key de seguridad en un objeto Authentication para spring boot.
 * Referencia: https://www.baeldung.com/spring-boot-api-key-secret
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken {
	
    private final String apiKey;

    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }
	
	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return apiKey;
	}

}
