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
	- GET	
	
- **/doi/nif**
	- Devuelve una lista de NIFs
	- GET
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
	- GET
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
	- GET
	- Parámetros:
		- nie: NIE a validar
	- Resultado: "OK" si nie es válido. "ERROR" en otro caso.		
	
- **/doi/cif**
	- Devuelve una lista de CIFs
	- GET
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
	- GET
	- Parámetros:
		- cif: CIF a validar
	- Resultado: "OK" si cif es válido. "ERROR" en otro caso.	
	
- **/doi/passport**
	- Devuelve una lista de pasaportes españoles.
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "TIO8225066",
		    "RWC0978890",
		    "LXG7179783"
		]
		```	
		
- **/doi/validatepassport**
	- Comprueba si un pasaporte español es válido
	- GET
	- Parámetros:
		- passport: Pasaporte a validar
	- Resultado: "OK" si el pasaporte es válido. "ERROR" en otro caso.	

- **/doi/calculatepassportdc**
	- Devuelve el digito de control del número de pasaporte
	- GET
	- Parámetros:
		- passport: Pasaporte a validar sin el digito de control.
	- Resultado: El digito de control calculado. Ejemplo: "7".
	
	
- **/profiles/person**	
	- Devuelve una lista de datos de una persona generados aleatoriamente
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- gender: Genero persona devolver. Defecto nulo no filtra. En otro caso: male, female
	- Ejemplo resultado
		```
		[
		    {
		        "nif": "89693680M",
		        "nie": "X6161429M",
		        "nombre": "joaquin benito",
		        "apellido1": "mares",
		        "apellido2": "fonseca",
		        "genero": "hombre",
		        "nombreCompleto": "joaquin benito mares fonseca",
		        "fechaNacimiento": "20/03/1950",
		        "edad": "74",
		        "telefonoMovil": "668300358",
		        "telefonoFijo": "953019436",
		        "login": "jmares_277",
		        "email": "jmares_277@btconnect.com",
		        "password": "o76*ANR%YmqPdU3ZU82h6",
		        "direccion": {
		            "direccion": "Alameda España Nicolas Stefan Rigual",
		            "numVia": "271",
		            "kilometro": null,
		            "bloque": null,
		            "portal": null,
		            "escalera": null,
		            "planta": "2º",
		            "puerta": "D",
		            "codPostal": "51001",
		            "ineMunicipio": "001",
		            "municipio": "Ceuta",
		            "ineProvincia": "51",
		            "provincia": "Ceuta",
		            "ineCcaa": "18",
		            "ccaa": "Ceuta",
		            "direccionAMedio": "Alameda España Nicolas Stefan Rigual, 271, 2º D.",
		            "direccionCompleta": "Alameda España Nicolas Stefan Rigual, 271, 2º D. 51001 Ceuta. Ceuta (Ceuta).",
		            "referenciaCatastral": "51001H006224133036AZ"
		        },
		        "iban": "ES4401880768110007177268",
		        "bic": "ALCLESMM111",
		        "tarjetaCredito": "3138889542480707",
		        "cvc": "357",
		        "expiracionCredito": "10/29",
		        "tipoTarjeta": "American Express",
		        "nss": "518818733435",
		        "pasaporte": "VIG3471738"
		    },
		    ...
		]
		```	
		
- **/profiles/company**	
	- Devuelve una lista de datos de una empresa generados aleatoriamente
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    {
		        "cif": "M0930107H",
		        "nombre": "POWER ELECTRONICS ESPAÑA SL",
		        "fechaCreacion": "06/01/1936",
		        "telefono": "952893915",
		        "fax": "923321387",
		        "email": "power_electronics_espaa_sl@hotpop.com",
		        "direccion": {
		            "direccion": "Poblado Eras Jeniffer Andrea Jouhari",
		            "numVia": "669",
		            "kilometro": null,
		            "bloque": "6",
		            "portal": null,
		            "escalera": null,
		            "planta": "1º",
		            "puerta": "I",
		            "codPostal": "20577",
		            "ineMunicipio": "011",
		            "municipio": "Antzuola",
		            "ineProvincia": "20",
		            "provincia": "Gipuzkoa",
		            "ineCcaa": "16",
		            "ccaa": "País Vasco",
		            "direccionAMedio": "Poblado Eras Jeniffer Andrea Jouhari, 669, Bloque 6, 1º I.",
		            "direccionCompleta": "Poblado Eras Jeniffer Andrea Jouhari, 669, Bloque 6, 1º I. 20577 Antzuola. Gipuzkoa (País Vasco).",
		            "referenciaCatastral": "20011N124298855134QK"
		        },
		        "cnae": "3513",
		        "actividad": "Distribución de energía eléctrica",
		        "paginaWeb": "www.powerelectronicsespaasl.net"
		    },
		    ...
		]
		```	
		
- **/bank/account**	
	- Devuelve una lista de cuentas bancarias generadas aleatoriamente
	- GET
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
	- GET
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
	- GET
	- Parámetros:
		- iban: IBAN a validar. Ejemplo: ES8921032698618414341240
	- Resultado: "OK" si iban es válido. "ERROR" en otro caso.	
	
- **/bank/validatecard**
	- Comprueba si un número de tarjeta de crédito es valido
	- GET
	- Parámetros:
		- card: Tarjeta a validar. Ejemplo: 4544363578091115
	- Resultado: "OK" si tarjeta es válida. "ERROR" en otro caso.	
	
- **/text/word**
	- Devuelve una lista de palabras generadas aleatoriamente.
	- GET
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
	- GET
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
	- GET
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
	- GET
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
	- GET
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
	- GET
	- Parámetros:
		- vin: Número de bastidor. Ejemplo: 1FTWF32Y191LCYKS0
	- Resultado: "OK" si tarjeta es válida. "ERROR" en otro caso.	
					

- **/misc/email**
	- Devuelve una lista de emails
	- GET
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
	- GET
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
	- GET
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

- **/misc/city**
	- Devuelve una lista de ciudades aleatoria
	- GET
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
	- GET
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
	- GET
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
	- GET
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
	- GET
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
	- GET
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
		
- **/misc/address**
	- Devuelve los datos de una lista de domicilios.
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- ineccaa: Opcional. Código INE de la CCAA si queremos filtrar por una concreta.
		- ineprovincia: Opcional. Coódigo INE de la provincia si queremos filtrar por una concreta. 
		- inemunicipio: Opcional. Coódigo INE del municipio si queremos filtrar por uno concreto. NOTA: El INE del municipio no puede identificar un municipio por si solo, requiere de al menos la provincia.
		- km: Opcional. y/n si se quiere forzar que esté o no esté el valor de kilometro. Valor no indicado: aparece aleatoriamente.
		- bloque: Opcional. y/n si se quiere forzar que esté o no esté el valor de bloque. Valor no indicado: aparece aleatoriamente.
		- portal: Opcional. y/n si se quiere forzar que esté o no esté el valor de portal. Valor no indicado: aparece aleatoriamente.
		- escalera: Opcional. y/n si se quiere forzar que esté o no esté el valor de escalera. Valor no indicado: aparece aleatoriamente.
		- planta: Opcional. y/n si se quiere forzar que esté o no esté el valor de planta. Valor no indicado: aparece siempre.
		- puerta: Opcional. y/n si se quiere forzar que esté o no esté el valor de puerta. Valor no indicado: aparece siempre.
	- Ejemplo resultado
		```
		[
		    {
		        "direccion": "Pasaje España Bader Morla",
		        "numVia": "731",
		        "kilometro": null,
		        "bloque": null,
		        "portal": null,
		        "escalera": null,
		        "planta": "5º",
		        "puerta": "E",
		        "codPostal": "04878",
		        "ineMunicipio": "084",
		        "municipio": "Sierro",
		        "ineProvincia": "04",
		        "provincia": "Almería",
		        "ineCcaa": "01",
		        "ccaa": "Andalucía",
		        "direccionAMedio": "Pasaje España Bader Morla, 731, 5º E.",
		        "direccionCompleta": "Pasaje España Bader Morla, 731, 5º E. 04878 Sierro. Almería (Andalucía).",
		        "referenciaCatastral": "04084O785087984348TE"
		    },
		    ...
		]
		```									

- **/misc/voucher**
	- Devuelve una lista de códigos promocionales formateados
	- GET
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

- **/misc/uuid**
	- Devuelve una lista de UUIDs
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "9634b1bd-c39c-4462-b922-9e6b63f241ed",
		    "acf4f88c-93f9-473c-a24e-0241c7d941f7",
		    "9b8ab243-bf87-41c3-9863-ae4d05103df4",
		    "cb778f2f-615e-4ff6-bf1c-b1b500800c71",
		    "8fefc81c-c72b-4704-a4ec-4db7024f9d1a",
		    "f439a64c-31dd-46ce-9156-713b02a2901f",
		    "50ab2d45-d2dc-444b-887e-dabcc2d7304b",
		    "2d91597f-d318-4620-9877-fd8fd659e794",
		    "5be7e30a-01b4-4a34-820e-9e7e1097cf09",
		    "b5b13d49-4949-429f-aa07-392dbcdf1682"
		]
		```	
		
- **/misc/catastral**
	- Devuelve una lista de referencias catastrales de ejemplo.
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- type: Opcional. Indicar el tipo concreto de referencia catastral: u para urbana, r para rústica.
	- Ejemplo resultado
		```
		[
		    "31044Z932050006628FU",
		    "16066Q578759192407JO",
		    "25905V209155811571AO",
		    "0456494PPMQL6N9631OI",
		    "52001I039130321495IE",
		    "85732929CHWGZR5596WY",
		    "46152V100593355396AT",
		    "2307443IGXL60N3987WA",
		    "5172254P298W153977SD",
		    "06029X331693665469BQ"
		]
		```	
		
- **/misc/validatecatastral**
	- Comprueba si una referencia catastral es válida (Formato adecuado, no comprueba que exista en catastro).
	- GET
	- Parámetros:
		- catastral: Referencia catastral. Ejemplo: 31044Z932050006628FU
	- Resultado: "OK" si la referencia es válida. "ERROR" en otro caso.	
		
- **/misc/cups**
	- Devuelve una lista de CUPS (Código Universal del Punto de Suministro).
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
		- type: Opcional. Indicar el tipo concreto de CUPS: e para energia, g para gas.
	- Ejemplo resultado
		```
		[
		    "ES0396176748886994HE",
		    "ES0220979668478278EZ",
		    "ES0238350554973179WN",
		    "ES0229316707134129RP",
		    "ES0288023277772933AL",
		    "ES0238782381709133BH",
		    "ES0223678468476634WK",
		    "ES0029578485263255WD",
		    "ES0024352265237641XT",
		    "ES0363084377144784RN"
		]
		```	
		
- **/misc/validatecups**
	- Comprueba si un CUPS (Código Universal del Punto de Suministro) es válido. (Formato adecuado).
	- GET
	- Parámetros:
		- cups: CUPS. Ejemplo: ES0223678468476634WK
	- Resultado: "OK" si el CUPS es válido. "ERROR" en otro caso.	
	
- **/misc/lei**
	- Devuelve una lista de LEIs (Identificador de Entidad Legal) (Solo de españa).
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "95988FWH829TXGRTPR68",
		    "9598WDK1688539P37X14",
		    "95980LPSSNQZPHQESQ28",
		    "9598KILQKFCE0ELSZE84",
		    "95989K13YIPE51224F98",
		    "95983ULXA1H080QYUD27",
		    "9598H4YKDEE1PXDDGW96",
		    "9598ZUNCQ4T9T1JLYM32",
		    "9598GN0Y8V4UTOU51C81",
		    "9598I0VFB776E9RA5192"
		]
		```	
		
- **/misc/validatelei**
	- Comprueba si un LEI (Identificador de Entidad Legal) es válido. (Formato adecuado).
	- GET
	- Parámetros:
		- lei: LEI. Ejemplo: 9598009DZMHWCT7D0W77
	- Resultado: "OK" si el LEI es válido. "ERROR" en otro caso.		

- **/misc/isin**
	- Devuelve una lista de ISIN (International Securities Identification Numbering) (Solo de españa).
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "ES0B7SFX1EB0",
		    "ES05668LKS78",
		    "ES08TTWMFKS7",
		    "ES050EKIBI08",
		    "ES0SRKPXC8C1",
		    "ES0AZK1KBCX4",
		    "ES0LRRW8JE44",
		    "ES07F8VKGW59",
		    "ES0LG92O0XA0",
		    "ES052RHOH566"
		]
		```	
		
- **/misc/validateisin**
	- Comprueba si un ISIN (International Securities Identification Numbering) es válido. (Formato adecuado).
	- GET
	- Parámetros:
		- isin: ISIN. Ejemplo: ES055XBM8IE4
	- Resultado: "OK" si el ISIN es válido. "ERROR" en otro caso.	
	
- **/misc/nss**
	- Devuelve una lista de NSS (Número Seguridad Social).
	- GET
	- Parámetros:
		- results: Número de resultados a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    "223107747468",
		    "355479016932",
		    "112086812750",
		    "368775263135",
		    "227609723996",
		    "424847881262",
		    "339078235714",
		    "333571110224",
		    "032248887354",
		    "474450537957"
		]
		```	
		
- **/misc/validatenss**
	- Comprueba si un NSS (Número Seguridad Social) es válido.
	- GET
	- Parámetros:
		- nss: NSS. Ejemplo: 047063802539
	- Resultado: "OK" si el NSS es válido. "ERROR" en otro caso.		

- **/number/random**
	- Devuelve una lista de números aleatorios
	- GET
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
	- GET
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
	- GET
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
	- GET
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
	- GET
	- Parámetros:
		- operand: Operando a realizar. VAlores válidos: +, -, *, /, resto, potencia, porcentaje, factorial, factorial, logaritmo, logaritmoNaturalNeperiano, logaritmoBase2, logaritmoBaseX, raizCuadrada, raizCubica, raizN. En caso de introducir algo distino opera con "+".
		- number1: Primer número
		- number2: Segundo número (en las operaciones donde es necesario)
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "6,64385619".
		
- **/number/proportion**
	- Realiza la operacion de una regla de tres directa o indirecta: a -> b , c -> x , para despegar X.
	- GET
	- Parámetros:
		- numberA: Valor A
		- numberB: Valor B
		- numberC: Valor C
		- direct: Opcional. y/n si es directa o indirecta. Por defecto y.
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
		
- **/number/area**
	- Calcular el area de algunas formas trigonometricas
	- GET
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
	- GET
	- Parámetros:
		- degrees: Grados
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
	
- **/number/radiansToDegrees**
	- Convertir grados en radianes
	- GET
	- Parámetros:
		- radians: Radianes
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Valor de X. Ejemplo: "6,64".
	
- **/number/trigonometric**
	- Calcular funciones trigonometricas
	- GET
	- Parámetros:
		- type: Funcion: seno, coseno, tangente, cotangente, cosecante, secante, arcoseno, arcocoseno, arcotangente, senohiperbolico, cosenohiperbolico, tangentehiperbolico.
		- number: Valor
		- typeNumber: Tipo de numero: radianes, grados
		- decimals: Opcional. Número de posisiones decimales a mostrar. Por defecto 2.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "6,64385619".
	
- **/number/baseConverter**
	- Conversor de base numerica
	- GET
	- Parámetros:
		- number: Numero a convertir
		- baseFrom: Base de origen. Desde 2 a 36. Incorrecto tomara 10.
		- baseTo: Base de destino. Desde 2 a 36. Incorrecto tomara 10.
	- Resultado: Cadena con el número resultante de la operación. Ejemplo: "1cki".
	
- **/number/arabicToRoman**
	- Convertir un numero a romano
	- GET
	- Parámetros:
		- number: Número arábigo
	- Resultado: Valor en número romano. Ejemplo: "XXIV".

- **/number/romanToArabic**
	- Convertir un numero romano a arabigo
	- GET
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
	- GET
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- El texto a convertir se pasa en la URL.
	- Resultado: image/png
	
- **/barcodes/upca**
	- Generar código de barras UPCA
	- GET
	- Parámetros:
		- width: Opcional. Ancho imagen (pixeles)
		- height: Opcional. Alto imagen (pixeles)
	- El texto a convertir se pasa en la URL.
	- Resultado: image/png
	
- **/barcodes/upce**
	- Generar código de barras UPCE
	- GET
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
	- GET
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

- **/file/zip**
	- Generar un zip para un conjunto de archivos.
	- POST
	- Body: Tipo form-data con los valores:
		- files: Lista de archivos que se incluirán en el zip
	- Resultado: Archivo zip en si mismo.


- **/color/color**	
	- Devuelve una lista de colores generadas aleatoriamente
	- GET
	- Parámetros:
		- results: Número de colores a devolver. Defecto 10, máximo valor 1000.
	- Ejemplo resultado
		```
		[
		    {
		        "red": 147,
		        "green": 163,
		        "blue": 57,
		        "hex": "#93a339"
		    },
		    ...
		]
		```	
		
- **/color/hexToRgb**
	- Convertir color hexadecimal en RGB
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
	- Resultado: Valor RGB. Ejemplo: "rgb(63,117,87)".
				

- **/color/rgbToHex**
	- Convertir color RGB a hexadecimal
	- GET
	- Parámetros:
		- red: Valor rojo.
		- green: Valor verde.
		- blue: Valor azul.
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
	
- **/color/lighten**
	- Aclarar un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- amount: Valor de aclarado 0-100.
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".

- **/color/darken**
	- Oscurecer un color
	- GET+
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- amount: Valor de ocurecimiento 0-100.
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
				
- **/color/hue**
	- Aplicar matíz a un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- amount: Valor de corrección de matíz. -1.0 - 1.0
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
		
- **/color/saturate**
	- Aplicar saturazión a un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- amount: Valor de corrección de saturación. -1.0 - 1.0
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
				
- **/color/brightness**
	- Aplicar correción de brillo a un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- amount: Porcentaje de corrección de brillo. 0 - 100
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
		
- **/color/invert**
	- Invertir un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
			
- **/color/alpha**	
	- Aplicar un canal alpha a un color
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- alpha: Valor de aplicación del alpha. 0 - 100
	- Ejemplo resultado
		```
		{
		    "red": 107,
		    "green": 57,
		    "blue": 146,
		    "alpha": 127,
		    "hex": "#6b39927f"
		}
		```	
				
- **/color/mix**
	- Mezclar dos colores
	- GET
	- Parámetros:
		- hexa1: Color 1 hexadecimal (con o sin #)
		- hexa2: Color 2 hexadecimal (con o sin #)
		- amount: Porcentaje de aplicación del color 2 sobre el 1. 0 - 100
	- Resultado: Valor hexadecimal. Ejemplo: "#3f7557".
	
- **/color/gradient**	
	- Obtener una lista de gradientes de colores
	- GET
	- Parámetros:
		- hexa1: Color 1 hexadecimal (con o sin #)
		- hexa2: Color 2 hexadecimal (con o sin #)
		- numberOfGradients: Número de gradientes a generar. 1 - 1000
	- Ejemplo resultado
		```
		[
		    {
		        "red": 255,
		        "green": 0,
		        "blue": 0,
		        "alpha": 0,
		        "hex": "#ff0000"
		    },
		    ...
		]
		```		
		
- **/color/monochrome**	
	- Obtener una lista de gradientes de colores monocromáticos
	- GET
	- Parámetros:
		- hexa: Color hexadecimal (con o sin #)
		- numberOfColors: Número de colores a generar. 1 - 1000
	- Ejemplo resultado
		```
		[
		    {
		        "red": 255,
		        "green": 0,
		        "blue": 0,
		        "alpha": 0,
		        "hex": "#ff0000"
		    },
		    ...
		]
		```				
			

- **/date/birthdate**
	- Devuelve una lista de fechas de nacimiento (Edades entre 18-100)
	- GET
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

- **/date/futuredate**
	- Devuelve una lista de fechas de a futuro (Entre 1-100 años futuros)
	- GET
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

- **/date/age**
	- Calcula la edad con la fecha de nacimiento (con respecto al momento actual en que se consulta)
	- GET
	- Parámetros:
		- birthDate: Fecha nacimiento. Formato "dd/MM/yyyy HH:mm:ss".
	- Ejemplo resultado
		```
		{
		    "anyos": 40,
		    "meses": 485,
		    "dias": 14791,
		    "horas": 354984,
		    "minutos": 21299057,
		    "segundos": 1277943458,
		    "anyosRelativos": 40,
		    "mesesRelativos": 5,
		    "diasRelativos": 28,
		    "horasRelativos": 0,
		    "minutosRelativos": 17,
		    "segundosRelativos": 38
		}
		```				
			
- **/date/datediff**
	- Calcula la diferencia entre dos fechas
	- GET
	- Parámetros:
		- startDate: Fecha inicial. Formato "dd/MM/yyyy HH:mm:ss".
		- endDate: Fecha final. Formato "dd/MM/yyyy HH:mm:ss".
	- Ejemplo resultado
		```
		{
		    "anyos": 40,
		    "meses": 485,
		    "dias": 14791,
		    "horas": 354984,
		    "minutos": 21299057,
		    "segundos": 1277943458,
		    "anyosRelativos": 40,
		    "mesesRelativos": 5,
		    "diasRelativos": 28,
		    "horasRelativos": 0,
		    "minutosRelativos": 17,
		    "segundosRelativos": 38
		}
		```		
		
- **/date/dateoperation**
	- Realiza una suma o resta sobre una fecha
	- GET
	- Parámetros:
		- date: Fecha inicial. Formato "dd/MM/yyyy HH:mm:ss".
		- operation: Operación a aplicar: "sum" sumar o "res" restar.
		- years: Años a incrementar/decrementar.
		- months: Meses a incrementar/decrementar.
		- days: Días a incrementar/decrementar.
		- hours: Horas a incrementar/decrementar.
		- minutes: Minutos a incrementar/decrementar.
		- seconds: Segundos a incrementar/decrementar.
	- Resultado: Fecha resultante. Formato "dd/MM/yyyy HH:mm:ss". Ejemplo "02/05/1984 14:00:00".
	
- **/date/dayofweek**
	- Comprueba el día de la semana para una fecha dada
	- GET
	- Parámetros:
		- date: Fecha. Formato "dd/MM/yyyy HH:mm:ss".
	- Resultado: Día de la semana. Ejemplo "Lunes".
		
- **/date/unixtimeToTime**
	- Dado un tiempo unix UTC, calcula las fecha/tiempo correspondiente en UTC y local.
	- GET
	- Parámetros:
		- unixTime: Tiempo unix UTC.
	- Ejemplo resultado
		```
		{
		    "tiempoUnixUTC": 1730199192,
		    "fechaUTC": "2024-10-29T10:53:12",
		    "fechaLocal": "2024-10-29T11:53:12"
		}
		```		
		
- **/date/timeToUnixtime**
	- Dado un fecha/tiempo local (Europe/Madrid) obtiene el tiempo unix UTC correspondiente
	- GET
	- Parámetros:
		- date: Fecha local. Formato "dd/MM/yyyy HH:mm:ss".
	- Ejemplo resultado
		```
		{
		    "tiempoUnixUTC": 1730199192,
		    "fechaUTC": "2024-10-29T10:53:12",
		    "fechaLocal": "2024-10-29T11:53:12"
		}
		```		
		
- **/date/holyWeek**
	- Devuelve las fechas Semana Santa (Pascua) de un año
	- GET
	- Parámetros:
		- year: Año a consultar
	- Ejemplo resultado
		```
		{
		    "fechaDomingoRamos": "2024-03-24T11:00:00.000+00:00",
		    "fechaLunesSanto": "2024-03-25T11:00:00.000+00:00",
		    "fechaMartesSanto": "2024-03-26T11:00:00.000+00:00",
		    "fechaMiercolesSanto": "2024-03-27T11:00:00.000+00:00",
		    "fechaJuevesSanto": "2024-03-28T11:00:00.000+00:00",
		    "fechaViernesSanto": "2024-03-29T11:00:00.000+00:00",
		    "fechaSabadoSanto": "2024-03-30T11:00:00.000+00:00",
		    "fechaDomingoResurreccion": "2024-03-31T10:00:00.000+00:00",
		    "fechaLunesPeriodoVacacional": "2024-03-25T11:00:00.000+00:00",
		    "fechaDomingoPeriodoVacacional": "2024-04-07T10:00:00.000+00:00"
		}
		```				
			
			

## Solventar SilentExitException exception lanzada al lanzar la aplicación en debug eclipse

https://cursos.alura.com.br/forum/topico-erro-com-silentexitexception-ao-executar-debug-java-e-erro-403-215211
https://stackoverflow.com/questions/32770884/breakpoint-at-throw-new-silentexitexception-in-eclipse-spring-boot

Si lanzas la aplicación en modo debug, siempre al iniciarse lanza esa SilentExitException, aunque dandole a continuar sigue.

Parece ser un bug de la version de spring-boot-devtools.

Para solventarlo, en el debug configurations, dentro de Arguments, VM arguments se ha puesto "-Dspring.devtools.restart.enabled=false".

					
## Docker - Render

Se incluye un Dockerfile que permite desplegar el servicio en Render:
- Define el entorno de construcción:
	- Simula un ~~ubuntu e instala openjdk17~~ (el requisito de la versión de spring boot utilizada). -> finalmente utilizamos una "eclipse-temurin:17-jdk" porque ya viene directamente con el jdk instalado y es más rápido para desplegar.
	- Instala maven, dado que lo utilizamos como gestor de dependencias en nuestro proyecto.
	- Se hace el "mvn clean package" para compilar y que cree el jar
- Se define el entorno de ejución de la aplicación:
	- con ~~openjdk17~~ -> Sustituido por la "eclipse-temurin:17-jdk", porque openjdk docker está deprecated. Esta es una de las alternativas que proponen.
		- Adicionalmente indicar que el uso de la versión "slim" no dejaba funcionar los textos que se añaden al QR debido a que entre las cosas que hacen para volverla más ligera es quitar las librerias de fuentes. "eclipse-temurin:17-jdk" lleba el openjdk completo por lo que solventamos dicho problema.
	- puerto expuesto 8085
	- Copiamos el jar generado del target (entorno contrucción) a raíz y nombre final.
	- Luego ejecutamos con java nuestro jar (La aplicación spring boot se está ejecutando con su tomcat embebido)


## Dependencias:
Esta aplicación utiliza las siguientes dependencias de terceras partes
- [iso-17442-java](https://github.com/EDumdum/iso-17442-java) - MIT license
- [ZXing Library](https://github.com/zxing/zxing) - Apache-2.0 license
					