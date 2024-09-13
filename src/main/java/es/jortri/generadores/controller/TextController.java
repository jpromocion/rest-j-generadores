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

import es.jortri.generadores.enumerados.Lenguaje;
import es.jortri.generadores.services.TextServices;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de texto
 * NOTA: Se ha habilitado CORS para permitir el acceso desde cualquier origen, porque el uso desde http://localhost:4200 que
 * despliega la aplicacion de Angular en desarrollo, no permite el acceso a los servicios REST de Spring Boot si no se habilita
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/text")
public class TextController {

	@Autowired
	private TextServices textServices;
	
	/**
	 * Obtener una lista de palabras del tamaño e idioma inndicado
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param words Opcional, número de palabras dentro de cada resultado. Por defecto 100.
	 * @param language Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	 * @return
	 */
	@GetMapping("/words")
	public List<String> words(@RequestParam String results, @RequestParam Optional<String> words, @RequestParam Optional<String> language) {

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
	
	/**
	 * Obtener una lista de palabras que rellenen un tamaño de carácteres indicado y en el idioma indicado
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param characters Opcional, número de carácteres del texto final. Por defecto 100.
	 * @param language Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	 * @return
	 */
	@GetMapping("/characters")
	public List<String> characters(@RequestParam String results, @RequestParam Optional<String> characters, @RequestParam Optional<String> language) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String charactersRev = characters.orElse("");
		int charactersInt = CommonUtil.revisarNumResultadoMaximo(charactersRev, 4000, 100);

		String lenguajeParam = language.orElse("");
		Lenguaje lengua = Lenguaje.getByValorParametro(lenguajeParam);
		if (lengua == null) {
			lengua = Lenguaje.SPANISH;
		}
		
		List<String> listaPalabras = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaPalabras.add(textServices.conformarRistraCaracteres(charactersInt, lengua));
		}

		return listaPalabras;
	}		
	
	/**
	 * Obtener una lista de parrafos dentro de un mismo texto y en el idioma indicado
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @param paragraphs Opcional, número de parrafos del texto final. Por defecto 5.
	 * @param language Opcional, indicar el lenguaje en el que generarse: spanish, english, latin. De no indicarse o indicarse algo erroneo se generar en castellano.
	 * @return
	 */
	@GetMapping("/paragraphs")
	public List<String> paragraphs(@RequestParam String results, @RequestParam Optional<String> paragraphs, @RequestParam Optional<String> language) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String paragraphsRev = paragraphs.orElse("");
		int paragraphsInt = CommonUtil.revisarNumResultadoMaximo(paragraphsRev, 20, 5);

		String lenguajeParam = language.orElse("");
		Lenguaje lengua = Lenguaje.getByValorParametro(lenguajeParam);
		if (lengua == null) {
			lengua = Lenguaje.SPANISH;
		}
		
		List<String> listaPalabras = new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaPalabras.add(textServices.conformarRistraParrafos(paragraphsInt, lengua));
		}

		return listaPalabras;
	}		
		
	
}
