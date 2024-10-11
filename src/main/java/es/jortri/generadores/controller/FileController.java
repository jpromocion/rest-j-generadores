package es.jortri.generadores.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	
}
