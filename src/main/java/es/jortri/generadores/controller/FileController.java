package es.jortri.generadores.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.jortri.generadores.entityreturn.TipoHashReturn;
import es.jortri.generadores.enumerados.TipoHash;
import es.jortri.generadores.services.ArchivosService;

/**
 * Controlador para operaciones de archivo
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private ArchivosService archivosService;
	
	
	/**
	 * Codificar un archivo a base 64
	 * 
	 * @param text Texto a codificar. Se recibe en el cuerpo de la petcion
	 * @return
	 */
	@PostMapping("/base64")
    public String base64(@RequestBody String text) {
        return archivosService.codificarBase64(text);
    }
    
    /**
     * Codificar un archivo fisico a base 64
     * Como subir un archivo: https://www.codervlogger.com/upload-file-via-rest-api-controller-spring-boot-tutorial/
     * @param file Archivo a codificar
     * @param name Nombre del archivo
     * @throws IOException 
     */
    @PostMapping(value = "/base64file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String base64file(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name			
			) throws IOException {        
		return archivosService.codificarBase64(file.getBytes());
	}
    
	/**
	 * Decodificar un texto de base 64
	 * 
	 * @param text Texto a decodificar. Se recibe en el cuerpo de la petcion
	 * @return
	 */
    @PostMapping("/decode64")
	public String decode64(@RequestBody String text) {
		return archivosService.decodificarBase64String(text);
	}
    
    /**
     * Decodificar un texto de base 64 y devolverlo como un archivo
     * @param text Texto a decodificar. Se recibe en el cuerpo de la petcion
     * @return
     */
    @PostMapping("/decode64file")
    public byte[] decode64file(@RequestBody String text) {
    	return archivosService.decodificarBase64(text);
    }
	
	
	/**
	 * Obtener la lista de tipos hash posibles
	 * @param results
	 * @param type
	 * @return
	 */
	@GetMapping("/hashtypes")
	public List<TipoHashReturn> hashtypes() {
		return archivosService.obtenerTiposHash();
	}    
    
    /**
     * Obtener el hash de un archivo
     * 
     * @param file Archivo a codificar
     * @param name Nombre del archivo
     * @param type Tipo de algotirmo de hash: MD5, SHA-1, SHA-256, SHA-512. Defecto MD5
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    @PostMapping(value = "/hash", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String hash(
            @Validated @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam Optional<String> type
			) throws IOException, NoSuchAlgorithmException {        

    	TipoHash tipoHash = TipoHash.getByTipo(type.orElse(""));
		if (tipoHash == null) {
			tipoHash = TipoHash.MD5;
		}     	
    	
		return archivosService.aplicarHash(file.getBytes(), tipoHash);
	}	
    
    /**
     * Generar un zip de una lista de archivos
     * @param files Lista de archivos a zippear
     * @return Lista de archivos zip en base 64
     * @throws IOException 
     * 
     */
    @PostMapping(value = "/zip", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public byte[] zip(
            @RequestParam("files") List<MultipartFile> files
			) throws IOException  {      		
		return archivosService.zipear(files);
	}
    
  
    
    
}
