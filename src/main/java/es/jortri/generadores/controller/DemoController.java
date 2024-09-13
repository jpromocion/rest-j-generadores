package es.jortri.generadores.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de ejemplo
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/hola")
	public String hola(){
		return "Hola Mundo";
	}
	
}
