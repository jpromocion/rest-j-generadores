package es.jortri.generadores.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.enumerados.Lenguaje;
import es.jortri.generadores.model.Palabras;
import es.jortri.generadores.repository.PalabrasRepository;

@Service
public class TextServices {

	@Autowired
	private PalabrasRepository palabrasRepository;

	private Random semilla;

	public TextServices() {
		this.semilla = new Random();
	}

	/**
	 * Genera una palabra aleatoria
	 */
	private Palabras generarPalabraRandom() {
		Palabras palabraPri = palabrasRepository.findFirstByOrderByIdAsc();
		Palabras palabraUlt = palabrasRepository.findFirstByOrderByIdDesc();
		int randomSele = semilla.nextInt(palabraPri.getId(), palabraUlt.getId());
		Palabras palabraSelect = palabrasRepository.findById(randomSele).get();

		return palabraSelect;
	}

	/**
	 * Conforma una lista de palabras de numero indicado y en el lenguaje indicado
	 * 
	 * @param numPalabras
	 * @param codigoLenguaje
	 * @return
	 */
	public String conformarRistraPalabras(int numPalabras, Lenguaje codigoLenguaje) {
		String cadenaPalabras = "";
		int cuantas = 0;
		while (cuantas < numPalabras) {
			Palabras palabra = generarPalabraRandom();
			// si codigoLenguaje es igual al Lenguaje SPANISH entonces cogeremos el campo
			// castellano de palabras
			if (codigoLenguaje == Lenguaje.SPANISH) {
				cadenaPalabras += palabra.getCastellano() + " ";
			} else if (codigoLenguaje == Lenguaje.ENGLISH) {
				cadenaPalabras += palabra.getIngles() + " ";
			} else if (codigoLenguaje == Lenguaje.LATIN) {
				cadenaPalabras += palabra.getLatin() + " ";
			}
			cuantas++;
		}
		
		//primera letra en mayusculas y trim a todo
		cadenaPalabras = cadenaPalabras.substring(0, 1).toUpperCase() + cadenaPalabras.substring(1).trim();   
		return cadenaPalabras;
	}

}
