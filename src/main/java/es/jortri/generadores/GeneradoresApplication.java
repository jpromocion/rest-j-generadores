package es.jortri.generadores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * Clase principal de spring boot para levantar la aplicacion
 * NOTA: Se incluye el descarte de la configuracion de seguridad por defecto que contiene el spring security
 * (por ello el exclude SecurityAutoConfiguration y UserDetailsServiceAutoConfiguration), 
 * dado que hemos creado una configuracion personalizada por API KEY en "es.jortri.generadores.configuration".
 * Ahora toda peticion:
 *   - Incluye en el SecurityConfig -> el filtro AuthenticationFilter
 *   - AuthenticationFilter delega la validacion sobre la peticion en AuthenticationService.getAuthentication
 *   - AuthenticationService.getAuthentication: es donde implementaremos nuestra forma de comprobar el API Key.
 *     Por defecto comprobamos que en el Header de la peticion venga un key de nombre "X-API-KEY" con un valor
 *     que sera el que comprobemos para dar o no acceso. ApiKeyAuthentication es el objeto que se devuelve
 *     como token de seguridad. 
 * Referencia: https://www.baeldung.com/spring-boot-api-key-secret
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class GeneradoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneradoresApplication.class, args);
	}

}
