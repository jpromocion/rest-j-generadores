package es.jortri.generadores.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.jortri.generadores.entityreturn.TipoHashReturn;
import es.jortri.generadores.enumerados.TipoHash;


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
	
	
	public List<TipoHashReturn> obtenerTiposHash(){
		List<TipoHashReturn> listaTiposHash = new ArrayList<TipoHashReturn>();
		
		TipoHash[] tip= TipoHash.values();
		List<TipoHash> lista = Arrays.asList(tip);
		listaTiposHash = lista.stream().map(tipo -> {
			TipoHashReturn tipoHashReturn = new TipoHashReturn();
			tipoHashReturn.setCodigo(tipo.getTipo());
			tipoHashReturn.setDescripcion(tipo.getDescripcion());
			return tipoHashReturn;
		}).collect(Collectors.toList());
		
		return listaTiposHash;
	}
	
	/**
	 * Aplicar un hash a un archivo
	 * 
	 * @param archivo
	 * @param tipo
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String aplicarHash(byte[] archivo, TipoHash tipo) throws NoSuchAlgorithmException {
		byte[] hash = MessageDigest.getInstance(tipo.getTipo()).digest(archivo);
		String checksum = new BigInteger(1, hash).toString(16);
		
		if (checksum.length() < 32 && tipo.equals(TipoHash.SHA512)) {
            checksum = "0" + checksum;
        }
		
		return checksum;
		
	}
	
}
