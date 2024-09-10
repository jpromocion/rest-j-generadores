package es.jortri.generadores.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MiscServices {

	@Autowired
	private ProfilesService profilesService;
	
	
	private Random semilla;
	
	public MiscServices() {
        this.semilla = new Random();
	}
	
	
	
	/**
	 * Generar un Email
	 * 
	 * @return
	 */
	public String conformarEmails() {
		return profilesService.generarEmail();
	}	
	
	/**
	 * Generar un Password
	 * @param length
	 * @param cases
	 * @param number
	 * @param special
	 * @return
	 */
	public String conformarPassword(int length, String cases, String number, String special) {
		return profilesService.generateCommonTextPassword(length, cases, number, special);
	}
	
	/**
	 * Generar un telefono
	 * 
	 * @return
	 */
	public String conformarTelefono(String tipo) {
		int entero = 0;
		if (tipo == null || tipo.isEmpty()) {
			entero = semilla.nextInt(0, 2);
		} else if (tipo.equals("m")) {
            entero = 0;
        } else {
            entero = 1;
        }
		
		if (entero == 0) {
			return profilesService.generarNumTelefonoMovil();
		} else {
			return profilesService.generarNumTelefonoFijo();
		}
		
	}		
	
}
