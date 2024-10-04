package es.jortri.generadores.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.jortri.generadores.enumerados.RomanNumeralEnum;

@Service
public class NumberServices {

	/**
	 * Generan un numero aleatorio
	 * 
	 * @param cantidad
	 * @param minimo
	 * @param maximo
	 * @param decimales
	 * @param repetidos
	 * @return
	 */
	public List<String> generarNumeroAleatorios(int cantidad, int minimo, int maximo, boolean decimales,
			boolean repetidos) {

		// utilizamos la marca de tiempo para asegurar saemillas distintas
		Random rand = new Random(System.currentTimeMillis());

		List<String> resultado = new ArrayList<String>();
		if (!decimales) {
			if (!repetidos) {
				Set<Integer> set = rand.ints(minimo, maximo + 1).distinct().limit(cantidad).boxed()
						.collect(Collectors.toSet());
				resultado = set.stream().map(String::valueOf).toList();
			} else {
				List<Integer> lista = rand.ints(minimo, maximo + 1).limit(cantidad).boxed()
						.collect(Collectors.toList());
				resultado = lista.stream().map(String::valueOf).toList();

			}

		} else {
			if (!repetidos) {
				Set<Double> set = rand.doubles(minimo, maximo + 1).distinct().limit(cantidad).boxed()
						.collect(Collectors.toSet());
				resultado = set.stream().map(l -> String.format("%.2f", l)).toList();
			} else {
				// hacerlo con double
				List<Double> lista = rand.doubles(minimo, maximo + 1).limit(cantidad).boxed()
						.collect(Collectors.toList());
				resultado = lista.stream().map(l -> String.format("%.2f", l)).toList();
			}
		}

		return resultado;

	}

	/**
	 * Genera numeros aleatorios gaussianos
	 * 
	 * @param cantidad
	 * @param media
	 * @param desviacionEstandar
	 * @param decimalesMostrar
	 * @return
	 */
	public List<String> generarNumeroAleatoriosGaussianos(int cantidad, double media, double desviacionEstandar,
			int decimalesMostrar) {
		Random rand = new Random(System.currentTimeMillis());
		List<Double> lista = rand.doubles(media, desviacionEstandar).limit(cantidad).boxed()
				.collect(Collectors.toList());
		List<String> resultado = lista.stream().map(l -> String.format("%." + decimalesMostrar + "f", l)).toList();
		return resultado;
	}

	/**
	 * Generan un lista de lanzamientos de moneda
	 * 
	 * @param cantidad
	 * @param cara
	 * @param cruz
	 * @return
	 */
	public List<String> generarLanzamientosMoneda(int cantidad, String cara, String cruz) {
		List<String> lista01 = generarNumeroAleatorios(cantidad, 0, 1, false, true);
		List<String> resultado = lista01.stream().map(l -> l.equals("0") ? cara : cruz).toList();
		return resultado;
	}

	/**
	 * Generan un lista de lanzamientos de dado
	 * 
	 * @param cantidad
	 * @return
	 */
	public List<String> generarLanzamientosDado(int cantidad) {
		return generarNumeroAleatorios(cantidad, 1, 6, false, true);
	}

	/**
	 * Factorial recursivo
	 * 
	 * @param num
	 * @return
	 */
	public static Double factorial(int num) {
		if (num == 0) {
			return 1.0;
		} else
			return num * factorial(num - 1);
	}

	/**
	 * Hacer operacion de una calcularadora
	 * 
	 * @param operador
	 * @param num1
	 * @param num2
	 * @return
	 */
	public Double calcular(String operador, Double num1, Double num2) {
		Double resultado = 0.0;
		switch (operador) {
		case "+":
			resultado = num1 + num2;
			break;
		case "-":
			resultado = num1 - num2;
			break;
		case "*":
			resultado = num1 * num2;
			break;
		case "/":
			resultado = num1 / num2;
			break;
		case "resto":
			resultado = num1 % num2;
			break;
		case "potencia":
			resultado = Math.pow(num1, num2);
			break;
		case "porcentaje":
			if (num2 == 0) {
				resultado = 0.0;
			} else {
				resultado = (num1 * num2) / 100;
			}
			break;
		case "factorial":
			resultado = factorial(num1.intValue());
			break;
		case "logaritmo":
			resultado = Math.log10(num1);
			break;
		case "logaritmoNaturalNeperiano":
			resultado = Math.log(num1);
			break;
		case "logaritmoBase2":
			resultado = Math.log(num1) / Math.log(2);
			break;
		case "logaritmoBaseX":
			resultado = Math.log(num1) / Math.log(num2);
			break;
		case "raizCuadrada":
			resultado = Math.sqrt(num1);
			break;
		case "raizCubica":
			resultado = Math.cbrt(num1);
			break;
		case "raizN":
			resultado = Math.pow(num1, 1 / num2);
			break;
		}
		return resultado;
	}

	/**
	 * Calcular una regla de tres directa o inversa
	 * 
	 * @param numA
	 * @param numB
	 * @param numC
	 * @param directa
	 * @return
	 */
	public Double calcularReglaDeTres(Double numA, Double numB, Double numC, boolean directa) {
		if (directa) {
			return (numB * numC) / numA;
		} else {
			return (numA * numB) / numC;
		}
	}

	/**
	 * Calcular el area de una forma trigonometrica
	 * 
	 * @param tipo
	 * @param num1
	 * @param num2
	 * @param num3
	 * @return
	 */
	public Double calcularArea(String tipo, Double num1, Double num2, Double num3) {
		Double resultado = 0.0;
		switch (tipo) {
		case "cuadrado":
			resultado = num1 * num1;
			break;
		case "rectangulo":
			resultado = num1 * num2;
			break;
		case "triangulo":
			resultado = (num1 * num2) / 2;
			break;
		case "paralelogramo":
			resultado = num1 * num2;
			break;
		case "trapezoide":
			resultado = ((num1 + num2) * num3) / 2;
			break;
		case "circulo":
			resultado = Math.PI * Math.pow(num1, 2);
			break;
		case "elipse":
			resultado = Math.PI * num1 * num2;
			break;
		}

		return resultado;
	}

	/**
	 * Convertir grados a radianes
	 * 
	 * @param grados
	 * @return
	 */
	public Double convertirGradosARadianes(Double grados) {
		return Math.toRadians(grados);
	}

	/**
	 * Convertir radianes a grados
	 * 
	 * @param radianes
	 * @return
	 */
	public Double convertirRadianesAGrados(Double radianes) {
		return Math.toDegrees(radianes);
	}

	/**
	 * Calcular una operacion trigonometrica
	 * 
	 * @param tipo
	 * @param num1 El numero en radianes
	 * @return
	 */
	public Double calcularTrigonometrica(String tipo, Double num1) {
		Double resultado = 0.0;
		switch (tipo) {
		case "seno":
			resultado = Math.sin(num1);
			break;
		case "coseno":
			resultado = Math.cos(num1);
			break;
		case "tangente":
			resultado = Math.tan(num1);
			break;
		case "cotangente":
			resultado = 1 / Math.tan(num1);
			break;
		case "cosecante":
			resultado = 1 / Math.sin(num1);
			break;
		case "secante":
			resultado = 1 / Math.cos(num1);
			break;
		case "arcoseno":
			resultado = Math.asin(num1);
			break;
		case "arcocoseno":
			resultado = Math.acos(num1);
			break;
		case "arcotangente":
			resultado = Math.atan(num1);
			break;
		case "senohiperbolico":
			resultado = Math.sinh(num1);
			break;
		case "cosenohiperbolico":
			resultado = Math.cosh(num1);
			break;
		case "tangentehiperbolico":
			resultado = Math.tanh(num1);
			break;
		}
		return resultado;
	}

	/**
	 * Conversor de base númerica
	 * 
	 * @param numero
	 * @param base
	 * @param newBase
	 * @return
	 */
	public String convertirBaseNumerica(String numero, int base, int newBase) {
		return Integer.toString(Integer.parseInt(numero, base), newBase);
	}

	/**
	 * Convertir numeros romanos a arabigos
	 * https://www.baeldung.com/java-convert-roman-arabic
	 * @param input
	 * @return
	 */
	public int romanToArabic(String input) throws IllegalArgumentException {
	    String romanNumeral = input.toUpperCase();
	    int result = 0;
	        
	    List<RomanNumeralEnum> romanNumerals = RomanNumeralEnum.getReverseSortedValues();

	    int i = 0;

	    while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
	    	RomanNumeralEnum symbol = romanNumerals.get(i);
	        if (romanNumeral.startsWith(symbol.name())) {
	            result += symbol.getValue();
	            romanNumeral = romanNumeral.substring(symbol.name().length());
	        } else {
	            i++;
	        }
	    }

	    if (romanNumeral.length() > 0) {
	        throw new IllegalArgumentException(input + " no se puede convertir");
	    }

	    return result;
	}
	
	/**
	 * Convertir numeros arabigos a romanos
	 * https://www.baeldung.com/java-convert-roman-arabic
	 * @param number
	 * @return
	 */
	public String arabicToRoman(int number) throws IllegalArgumentException {
	    if ((number <= 0) || (number > 4000)) {
	        throw new IllegalArgumentException(number + " no está en rango (0,4000]");
	    }

	    List<RomanNumeralEnum> romanNumerals = RomanNumeralEnum.getReverseSortedValues();

	    int i = 0;
	    StringBuilder sb = new StringBuilder();

	    while ((number > 0) && (i < romanNumerals.size())) {
	    	RomanNumeralEnum currentSymbol = romanNumerals.get(i);
	        if (currentSymbol.getValue() <= number) {
	            sb.append(currentSymbol.name());
	            number -= currentSymbol.getValue();
	        } else {
	            i++;
	        }
	    }

	    return sb.toString();
	}	
	
}
