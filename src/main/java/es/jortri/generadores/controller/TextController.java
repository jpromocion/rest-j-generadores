package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.enumerados.Lenguaje;
import es.jortri.generadores.services.TextServices;
import es.jortri.generadores.util.CommonUtil;

@RestController
@RequestMapping("/text")
public class TextController {

	@Autowired
	private TextServices textServices;
	
	/**
	 * Obtener una lista de palabras del tamaño e idioma inndicado
	 * @param results
	 * @return
	 */
	@GetMapping("/words")
	public List<String> account(@RequestParam String results, @RequestParam Optional<String> words, @RequestParam Optional<String> language) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String wordsRev = words.orElse("");
		int wordsInt = CommonUtil.revisarNumResultadoMaximo(wordsRev, 1000, 100);

		String lenguajeParam = language.orElse("");
		Lenguaje lengua = Lenguaje.getByValorParametro(lenguajeParam);
		if (lengua == null) {
			lengua = Lenguaje.SPANISH;
		}
		
		List<String> listaPalabras = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaPalabras.add(textServices.conformarRistraPalabras(wordsInt, lengua));
		}

		return listaPalabras;
	}	
		
	
}
