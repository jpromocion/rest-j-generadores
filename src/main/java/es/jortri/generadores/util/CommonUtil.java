package es.jortri.generadores.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommonUtil {

	// Devolver un resultado sencillo de OK/ERROR en el servicio
	public static final String RESULTADO_OK = "OK";
	public static final String RESULTADO_ERROR = "ERROR";

	// maximo de resultados permitido por defecto
	public static final int MAX_RESULTADO_PERMITIDO = 1000;
	
	public static final String CARACTERES_NUMERICOS = "0123456789";
	public static final String CARACTERES_ALFA_LATINOS = "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CARACTERES_ALFANUMERICOS_LATINOS = "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String CARACTERES_EMAIL_VALIDOS = "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
	public static final String CARACTERES_ALFA_LATINOS_MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CARACTERES_ALFANUMERICOS_MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * Devuelve el maximo de resultados de cadena como numero si es valido. En caso
	 * de ausencia o erroneo, el valor defecto es 10.
	 * 
	 * @param results
	 * @param maximo  Máximo permitido
	 * @return
	 */
	public static int revisarNumResultadoMaximo(String results, int maximo, int defecto) {
		String numRevisar = results;
		if (numRevisar == null || numRevisar.isEmpty()) {
			numRevisar = defecto + "";
		}
		int resultsInt;
		try {
			resultsInt = Integer.parseInt(numRevisar);
		} catch (NumberFormatException e) {
			resultsInt = defecto;
		}
		if (resultsInt > maximo) {
			resultsInt = maximo;
		}
		return resultsInt;
	}		
	
	public static int revisarNumResultadoMaximo(String results, int maximo) {
		return revisarNumResultadoMaximo(results, maximo, 10);
	}

	/**
	 * Igual que anterior pero sin maximo
	 * @param results
	 * @param defecto
	 * @return
	 */
	public static int revisarNumResultado(String results, int defecto) {
		String numRevisar = results;
		if (numRevisar == null || numRevisar.isEmpty()) {
			numRevisar = defecto + "";
		}
		int resultsInt;
		try {
			resultsInt = Integer.parseInt(numRevisar);
		} catch (NumberFormatException e) {
			resultsInt = defecto;
		}
		return resultsInt;
	}		
	
	
	public static int revisarNumResultado(String results) {
		return revisarNumResultado(results, 10);
	}
	
	
	/**	
	 * Devuelve el valor numerico en cada como double si es valido. 
	 * En caso de ausencia o erroneo, el valor defecto.
	 * @param results
	 * @param defecto
	 * @return
	 */
	public static double revisarNumResultadoDouble(String results, double defecto) {
		String numRevisar = results;
		if (numRevisar == null || numRevisar.isEmpty()) {
			numRevisar = defecto + "";
		}
		double resultsDouble;
		try {
			resultsDouble = Double.parseDouble(numRevisar);
		} catch (NumberFormatException e) {
			resultsDouble = defecto;
		}
		return resultsDouble;
	}
		
	public static double revisarNumResultadoDouble(String results) {
		return revisarNumResultadoDouble(results, 0);
	}
	
	/**
	 * Revisa parametro  esperado y/n
	 * @param results
	 * @param maximo
	 * @param defecto
	 * @return
	 */
	public static String revisarParamYN(Optional<String> param, String defecto) {
		String paramRev = param.orElse("").toLowerCase();
		if (paramRev.isEmpty()) {
			paramRev = defecto;
		} else {
			if (!paramRev.equals("y") && !paramRev.equals("n")) {
				paramRev = defecto;
			}
		}
		return paramRev;
	}		
	
	public static String revisarParamYN(Optional<String> param) {
		return revisarParamYN(param, "y");
	}		
		
	
	
	/**
	 * Genera un array con los codigos de provincias
	 * 
	 * @return Array con codigos de provincias
	 */
	public static String[] numerosProvincias() {

		String[] numerosProvincias = new String[89];

		for (int i = 0, valorActual = 1; i < numerosProvincias.length; i++, valorActual++) {

			if ((i + 1) < 10) {
				numerosProvincias[i] = "0" + valorActual;
			} else {

				if ((valorActual == 65)) {

					valorActual = 70;

				}

				if (valorActual == 86) {
					valorActual = 91;
				}

				numerosProvincias[i] = String.valueOf(valorActual);

			}
		}

		return numerosProvincias;
	}

	/**
	 * Devuelve el número de cifras de un número
	 * 
	 * @param num Número para contar
	 * @return Número de cifras
	 */
	public static int cuentaCifras(int num) {
		int contador = 0;
		if (num == 0) {
			return 1;
		} else if (num > 0) {
			// bucle que cuenta las cifras
			while (num > 0) {
				num = (int) Math.floor(num / 10);
				contador += 1;
			}
			return contador;
		} else {
			while (num < 0) {
				num = (int) Math.floor(num / 10);
				contador += 1;
			}
			return contador;
		}
	}

	/**
	 * Invierte un array
	 * 
	 * @param array Array de int
	 * @return Array ya invertido
	 */
	public static int[] invertirArray(int array[]) {

		int temp[] = new int[array.length];

		for (int i = temp.length - 1, j = 0; i >= 0; i--, j++) {
			temp[i] = array[j];
		}

		return temp;
	}

	/**
	 * Devuelve los digitos de un números
	 * 
	 * @param numeroInicial Número
	 * @return Array con los digitos
	 */
	public static int[] devuelveDigitos(int numeroInicial) {

		int numero = numeroInicial;

		int digitos[] = new int[cuentaCifras(numeroInicial)];
		int numero_solo;

		for (int i = 0; numeroInicial > 0; i++) {
			numero /= 10;
			numero_solo = numeroInicial - (numero * 10);
			digitos[i] = numero_solo;
			numeroInicial = numero;
		}
		return invertirArray(digitos);

	}

	/**
	 * Suma un array de int
	 * 
	 * @param array Array con los valores a sumar
	 * @return Suma del array de int
	 */
	public static int sumaArray(int array[]) {

		int suma = 0;
		for (int i = 0; i < array.length; i++) {
			suma += array[i];
		}

		return suma;
	}

	/**
	 * Obtener una fecha aleatoria entre dos margenes
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public static Date getFechaAleatoria(Date fechaInicio, Date fechaFin) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		// getYear getMonth y getDate estan deprecated... forma altenativaa?
		long start = cal.getTimeInMillis();
		cal.setTime(fechaFin);
		long end = cal.getTimeInMillis();
		long ms = start + (long) (Math.random() * (end - start));
		cal.setTimeInMillis(ms);
		return cal.getTime();
	}

	/**
	 * Fecha en formato DD/MM/YYYY
	 * 
	 * @param fecha
	 * @return
	 */
	public static String getFechaFormateada(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		//devolverla con el formato indicado en formato
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(cal.getTime());
	}

	public static String getFechaFormateada(Date fecha, String formato) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		//devolverla con el formato indicado en formato
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * Calcular la edad de una persona
	 * 
	 * @param fechaDeNacimiento
	 * @param fecha
	 * @return
	 */
	public static int calcularEdad(Date fechaDeNacimiento, Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int d1 = Integer.parseInt(formatter.format(fechaDeNacimiento));
		int d2 = Integer.parseInt(formatter.format(fecha));
		int edad = (d2 - d1) / 10000;
		return edad;
	}

	/**
	 * Capitaliza las palabras de una cadena NOTA:
	 * https://www.baeldung.com/java-string-initial-capital-letter-every-word
	 * 
	 * @param input
	 * @return
	 */
	public static String usingStringToUpperCaseMethod(String input) {
		return Arrays.stream(input.split("\\s+")).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
				.collect(Collectors.joining(" "));
	}

	/**
	 * Relleno de 0 a la izquierda para una longitud determinada
	 * 
	 * @param str
	 * @param longitud
	 * @return
	 */
	public static String ponCerosIzquierda(String str, int longitud) {
		return String.format("%1$" + longitud + "s", str).replace(' ', '0');
	}
	
	/**
	 * Generar letras aleatorias
	 * 
	 * @param longitud
	 * @param letras
	 * @return
	 */
	public static String generarLetrasAleatorias(int longitud, String letras) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < longitud; i++) {
			int index = (int) (letras.length() * Math.random());
			sb.append(letras.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * Corregir un color hexadecimal para incorporar el #
	 * @param hexa
	 * @return
	 */
	public static String corregirHexadecimal(String hexa) {
		String hexaCorre = hexa;
		if (hexaCorre.charAt(0) != '#') {
			hexaCorre = "#" + hexaCorre;
		}
		return hexaCorre;
	}

}
