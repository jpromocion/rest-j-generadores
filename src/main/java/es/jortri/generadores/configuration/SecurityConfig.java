package es.jortri.generadores.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.jortri.generadores.services.AuthenticationService;

/**
 * Registramos el filtro de seguridad creado antes "AuthenticationFilter", añadiendole antes de la UsernamePasswordAuthenticationFilter.
 * La politica de session se fija en STATELESS porque usamos REST endpoints: autenticacion sin estado para manternerse entre peticiones
 * en el lado del cliente... para que no la solicite en cada peticion que se haga. La autenticación sin estado presenta una forma de mantener 
 * los datos de autenticación en el lado del cliente entre solicitudes. A diferencia de las soluciones de almacenamiento de autenticación 
 * del lado del servidor, que generalmente se basan en sesiones, la autenticación sin estado no requiere que realice un 
 * seguimiento de las sesiones en el servidor.
 * Referencia: https://www.baeldung.com/spring-boot-api-key-secret
 * Referencia 2: https://smattme.com/posts/spring-boot-api-key-authentication/
 *   -Para permitir hacer uso del SecurityApìKeysRepository (comprobar keys en la bbdd) sin que de Access Denied
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//lo necesitamos desde aqui para pasarselo al autenticador... sino
	//al intentar hacer uso del repository para consultar la bbdd dara un Access Denied
    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/**").authenticated())
          .httpBasic(Customizer.withDefaults())
          .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .addFilterBefore(new AuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	
}
