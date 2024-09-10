package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.enumerados.TipoMatricula;
import es.jortri.generadores.services.VehicleServices;
import es.jortri.generadores.util.CommonUtil;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleServices vehicleServices;

	/**
	 * Obtener una lista de numeros de matricula por tipo vehiculo
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param type    Opcional, indicar el tipo de matricula a generarse: t
	 *                (Turismo), c (Ciclomotor), r (Remolque), e (Especial), u
	 *                (Turístico), h (Histórico), tp (Temporal particular), te
	 *                (Temporal empresa), d (Diplomática). De no indicarse o
	 *                indicarse algo erroneo se generar para turismos.
	 * @return
	 */
	@GetMapping("/platenumber")
	public List<String> platenumber(@RequestParam String results, @RequestParam Optional<String> type) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String typeParam = type.orElse("");
		TipoMatricula tipoMatricula = TipoMatricula.getByCodigo(typeParam);
		if (tipoMatricula == null) {
			tipoMatricula = TipoMatricula.TURISMO;
		}

		List<String> listaMatriculas = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaMatriculas.add(vehicleServices.conformarMatricula(tipoMatricula));
		}

		return listaMatriculas;
	}
	
	/**
	 * Obtener una lista de numeros de bastidor de vehiculos
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/vin")
	public List<String> vin(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaVin = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaVin.add(vehicleServices.conformarBastidor());
		}

		return listaVin;
	}	
	
	
	/**
	 * Validar un numero de bastidor
	 * 
	 * @param vin
	 * @return
	 */
	@GetMapping("/validatevin")
	public String validatevin(@RequestParam String vin) {
		String resultado = CommonUtil.RESULTADO_ERROR;
		
		String vinRevisado = vin.replace(" ", "").toUpperCase();
		if (vehicleServices.validarBastidor(vinRevisado)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}		
}
