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

import es.jortri.generadores.entityreturn.EmpresaReturn;
import es.jortri.generadores.entityreturn.PersonaReturn;
import es.jortri.generadores.enumerados.Genero;
import es.jortri.generadores.enumerados.PrefijoTarjeta;
import es.jortri.generadores.model.Apellidos;
import es.jortri.generadores.model.Ccaa;
import es.jortri.generadores.model.Cnaes;
import es.jortri.generadores.model.Codpostales;
import es.jortri.generadores.model.Municipios;
import es.jortri.generadores.model.Nombres;
import es.jortri.generadores.model.NombresEmpresas;
import es.jortri.generadores.model.Provincias;
import es.jortri.generadores.model.Tipovias;
import es.jortri.generadores.repository.ApellidosRepository;
import es.jortri.generadores.repository.CcaaRepository;
import es.jortri.generadores.repository.CnaesRepository;
import es.jortri.generadores.repository.CodpostalesRepository;
import es.jortri.generadores.repository.MunicipiosRepository;
import es.jortri.generadores.repository.NombresEmpresasRepository;
import es.jortri.generadores.repository.NombresRepository;
import es.jortri.generadores.repository.ProvinciasRepository;
import es.jortri.generadores.repository.TipoviasRepository;
import es.jortri.generadores.util.CommonUtil;

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
	private NombresEmpresasRepository nombresEmpresasRepository;

	@Autowired
	private CnaesRepository cnaesRepository;

	@Autowired
	private BankServices bankServices;

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

	public ProfilesService() {
		this.semilla = new Random();
	}

	private Nombres generaNombrePersonaRandom(Genero gender) {
		Nombres nombre = null;
		if (gender == Genero.AMBOS) {
			Nombres nombrePri = nombresRepository.findFirstByOrderByIdAsc();
			Nombres nombreUlt = nombresRepository.findFirstByOrderByIdDesc();
			int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());
			nombre = nombresRepository.findById(randomSele).get();
		} else {
			String generoStr = "";
			if (gender == Genero.MALE) {
				generoStr = "h";
			} else {
				generoStr = "m";
			}

			Nombres nombrePri = nombresRepository.findFirstByGeneroOrderByIdAsc(generoStr);
			Nombres nombreUlt = nombresRepository.findFirstByGeneroOrderByIdDesc(generoStr);
			boolean encontrado = false;
			while (!encontrado) {
				int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());
				nombre = nombresRepository.findById(randomSele).get();
				if (generoStr.equals(nombre.getGenero())) {
					encontrado = true;
				}
			}

		}

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

		int indice = semilla.nextInt(0, PREFIJOS_TLF_FIJOS.length);
		String prefijo = PREFIJOS_TLF_FIJOS[indice];
		int maximo = 999999;
		int relleno = 6;
		if (prefijo.length() == 2) {
			maximo = 9999999;
			relleno = 7;
		}

		int restoTlfInt = semilla.nextInt(1, maximo);
		String restoTlf = CommonUtil.ponCerosIzquierda(Integer.toString(restoTlfInt), relleno);

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

		int indice = semilla.nextInt(0, SERVIDORES_EMAIL.length);
		String servidor = SERVIDORES_EMAIL[indice];

		return login + "@" + servidor;
	}

	/**
	 * Generar un Email inventandose un login
	 * @return
	 */
	public String generarEmail() {
		String login = CommonUtil.generarLetrasAleatorias(1, CommonUtil.CARACTERES_ALFA_LATINOS)
                + CommonUtil.generarLetrasAleatorias(24, CommonUtil.CARACTERES_EMAIL_VALIDOS);

		return generarEmail(login);
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
		//RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').build();
		//return pwdGenerator.generate(length);
		
		//Referencia: https://www.baeldung.com/java-random-string
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'		
	    String generatedString = semilla.ints(leftLimit, rightLimit + 1)
	    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	    	      .limit(length)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();		
		
		return generatedString;
	}

	/**
	 * Genera un password aleatorio de una logintud indicada donde se mezclan letras
	 * mayus minus, numeros, y caracteres especiales. NOTA:
	 * https://www.baeldung.com/java-generate-secure-password
	 * 
	 * @param length longitud a generar. Minimo de 15
	 * @param cases "y" si quiere mayusculas y minusculas 
	 * @param number "y" si quiere numeros
	 * @param special "y" si quiere caracteres especiales
	 * @return
	 */
	public String generateCommonTextPassword(int length, String cases, String number, String special) {

		int lengthFinal = length;
		if (lengthFinal < 15) {
			lengthFinal = 15;
		}


		int lengthSpecial = 2;
		int lengthNumbers = 4;
		int lengthUpper = 4;
		int lengthLower = 4;
		int lengthAlphaNum = 1 + (lengthFinal - 15);
		
		if (!"y".equals(cases)) {
			lengthUpper += lengthLower;
			lengthLower = 0;
			lengthUpper += lengthAlphaNum;
			lengthAlphaNum = 0;			
		}		
		if (!"y".equals(number)) {
			lengthUpper += lengthNumbers;
			lengthNumbers = 0;
			lengthUpper += lengthAlphaNum;
			lengthAlphaNum = 0;			
		}
		if (!"y".equals(special)) {
			lengthUpper += lengthSpecial;
			lengthSpecial = 0;		
		}			
		
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
	
	public String generateCommonTextPassword(int length) {
		return generateCommonTextPassword(length, "y", "y", "y");
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
			indice = semilla.nextInt(0, listaCCAA.size());
		}

		return listaCCAA.get(indice);
	}
	
	/**
	 * Listar todas las CCAA
	 * 
	 * @return
	 */
	public List<Ccaa> getTodasCcaa() {
		List<Ccaa> listaCCAA = ccaaRepository.findAll();

		return listaCCAA;
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
			indice = semilla.nextInt(0, listProvincias.size());
		}

		return listProvincias.get(indice);
	}

	/**
	 * Devolver lista provincias dentro de una comunidad autonoma
	 * @param idCcaa
	 * @return
	 */
	public List<Provincias> getTodasProvincia(String idCcaa) {
		List<Provincias> listProvincias = provinciasRepository.findByIdccaa(idCcaa);

		return listProvincias;
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
			indice = semilla.nextInt(0, listMunicipios.size());
		}

		return listMunicipios.get(indice);
	}

	/**
	 * Generar un municipio aleatorio de ebtre todos los existentes
	 * 
	 * @return
	 */
	public Municipios generarMunicipioRandom() {
		Municipios muniIni = municipiosRepository.findFirstByOrderByIdAsc();
		Municipios muniUlt = municipiosRepository.findFirstByOrderByIdDesc();
		
		int randomSele = semilla.nextInt(muniIni.getId(), muniUlt.getId());
		Municipios muni = municipiosRepository.findById(randomSele).get();

		return muni;
	}	
	
	/**
	 * Devolver lista municipios dentro de una provincia
	 * @param idProvincia
	 * @return
	 */
	public List<Municipios> getTodosMunicipios(String idProvincia) {
		List<Municipios> listMunicipios = municipiosRepository.findByIdprovincias(idProvincia);

		return listMunicipios;
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
			indice = semilla.nextInt(0, listaTiposVia.size());
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

		int indice = semilla.nextInt(0, NOMBRES_COMUNES_CALLEJERO.length);
		String nombreComun = NOMBRES_COMUNES_CALLEJERO[indice];

		Nombres nombre = generaNombrePersonaRandom(Genero.AMBOS);
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
				indice = semilla.nextInt(0, listaCodPostal.size());
			}

			codPostal = listaCodPostal.get(indice).getCodpostal();

		}

		return codPostal;
	}
	
	/**
	 * Genera un cod postal random 
	 * @param idProvincia
	 * @param ineMunicipio
	 * @return
	 */
	public String generarCodPostalRandom() {
		Codpostales codpostalIni = codpostalesRepository.findFirstByOrderByIdAsc();
		Codpostales codpostalUlt = codpostalesRepository.findFirstByOrderByIdDesc();
		
		int randomSele = semilla.nextInt(codpostalIni.getId(), codpostalUlt.getId());
		Codpostales codpostal = codpostalesRepository.findById(randomSele).get();

		return codpostal.getCodpostal();
	}	
	
	
	/**
	 * GEnerar una fecha futuro aleatoria entre dos margenes
	 * @param offsetInicial Años que se incrementan sobre el inicio para el periodo a considerar
	 * @param offsetFinal Años que se incrementan sobre el fin para el periodo a considerar
	 * @return
	 */
	public Date generarFechaFutura(int offsetInicial, int offsetFinal) {
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, offsetInicial);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, offsetFinal);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}	

	/**
	 * GEnerar una fecha de nacimiento aleatoria entre dos edades
	 * @param minEdad Años que se restan para obtener la edad mas joven 
	 * sobre la que generar la fecha
	 * @param maxEdad Años que se restan para obtener la edad mas adulta 
	 * sobre la que generar la fecha
	 * @return
	 */
	public Date generarFechaNacimiento(int minEdad, int maxEdad) {
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, -minEdad);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, -maxEdad);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}
	
	/**
	 * GEnerar una fecha de nacimiento aleatoria entre las edades 
	 * de 18 y 100 años
	 * @return
	 */
	public Date generarFechaNacimiento() {
		//por defecto generamos una fgecha entre 18 y 100 años
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, -MIN_EDAD);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, -MAX_EDAD);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}	
	
	/**
	 * Conformar los datos de una persona completamente aleatoria a devolver
	 * 
	 * @param gender genero de la persona a limitar
	 * @return
	 */
	public PersonaReturn conformarPersona(Genero gender) {
		PersonaReturn persona = new PersonaReturn();
		persona.setNif(doiService.getNif());
		persona.setNie(doiService.getNie());

		// buscamos un id de nombre aleatorio
		Nombres nombre = generaNombrePersonaRandom(gender);
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
		Date fechaNacimiento = generarFechaNacimiento();
		// fijamos la fecha en formato dd/mm/yyyy
		persona.setFechaNacimiento(CommonUtil.getFechaFormateada(fechaNacimiento));
		Calendar cal = Calendar.getInstance();
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
		persona.setIban(bankServices.generarIbanRandom());
		persona.setBic(bankServices.generarBicAsociado(persona.getIban().substring(4, 8)));
		persona.setTarjetaCredito(bankServices.generarTarjetaCredito(null));
		persona.setCvc(bankServices.generarCvc());
		persona.setExpiracionCredito(bankServices.fechaExpiracionTarjeta());

		String tipoTarjeta = persona.getTarjetaCredito().substring(0,1);
		PrefijoTarjeta prefijo = PrefijoTarjeta.getByCodigoInicio(tipoTarjeta);
		if (prefijo != null) {
			persona.setTipoTarjeta(prefijo.getNombre());
		}		

		return persona;

	}

	/**
	 * Generar un nombre de empresa aleatorio
	 * 
	 * @return
	 */
	private NombresEmpresas generaNombreEmpresaRandom() {
		NombresEmpresas nombrePri = nombresEmpresasRepository.findFirstByOrderByIdAsc();
		NombresEmpresas nombreUlt = nombresEmpresasRepository.findFirstByOrderByIdDesc();
		int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());
		NombresEmpresas nombre = nombresEmpresasRepository.findById(randomSele).get();

		return nombre;
	}

	/**
	 * Generar un CNAE aleatorio
	 * 
	 * @return
	 */
	private Cnaes generarCnaeRandom() {
		Cnaes cnaePri = cnaesRepository.findFirstByOrderByIdAsc();
		Cnaes cnaeUlt = cnaesRepository.findFirstByOrderByIdDesc();
		boolean cnae4digitos = false;
		Cnaes cnae = null;
		while (!cnae4digitos) {
			int randomSele = semilla.nextInt(cnaePri.getId(), cnaeUlt.getId());
			cnae = cnaesRepository.findById(randomSele).get();
			if (cnae.getCodigo().length() == 4) {
				cnae4digitos = true;
			}
		}

		return cnae;
	}

	/**
	 * Conformar los datos de una empresa completamente aleatoria a devolver
	 * 
	 * @return
	 */
	public EmpresaReturn conformarEmpresa() {
		EmpresaReturn empresa = new EmpresaReturn();

		// identificador CIF
		empresa.setCif(doiService.getCif(DoiService.LETRA_CIF_NO_ASIGNADA));
		empresa.setNombre(generaNombreEmpresaRandom().getNombre());

		// fecha creacion
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -100);
		Calendar calF = Calendar.getInstance();
		calF.add(Calendar.YEAR, -1);
		Date fechaCreacion = CommonUtil.getFechaAleatoria(cal.getTime(), calF.getTime());
		empresa.setFechaCreacion(CommonUtil.getFechaFormateada(fechaCreacion));

		// telefonos/email..
		empresa.setTelefono(generarNumTelefonoFijo());
		empresa.setFax(generarNumTelefonoFijo());
		empresa.setEmail(
				generarEmail(empresa.getNombre().replace(" ", "_").replaceAll("[^A-Za-z0-9_]", "")).toLowerCase());

		// datos de localizacion
		Ccaa ccaa = generarCCAARandom();
		empresa.setCcaa(ccaa.getNombre());
		empresa.setCcaaIne(ccaa.getId());
		Provincias provin = generarProvinciaRandom(empresa.getCcaaIne());
		empresa.setProvincia(provin.getNombre());
		empresa.setProvinciaIne(provin.getId());
		Municipios muni = generarMunicipioRandom(empresa.getProvinciaIne());
		empresa.setMunicipio(muni.getNombre());
		empresa.setMunicipioIne(muni.getCodigoine());
		empresa.setDireccion(generaDireccionRandom());
		empresa.setNumerovia(Integer.toString(semilla.nextInt(1, 999)));
		empresa.setCodigoPostal(generarCodPostalRandom(empresa.getProvinciaIne(), empresa.getMunicipioIne()));

		// actividad
		Cnaes cnae = generarCnaeRandom();
		empresa.setCnae(cnae.getCodigo());
		empresa.setActividad(cnae.getNombre());

		return empresa;
	}

}
