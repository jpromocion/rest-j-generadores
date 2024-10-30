package es.jortri.generadores.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import es.jortri.generadores.entityreturn.EdadReturn;
import es.jortri.generadores.entityreturn.PascuaReturn;
import es.jortri.generadores.entityreturn.TiempoUnixReturn;
import es.jortri.generadores.util.CommonUtil;
import es.jortri.generadores.util.Pascua;

@Service
public class DateServices {

	private static final int MAX_EDAD = 100;
	private static final int MIN_EDAD = 18;

	/**
	 * Convertir Date a LocalDate
	 * NOTA:https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	 * @param dateToConvert
	 * @return
	 */
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	/**
	 * Convertir Date a LocalDateTime
	 * NOTA:https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}	
	
	/**
	 * Convertir LocalDate a Date
	 * NOTA:https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	 * 
	 * @param dateToConvert
	 * @return
	 */
	public Date convertToDateViaInstant(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	
	/**
	 * Convertir LocalDateTime a Date
	 * NOTA:https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	 * @param dateToConvert
	 * @return
	 */
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}		
	
	/**
	 * Formatear una fecha LocalDateTime en un String
	 * @param fecha
	 * @return
	 */
	public String formatearFecha(LocalDateTime fecha) {
	    //fecha formateada en String como dd/MM/yyyy HH:mm:ss
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    return fecha.format(formatter);
	}

	
	/**
	 * GEnerar una fecha futuro aleatoria entre dos margenes
	 * 
	 * @param offsetInicial Años que se incrementan sobre el inicio para el periodo
	 *                      a considerar
	 * @param offsetFinal   Años que se incrementan sobre el fin para el periodo a
	 *                      considerar
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
	 * 
	 * @param minEdad Años que se restan para obtener la edad mas joven sobre la que
	 *                generar la fecha
	 * @param maxEdad Años que se restan para obtener la edad mas adulta sobre la
	 *                que generar la fecha
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
	 * GEnerar una fecha de nacimiento aleatoria entre las edades de 18 y 100 años
	 * 
	 * @return
	 */
	public Date generarFechaNacimiento() {
		// por defecto generamos una fgecha entre 18 y 100 años
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
		return CommonUtil.getFechaFormateada(generarFechaFutura(1, 100));
	}

	/**
	 * Calcular la diferencia entre dos fechas en años absolutos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularAnyos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.YEARS.between(fechaInicio, fechaFin);

	}

	/**
	 * Calcular la diferencia entre dos fechas en meses absolutos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularMeses(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.MONTHS.between(fechaInicio, fechaFin);
	}

	/**
	 * Calcular la diferencia entre dos fechas en meses relativos (solo el resto
	 * tras descontar los años)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularMesesRelativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		long anyos = ChronoUnit.YEARS.between(fechaInicio, fechaFin);
		LocalDateTime fechaInicioMasAnyos = fechaInicio.plusYears(anyos);		
		
		long mesesCorrespondientes = ChronoUnit.MONTHS.between(fechaInicioMasAnyos, fechaFin);
		return mesesCorrespondientes;		
	}

	/**
	 * Calcular la diferencia entre dos fechas en dias absolutos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularDias(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
	}

	/**
	 * Calcular la diferencia entre dos fechas en dias relativos (solo el resto tras
	 * descontar los años y los meses)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularDiasRelativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		long anyos = ChronoUnit.YEARS.between(fechaInicio, fechaFin);
		LocalDateTime fechaInicioMasAnyos = fechaInicio.plusYears(anyos);
		
		long meses = ChronoUnit.MONTHS.between(fechaInicioMasAnyos, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMeses = fechaInicioMasAnyos.plusMonths(meses);
		
		long diasCorrespondientes = ChronoUnit.DAYS.between(fechaInicioMasAnyosYMeses, fechaFin);
		return diasCorrespondientes;
	}

	/**
	 * Calcular la diferencia entre dos fechas en horas absolutas
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularHoras(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.HOURS.between(fechaInicio, fechaFin);
	}

	/**
	 * Calcular la diferencia entre dos fechas en horas relativas (solo el resto
	 * tras descontar los años y los meses y los dias)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularHorasRelativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		
		long anyos = ChronoUnit.YEARS.between(fechaInicio, fechaFin);
		LocalDateTime fechaInicioMasAnyos = fechaInicio.plusYears(anyos);
		
		long meses = ChronoUnit.MONTHS.between(fechaInicioMasAnyos, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMeses = fechaInicioMasAnyos.plusMonths(meses);
		
		long dias = ChronoUnit.DAYS.between(fechaInicioMasAnyosYMeses, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDias = fechaInicioMasAnyosYMeses.plusDays(dias);
		
		long horasCorrespondientes = ChronoUnit.HOURS.between(fechaInicioMasAnyosYMesesYDias, fechaFin);
		return horasCorrespondientes;
	}

	/**
	 * Calcular la diferencia entre dos fechas en minutos absolutos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularMinutos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.MINUTES.between(fechaInicio, fechaFin);
	}

	/**
	 * Calcular la diferencia entre dos fechas en minutos relativas (solo el resto
	 * tras descontar los años, los meses, los dias, jpras)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularMinutosRelativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {		
		long anyos = ChronoUnit.YEARS.between(fechaInicio, fechaFin);
		LocalDateTime fechaInicioMasAnyos = fechaInicio.plusYears(anyos);
		
		long meses = ChronoUnit.MONTHS.between(fechaInicioMasAnyos, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMeses = fechaInicioMasAnyos.plusMonths(meses);
		
		long dias = ChronoUnit.DAYS.between(fechaInicioMasAnyosYMeses, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDias = fechaInicioMasAnyosYMeses.plusDays(dias);
		
		long horas = ChronoUnit.HOURS.between(fechaInicioMasAnyosYMesesYDias, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDiasYHoras = fechaInicioMasAnyosYMesesYDias
				.plusHours(horas);
		
		
		long minutosCorrespondientes = ChronoUnit.MINUTES.between(fechaInicioMasAnyosYMesesYDiasYHoras, fechaFin);
		return minutosCorrespondientes;
	}

	/**
	 * Calcular la diferencia entre dos fechas en segundos absolutos
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularSegundos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return ChronoUnit.SECONDS.between(fechaInicio, fechaFin);
	}

	/**
	 * Calcular la diferencia entre dos fechas en segundos relativos (solo el resto
	 * tras descontar los años, los meses, los dias, horas, minutos)
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	private Long calcularSegundosRelativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		
		long anyos = ChronoUnit.YEARS.between(fechaInicio, fechaFin);
		LocalDateTime fechaInicioMasAnyos = fechaInicio.plusYears(anyos);
		
		long meses = ChronoUnit.MONTHS.between(fechaInicioMasAnyos, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMeses = fechaInicioMasAnyos.plusMonths(meses);
		
		long dias = ChronoUnit.DAYS.between(fechaInicioMasAnyosYMeses, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDias = fechaInicioMasAnyosYMeses.plusDays(dias);
		
		long horas = ChronoUnit.HOURS.between(fechaInicioMasAnyosYMesesYDias, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDiasYHoras = fechaInicioMasAnyosYMesesYDias
				.plusHours(horas);
		
		long minutos = ChronoUnit.MINUTES.between(fechaInicioMasAnyosYMesesYDiasYHoras, fechaFin);
		LocalDateTime fechaInicioMasAnyosYMesesYDiasYHorasYMinutos = fechaInicioMasAnyosYMesesYDiasYHoras
				.plusMinutes(minutos);
		
		long segundosCorrespondientes = ChronoUnit.SECONDS.between(fechaInicioMasAnyosYMesesYDiasYHorasYMinutos,
				fechaFin);
		return segundosCorrespondientes;
	}

	/**
	 * Calcular la diferencia entre dos fechas en años, meses, dias, horas, minutos
	 * y segundos
	 */
	public EdadReturn calcularDiferenciaFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		EdadReturn edadReturn = new EdadReturn();
		
		//Para los calculos se utiliza ChronoUnit
		// https://www.baeldung.com/java-date-difference
		edadReturn.setAnyos(calcularAnyos(fechaInicio, fechaFin));
		edadReturn.setAnyosRelativos(edadReturn.getAnyos());
		edadReturn.setMeses(calcularMeses(fechaInicio, fechaFin));
		edadReturn.setMesesRelativos(calcularMesesRelativo(fechaInicio, fechaFin));
		edadReturn.setDias(calcularDias(fechaInicio, fechaFin));
		edadReturn.setDiasRelativos(calcularDiasRelativo(fechaInicio, fechaFin));
		edadReturn.setHoras(calcularHoras(fechaInicio, fechaFin));
		edadReturn.setHorasRelativos(calcularHorasRelativo(fechaInicio, fechaFin));
		edadReturn.setMinutos(calcularMinutos(fechaInicio, fechaFin));
		edadReturn.setMinutosRelativos(calcularMinutosRelativo(fechaInicio, fechaFin));
		edadReturn.setSegundos(calcularSegundos(fechaInicio, fechaFin));
		edadReturn.setSegundosRelativos(calcularSegundosRelativo(fechaInicio, fechaFin));
		return edadReturn;
	}

	/**
	 * Calcular edad a partir de una fecha de nacimiento
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	public EdadReturn calcularEdad(LocalDateTime fechaNacimiento) {
		Calendar cal = Calendar.getInstance();
		return calcularDiferenciaFechas(fechaNacimiento,
				LocalDateTime.ofInstant(cal.toInstant(), ZoneId.systemDefault()));
	}
	
	
	/**
	 * Operar con una fecha
	 * @param fecha Fecha sobre la que se opera
	 * @param operacion Operacion a realizar: "sum" para sumar, "res" para restar
	 * @param anyos Años a sumar o restar
	 * @param meses Meses a sumar o restar
	 * @param dias Dias a sumar o restar
	 * @param horas Horas a sumar o restar
	 * @param minutos Minutos a sumar o restar
	 * @param segundos Segundos a sumar o restar
	 * @return Fecha resultante
	 */
	public LocalDateTime operarFecha(LocalDateTime fecha, String operacion, long anyos, long meses, long dias, long horas, long minutos, long segundos) {
		LocalDateTime fechaResultante = null;
		if (operacion.equals("sum")) {
			fechaResultante = fecha.plusYears(anyos);
			fechaResultante = fechaResultante.plusMonths(meses);
			fechaResultante = fechaResultante.plusDays(dias);
			fechaResultante = fechaResultante.plusHours(horas);
			fechaResultante = fechaResultante.plusMinutes(minutos);
			fechaResultante = fechaResultante.plusSeconds(segundos);
        } else {
        	fechaResultante = fecha.minusYears(anyos);
        	fechaResultante = fechaResultante.minusMonths(meses);
        	fechaResultante = fechaResultante.minusDays(dias);
        	fechaResultante = fechaResultante.minusHours(horas);
        	fechaResultante = fechaResultante.minusMinutes(minutos);
        	fechaResultante = fechaResultante.minusSeconds(segundos);
        }
        return fechaResultante;
	}
	
	/**
	 * Dada una fecha, devuelve a que dia de la semana corresponde en español
	 * @param fecha
	 */
	public String diaSemana(LocalDateTime fecha) {
		String[] diasSemana = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        return diasSemana[fecha.getDayOfWeek().getValue()];
	}
	
	/**
	 * Dada una fecha LocalDateTime le asegura el uso hoario de madrid
	 * 
	 * @param fecha
	 */
	public ZonedDateTime ajustarHoraMadrid(LocalDateTime fecha) {
		//aseguramos fijarle nuestro uso horario
        ZoneId zoneId = ZoneId.of("Europe/Madrid");
        ZonedDateTime zonedDateTime = fecha.atZone(zoneId);		
		return zonedDateTime;
	}
	
	/**
	 * Convertir tiempo UNIX (UTC) en un LocalDateTime
	 * @param tiempo
	 * @return
	 */
	public TiempoUnixReturn convertirTiempoUnix(long tiempo) {
		TiempoUnixReturn tiempoUnixReturn = new TiempoUnixReturn();
		
		Instant givenDate = Instant.ofEpochSecond(tiempo);
		tiempoUnixReturn.setTiempoUnixUTC(tiempo);
		tiempoUnixReturn.setFechaLocal(givenDate.atZone(ZoneId.systemDefault()).toLocalDateTime());
		tiempoUnixReturn.setFechaUTC(givenDate.atZone(ZoneId.of("UTC")).toLocalDateTime());
		
		return tiempoUnixReturn;
	}
	
	/**
	 * Convertir un LocalDateTime proporcionado en local, en un tiempo UNIX
	 * @param fecha
	 * @return
	 */
	public TiempoUnixReturn convertirFechaUnix(ZonedDateTime fecha) {
		TiempoUnixReturn tiempoUnixReturn = new TiempoUnixReturn();
		
		Instant givenDate = fecha.toInstant();
		tiempoUnixReturn.setFechaLocal(givenDate.atZone(ZoneId.systemDefault()).toLocalDateTime());
		ZonedDateTime utcZonedDateTime = fecha.withZoneSameInstant(ZoneOffset.UTC);		 
		tiempoUnixReturn.setFechaUTC(utcZonedDateTime.toLocalDateTime());
		tiempoUnixReturn.setTiempoUnixUTC(utcZonedDateTime.toInstant().getEpochSecond());	

		return tiempoUnixReturn;
	}
	


	/**
	 * Calcular todas las fechas de la festividad de Pascua-Semana santa de un año
	 * @param ejercicio
	 * @return
	 */
	public PascuaReturn damePascua(Integer ejercicio) {
	    PascuaReturn pascuaReturn = new PascuaReturn();

	    Pascua pascua = new Pascua(ejercicio);

	    Date fecpascua = pascua.getFechaPascua();
	    LocalDateTime fechaDomingoResu = convertToLocalDateTimeViaInstant(fecpascua);
	    pascuaReturn.setFechaDomingoResurreccion(convertToDateViaSqlTimestamp(fechaDomingoResu));
	    
	    pascuaReturn.setFechaDomingoRamos(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(7)));
	    pascuaReturn.setFechaLunesSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(6)));
	    pascuaReturn.setFechaMartesSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(5)));
	    pascuaReturn.setFechaMiercolesSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(4)));
	    pascuaReturn.setFechaJuevesSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(3)));
	    pascuaReturn.setFechaViernesSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(2)));
	    pascuaReturn.setFechaSabadoSanto(convertToDateViaSqlTimestamp(fechaDomingoResu.minusDays(1)));
	    
	    pascuaReturn.setFechaLunesPeriodoVacacional(pascuaReturn.getFechaLunesSanto());
	    pascuaReturn.setFechaDomingoPeriodoVacacional(convertToDateViaSqlTimestamp(fechaDomingoResu.plusDays(7)));

	    return pascuaReturn;        
	}
	

	
}
