package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.entityreturn.CcaaReturn;
import es.jortri.generadores.entityreturn.DireccionCompletaReturn;
import es.jortri.generadores.entityreturn.MunicipioReturn;
import es.jortri.generadores.entityreturn.ProvinciaReturn;
import es.jortri.generadores.services.MiscServices;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de datos varios
 * NOTA: Se ha habilitado CORS para permitir el acceso desde cualquier origen, porque el uso desde http://localhost:4200 que
 * despliega la aplicacion de Angular en desarrollo, no permite el acceso a los servicios REST de Spring Boot si no se habilita
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/misc")
public class MiscController {

	@Autowired
	private MiscServices miscServices;	
	
	/**
	 * Obtener una lista de emails aleatorios
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/email")
	public List<String> email(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaEmails = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaEmails.add(miscServices.conformarEmails());
		}

		return listaEmails;
	}	
	

	/**
	 * Obtener una lista de passwords aleatorios
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param length Opcional, longitud de la contraseña. Por defecto 21.
	 * @param cases
	 * @param number
	 * @param special
	 * @return
	 */
	@GetMapping("/password")
	public List<String> password(@RequestParam String results, @RequestParam Optional<String> length, 
			@RequestParam Optional<String> cases, @RequestParam Optional<String> number, @RequestParam Optional<String> special) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String lengthRev = length.orElse("");
		int lengthInt = CommonUtil.revisarNumResultadoMaximo(lengthRev, 1000, 21);		
		
		String casesRev = CommonUtil.revisarParamYN(cases);
		
		String numberRev = CommonUtil.revisarParamYN(number);
		
		String specialRev = CommonUtil.revisarParamYN(special);
		
		
		List<String> listaPassword = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaPassword.add(miscServices.conformarPassword(lengthInt, casesRev, numberRev, specialRev));
		}

		return listaPassword;
	}		

	
	/**
	 * Obtener una lista de números de telefono
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param type Opcional, tipo de telefono a generar: m para móvil, f para fijo. Por defecto se genera aleatorio.
	 * @return
	 */
	@GetMapping("/phonenumber")
	public List<String> phonenumber(@RequestParam String results, @RequestParam Optional<String> type) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String typeRev = type.orElse("");
		if (!"f".equals(typeRev) && !"m".equals(typeRev)) {
			typeRev = "";
		}

		List<String> listaTelefonos = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaTelefonos.add(miscServices.conformarTelefono(typeRev));
		}

		return listaTelefonos;
	}	
		
	
	
	
	/**
	 * Obtener una lista de ciudades aleatorias
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/city")
	public List<String> city(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaCiudades= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaCiudades.add(miscServices.conformarCiudad());
		}

		return listaCiudades;
	}		
	
	/**
	 * Obtener una lista de codigos postales aleatorios
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/zipcode")
	public List<String> zipcode(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaCodPostales = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaCodPostales.add(miscServices.conformarCodPostal());
		}

		return listaCodPostales;
	}	
	
	/**
	 * Obtener una lista de codigos IMEI
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/imei")
	public List<String> imei(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaImeis= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaImeis.add(miscServices.conformarImei());
		}

		return listaImeis;
	}		
	
	/**
	 * Obtener una lista de las CCAA
	 * 
	 * @return
	 */
	@GetMapping("/ccaa")
	public List<CcaaReturn> ccaa() {
		return miscServices.listarCcaa();
	}		

	
	/**
	 * Obtener una lista de las provincias de una CCAA
	 * @param idccaa
	 * @return
	 */
	@GetMapping("/provincias")
	public List<ProvinciaReturn> provincias(@RequestParam String idccaa) {
		return miscServices.listarProvincias(idccaa);
	}		

	/**
	 * Obtener una lista de los municipios de una provincia
	 * @param idprovincia Identificador de la provincia
	 * @return
	 */
	@GetMapping("/municipios")
	public List<MunicipioReturn> municipios(@RequestParam String idprovincia) {
		return miscServices.listarMunicipios(idprovincia);
	}		
	
	/**
	 * Obtener una lista de direcciones
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param ineccaa Opcional, código INE de la CCAA
	 * @param ineprovincia Opcional, código INE de la provincia
	 * @param inemunicipio Opcional, código INE del municipio
	 * @return Lista del tipo de direccion a devolver
	 */
	@GetMapping("/address")
	public List<DireccionCompletaReturn> address(@RequestParam String results, @RequestParam Optional<String> ineccaa,
			@RequestParam Optional<String> ineprovincia, @RequestParam Optional<String> inemunicipio) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String ineccaaRev = ineccaa.orElse("");
		String ineprovinciaRev = ineprovincia.orElse("");
		String inemunicipioRev = inemunicipio.orElse("");

		List<DireccionCompletaReturn> listaDirecciones = new ArrayList<DireccionCompletaReturn>();
		for (int i = 0; i < resultsInt; i++) {
			listaDirecciones.add(miscServices.conformarDireccionCompletaTrata(ineccaaRev, ineprovinciaRev, inemunicipioRev));
		}

		return listaDirecciones;
	}
	
	/**
	 * Obtener una lista de códigos promocionales formateados
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param charset Opcional, conjunto de caracteres a utilizar. Por defecto "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	 * @param length Opcional, longitud del código. Por defecto 10.
	 * @param pattern Opcional, patrón de formato del código. Ejemplo: "XXXX-XXXX-XXXX". Si se rellena este valor, se omite el valor de length.
	 * @param prefix Opcional, prefijo del código generado.
	 * @param suffix Opcional, sufijo del código generado.
	 * @return
	 */
	@GetMapping("/voucher")
	public List<String> voucher(@RequestParam String results, @RequestParam Optional<String> charset, 
			@RequestParam Optional<String> length, @RequestParam Optional<String> pattern, 
			@RequestParam Optional<String> prefix, @RequestParam Optional<String> suffix){
		
		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String lengthRev = length.orElse("");
		int lengthInt = CommonUtil.revisarNumResultadoMaximo(lengthRev, 500, 10);		
						
		String patternRev = pattern.orElse("");
		
		String prefixRev = prefix.orElse("");		
		String suffixRev = suffix.orElse("");
		
		String charsetRev = charset.orElse("");		
		if (charsetRev == null || charsetRev.isEmpty()) {
			charsetRev = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		}
		//Si se indico patron y el charset  tiene el "-" se elimina -> es el caracter para patron
		if (patternRev != null && !patternRev.isEmpty() && charsetRev.contains("-")) {
			charsetRev = charsetRev.replaceAll("-", "");
		}		

		List<String> listaVouchers = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaVouchers.add(miscServices.conformarCodigoPromocional(charsetRev, lengthInt, patternRev, prefixRev, suffixRev));
		}

		return listaVouchers;
	}	
	
	
	/**
	 * Obtener una lista de uuids generados aleatoriamente
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/uuid")
	public List<String> uuid(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaEmails = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaEmails.add(miscServices.getUuid());
		}

		return listaEmails;
	}		
	
	/**
	 * Obtener una lista de números de referencias catastrales
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param type. Opcional, tipo de referencia catastral a generar: u para urbana, r para rústica. Por defecto se genera aleatorio.
	 */
	@GetMapping("/catastral")
	public List<String> catastral(@RequestParam String results, @RequestParam Optional<String> type) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String typeRev = type.orElse("").toLowerCase();
		if (!"u".equals(typeRev) && !"r".equals(typeRev)) {
			typeRev = "";
		}

		List<String> listaReferencias = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaReferencias.add(miscServices.conformarReferenciaCatastralTrata(typeRev));
		}

		return listaReferencias;
	}
	
	/**
	 * Validar una referencia catrastral
	 * 
	 * @param catastral Referencia catastral
	 * @return
	 */
	@GetMapping("/validatecatastral")
	public String validatecatastral(@RequestParam String catastral) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		if (miscServices.validarReferenciaCatastral(catastral.toUpperCase())) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}
	
	/**
	 * Obtener una lista de CUPS generados aleatoriamente
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param type Opcional. Tipo de CUPS a generar: e para electricidad, g para gas. Por defecto se genera aleatorio.
	 * @return Lista de cups generados
	 */
	@GetMapping("/cups")
	public List<String> cups(@RequestParam String results, @RequestParam Optional<String> type) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String typeRev = type.orElse("").toLowerCase();
		if (!"e".equals(typeRev) && !"g".equals(typeRev)) {
			typeRev = "";
		}

		List<String> listaCups = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaCups.add(miscServices.generarCUPS(typeRev));
		}

		return listaCups;
	}
	
	/**
	 * Validar un CUPS
	 * 
	 * @param cups CUPS a validar
	 * @return
	 */
	@GetMapping("/validatecups")
	public String validatecups(@RequestParam String cups) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		if (miscServices.validarCUPS(cups)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}
	
	/**
	 * Obtener una lista de LEIs generados aleatoriamente
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return Lista de LEIs generados
	 */
	@GetMapping("/lei")
	public List<String> lei(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaLeis = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaLeis.add(miscServices.generarLEI());
		}

		return listaLeis;
	}
	
	/**
	 * Validar un LEI
	 * 
	 * @param lei LEI a validar
	 * @return
	 */
	@GetMapping("/validatelei")
	public String validatelei(@RequestParam String lei) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		if (miscServices.validarLEI(lei)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}
	
	
}
;