package es.jortri.generadores.util;

public class CommonUtil {
	
	//Devolver un resultado sencillo de OK/ERROR en el servicio
	public static final String RESULTADO_OK = "OK";
	public static final String RESULTADO_ERROR = "ERROR";	
	
	//maximo de resultados permitido por defecto
	public static final int MAX_RESULTADO_PERMITIDO = 1000;
	
	/**
	 * Devuelve el maximo de resultados de cadena como numero si es valido.
	 * En caso de ausencia o erroneo, el valor defecto es 10.
	 * @param results
	 * @param maximo Máximo permitido
	 * @return
	 */
	public static int revisarNumResultadoMaximo(String results, int maximo) {
		String numRevisar = results;
		if (numRevisar == null || numRevisar.isEmpty()) {
			numRevisar = "10";
		}
		int resultsInt;
		try {
			resultsInt = Integer.parseInt(numRevisar);
		} catch (NumberFormatException e) {
			resultsInt = 10;
		}
		if (resultsInt > 1000) {
			resultsInt = 1000;
		}
		return resultsInt;
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
	
}
