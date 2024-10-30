package es.jortri.generadores.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.entityreturn.EdadReturn;
import es.jortri.generadores.entityreturn.PascuaReturn;
import es.jortri.generadores.entityreturn.TiempoUnixReturn;
import es.jortri.generadores.services.DateServices;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de datos varios
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/date")
public class DateController {

	@Autowired
	private DateServices dateServices;	
	
	/**
	 * Obtener una lista de fechas de nacimiento
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/birthdate")
	public List<String> birthdate(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaFechas= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaFechas.add(dateServices.conformarFechaNacimiento());
		}

		return listaFechas;
	}		
	
	/**
	 * Obtener una lista de fechas a futuro
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor 1000.
	 * @return
	 */
	@GetMapping("/futuredate")
	public List<String> futuredate(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<String> listaFechas= new ArrayList<String>();
		for (int i = 0; i < resultsInt; i++) {
			listaFechas.add(dateServices.conformarFechaFuturo());
		}

		return listaFechas;
	}		
	
	/**
	 * Calcular edad
	 * 
	 * @param birthDate Fecha nacimiento formato definido en el application.properties "dd/MM/yyyy HH:mm:ss"
	 * @return
	 */
	@GetMapping("/age")
	public EdadReturn age(@RequestParam LocalDateTime birthDate) {
		return dateServices.calcularEdad(birthDate);
	}		
	
	
	
	/**
	 * Calcular la diferencia entre dos fechas
	 * @param startDate Fecha inicio. Formato definido en el application.properties "dd/MM/yyyy HH:mm:ss"
	 * @param endDate Fecha fin. Formato definido en el application.properties "dd/MM/yyyy HH:mm:ss"
	 * @return
	 */
	@GetMapping("/datediff")
	public EdadReturn datediff(@RequestParam LocalDateTime startDate, 
			@RequestParam LocalDateTime  endDate) {
		
		//La fecha fin debe ser igual o superor a la fecha inicio
		if (endDate.isBefore(startDate)) {
			LocalDateTime tempDate = startDate;
			startDate = endDate;
			endDate = tempDate;
		}		

		return dateServices.calcularDiferenciaFechas(startDate, endDate);
	}
	
	/**
	 * Operar con una fecha
	 * 
	 * @param date Fecha a operar. Formato definido en el application.properties "dd/MM/yyyy HH:mm:ss"
	 * @param operation Operador: "sum" sumar o "res" restar
	 * @param years Años a sumar o restar
	 * @param months Meses a sumar o restar
	 * @param days Días a sumar o restar
	 * @param hours Horas a sumar o restar
	 * @param minutes Minutos a sumar o restar
	 * @param seconds Segundos a sumar o restar
	 * @return Fecha resultante. Formato "dd/MM/yyyy HH:mm:ss"
	 */
	@GetMapping("/dateoperation")
	public String dateoperation(@RequestParam LocalDateTime date, @RequestParam String operation,
			@RequestParam int years, @RequestParam int months, @RequestParam int days, @RequestParam int hours,
			@RequestParam int minutes, @RequestParam int seconds) {
		//revisamos que la operation sea una de los valores permitidos
		if (!operation.equals("sum") && !operation.equals("res")) {
            operation = "sum";
        }
		
		LocalDateTime fechaOperada = dateServices.operarFecha(date, operation, years, months, days, hours, minutes, seconds);
		return dateServices.formatearFecha(fechaOperada);

	}
	
	/**
	 * Devuelve día de la semana
	 * 
	 * @param date Fecha a operar. Formato definido en el application.properties "dd/MM/yyyy HH:mm:ss"
	 * @return Día de la semana
	 */
	@GetMapping("/dayofweek")
	public String dayofweek(@RequestParam LocalDateTime date) {
		return dateServices.diaSemana(date);
	}
	
	/**
	 * Convertir un tiempo UNIX proporcionado en UTC a matriz de tiempos convertida
	 * 
	 * @param unixTime Tiempo UNIX en UTC
	 * @return Matriz de tiempos convertida
	 */
	@GetMapping("/unixtimeToTime")
	public TiempoUnixReturn unixtimeToTime(@RequestParam Long unixTime) {
		return dateServices.convertirTiempoUnix(unixTime);
	}
	
	/**
	 * Convertir una fecha a tiempo UNIX
	 * 
	 * @param date Fecha a convertir. Formato definido en el application.properties
	 *             "dd/MM/yyyy HH:mm:ss"
	 * @return Tiempo UNIX
	 */
	@GetMapping("/timeToUnixtime")
	public TiempoUnixReturn timetounix(@RequestParam LocalDateTime date) {
		return dateServices.convertirFechaUnix(dateServices.ajustarHoraMadrid(date));
	}
	
	/**
	 * Obtiene las fechas de la Semana Santa para un ejercicio dado
	 * 
	 * @param ejercicio Ejercicio. Ejemplo: "2024"
	 * @return
	 */
	@GetMapping("/holyWeek")
	public PascuaReturn holyWeek(@RequestParam Integer year) {
		return dateServices.damePascua(year);
	}	
	
}
