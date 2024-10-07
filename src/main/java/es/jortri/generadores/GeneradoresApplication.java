package es.jortri.generadores;

import java.awt.image.BufferedImage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;

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

	/**
	 * Conversor para devolver como imagen los codigos de barras y qr
	 * Referencia: https://www.baeldung.com/java-generating-barcodes-qr-codes
	 * https://github.com/eugenp/tutorials/blob/master/spring-boot-modules/spring-boot-libraries/src/main/java/com/baeldung/barcodes/SpringBootApp.java
	 * @return
	 */
    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
	
}
