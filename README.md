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
		        "actividad": "Confección de otras prendas de vestir de punto",
		        "paginaWeb": "www.mondelezespaaservicesslu.com"
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
		
- **/vehicle/platenumber**
	- Devuelve una lista de matriculas de vehiculos de España.
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- type: Opcional, indicar el tipo de matricula a generarse: t (Turismo), c (Ciclomotor), r (Remolque), e (Especial), u (Turístico), h (Histórico), tp (Temporal particular), te (Temporal empresa), d (Diplomática). De no indicarse o indicarse algo erroneo se generar para turismos.
	- Ejemplo resultado
		```
		[
		    "8453XRX",
		    "9008QBP",
		    "3576YTL",
		    "1770GQJ",
		    "9419BVB",
		    "8419NTJ",
		    "6110LBW"
		]
		```						
			
- **/vehicle/vin**
	- Devuelve una lista de números de bastidor.
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "2GCEK19R3VR2PMB9N",
		    "1FTSW31Y55PEPF1XD",
		    "3GTGC26F5V4LX9MHS",
		    "1GTJ5MEE7B29E3DY8",
		    "2GTJC83121S5F8S9V",
		    "2GTJC39G533ZAFAUH",
		    "1G6DN57U67TLBSWKZ"
		]
		```							
				
- **/vehicle/validatevin**
	- Comprueba si un número de bastidor es válido.
	- Parámetros:
		- vin: Número de bastidor. Ejemplo: 1FTWF32Y191LCYKS0
	- Resultado: "OK" si tarjeta es válida. "ERROR" en otro caso.	
					

- **/misc/email**
	- Devuelve una lista de emails
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "TEd94yai6X0PSXM4dK3rG52aR@libero.it",
		    "R-_jOqMWVRJGYBwPIrT6SVu8G@terra.com",
		    "kb8GvJjkTLBvHBhJx14GVmG-x@hotpop.com",
		    "eNDLiOhUEd1gtRkr3EjkY7buN@yahoo.com",
		    "EZ69ApX3gB1dBeIUr_jkRPMVB@lycos.com",
		    "NuC9xyr6A1eNYf2pY4Bj7aOz_@comcast.net",
		    "msbCsyk4LfplwNCjpXgwok0ST@yahoo.com"
		]
		```	
		
- **/misc/password**
	- Devuelve una lista de passwords
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- length: Longitud del password. Minimo 15, defecto 21.
		- cases: "y" si quiere mayusculas/minusculas, "n" solo mayusculas. Defecto: "y".
		- number: "y" si quiere números, "n" solo mayusculas. Defecto: "y".
		- special: "y" si quiere carácteres especiales, "n" solo mayusculas. Defecto: "y".
	- Ejemplo resultado
		```
		[
		    "TQDZoSXsLNU)OEQTCpCIIKbZ'",
		    "HEVURWOERWXT+k%DPyJGwWFRu",
		    "REy'EDHEbwKRLAExRVEPQPP(R",
		    "tEXOZJSKGECgKfVT,JnF\"PVUZ",
		    "OR#EAPOSr\"IVFOFOFamDTMhNB",
		    "QEKBHqqSCHKbE#NUURRE,RkFA",
		    "mWgLiN)ACRID(QCYGhTJCMCTD"
		]
		```			
		
- **/misc/phonenumber**
	- Devuelve una lista de telefonos
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- type: "f" fijo, "m" movil. Si omite genera de ambos aleatoriamente.
	- Ejemplo resultado
		```
		[
		    "697446708",
		    "622908884",
		    "975127185",
		    "668978407",
		    "965134623",
		    "942557223",
		    "624322863"
		]
		```				

- **/misc/birthdate**
	- Devuelve una lista de fechas de nacimiento (Edades entre 18-100)
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "24/02/1930",
		    "26/02/1993",
		    "24/09/1933",
		    "18/03/1998",
		    "01/07/1994",
		    "01/12/1988",
		    "19/02/2004"
		]
		```	

- **/misc/futuredate**
	- Devuelve una lista de fechas de a futuro (Entre 1-100 años futuros)
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "19/07/2062",
		    "16/06/2093",
		    "05/03/2035",
		    "16/08/2057",
		    "23/07/2123",
		    "29/07/2081",
		    "13/05/2086"
		]
		```	
		
- **/misc/city**
	- Devuelve una lista de ciudades aleatoria
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "Selaya",
		    "Torrelapaja",
		    "Nuño Gómez",
		    "Carrascalejo, El",
		    "Calicasas",
		    "Aldehuela de Jerte",
		    "Fruiz"
		]
		```			

- **/misc/zipcode**
	- Devuelve una lista de códigos postales
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "37766",
		    "27817",
		    "08585",
		    "03689",
		    "31522",
		    "31492",
		    "09290"
		]
		```	
		
- **/misc/imei**
	- Devuelve una lista de códigos IMEI
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "37766",
		    "27817",
		    "08585",
		    "03689",
		    "31522",
		    "31492",
		    "09290"
		]
		```		
		
- **/misc/ccaa**
	- Devuelve una lista de CCAA de España
	- Ejemplo resultado
		```
		[
		    {
		        "id": "01",
		        "nombre": "Andalucía"
		    },
		    {
		        "id": "02",
		        "nombre": "Aragón"
		    },
		    ...
		]
		```		
		
- **/misc/provincias**
	- Devuelve una lista de provincias de España dentro de la CCAA indicada
	- Parámetros:
		- idccaa: identificador de la CCAA obtenido del servicio */misc/ccaa*
	- Ejemplo resultado
		```
		[
		    {
		        "id": "01",
		        "nombre": "Andalucía"
		    },
		    {
		        "id": "02",
		        "nombre": "Aragón"
		    },
		    ...
		]
		```			
		
- **/misc/municipios**
	- Devuelve una lista de municipios de España dentro de la Provincia indicada
	- Parámetros:
		- idprovincia: identificador de la Provincia obtenido del servicio */misc/provincias*
	- Ejemplo resultado
		```
		[
		    {
		        "id": "001",
		        "nombre": "Abadía"
		    },
		    {
		        "id": "002",
		        "nombre": "Abertura"
		    },
		    ...
		]
		```									

- **/misc/voucher**
	- Devuelve una lista de códigos promocionales formateados
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- charset: Opcional. Conjunto de caracteres que se usaran para la generación. Por defecto: "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".
		- length: Opcional. Longitud de los códigos generados. Por defecto 10.
		- pattern: Opcional. Patrón que seguirán los códigos generados. Se utiliza el "-" como separador de partes. Se indicará patrón como "AA-AAAAA-AAA-AAAA" y cada parte que separa el guión será generada. En caso de rellenar pattern, se ignora lo rellenado en length.
		- prefix: Opcional. Prefijo a añadir en todo código promocial de la forma "prefix-".
		- suffix: Opcional. Sufijo a añadir en todo código promocial de la forma "-prefix".
	- Ejemplo resultado
		```
		[
		    "JJ-Xz-MFk#N-Oh&-jpmi-GG",
		    "JJ-x#-7A#EJ-mnb-aNQ@-GG",
		    "JJ-6w-6og9X-wxm-&&4p-GG",
		    "JJ-HL-a4qB@-540-FiHy-GG",
		    "JJ-SH-BRdh0-for-uZvx-GG",
		    "JJ-91-WzqC7-FXR-zGsT-GG",
		    "JJ-dl-ZiEeh-8wT-p1wV-GG",
		    "JJ-Xs-F06#c-xoF-71Oa-GG",
		    "JJ-tB-32srb-hf7-x2XY-GG",
		    "JJ-#0-GmzD1-1k0-0#cG-GG"
		]
		```		

- **/number/random**
	- Devuelve una lista de números aleatorios
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- minimum: Opcional. Mínimo valor. Por defecto 0.
		- maximum: Opcional. Máximo valor. Por defecto 100.
		- decimals: Opcional. y/n si quiere o no decimales. Por defecto n.
		- repeated: Opcional. y/n si quiere o no repetidos. Por defecto y.
	- Ejemplo resultado
		```
		[
		    "83",
		    "99",
		    "84",
		    "86",
		    "72",
		    "73",
		    "90",
		    "93",
		    "62",
		    "79"
		]
		```	

- **/number/coin**
	- Obtener una lista de lanzamientos de moneda
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- faceSymbol: Opcional. simbolo para cara. por defecto O.
		- crossSymbol: Opcional. simbolo para cruz. por defecto X.
	- Ejemplo resultado
		```
		[
		    "O",
		    "O",
		    "X",
		    "O",
		    "O",
		    "O",
		    "O",
		    "O",
		    "X",
		    "O"
		]
		```	
		
- **/number/dice**
	- Obtener una lista de lanzamientos de dado
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "6",
		    "5",
		    "1",
		    "6",
		    "5",
		    "6",
		    "5",
		    "6",
		    "2",
		    "3"
		]
		```			
		
- **/number/gauss**
	- Devuelve una lista aleatoria de numeros por el método gauss
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- mean: Opcional. Media. Por defecto 0
		- standardDeviation: Opcional. Desviación estandar. Por defecto 1.
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 5.
	- Ejemplo resultado
		```
		[
		    "0,27905",
		    "0,58651",
		    "0,56347",
		    "0,66746",
		    "0,30250",
		    "0,79176",
		    "0,28064",
		    "0,66422",
		    "0,57328",
		    "0,54248"
		]
		```			
		
- **/number/calculator**
	- Realiza una operacion de calculadora con los parametros dados
	- Parámetros:
		- operand: Operando a realizar. VAlores válidos: +, -, *, /, resto, potencia, porcentaje, factorial, factorial, logaritmo, logaritmoNaturalNeperiano, logaritmoBase2, logaritmoBaseX, raizCuadrada, raizCubica, raizN. En caso de introducir algo distino opera con "+".
		- number1: Primer número
		- number2: Segundo número (en las operaciones donde es necesario)
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "6,64385619".
		
- **/number/proportion**
	- Realiza la operacion de una regla de tres directa o indirecta: a -> b , c -> x , para despegar X.
	- Parámetros:
		- numberA: Valor A
		- numberB: Valor B
		- numberC: Valor C
		- direct: Opcional. y/n si es directa o indirecta. Por defecto y.
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
		
- **/number/area**
	- Calcular el area de algunas formas trigonometricas
	- Parámetros:
		- type: Forma: cuadrado, rectangulo, triangulo, paralelogramo, trapezoide, circulo, elipse. De asignar otra cosa se supondrá "cuadrado".
		- numberA: Valor A. Según cada forma implica un valor:
			- cuadrado: Longitud de lado.
			- rectangulo: Ancho.
			- triangulo: Base.
			- paralelogramo: Base.		
			- trapezoide: Base superior (corta/menor)
			- circulo: Radio		
			- elipse: Eje corto o menor (similar al radio en círculos)
		- numberB: Valor B. Opcional para las formas que lo requieran. Según cada forma implica un valor.
			- rectangulo: Alto.		
			- triangulo: Altura vertical.
			- paralelogramo: Altura vertical.
			- trapezoide: Base inferior (larga/mayor)
			- elipse: Eje largo o mayor (similar al radio en círculos)
		- numberC: Valor C. Opcional para las formas que lo requieran. Según cada forma implica un valor.
			- trapezoide: Altura vertical		
		- direct: Opcional. y/n si es directa o indirecta. Por defecto y.
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
		
- **/number/degreesToRadians**
	- Convertir grados en radianes
	- Parámetros:
		- degrees: Grados
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
	
- **/number/radiansToDegrees**
	- Convertir grados en radianes
	- Parámetros:
		- radians: Radianes
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
	
- **/number/trigonometric**
	- Calcular funciones trigonometricas
	- Parámetros:
		- type: Funcion: seno, coseno, tangente, cotangente, cosecante, secante, arcoseno, arcocoseno, arcotangente, senohiperbolico, cosenohiperbolico, tangentehiperbolico.
		- number: Valor
		- typeNumber: Tipo de numero: radianes, grados
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "6,64385619".
	
- **/number/baseConverter**
	- Conversor de base numerica
	- Parámetros:
		- number: Numero a convertir
		- baseFrom: Base de origen. Desde 2 a 36. Incorrecto tomara 10.
		- baseTo: Base de destino. Desde 2 a 36. Incorrecto tomara 10.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "1cki".
	
- **/number/arabicToRoman**
	- Convertir un numero a romano
	- Parámetros:
		- number: Número arábigo
	- Resultado: Valor en número romano. Ejemplo: "XXIV".

- **/number/romanToArabic**
	- Convertir un numero romano a arabigo
	- Parámetros:
		- number: Número romano
	- Resultado: Valor en número arábigo. Ejemplo: "19".	
	
- **/barcodes/code128**
	- Generar código de barras 128
	- POST
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- Body: el texto a convertir.
	- Resultado: image/png
	
- **/barcodes/ean13**
	- Generar código de barras EAN13
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- El texto a convertir se pasa en la URL.
	- Resultado: image/png
	
- **/barcodes/upca**
	- Generar código de barras UPCA
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- El texto a convertir se pasa en la URL.
	- Resultado: image/png
	
- **/barcodes/upce**
	- Generar código de barras UPCE
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- El texto a convertir se pasa en la URL.
	- Resultado: image/png		

- **/barcodes/pdf417**
	- Generar código de barras PDF147
	- POST
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- Body: el texto a convertir.
	- Resultado: image/png
	
- **/barcodes/qrcode**
	- Generar código de QR
	- POST
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
		- topText: Opcional. Texto que aparece por encima del código QR.
		- bottomtext: Opcional. Texto que aparece por encima del código QR.
	- Body: el texto a convertir.
	- Resultado: image/png	
	
- **/file/base64**
	- Codificar en base 64
	- POST
	- Body: texto a codificar.
	- Resultado: Cadena codificada

- **/file/base64file**
	- Codificar en base 64 (archivo)
	- POST
	- Body: Tipo form-data con los valores:
		- file: archivo a codificar
		- name: nombre del archivo
	- Resultado: Cadena codificada
	
- **/file/decode64**
	- Decodificar de base 64
	- POST
	- Body: texto a decodificar.
	- Resultado: Cadena decodificada	
	
- **/file/decode64file**
	- Decodificar de base 64 (archivo)
	- POST
	- Body: texto a decodificar.
	- Resultado: Archivo en si decodificado	

- **/file/hashtypes**
	- Devuelve una lista de los algoritmos de hash válidos para ser utilizados en el servicio `/file/hash`
	- Ejemplo resultado
		```
		[
		    {
		        "codigo": "MD5",
		        "descripcion": "MD5 con longitud de 128-bit"
		    },
		    {
		        "codigo": "SHA-1",
		        "descripcion": "SHA-1 con longitud 160-bit"
		    },
		    {
		        "codigo": "SHA-256",
		        "descripcion": "SHA-256 con longitud de 256-bit"
		    },
		    {
		        "codigo": "SHA-512",
		        "descripcion": "SHA-512 con longitud de 512-bit"
		    }
		]
		```		

- **/file/hash**
	- Obtener el hash de un archivo
	- POST
	- Body: Tipo form-data con los valores:
		- file: archivo a codificar
		- name: nombre del archivo
		- type: Opcional (Por defecto "MD5"). Código del algotimo de codificación de los existentes en `/file/hashtypes`.
	- Resultado: Cadena con el hash del archivo según algoritmo.


## Solventar SilentExitException exception lanzada al lanzar la aplicación en debug eclipse

https://cursos.alura.com.br/forum/topico-erro-com-silentexitexception-ao-executar-debug-java-e-erro-403-215211
https://stackoverflow.com/questions/32770884/breakpoint-at-throw-new-silentexitexception-in-eclipse-spring-boot

Si lanzas la aplicación en modo debug, siempre al iniciarse lanza esa SilentExitException, aunque dandole a continuar sigue.

Parece ser un bug de la version de spring-boot-devtools.

Para solventarlo, en el debug configurations, dentro de Arguments, VM arguments se ha puesto "-Dspring.devtools.restart.enabled=false".

					
## Docker - Render

Se incluye un Dockerfile que permite desplegar el servicio en Render:
- Define el contenedor:
	- Simula un ubuntu 
	- Con openjdk17 (el requisito de la versión de spring boot utilizada) instalado.
	- Con maven, dado que lo utilizamos como gestor de dependencias en nuestro proyecto.
- Se hace el "mvn clean package" para compilar y que cree el jar
- Se define el entorno para la aplicación 
	- con openjdk17
	- puerto expuesto 8085
	- Copiamos el jar generado del target a raíz y nombre final.
	- Luego ejecutamos con java nuestro jar

					