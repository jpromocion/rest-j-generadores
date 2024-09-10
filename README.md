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



## Seguridad - Spring security

Se incluye Spring security para aportar una "Securing REST APIs with API Keys".
Las peticiones deberán incluir en el Header una key de nombre **"X-API-KEY"**, y cuyo valor deberá ser una key válida para proceder a responder la petición REST.


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
	- Resultado: "OK" si nie es válido. "ERROR" en otro caso.		
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
	- Resultado: "OK" si cif es válido. "ERROR" en otro caso.	
	
- **/profiles/person**	
	- Devuelve una lista de datos de una persona generados aleatoriamente
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- gender: Genero persona devolver. Defecto nulo no filtra. En otro caso: male, female
	- Ejemplo resultado
		```
		[
		    {
		        "nif": "52376422V",
		        "nie": "X2920027Q",
		        "nombre": "senaida",
		        "apellido1": "carulla",
		        "apellido2": "yaque",
		        "genero": "mujer",
		        "nombreCompleto": "senaida carulla yaque",
		        "fechaNacimiento": "21/11/1974",
		        "edad": "49",
		        "telefonoMovil": "661300365",
		        "telefonoFijo": "962833334",
		        "login": "scarulla_338",
		        "email": "scarulla_338@comcast.net",
		        "password": "G&Seq4fHo>W!2I9l8S2[V",
		        "ccaa": "Madrid, Comunidad de",
		        "ccaaIne": "13",
		        "provincia": "Madrid",
		        "provinciaIne": "28",
		        "municipio": "Carabaña",
		        "municipioIne": "035",
		        "direccion": "Edificio Héroes Karla Veronica Quijorna",
		        "numerovia": "596",
		        "codigoPostal": "28560",
		        "iban": "ES8200757534480227770871",
		        "bic": "POPUESMM",
		        "tarjetaCredito": "3420158827908130",
		        "cvc": "986",
		        "expiracionCredito": "12/27",
		        "tipoTarjeta": "American Express"
		    },
		    ...
		]
		```	
		
- **/profiles/company**	
	- Devuelve una lista de datos de una empresa generados aleatoriamente
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    {
		        "cif": "C8289952G",
		        "nombre": "CENTRALES NUCLEARES ALMARAZ TRILLO AIE",
		        "fechaCreacion": "14/09/1969",
		        "telefono": "942365447",
		        "fax": "988488624",
		        "email": "centrales_nucleares_almaraz_trillo_aie@airmail.net",
		        "ccaa": "Andalucía",
		        "ccaaIne": "01",
		        "municipio": "Tahal",
		        "municipioIne": "090",
		        "provincia": "Almería",
		        "provinciaIne": "04",
		        "direccion": "Calle Sol Emilio Alexander Bouzon",
		        "numerovia": "167",
		        "codigoPostal": "04274",
		        "cnae": "1439",
		        "actividad": "Confección de otras prendas de vestir de punto"
		    },
		    ...
		]
		```	
		
- **/bank/account**	
	- Devuelve una lista de cuentas bancarias generadas aleatoriamente
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    {
		        "ccc": "01084642890907430294",
		        "cccFormateado": "0108 4642 89 0907430294",
		        "iban": "ES8601084642890907430294",
		        "ibanFormateado": "ES86 0108 4642 8909 0743 0294",
		        "bic": "SOGEESMM444",
		        "entidad": "Société Générale"
		    },
		    ...
		]
		```			
		
- **/bank/card**
	- Devuelve una lista de tarjetas de crédito generadas aleatoriamente
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- type: Opcional, en caso de generar de una marca concreta: americanexpress, visa, mastercard, discover
	- Ejemplo resultado
		```
		[
		    {
		        "tarjeta": "4032747857055024",
		        "tarjetaFormateada": "4032 7478 5705 5024",
		        "expiracionCredito": "08/29",
		        "cvc": "182",
		        "tipoTarjeta": "Visa"
		    },
		    ...
		]
		```				
		
- **/bank/validateiban**
	- Comprueba si un IBAN es valido
	- Parámetros:
		- iban: IBAN a validar. Ejemplo: ES8921032698618414341240
	- Resultado: "OK" si iban es válido. "ERROR" en otro caso.	
	
- **/bank/validatecard**
	- Comprueba si un número de tarjeta de crédito es valido
	- Parámetros:
		- card: Tarjeta a validar. Ejemplo: 4544363578091115
	- Resultado: "OK" si tarjeta es válida. "ERROR" en otro caso.	
	
- **/text/word**
	- Devuelve una lista de palabras generadas aleatoriamente.
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- words: Opcional, número de palabras dentro de cada resultado. Por defecto 100.
		- language: Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	- Ejemplo resultado
		```
		[
    		"Ministri potuit manus fundamentum palaestra massa traditio textus magnitudo vetus magnus familiaris gradus debebat auxilium facere officina aedificium valores praefectus temperatura unus regina carolus etiam",
		    ...
		]
		```		
- **/text/characters**
	- Devuelve una lista de palabras generadas aleatoriamente con el tamaño máximo de carácteres especificado.
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- characters: Opcional, número de carácteres de la lista de palabras final resultado. Por defecto 100.
		- language: Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	- Ejemplo resultado
		```
		[
		    "Coche noche alma abrió algo físico empresarios vez qué mala utilizar golpe formas mismo juventud plantas medicina ahí finales siempre suelo sesión teoría síntomas proceso actor histórico sábado causa mesa acierto cifra semanas familia literatura sino ",
		    ...
		]
		```		
		
- **/text/paragraphs**
	- Devuelve una lista que contine cada elemento un texto generado aleatoriamente con el número de parráfos indicado
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- paragraphs: Opcional, número de parráfos en cada resultado. Por defecto 5. Máximo: 20.
		- language: Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	- Ejemplo resultado
		```
		[
			"Campaign raul direction leave five empty produced has candidate banks another probably importance body lack differences land free formula mainly just american members negotiations waters position element material create bodies doctor june land solution note thing seven showed food first were woman face present camera then seen bedroom motif for fernandez sensation faith sound value entity construction state sculpture where part pact good forces board precisely love reach symptoms should values attempt recognition below union fits difference wrist importance use which realization you.\nNext yours knows neck just exercise all scenario enrique should read same same angel opened who event attitude only value instant services airs knowledge processes final activity identity news street spokesperson image court cultural nothing your free present construction television english professor enrique poetry full rome station purpose yes figure planned daughter relations thigh size huge face recollection socialists strong safety traditional import.\nEconomic player sequence green monday take saturday success intention kitchen meeting situation direct table choice construction also republic institution elbow reduction greater representatives information some problem instant tools maximum supposed to fifteen majority opinion commission basque letter motif art science nine difference produce operation achieve never good ensures the screen those building classes opinion from types relation york function even economic should cup skin loss bodies pact be situation.",
		    ...
		]
		```							
					

## Solventar SilentExitException exception lanzada al lanzar la aplicación en debug eclipse

https://cursos.alura.com.br/forum/topico-erro-com-silentexitexception-ao-executar-debug-java-e-erro-403-215211
https://stackoverflow.com/questions/32770884/breakpoint-at-throw-new-silentexitexception-in-eclipse-spring-boot

Si lanzas la aplicación en modo debug, siempre al iniciarse lanza esa SilentExitException, aunque dandole a continuar sigue.

Parece ser un bug de la version de spring-boot-devtools.

Para solventarlo, en el debug configurations, dentro de Arguments, VM arguments se ha puesto "-Dspring.devtools.restart.enabled=false".

					