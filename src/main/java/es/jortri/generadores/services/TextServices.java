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

	/**
	 * Conforma una lista de palabras de numero de caracteres indicado y en el lenguaje indicado
	 * 
	 * @param numCaracteres
	 * @param codigoLenguaje
	 * @return
	 */
	public String conformarRistraCaracteres(int numCaracteres, Lenguaje codigoLenguaje) {
		String cadenaPalabras = "";
		int tamanyo = 0;
		while (tamanyo < numCaracteres) {
			Palabras palabra = generarPalabraRandom();
			String palabraString = "";
			// si codigoLenguaje es igual al Lenguaje SPANISH entonces cogeremos el campo
			// castellano de palabras
			if (codigoLenguaje == Lenguaje.SPANISH) {
				palabraString = palabra.getCastellano();
			} else if (codigoLenguaje == Lenguaje.ENGLISH) {
				palabraString = palabra.getIngles();
			} else if (codigoLenguaje == Lenguaje.LATIN) {
				palabraString = palabra.getLatin();
			}
			
			if (cadenaPalabras.isEmpty()) {
				cadenaPalabras = palabraString;
				tamanyo += palabraString.length();
			} else {
				cadenaPalabras += " " + palabraString;
				tamanyo += palabraString.length() + 1;
			}			
		}
				
		//primera letra en mayusculas y trim a todo
		cadenaPalabras = cadenaPalabras.substring(0, 1).toUpperCase() + cadenaPalabras.substring(1).trim();
		
		//como añadimos bloques de palabras, podemos haber excedido el numero de caracteres, lo recortamos
		if (cadenaPalabras.length() > numCaracteres) {
            cadenaPalabras = cadenaPalabras.substring(0, numCaracteres);
        }
		
		return cadenaPalabras;
	}	
	
	/**
	 * Conforma un texto de X parrafos en el lenguaje indicado
	 * 
	 * @param parrafos
	 * @param codigoLenguaje
	 * @return
	 */
	public String conformarRistraParrafos(int parrafos, Lenguaje codigoLenguaje) {
		

		
		
		String cadenaParrafos = "";
		int cuantos = 0;
		while (cuantos < parrafos) {
			
			//Consideraremos un parrafo como la conformacion entre 50 - 100 palabras
			//aleatoriamente para cada parrafo
			int palabrasParrafo = semilla.nextInt(50, 100);
			int palabrasYa = 0;
			String cadenaPalabras = "";
			while (palabrasYa < palabrasParrafo) {
				Palabras palabra = generarPalabraRandom();
				String palabraString = "";
				// si codigoLenguaje es igual al Lenguaje SPANISH entonces cogeremos el campo
				// castellano de palabras
				if (codigoLenguaje == Lenguaje.SPANISH) {
					palabraString = palabra.getCastellano();
				} else if (codigoLenguaje == Lenguaje.ENGLISH) {
					palabraString = palabra.getIngles();
				} else if (codigoLenguaje == Lenguaje.LATIN) {
					palabraString = palabra.getLatin();
				}
				
				if (cadenaPalabras.isEmpty()) {
					cadenaPalabras = palabraString;
				} else {
					cadenaPalabras += " " + palabraString;
				}	
				palabrasYa++;
			}

			//primera letra en mayusculas y trim a todo y punto al final del parrafo
			cadenaPalabras = cadenaPalabras.substring(0, 1).toUpperCase() + cadenaPalabras.substring(1).trim();		
			cadenaPalabras += ".";
			
			//ahora incluimos cada parrafo
			if (cadenaParrafos.isEmpty()) {
				cadenaParrafos = cadenaPalabras;
			} else {
				cadenaParrafos += "\n" + cadenaPalabras;
			}

			cuantos++;
		}			


		return cadenaParrafos;
	}	
	
}
