package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.services.DateServices;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de datos varios
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/date")
public class DateController {

	@Autowired
	private DateServices dateServices;	
	
	/**
	 * Obtener una lista de fechas de nacimiento
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/birthdate")
	public List<String> birthdate(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaFechas= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaFechas.add(dateServices.conformarFechaNacimiento());
		}

		return listaFechas;
	}		
	
	/**
	 * Obtener una lista de fechas a futuro
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/futuredate")
	public List<String> futuredate(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaFechas= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaFechas.add(dateServices.conformarFechaFuturo());
		}

		return listaFechas;
	}		
	
}
