package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.services.MiscServices;
import es.jortri.generadores.util.CommonUtil;

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
		
	
	
}
;