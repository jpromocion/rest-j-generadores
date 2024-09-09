package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.enumerados.PrefijoTarjeta;
import es.jortri.generadores.model.Cuenta;
import es.jortri.generadores.model.Tarjeta;
import es.jortri.generadores.services.BankServices;
import es.jortri.generadores.util.CommonUtil;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private BankServices bankServices;
	
	/**
	 * Obtener una lista de cuentas bancarias
	 * @param results
	 * @return
	 */
	@GetMapping("/account")
	public List<Cuenta> account(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		for (int i = 0; i < resultsInt; i++) {
			listaCuentas.add(bankServices.conformarCuenta());
		}

		return listaCuentas;
	}	
	
	
	/**
	 * Obtener una lista de tarjetas bancarias
	 * @param results
	 * @param type
	 * @return
	 */
	@GetMapping("/card")
	public List<Tarjeta> card(@RequestParam String results, @RequestParam Optional<String> type) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String tipoTarjeta = type.orElse("");
		String tipoTarjetaCod = "";
		
		PrefijoTarjeta prefijo = PrefijoTarjeta.getByValorParametro(tipoTarjeta);
		if (prefijo != null) {
			tipoTarjetaCod = prefijo.getCodigoInicio();
		} 
		
		List<Tarjeta> listaTarjetas = new ArrayList<Tarjeta>();
		for (int i = 0; i < resultsInt; i++) {
			listaTarjetas.add(bankServices.conformarTarjeta(tipoTarjetaCod));
		}

		return listaTarjetas;
	}		
	
	/**
	 * Validar un iban
	 * 
	 * @param iban
	 * @return
	 */
	@GetMapping("/validateiban")
	public String validateiban(@RequestParam String iban) {
		String resultado = CommonUtil.RESULTADO_ERROR;
		
		String ibanRevisado = iban.replace(" ", "").toUpperCase();
		if (bankServices.validarIban(ibanRevisado)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}	
	
	/**
	 * Validar una tarjeta credito
	 * 
	 * @param card
	 * @return
	 */
	@GetMapping("/validatecard")
	public String validatecard(@RequestParam String card) {
		String resultado = CommonUtil.RESULTADO_ERROR;

		String cardRevisado = card.replace(" ", "").toUpperCase();
		if (bankServices.validarTarjetaCredito(cardRevisado)) {
			resultado = CommonUtil.RESULTADO_OK;
		}

		return resultado;
	}		
	
}
