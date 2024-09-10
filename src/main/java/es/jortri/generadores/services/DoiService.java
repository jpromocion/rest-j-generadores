package es.jortri.generadores.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import es.jortri.generadores.util.CommonUtil;

@Service
public class DoiService {

	private Random semilla;

	private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKET";

	private static final String LETRAS_NIE = "XYZ";

	public static char LETRASCIF[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'U', 'V', 'W' };
	private static boolean NUMERO_O_LETRAS[] = { true, true, false, true, true, true, true, true, true, false, false,
			false, false, false, false, false, false, true, true, false }; // True = numero, false = letra
	private static char letrasCIFFINAL[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
	
	
	public static char LETRA_CIF_NO_ASIGNADA = '0';

	public DoiService() {
		semilla = new Random();
	}

	/**
	 * Obtiene la letra para un numero de DNI
	 * 
	 * @param dni
	 * @return
	 */
	public String letraDNI(int dni) {
		int indice = dni % 23;
		return LETRAS.substring(indice, indice + 1);
	}

	/**
	 * Obtiene un NIF aleatorio
	 * 
	 * @return
	 */
	public String getNif() {
		int numDNI = semilla.nextInt(1000, 100000000);
		String numDNIString = Integer.toString(numDNI);
		return String.format("%1$" + 8 + "s", numDNIString).replace(' ', '0') + letraDNI(numDNI);
	}
	
	/**
	 * Validar un NIF
	 * @param nif
	 * @return
	 */
	public boolean validarNif(String nif) {
		boolean resultado = false;
		if (nif != null && !nif.isEmpty()) {
			if (nif.length() == 9) {
				String numero = nif.substring(0, 8);
				String letra = nif.substring(8, 9);
				if (letra.equals(letraDNI(Integer.parseInt(numero)))) {
					resultado = true;
				}
			}
		}
		
		return resultado;
	}	

	/**
	 * Obtiene un NIE aleatorio
	 * 
	 * @return
	 */
	public String getNie() {
		int numLetraNIE = semilla.nextInt(0, 2);
		char letraNIE = LETRAS_NIE.charAt(numLetraNIE);

		int numDNI = semilla.nextInt(1000, 10000000);
		String numDNIString = Integer.toString(numDNI);

		String cadenaSinInicial = String.format("%1$" + 7 + "s", numDNIString).replace(' ', '0');
		String cadenaConInicial = numLetraNIE + cadenaSinInicial;

		return letraNIE + cadenaSinInicial + letraDNI(Integer.valueOf(cadenaConInicial));
	}
	
	/**
	 * Validar un NIE
	 * @param nie
	 * @return
	 */
	public boolean validarNie(String nie) {
		boolean resultado = false;
		if (nie != null && !nie.isEmpty()) {
			if (nie.length() == 9) {
				String letraNIE = nie.substring(0, 1);
				String numero = nie.substring(1, 8);
				String letraFinal = nie.substring(8, 9);

				Integer numDNI = null;
				try {
					numDNI = Integer.parseInt(numero);
				} catch (NumberFormatException e) {
					numDNI = null;
				}
				if (numDNI != null) {

					if ("X".equals(letraNIE) || "Y".equals(letraNIE) || "Z".equals(letraNIE)) {
						int numLetraNIE;
						if ("X".equals(letraNIE)) {
							numLetraNIE = 0;
						} else if ("Y".equals(letraNIE)) {
							numLetraNIE = 1;
						} else {
							numLetraNIE = 2;
						}
						String cadenaConInicial = numLetraNIE + numero;
						String letraFinalCalcu = letraDNI(Integer.valueOf(cadenaConInicial));

						if (letraFinalCalcu.equals(letraFinal)) {
							resultado = true;
						}
					}

				}

			}
		}
		
		return resultado;
	}	

	/**
	 * Genera el valor D necesario para calcular el digito de control
	 * 
	 * @param numeroCompleto Número CIF
	 * @return Valor de D
	 */
	private int generarD(String numeroCompleto) {

		int A = 0;
		int B = 0;
		int digitoActual;
		for (int i = 0; i < numeroCompleto.length(); i++) {

			digitoActual = Integer.parseInt(String.valueOf(numeroCompleto.charAt(i)));

			if ((i + 1) % 2 == 0) {
				A += digitoActual;
			} else {
				B += CommonUtil.sumaArray(CommonUtil.devuelveDigitos(digitoActual * 2));
			}

		}

		int C = A + B;

		int D = Integer.parseInt(String.valueOf(String.valueOf(C).charAt(String.valueOf(C).length() - 1)));

		return D;

	}

	/**
	 * Genera un CIF aleatorio. NOTA: Demos las gracias a:
	 * https://github.com/DiscoDurodeRoer/Biblioteca-DDR-Java/blob/master/Biblioteca%20DiscoDurodeRoer/src/es/discoduroderoer/clases/documentos/CIF.java
	 * 
	 * @param letraCifSele 0 si no se ha indicado una letra de inicio. EN otro caso
	 *                     llevara la letra
	 * @return
	 */
	public String getCif(char letraInicial) {
		int indiceLetraInicial;
		if (letraInicial == LETRA_CIF_NO_ASIGNADA) {
			indiceLetraInicial = semilla.nextInt(0, LETRASCIF.length);
			letraInicial = LETRASCIF[indiceLetraInicial];
		} else {
			indiceLetraInicial = new String(LETRASCIF).indexOf(letraInicial);
		}

		String numerosProvincias[] = CommonUtil.numerosProvincias();

		String digitoProvincia = numerosProvincias[semilla.nextInt(0, numerosProvincias.length)];

		String restoNumeros = String.valueOf(semilla.nextInt(10000, 99999));

		String numeroCompleto = digitoProvincia + restoNumeros;

		int resultado = 10 - generarD(numeroCompleto);

		String digitoControl;

		if (NUMERO_O_LETRAS[indiceLetraInicial]) {
			if (resultado == 10) {
				digitoControl = "0";
			} else {
				digitoControl = String.valueOf(resultado);
			}
		} else {
			digitoControl = String.valueOf(letrasCIFFINAL[resultado - 1]);
		}

		return letraInicial + numeroCompleto + digitoControl;

	}

	/**
	 * Valida si el CIF es correcto NOTA: Demos las gracias a:
	 * https://github.com/DiscoDurodeRoer/Biblioteca-DDR-Java/blob/master/Biblioteca%20DiscoDurodeRoer/src/es/discoduroderoer/clases/documentos/CIF.java
	 * 
	 * @param cifIn CIF
	 * @return True = es valido
	 */
	public boolean validarCIF(String cifIn) {

		if (cifIn != null && !cifIn.isEmpty() && cifIn.length() <= 9) {

			char letraInicial = cifIn.charAt(0);

			try {
				int resultado;
				int indice = 0;
				String digitoControl;
				String cif;

				boolean encontrado = false;

				for (int i = 0; i < LETRASCIF.length && !encontrado; i++) {
					if (LETRASCIF[i] == letraInicial) {
						indice = i;
						encontrado = true;
					}
				}

				if (encontrado) {

					cif = cifIn.substring(1, cifIn.length() - 1);

					resultado = 10 - generarD(cif);

					if (NUMERO_O_LETRAS[indice]) {

						if (resultado == 10) {
							digitoControl = "0";
						} else {
							digitoControl = String.valueOf(resultado);
						}

					} else {
						digitoControl = String.valueOf(letrasCIFFINAL[resultado - 1]);
					}

				} else {
					return false;
				}

				return cifIn.trim().equals((LETRASCIF[indice] + String.valueOf(cif) + digitoControl).trim());

			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}

	}

}
