package es.jortri.generadores.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import es.jortri.generadores.util.CommonUtil;

@Service
public class DateServices {
	
	private static final int MAX_EDAD = 100;
	private static final int MIN_EDAD = 18;
	
	/**
	 * GEnerar una fecha futuro aleatoria entre dos margenes
	 * @param offsetInicial Años que se incrementan sobre el inicio para el periodo a considerar
	 * @param offsetFinal Años que se incrementan sobre el fin para el periodo a considerar
	 * @return
	 */
	public Date generarFechaFutura(int offsetInicial, int offsetFinal) {
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, offsetInicial);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, offsetFinal);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}	

	/**
	 * GEnerar una fecha de nacimiento aleatoria entre dos edades
	 * @param minEdad Años que se restan para obtener la edad mas joven 
	 * sobre la que generar la fecha
	 * @param maxEdad Años que se restan para obtener la edad mas adulta 
	 * sobre la que generar la fecha
	 * @return
	 */
	public Date generarFechaNacimiento(int minEdad, int maxEdad) {
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, -minEdad);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, -maxEdad);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}
	
	/**
	 * GEnerar una fecha de nacimiento aleatoria entre las edades 
	 * de 18 y 100 años
	 * @return
	 */
	public Date generarFechaNacimiento() {
		//por defecto generamos una fgecha entre 18 y 100 años
		Calendar calFin = Calendar.getInstance();
		calFin.add(Calendar.YEAR, -MIN_EDAD);
		Calendar calIni = Calendar.getInstance();
		calIni.add(Calendar.YEAR, -MAX_EDAD);
		return CommonUtil.getFechaAleatoria(calIni.getTime(), calFin.getTime());
	}	
	
	
	/**
	 * Generar una fecha nacimiento
	 * 
	 * @return
	 */
	public String conformarFechaNacimiento() {
		return CommonUtil.getFechaFormateada(generarFechaNacimiento());
	}	
	
	/**
	 * Generar una fecha a futuro
	 * 
	 * @return
	 */
	public String conformarFechaFuturo() {
		return CommonUtil.getFechaFormateada(generarFechaFutura(1,100));
	}		
	
}
