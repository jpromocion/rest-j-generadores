# rest-j-generadores

Api RestFul para generar datos

## Creacion inicial
Creada siguiendo instrucciones https://blog.codmind.com/mi-primer-api-rest-con-spring-boot/

Se utiliza spring intializr https://start.spring.io/ con las opciones:
- Project: maven
- Language: Java
- Spring boot: 3.3.3
- Project metada:
  - Group: es.jortri
  - Artifact: generadores
  - Name: generadores
  - Description: API rest de generadors con SpringBoot
  - Package name: es.jortri.generadores
  - packaging: jar
  - Java: 17
- Dependencias:
  - Spring Web
  - Spring Boot DevTools
  - Spring Data JPA
  - H2 Database (por si necesitamos guardar datos)

NOTA: Necesitaremos un JDK 17 instalado en pc y en eclipse para este proyecto.

Generamos y descargamos.
Importamos en eclipse como maven existing proyect.
- En application.properties le añadimos "server.contextPath=/generadores" "server.servlet.contextPath=/generadores" para prefijar
- Creamos el DemoController para tener un servicio /demo/hola que devuelve el string "hola mundo".

Sobre la clase "GeneradoresApplication" se lanzara la aplicación como Run as... Java applcation. Una vez desplegada, atacaremos el servicio demo con un GET en http://localhost:8080/generadores/demo/hola (POSTMAN por ejemplo).


## Solventar SilentExitException exception lanzada al lanzar la aplicación en debug

https://cursos.alura.com.br/forum/topico-erro-com-silentexitexception-ao-executar-debug-java-e-erro-403-215211
https://stackoverflow.com/questions/32770884/breakpoint-at-throw-new-silentexitexception-in-eclipse-spring-boot

Si lanzas la aplicación en modo debug, siempre al iniciarse lanza esa SilentExitException, aunque dandole a continuar sigue.

Parece ser un bug de la version de spring-boot-devtools.

Para solventarlo, en el debug configurations, dentro de Arguments, VM arguments se ha puesto "-Dspring.devtools.restart.enabled=false".


## API especificación

- **/demo/hola**
	- Devuelve la cadena "Hola Mundo"
- **/doi/nif**
	- Devuelve una lista de NIFs
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "48747005T",
		    "73656644H",
		    "81692150Z"
		]
		```	
- **/doi/validatenif**
	- Comprueba si un NIF es válido
	- Parámetros:
		- nif: NIF a validar
	- Resultado: "OK" si nif es válido. "ERROR" en otro caso.		
- **/doi/nie**
	- Devuelve una lista de NIEs
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "Y6700117R",
		    "Y7844030D",
		    "X7570499A"
		]
		```	
- **/doi/validatenie**
	- Comprueba si un NIE es válido
	- Parámetros:
		- nie: NIE a validar
	- Resultado: "OK" si nif es válido. "ERROR" en otro caso.		
- **/doi/cif**
	- Devuelve una lista de CIFs
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- custom_letter: Letra de inicio del CIF. Si se informa con una letra de CIF válida, los cifs se generan para esa letra. En otro caso los genera con letra aleatoria.
	- Ejemplo resultado
		```
		[
		    "G08141392",
		    "G15107998",
		    "G81294811"
		]
		```	
- **/doi/validatecif**
	- Comprueba si un CIF es válido
	- Parámetros:
		- cif: CIF a validar
	- Resultado: "OK" si nif es válido. "ERROR" en otro caso.	
