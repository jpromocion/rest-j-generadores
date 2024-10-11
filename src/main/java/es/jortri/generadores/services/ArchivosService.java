package es.jortri.generadores.services;

import org.springframework.stereotype.Service;


@Service
public class ArchivosService {

	
	/**
	 * Codificar un archivo a base 64
	 * https://www.baeldung.com/java-base64-encode-and-decode
	 * @param archivo
	 * @return
	 */
	public String codificarBase64(byte[] archivo) {
		return java.util.Base64.getEncoder().encodeToString(archivo);
	}
	
	/**
	 * Codificar una cadena a base 64
	 * 
	 * @param cadena
	 * @return
	 */
	public String codificarBase64(String cadena) {
		return java.util.Base64.getEncoder().encodeToString(cadena.getBytes());
	}	
	
	/**
	 * Decodificar un archivo de base 64
	 * 
	 * @param archivo
	 * @return
	 */
	public byte[] decodificarBase64(String archivo) {
		return java.util.Base64.getDecoder().decode(archivo);
    }
	
	/**
	 * Decodificar un archivo de base 64 a una cadena
	 * @param archivo
	 * @return
	 */
	public String decodificarBase64String(String archivo) {
		return new String(java.util.Base64.getDecoder().decode(archivo));
	}
	
}
