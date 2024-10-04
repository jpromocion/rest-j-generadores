package es.jortri.generadores.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.services.NumberServices;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de los servicios de generacion de numeros
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/number")
public class NumberController {

	@Autowired
	private NumberServices numberServices;

	/**
	 * Obtener una lista de numeros aleatorios
	 * 
	 * @param results  Número de resultados a devolver. Defecto 10, máximo valor
	 *                 1000.
	 * @param minimum  Minimo valor. Por defecto 0.
	 * @param maximum  Maximo valor. Por defecto 100.
	 * @param decimals y/n si quiere o no decimales. Por defecto n.
	 * @param repeated y/n si quiere o no repetidos. Por defecto y.
	 * @return
	 */
	@GetMapping("/random")
	public List<String> random(@RequestParam String results, @RequestParam Optional<String> minimum,
			@RequestParam Optional<String> maximum, @RequestParam Optional<String> decimals,
			@RequestParam Optional<String> repeated) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		int minimumInt = CommonUtil.revisarNumResultado(minimum.orElse(""), 0);

		int maximumInt = CommonUtil.revisarNumResultado(maximum.orElse(""), 100);

		// el valor minimo no puede ser mayor que el maximo, de ser asi lo consideramos
		// al reves
		if (minimumInt > maximumInt) {
			int temp = maximumInt;
			maximumInt = minimumInt;
			minimumInt = temp;
		}

		String decimalsRev = CommonUtil.revisarParamYN(decimals, "n");

		String repeatedRev = CommonUtil.revisarParamYN(repeated, "y");

		// si no se admiten repeticiones, la cantidad posible entre minimum y maximum
		// debe ser igual o superior a results
		if (!repeatedRev.equals("y") && (maximumInt - minimumInt + 1) < resultsInt) {
			resultsInt = maximumInt - minimumInt + 1;
		}

		return numberServices.generarNumeroAleatorios(resultsInt, minimumInt, maximumInt, decimalsRev.equals("y"),
				repeatedRev.equals("y"));
	}

	/**
	 * Obtener una lista de lanzamientos de moneda
	 * 
	 * @param results     Número de resultados a devolver. Defecto 10, máximo valor
	 *                    1000.
	 * @param faceSymbol  simbolo para cara. por defecto O.
	 * @param crossSymbol simbolo para cruz. por defecto X.
	 * @return
	 */
	@GetMapping("/coin")
	public List<String> coin(@RequestParam String results, @RequestParam Optional<String> faceSymbol,
			@RequestParam Optional<String> crossSymbol) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		String faceSymbolRev = faceSymbol.orElse("");
		if (faceSymbolRev.isEmpty()) {
			faceSymbolRev = "O";
		}

		String crossSymbolRev = crossSymbol.orElse("");
		if (crossSymbolRev.isEmpty()) {
			crossSymbolRev = "X";
		}

		return numberServices.generarLanzamientosMoneda(resultsInt, faceSymbolRev, crossSymbolRev);

	}

	/**
	 * Obtener una lista de lanzamientos de dado
	 * 
	 * @param results Número de resultados a devolver. Defecto 10, máximo valor
	 *                1000.
	 * @return
	 */
	@GetMapping("/dice")
	public List<String> dice(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		return numberServices.generarLanzamientosDado(resultsInt);

	}

	/**
	 * Devuelve una lista aleatoria de numeros por el método gauss
	 * 
	 * @param results            Número de resultados a devolver. Defecto 10, máximo
	 *                           valor 1000.
	 * @param mean               Media. Por defecto 0
	 * @param standardDeviation. Desviación estandar. Por defecto 1.
	 * @param decimals           Número de posisiones decimales a mostrar. Por
	 *                           defecto 5.
	 * @return
	 */
	@GetMapping("/gauss")
	public List<String> gauss(@RequestParam String results, @RequestParam Optional<String> mean,
			@RequestParam Optional<String> standardDeviation, @RequestParam Optional<String> decimals) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		double meanDouble = CommonUtil.revisarNumResultadoDouble(mean.orElse(""), 0);

		double standardDeviationDouble = CommonUtil.revisarNumResultadoDouble(standardDeviation.orElse(""), 1);

		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 5);

		return numberServices.generarNumeroAleatoriosGaussianos(resultsInt, meanDouble, standardDeviationDouble,
				decimalsInt);

	}

	/**
	 * Realiza una operacion de calculadora con los parametros dados
	 * 
	 * @param operand Operando a realizar. VAlores válidos: +, -, *, /, resto, potencia, porcentaje, factorial, 
	 *   factorial, logaritmo, logaritmoNaturalNeperiano, logaritmoBase2, logaritmoBaseX, 
	 *   raizCuadrada, raizCubica, raizN
	 * @param number1 Primer número
	 * @param number2 Segundo número
	 * @param decimals Número de posisiones decimales a mostrar. Por
	 *                           defecto 2. 
	 * @return
	 */
	@GetMapping("/calculator")
	public String calculator(@RequestParam String operand, @RequestParam Double number1,
			@RequestParam Optional<Double> number2, @RequestParam Optional<String> decimals) {

		Double number1Rev = number1;
		if (number1Rev == null) {
			number1Rev = Double.valueOf(0);
		}

		Double number2Rev = number2.orElse(null);
		if (number2Rev == null) {
			number2Rev = Double.valueOf(0);
		}

		String operandRev = operand;
		if (operand == null || operand.isEmpty() || (!operand.equals("+") && !operand.equals("-")
				&& !operand.equals("*") && !operand.equals("/") && !operand.equals("resto") && !operand.equals("potencia"))
				&& !operand.equals("porcentaje") && !operand.equals("factorial") && !operand.equals("logaritmo")
				&& !operand.equals("logaritmoNaturalNeperiano") && !operand.equals("logaritmoBase2") 
				&& !operand.equals("logaritmoBaseX") && !operand.equals("raizCuadrada")
				&& !operand.equals("raizCubica") && !operand.equals("raizN")) {
			operandRev = "+";
		}
		
		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);

		Double resultado = numberServices.calcular(operandRev, number1Rev, number2Rev);
		return String.format("%." + decimalsInt + "f", resultado);

	}
	
	/**
	 * Realiza la operacion de una regla de tres directa o indirecta: 
	 *    a -> b
	 *    c -> x
	 * @param numberA Valor A
	 * @param numberB Valor B
	 * @param numberC Valor C
	 * @param direct y/n si es directa o indirecta. Por defecto y. 
	 * @param decimals Número de posisiones decimales a mostrar. Por
	 *                          defecto 2.
	 * @return
	 */
	@GetMapping("/proportion")	
	public String proportion(@RequestParam Double numberA, @RequestParam Double numberB, @RequestParam Double numberC,
			@RequestParam Optional<String> direct, @RequestParam Optional<String> decimals) {

		Double numberARev = numberA;
		if (numberARev == null) {
			numberARev = Double.valueOf(0);
		}

		Double numberBRev = numberB;
		if (numberBRev == null) {
			numberBRev = Double.valueOf(0);
		}

		Double numberCRev = numberC;
		if (numberCRev == null) {
			numberCRev = Double.valueOf(0);
		}
		
		String directRev = CommonUtil.revisarParamYN(direct, "y");

		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);

		Double resultado = numberServices.calcularReglaDeTres(numberARev, numberBRev, numberCRev, directRev.equals("y"));
		return String.format("%." + decimalsInt + "f", resultado);

	}
	
	/**
	 * Calcular el area de algunas formas trigonometricas
	 * @param type Forma: cuadrado, rectangulo, triangulo, paralelogramo, trapezoide, circulo, elipse
	 * @param numberA
	 * @param numberB
	 * @param numberC
	 * @param decimals
	 * @return
	 */
	@GetMapping("/area")	
	public String area(@RequestParam String type, 
			@RequestParam Double numberA, @RequestParam Optional<Double> numberB, @RequestParam Optional<Double> numberC, 
			@RequestParam Optional<String> decimals) {
		
		Double numberARev = numberA;
		if (numberARev == null) {
			numberARev = Double.valueOf(0);
		}
		
		Double numberBRev = numberB.orElse(null);
		if (numberBRev == null) {
            numberBRev = Double.valueOf(0);
        }
		
		Double numberCRev = numberC.orElse(null);
		if (numberCRev == null) {
            numberCRev = Double.valueOf(0);
        }
		
		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);
		
		String typeRev = type;
		if (type == null || type.isEmpty() || (!type.equals("cuadrado") && !type.equals("rectangulo")
                && !type.equals("triangulo") && !type.equals("paralelogramo") && !type.equals("trapezoide")
                && !type.equals("circulo") && !type.equals("elipse"))) {
			typeRev = "cuadrado";
		}		
		 
		
		Double resultado = numberServices.calcularArea(typeRev, numberARev, numberBRev, numberCRev);
		return String.format("%." + decimalsInt + "f", resultado);		
	}
	
	/**
	 * Convertir grados en radianes
	 * @param degrees Grados
	 * @param decimals Número de posisiones decimales a mostrar
	 * @return
	 */
	@GetMapping("/degreesToRadians")
	public String degreesToRadians(@RequestParam Double degrees, @RequestParam Optional<String> decimals) {

		Double degreesRev = degrees;
		if (degreesRev == null) {
			degreesRev = Double.valueOf(0);
		}

		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);

		Double resultado = numberServices.convertirGradosARadianes(degreesRev);
		return String.format("%." + decimalsInt + "f", resultado);
	}
	
	/**
	 * Convertir radianes en grados
	 * 
	 * @param radians  Radianes
	 * @param decimals Número de posisiones decimales a mostrar
	 * @return
	 */
	@GetMapping("/radiansToDegrees")
	public String radiansToDegrees(@RequestParam Double radians, @RequestParam Optional<String> decimals) {

		Double radiansRev = radians;
		if (radiansRev == null) {
			radiansRev = Double.valueOf(0);
		}

		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);

		Double resultado = numberServices.convertirRadianesAGrados(radiansRev);
		return String.format("%." + decimalsInt + "f", resultado);
	}
	
	/**
	 * Calcular funciones trigonometricas
	 * @param type Funcion: seno, coseno, tangente, cotangente, cosecante, secante, arcoseno, arcocoseno, arcotangente, senohiperbolico, cosenohiperbolico, tangentehiperbolico
	 * @param number Número en radianes/grados
	 * @param typeNumber Tipo de numero: radianes, grados
	 * @param decimals Número de posisiones decimales a mostrar
	 */
	@GetMapping("/trigonometric")
	public String trigonometric(@RequestParam String type, @RequestParam Double number, @RequestParam String typeNumber,
			@RequestParam Optional<String> decimals) {

		String typeRev = type;
		if (type == null || type.isEmpty()
				|| (!type.equals("seno") && !type.equals("coseno") && !type.equals("tangente")
						&& !type.equals("cotangente") && !type.equals("cosecante") && !type.equals("secante")
						&& !type.equals("arcoseno") && !type.equals("arcocoseno") && !type.equals("arcotangente")
						&& !type.equals("senohiperbolico") && !type.equals("cosenohiperbolico")
						&& !type.equals("tangentehiperbolico"))) {
			typeRev = "seno";
		}
		
		Double numberRev = number;
		if (numberRev == null) {
			numberRev = Double.valueOf(0);
		}
		
		String typeNumberRev = typeNumber;
		if (typeNumber == null || typeNumber.isEmpty()
				|| (!typeNumber.equals("radianes") && !typeNumber.equals("grados"))) {
			typeNumberRev = "radianes";
		}

		int decimalsInt = CommonUtil.revisarNumResultado(decimals.orElse(""), 2);
		
		Double paramRadianes = numberRev;
		if (typeNumberRev.equals("grados")) {
            paramRadianes = numberServices.convertirGradosARadianes(numberRev);
        }
		
		Double resultado = numberServices.calcularTrigonometrica(typeRev, paramRadianes);
		return String.format("%." + decimalsInt + "f", resultado);
	}
	
	/**
	 * Conversor de base numerica
	 * @param number Numero a convertir
	 * @param baseFrom Base de origen. Desde 2 a 36. Incorrecto tomara 10.
	 * @param baseTo Base de destino. Desde 2 a 36. Incorrecto tomara 10.
	 * @return
	 */
	@GetMapping("/baseConverter")
	public String baseConverter(@RequestParam String number, @RequestParam Integer baseFrom,
			@RequestParam Integer baseTo) {
		
		String numberRev = number;
		if (numberRev == null || numberRev.isEmpty()) {
			numberRev = "0";
		}
		
		Integer baseFromRev = baseFrom;
		if (baseFromRev == null || baseFromRev < 2 || baseFromRev > 36) {
			baseFromRev = 10;
		}
		
		Integer baseToRev = baseTo;
		if (baseToRev == null || baseToRev < 2 || baseToRev > 36) {
			baseToRev = 10;
		}
		
		return numberServices.convertirBaseNumerica(numberRev, baseFromRev, baseToRev);
	}
	
	
	/**
	 * Convertir un numero a romano
	 * 
	 * @param number Numero a convertir
	 * @return
	 */
	@GetMapping("/arabicToRoman")
	public String arabicToRoman(@RequestParam Integer number) {
		Integer numberRev = number;
		if (numberRev == null) {
			numberRev = 0;
		}
		return numberServices.arabicToRoman(numberRev);
	}
	
	/**
	 * Convertir un numero romano a arabigo
	 * 
	 * @param number Numero a convertir
	 * @return
	 */
	@GetMapping("/romanToArabic")
	public String romanToArabic(@RequestParam String number) {
		String numberRev = number;
		if (numberRev == null || numberRev.isEmpty()) {
			numberRev = "0";
		}
		int resultado = numberServices.romanToArabic(numberRev);
		return String.valueOf(resultado);
	}

}
