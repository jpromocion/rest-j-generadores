package es.jortri.generadores.services;

import java.util.Enumeration;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import es.jortri.generadores.configuration.ApiKeyAuthentication;
import es.jortri.generadores.model.SecurityApiKeys;
import es.jortri.generadores.repository.SecurityApìKeysRepository;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Servicio donde vamos a delegar la comprobacion a realizar sobre la API key
 * de spring security, para dar por valido o no una conexion. 
 * Básicamente la peticion HTTP debe tener un Header con una key "X-API-KEY"
 * cuyo valor comprobaremos aqui que sea uno de los que demos por valido: de momento
 * simplemente los registramos en el AUTH_TOKEN -> finalmente nos los llevamos
 * a la bbdd
 * 
 * Referencia: https://www.baeldung.com/spring-boot-api-key-secret
 * Referencia 2: https://smattme.com/posts/spring-boot-api-key-authentication/
 *   -Para permitir hacer uso del SecurityApìKeysRepository (comprobar keys en la bbdd) sin que de Access Denied
 */
@Service
public class AuthenticationService {
	
	private SecurityApìKeysRepository securityApìKeysRepository;		
	
	//evitar llamarla cada peticion, cargamos en el constructor todo en una lista
	//y en repitadas llamadas solo consultamos la lista
	//NOTA: esto probablemente hara que si cambiamos las api-keys haya que levantar la aplicacion
	//de nuevo
	private List<SecurityApiKeys> listaApiCargada = null;
	
	
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    //private static final String AUTH_TOKEN = "jortri0105";
    //private static String AUTH_TOKEN[] = { "jortri0105", "test189752" };
  
    public AuthenticationService(SecurityApìKeysRepository securityApìKeysRepository) {
        this.securityApìKeysRepository = securityApìKeysRepository;
        this.listaApiCargada = this.securityApìKeysRepository.findAll();
    }    
    
    
    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        //mostrar en log todos los valores del header
//        Enumeration<String> headerNames = request.getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//		}
        
        
        //if (apiKey == null || !Arrays.asList(AUTH_TOKEN).contains(apiKey)) {
        SecurityApiKeys secApiKey = null;
        if (apiKey != null) {
        	//evitar llamarla cada peticion, cargamos en el constructor todo en una lista
        	//y en repitadas llamadas solo consultamos la lista
        	//secApiKey = securityApìKeysRepository.findFirstBy1Apikey(apiKey);
        	for (SecurityApiKeys api : listaApiCargada) {
        		if (apiKey.equals(api.getApikey())) {
        			secApiKey = api;
        			break;
        		}
        	}
        }
        if (apiKey == null || secApiKey == null || secApiKey.getId() == null) {
            throw new BadCredentialsException("¡Ah, ah, ah! ¡No has dicho la palabra mágica!"
            		+ " (Ah, ah, ah! You didn't say the magic word!)");        		
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }    
    
}
