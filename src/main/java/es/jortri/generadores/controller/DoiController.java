package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.services.DoiService;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de DOI
 * NOTA: Se ha habilitado CORS para permitir el acceso desde cualquier origen, porque el uso desde http://localhost:4200 que
 * despliega la aplicacion de Angular en desarrollo, no permite el acceso a los servicios REST de Spring Boot si no se habilita
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/doi")
public class DoiController {

	@Autowired
	private DoiService doiService;	



	/**
	 * Obtener NIF aleatorios
	 * 
	 * @param results NUmero de NIFS a devolver. 10 defecto. Maximo 1000.
	 * @return
	 */
	@GetMapping("/nif")
	public List<String> nif(@RequestParam String results) {
		// el parametro es un valor plano que se invoca en la misma url con ?
		// por ello usamos @RequestParam y no @PathVariable que seria un parametro
		// incluido dentro de la peticion

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaNif = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaNif.add(doiService.getNif());
		}

		return listaNif;
	}

	/**
	 * Validar un NIF
	 * 
	 * @param nif NIF a validar
	 * @return
	 */
	@GetMapping("/validatenif")
	public String validatenif(@RequestParam String nif) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		if (doiService.validarNif(nif)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}

	/**
	 * Obtener NIE aleatorios
	 * 
	 * @param results NUmero de NIEs a devolver. 10 defecto. Maximo 1000.
	 * @return
	 */
	@GetMapping("/nie")
	public List<String> nie(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaNie = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaNie.add(doiService.getNie());
		}

		return listaNie;
	}

	/**
	 * Validar un NIE
	 * 
	 * @param nie NIE a validar
	 * @return
	 */
	@GetMapping("/validatenie")
	public String validatenie(@RequestParam String nie) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		if (doiService.validarNie(nie)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;

	}

	/**
	 * Obtener CIF aleatorios
	 * 
	 * @param results       Numero de CIFs a devolver. 10 defecto. Maximo 1000.
	 * @param custom_letter Letra de inicio del CIF si se desea. Si no se informa se
	 *                      generara aleatoriamente.
	 * @return
	 */
	@GetMapping("/cif")
	public List<String> cif(@RequestParam String results, @RequestParam String custom_letter) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		// Nos quedamos con la letra de CIF indicada si es valida. Sino dejamos un 0
		// para saber la situacion
		char letraCifSele = DoiService.LETRA_CIF_NO_ASIGNADA;
		if (custom_letter != null && !custom_letter.isEmpty() && custom_letter.length() == 1) {
			letraCifSele = custom_letter.charAt(0);
			if (new String(DoiService.LETRASCIF).indexOf(letraCifSele) == -1) {
				letraCifSele = DoiService.LETRA_CIF_NO_ASIGNADA;
			}
		}

		List<String> listaCifs = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaCifs.add(doiService.getCif(letraCifSele));
		}

		return listaCifs;
	}

	/**
	 * Validar un CIF
	 * 
	 * @param cif CIF a validar
	 * @return
	 */
	@GetMapping("/validatecif")
	public String validatecif(@RequestParam String cif) {
		String resultado = CommonUtil.RESULTADO_ERROR;
		
		if (doiService.validarCIF(cif)) {
			resultado = CommonUtil.RESULTADO_OK;
		}
				
		return resultado;
	}
	
	
}
