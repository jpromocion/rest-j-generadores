package es.jortri.generadores.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.model.Apellidos;
import es.jortri.generadores.model.Bancos;
import es.jortri.generadores.model.Ccaa;
import es.jortri.generadores.model.Codpostales;
import es.jortri.generadores.model.Municipios;
import es.jortri.generadores.model.Nombres;
import es.jortri.generadores.model.Persona;
import es.jortri.generadores.model.Provincias;
import es.jortri.generadores.model.Tipovias;
import es.jortri.generadores.repository.ApellidosRepository;
import es.jortri.generadores.repository.BancosRepository;
import es.jortri.generadores.repository.CcaaRepository;
import es.jortri.generadores.repository.CodpostalesRepository;
import es.jortri.generadores.repository.MunicipiosRepository;
import es.jortri.generadores.repository.NombresRepository;
import es.jortri.generadores.repository.ProvinciasRepository;
import es.jortri.generadores.repository.TipoviasRepository;
import es.jortri.generadores.util.CardValidationResult;
import es.jortri.generadores.util.CommonUtil;
import es.jortri.generadores.util.CreditCardNumberGenerator;
import es.jortri.generadores.util.RegexCardValidator;

@Service
public class ProfilesService {

	@Autowired
	private NombresRepository nombresRepository;

	@Autowired
	private ApellidosRepository apellidosRepository;

	@Autowired
	private CcaaRepository ccaaRepository;

	@Autowired
	private ProvinciasRepository provinciasRepository;

	@Autowired
	private MunicipiosRepository municipiosRepository;

	@Autowired
	private TipoviasRepository tipoviasRepository;

	@Autowired
	private DoiService doiService;

	@Autowired
	private CodpostalesRepository codpostalesRepository;

	@Autowired
	private BancosRepository bancosRepository;

	private Random semilla;

	private static final int MAX_EDAD = 100;

	private static final int MIN_EDAD = 18;

	private static final char PREFIJO_TLF_MOVIL = '6';

	private static final String GENERO_HOMBRE = "h";
	private static final String GENERO_MUJER = "m";
	private static final String GENERO_HOMBRE_DESC = "hombre";
	private static final String GENERO_MUJER_DESC = "mujer";
	private static final String GENERO_INDEFINIDO_DESC = "indefinido";

	public static String PREFIJOS_TLF_FIJOS[] = { "945", "967", "965", "966", "950", "984", "985", "920", "924", "971",
			"93", "947", "927", "956", "942", "964", "926", "957", "981", "969", "972", "958", "949", "943", "959",
			"974", "953", "987", "973", "982", "91", "951", "952", "968", "948", "988", "979", "928", "986", "941",
			"923", "921", "954", "955", "975", "977", "922", "978", "925", "960", "961", "962", "963", "983", "944",
			"946", "980", "976" };

	public static String SERVIDORES_EMAIL[] = { "1and1.com", "airmail.net", "aol.com", "att.net", "bluewin.ch",
			"btconnect.com", "comcast.net", "earthlink.net", "gmail.com", "gmx.net", "hotpop.com", "libero.it",
			"lycos.com", "o2.com", "orange.net", "outlook.com", "terra.com", "tiscali.co.uk", "verizon.net",
			"virgin.net", "wanadoo.fr", "yahoo.com" };

	public static String NOMBRES_COMUNES_CALLEJERO[] = { "Iglesia", "Mayor", "Fuente", "Constitución", "Real", "Nueva",
			"San", "Héroes", "Eras", "Sol", "España" };

	// 3-American Express, 4-Visa, 5-Mastercard, 6-Discover
	public static String PREFIJO_TARJETA[] = { "3", "4", "5", "6" };

	public ProfilesService() {
		this.semilla = new Random();
	}

	private Nombres generaNombrePersonaRandom() {
		Nombres nombrePri = nombresRepository.findFirstByOrderByIdAsc();
		Nombres nombreUlt = nombresRepository.findFirstByOrderByIdDesc();
		int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());
		Nombres nombre = nombresRepository.findById(randomSele).get();
		return nombre;
	}

	/**
	 * Generar los dos apellidos de una persona aleatoriamente
	 */
	private List<Apellidos> generarApellidosRandom() {
		List<Apellidos> listaApellidosSele = new ArrayList<Apellidos>();
		Apellidos apePri = apellidosRepository.findFirstByOrderByIdAsc();
		Apellidos apeUlt = apellidosRepository.findFirstByOrderByIdDesc();
		int randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido1 = apellidosRepository.findById(randomSele).get();
		randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido2 = apellidosRepository.findById(randomSele).get();

		listaApellidosSele.add(apellido1);
		listaApellidosSele.add(apellido2);

		return listaApellidosSele;
	}

	/**
	 * Generamos un nuemero de telefono movil aleatorio
	 * 
	 * @return
	 */
	public String generarNumTelefonoMovil() {
		int restoTlfInt = semilla.nextInt(1, 99999999);
		String restoTlf = Integer.toString(restoTlfInt);
		return PREFIJO_TLF_MOVIL + String.format("%1$" + 8 + "s", restoTlf).replace(' ', '0');
	}

	/**
	 * Generamos un numero de telefono fijo aleatorio
	 * 
	 * @return
	 */
	public String generarNumTelefonoFijo() {

		int indice = semilla.nextInt(0, PREFIJOS_TLF_FIJOS.length - 1);
		String prefijo = PREFIJOS_TLF_FIJOS[indice];
		int maximo = 999999;
		if (prefijo.length() == 2) {
			maximo = 9999999;
		}

		int restoTlfInt = semilla.nextInt(1, maximo);
		String restoTlf = Integer.toString(restoTlfInt);

		return prefijo + restoTlf;
	}

	/**
	 * Generar un login de usuario a partir del nombre y apellido
	 * 
	 * @param nombre
	 * @param apellido1
	 * @return inicial nombre + apellido + _ + un numero aleatorio
	 */
	public String generarLoginUsuario(String nombre, String apellido1) {
		String login = nombre.substring(0, 1) + apellido1.replace(' ', '_');

		int numInt = semilla.nextInt(1, 999);
		String num = Integer.toString(numInt);

		return login + "_" + num;
	}

	/**
	 * Generar un email a partir del login
	 * 
	 * @param login
	 * @return login + un servidor de correo aleatorio
	 */
	public String generarEmail(String login) {

		int indice = semilla.nextInt(0, SERVIDORES_EMAIL.length - 1);
		String servidor = SERVIDORES_EMAIL[indice];

		return login + "@" + servidor;
	}

	/**
	 * Genera un password aleatorio con caracteres especiales de la longitud indica
	 * 
	 * @param length longitud a generar
	 * @return
	 */
	private String generateRandomSpecialCharacters(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
		return pwdGenerator.generate(length);
	}

	/**
	 * Genera un password aleatorio con caracteres númericos de la longitud indica
	 * 
	 * @param length longitud a generar
	 * @return
	 */
	private String generateRandomNumbers(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		return pwdGenerator.generate(length);
	}

	/**
	 * Genera un password aleatorio con caracteres alfabeticos en mayuscula de la
	 * longitud indica
	 * 
	 * @param length longitud a generar
	 * @return
	 */
	private String generateRandomAlphabetUpper(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();
		return pwdGenerator.generate(length);
	}

	/**
	 * Genera un password aleatorio con caracteres alfabeticos en minuscula de la
	 * longitud indica
	 * 
	 * @param length longitud a generar
	 * @return
	 */
	private String generateRandomAlphabetLower(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		return pwdGenerator.generate(length);
	}

	/**
	 * Genera un password aleatorio con caracteres alfanumericos de la longitud
	 * indica
	 * 
	 * @param length longitud a generar
	 * @return
	 */
	private String generateRandomCharactersAlphaNum(int length) {
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').build();
		return pwdGenerator.generate(length);
	}

	/**
	 * Genera un password aleatorio de una logintud indicada donde se mezclan letras
	 * mayus minus, numeros, y caracteres especiales. NOTA:
	 * https://www.baeldung.com/java-generate-secure-password
	 * 
	 * @param length longitud a generar. Minimo de 15
	 * @return
	 */
	public String generateCommonTextPassword(int length) {

		int lengthFinal = length;
		if (lengthFinal < 15) {
			lengthFinal = 15;
		}

		int lengthSpecial = 2;
		int lengthNumbers = 4;
		int lengthUpper = 4;
		int lengthLower = 4;
		int lengthAlphaNum = 1 + (lengthFinal - 15);

		// prepara un string
		String pwString = generateRandomSpecialCharacters(lengthSpecial).concat(generateRandomNumbers(lengthNumbers))
				.concat(generateRandomAlphabetUpper(lengthUpper)).concat(generateRandomAlphabetLower(lengthLower))
				.concat(generateRandomCharactersAlphaNum(lengthAlphaNum));

		// se reordenan la cadena para que no aparezcan los del mismo tipo
		// siempre en el mismo orden
		List<Character> pwChars = pwString.chars().mapToObj(data -> (char) data).collect(Collectors.toList());
		Collections.shuffle(pwChars);
		String password = pwChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}

	/**
	 * Generar una comunidad autonoma aleatoria
	 * 
	 * @return
	 */
	private Ccaa generarCCAARandom() {
		List<Ccaa> listaCCAA = ccaaRepository.findAll();

		// si solo hay una opcion... no hay aleatoriedad posible
		int indice = 0;
		if (listaCCAA.size() > 1) {
			indice = semilla.nextInt(0, listaCCAA.size() - 1);
		}

		return listaCCAA.get(indice);
	}

	/**
	 * Generar una provincia aleatoria dentro de una comunidad autonoma
	 * 
	 * @return
	 */
	private Provincias generarProvinciaRandom(String idCcaa) {
		List<Provincias> listProvincias = provinciasRepository.findByIdccaa(idCcaa);

		// si solo hay una opcion... no hay aleatoriedad posible
		int indice = 0;
		if (listProvincias.size() > 1) {
			indice = semilla.nextInt(0, listProvincias.size() - 1);
		}

		return listProvincias.get(indice);
	}

	/**
	 * Generar un municipio aleatorio dentro de una provincia
	 * 
	 * @param idProvincia
	 * @return
	 */
	private Municipios generarMunicipioRandom(String idProvincia) {
		List<Municipios> listMunicipios = municipiosRepository.findByIdprovincias(idProvincia);

		// si solo hay una opcion... no hay aleatoriedad posible
		int indice = 0;
		if (listMunicipios.size() > 1) {
			indice = semilla.nextInt(0, listMunicipios.size() - 1);
		}

		return listMunicipios.get(indice);
	}

	/**
	 * Generar un tipo de via aleatorio
	 * 
	 * @return
	 */
	private Tipovias generarTipoViaRandom() {
		List<Tipovias> listaTiposVia = tipoviasRepository.findAll();

		// si solo hay una opcion... no hay aleatoriedad posible
		int indice = 0;
		if (listaTiposVia.size() > 1) {
			indice = semilla.nextInt(0, listaTiposVia.size() - 1);
		}

		return listaTiposVia.get(indice);
	}

	/**
	 * Genera una direccion random a partir de un tipo de via y un nombre comun
	 * 
	 * @return
	 */
	private String generaDireccionRandom() {
		Tipovias tipoVia = generarTipoViaRandom();

		int indice = semilla.nextInt(0, NOMBRES_COMUNES_CALLEJERO.length - 1);
		String nombreComun = NOMBRES_COMUNES_CALLEJERO[indice];

		Nombres nombre = generaNombrePersonaRandom();
		List<Apellidos> listaApellidosSele = generarApellidosRandom();

		String nombreFinal = CommonUtil.usingStringToUpperCaseMethod(nombre.getNombre());
		// Pattern.compile("^.").matcher(nombre.getNombre()).replaceFirst(m ->
		// m.group().toUpperCase());
		String apellidoFinal = CommonUtil.usingStringToUpperCaseMethod(listaApellidosSele.get(0).getApellido());
		// Pattern.compile("^.").matcher(listaApellidosSele.get(0).getApellido()).replaceFirst(m
		// -> m.group().toUpperCase());

		return tipoVia.getNombre() + " " + nombreComun + " " + nombreFinal + " " + apellidoFinal;
	}

	/**
	 * Genera un cod postal random que este asociado a la pronvincia/municipio
	 * concreta
	 * 
	 * @param idProvincia
	 * @param ineMunicipio
	 * @return
	 */
	public String generarCodPostalRandom(String idProvincia, String ineMunicipio) {
		List<Codpostales> listaCodPostal = codpostalesRepository.findByIdprovinciasAndInemunicipios(idProvincia,
				ineMunicipio);

		String codPostal = "";
		// si no se hubiera podido coaligar nada, generamos un id rando para esa
		// provincia
		if (listaCodPostal == null || listaCodPostal.isEmpty()) {
			codPostal = idProvincia + semilla.nextInt(1, 999);
		} else {

			// si solo hay una opcion... no hay aleatoriedad posible
			int indice = 0;
			if (listaCodPostal.size() > 1) {
				indice = semilla.nextInt(0, listaCodPostal.size() - 1);
			}

			codPostal = listaCodPostal.get(indice).getCodpostal();

		}

		return codPostal;
	}

	/**
	 * Obtener los DC para los datos de banco,oficina y cuenta de un CCC NOTA:
	 * https://github.com/rociodemula/prog11/blob/master/src/basicos/CuentaBancaria.java
	 * 
	 * @param entidad
	 * @param sucursal
	 * @param cuenta
	 * @return
	 */
	public static int[] obtenerDigitosControlCCC(String entidad, String sucursal, String cuenta) {
		// Factores multiplicadores para la operación de validación.
		int factores[] = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		// array para alojar los DC
		int[] digitosControl = new int[2];
		// Separamos en 2 cadenas para posteriores cálculos necesarios.
		// Se sigue el procedimiento para el cálculo indicado en
		// http://es.wikipedia.org/wiki/C%C3%B3digo_cuenta_cliente
		String cadenaValidacion[] = { "00" + entidad + sucursal, cuenta };
		// Declaramos las variables intermedias que necesitaremos
		int resultado, resto;
		// Mediante un bucle acumulamos el pre-resultado de cada dígito
		for (int i = 0; i < digitosControl.length; i++) {
			resultado = 0; // INicializamos el acumulador
			for (int j = 0; j < factores.length; j++) {
				// Vamos multiplicando cada operando y acumulando en la variable
				resultado += factores[j] * Integer.parseInt(cadenaValidacion[i].substring(j, j + 1));
			}
			// Seguimos la cadena de operaciones reglamentaria, con otra variable
			resto = resultado % 11;
			// Guardamos cada dígito en su índice correspondiente,
			digitosControl[i] = 11 - resto;
			switch (digitosControl[i]) { // Retocamos en caso de ser 10 u 11.
			case 10:
				digitosControl[i] = 1;
				break;
			case 11:
				digitosControl[i] = 0;
				break;
			}
		}
		// Devolvemos un array con ambos dígitos
		return digitosControl;
	}

	/**
	 * Generan un IBAN aleatorio
	 * 
	 * @return
	 */
	public String generarIbanRandom() {
		String iban = "ES";

		// el banco lo de uno de los existentes para poder localizar swfit
		List<Bancos> listaBancos = bancosRepository.findAll();
		int indice = 0;
		if (listaBancos.size() > 1) {
			indice = semilla.nextInt(0, listaBancos.size() - 1);
		}
		Bancos banObj = listaBancos.get(indice);
		String banco = banObj.getCodigo();

		// generamos una sucursal y cuenta aleatoria
		String oficina = String.format("%1$" + 4 + "s", Integer.toString(semilla.nextInt(0, 9999))).replace(' ', '0');
		String cuenta = String.format("%1$" + 10 + "s", Integer.toString(semilla.nextInt(0, 999999999))).replace(' ',
				'0');
		String dc = "" + Integer.toString(obtenerDigitosControlCCC(banco, oficina, cuenta)[0])
				+ Integer.toString(obtenerDigitosControlCCC(banco, oficina, cuenta)[1]);

		iban += CommonUtil.obtenerDigitosControlIban(banco, oficina, dc, cuenta) + banco + oficina + dc + cuenta;

		return iban;
	}

	/**
	 * Generar un BIC asociado a un banco
	 * 
	 * @param banco
	 * @return
	 */
	public String generarBicAsociado(String banco) {
		Bancos bancoObj = bancosRepository.findFirstByCodigo(banco);
		return bancoObj.getBic();
	}

	public String generarTarjetaCredito() {
		CreditCardNumberGenerator ccng = new CreditCardNumberGenerator();

		int indice = semilla.nextInt(0, PREFIJO_TARJETA.length - 1);
		String tipo = PREFIJO_TARJETA[indice];

		return ccng.generate(tipo, 16);
	}

	public boolean validarTarjetaCredito(String tarjeta) {
		CardValidationResult valida = RegexCardValidator.isValid(tarjeta);
		return valida.isValid();
	}

	/**
	 * Conformar los datos de una persona completamente aleatoria a devolver
	 * 
	 * @return
	 */
	public Persona conformarPersona() {
		Persona persona = new Persona();
		persona.setNif(doiService.getNif());
		persona.setNie(doiService.getNie());

		// buscamos un id de nombre aleatorio
		Nombres nombre = generaNombrePersonaRandom();
		persona.setNombre(nombre.getNombre());

		// bucamos tambien aleatoriamente los apelliudoos
		List<Apellidos> listaApellidosSele = generarApellidosRandom();
		persona.setApellido1(listaApellidosSele.get(0).getApellido());
		persona.setApellido2(listaApellidosSele.get(1).getApellido());

		// el genero es obtenido del nombre
		if (GENERO_HOMBRE.equals(nombre.getGenero()))
			persona.setGenero(GENERO_HOMBRE_DESC);
		else if (GENERO_MUJER.equals(nombre.getGenero()))
			persona.setGenero(GENERO_MUJER_DESC);
		else
			persona.setGenero(GENERO_INDEFINIDO_DESC);

		// nombre completo lo formamos
		persona.setNombreCompleto(persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2());

		// fecha nacimiento
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -MIN_EDAD);
		Calendar calF = Calendar.getInstance();
		calF.add(Calendar.YEAR, -MAX_EDAD);
		Date fechaNacimiento = CommonUtil.getFechaAleatoria(calF.getTime(), cal.getTime());
		// fijamos la fecha en formato dd/mm/yyyy
		persona.setFechaNacimiento(CommonUtil.getFechaFormateada(fechaNacimiento));
		cal = Calendar.getInstance();
		persona.setEdad(Integer.toString(CommonUtil.calcularEdad(fechaNacimiento, cal.getTime())));

		// telefonos
		persona.setTelefonoMovil(generarNumTelefonoMovil());
		persona.setTelefonoFijo(generarNumTelefonoFijo());

		// login, email, password
		persona.setLogin(generarLoginUsuario(persona.getNombre(), persona.getApellido1()));
		persona.setEmail(generarEmail(persona.getLogin()));
		persona.setPassword(generateCommonTextPassword(21));

		// datos de localizacion
		Ccaa ccaa = generarCCAARandom();
		persona.setCcaa(ccaa.getNombre());
		persona.setCcaaIne(ccaa.getId());
		Provincias provin = generarProvinciaRandom(persona.getCcaaIne());
		persona.setProvincia(provin.getNombre());
		persona.setProvinciaIne(provin.getId());
		Municipios muni = generarMunicipioRandom(persona.getProvinciaIne());
		persona.setMunicipio(muni.getNombre());
		persona.setMunicipioIne(muni.getCodigoine());
		persona.setDireccion(generaDireccionRandom());
		persona.setNumerovia(Integer.toString(semilla.nextInt(1, 999)));
		persona.setCodigoPostal(generarCodPostalRandom(persona.getProvinciaIne(), persona.getMunicipioIne()));

		// cuenta bancaria
		persona.setIban(generarIbanRandom());
		persona.setBic(generarBicAsociado(persona.getIban().substring(4, 8)));
		persona.setTarjetaCredito(generarTarjetaCredito());
		persona.setCvc(semilla.nextInt(100, 999) + "");
		cal = Calendar.getInstance();
		calF = Calendar.getInstance();
		calF.add(Calendar.YEAR, 5);
		Date fechaCaducidad = CommonUtil.getFechaAleatoria(cal.getTime(), calF.getTime());
		// formatearla como MM/YY
		persona.setExpiracionCredito(CommonUtil.getFechaFormateada(fechaCaducidad, "MM/yy"));

		String tipoTarjeta = "";
		// 3-American Express, 4-Visa, 5-Mastercard, 6-Discover
		if (persona.getTarjetaCredito().startsWith("3")) {
			tipoTarjeta = "American Express";
		} else if (persona.getTarjetaCredito().startsWith("4")) {
			tipoTarjeta = "Visa";
		} else if (persona.getTarjetaCredito().startsWith("5")) {
			tipoTarjeta = "Mastercard";
		} else if (persona.getTarjetaCredito().startsWith("6")) {
			tipoTarjeta = "Discover";
		}
		persona.setTipoTarjeta(tipoTarjeta);

		return persona;

	}

}
