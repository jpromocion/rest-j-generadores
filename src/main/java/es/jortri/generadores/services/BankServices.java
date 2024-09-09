package es.jortri.generadores.services;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.validator.routines.IBANValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.enumerados.PrefijoTarjeta;
import es.jortri.generadores.model.Bancos;
import es.jortri.generadores.model.Cuenta;
import es.jortri.generadores.model.Tarjeta;
import es.jortri.generadores.repository.BancosRepository;
import es.jortri.generadores.util.CardValidationResult;
import es.jortri.generadores.util.CommonUtil;
import es.jortri.generadores.util.CreditCardNumberGenerator;
import es.jortri.generadores.util.RegexCardValidator;

@Service
public class BankServices {

	@Autowired
	private BancosRepository bancosRepository;
	
	
	private Random semilla;
	
	
	// 3-American Express, 4-Visa, 5-Mastercard, 6-Discover
	public static String PREFIJO_TARJETA[] = { "3", "4", "5", "6" };	
	
	public BankServices() {
		this.semilla = new Random();
	}	

	/**
	 * Obtener los DC para los datos de banco,oficina y cuenta de un CCC NOTA:
	 * https://github.com/rociodemula/prog11/blob/master/src/basicos/CuentaBancaria.java
	 * 
	 * @param entidad
	 * @param sucursal
	 * @param cuenta
	 * @return
	 */
	public static int[] obtenerDigitosControlCCC(String entidad, String sucursal, String cuenta) {
		// Factores multiplicadores para la operación de validación.
		int factores[] = { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		// array para alojar los DC
		int[] digitosControl = new int[2];
		// Separamos en 2 cadenas para posteriores cálculos necesarios.
		// Se sigue el procedimiento para el cálculo indicado en
		// http://es.wikipedia.org/wiki/C%C3%B3digo_cuenta_cliente
		String cadenaValidacion[] = { "00" + entidad + sucursal, cuenta };
		// Declaramos las variables intermedias que necesitaremos
		int resultado, resto;
		// Mediante un bucle acumulamos el pre-resultado de cada dígito
		for (int i = 0; i < digitosControl.length; i++) {
			resultado = 0; // INicializamos el acumulador
			for (int j = 0; j < factores.length; j++) {
				// Vamos multiplicando cada operando y acumulando en la variable
				resultado += factores[j] * Integer.parseInt(cadenaValidacion[i].substring(j, j + 1));
			}
			// Seguimos la cadena de operaciones reglamentaria, con otra variable
			resto = resultado % 11;
			// Guardamos cada dígito en su índice correspondiente,
			digitosControl[i] = 11 - resto;
			switch (digitosControl[i]) { // Retocamos en caso de ser 10 u 11.
			case 10:
				digitosControl[i] = 1;
				break;
			case 11:
				digitosControl[i] = 0;
				break;
			}
		}
		// Devolvemos un array con ambos dígitos
		return digitosControl;
	}	
	
	/**
	 * Mapeo de pesos por letra de pais del IBAN
	 * 
	 * @param letra
	 * @return
	 */
	public static int damePesoIBAN(char letra) {

		switch (letra) {
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		case 'G':
			return 16;
		case 'H':
			return 17;
		case 'I':
			return 18;
		case 'J':
			return 19;
		case 'K':
			return 20;
		case 'L':
			return 21;
		case 'M':
			return 22;
		case 'N':
			return 23;
		case 'O':
			return 24;
		case 'P':
			return 25;
		case 'Q':
			return 26;
		case 'R':
			return 27;
		case 'S':
			return 28;
		case 'T':
			return 29;
		case 'U':
			return 30;
		case 'V':
			return 31;
		case 'W':
			return 32;
		case 'X':
			return 33;
		case 'Y':
			return 34;
		case 'Z':
			return 35;
		}
		return 0;

	}

	/**
	 * Obtener los DC de un IBAN
	 * 
	 * @param entidad
	 * @param oficina
	 * @param dc
	 * @param numeroCuenta
	 * @return
	 */
	public static String obtenerDigitosControlIban(String entidad, String oficina, String dc, String numeroCuenta) {
		String codigoPais = "ES";
		String preIban = entidad + oficina + dc + numeroCuenta + damePesoIBAN(codigoPais.charAt(0))
				+ damePesoIBAN(codigoPais.charAt(1)) + "00";
		BigInteger ccc = new BigInteger(preIban);
		BigInteger noventaysiete = new BigInteger("97");
		ccc = ccc.mod(noventaysiete);
		int dcIb = ccc.intValue();
		dcIb = 98 - dcIb;
		return CommonUtil.ponCerosIzquierda(Integer.toString(dcIb), 2);
	}	
	
	/**
	 * Generan un IBAN aleatorio
	 * 
	 * @return
	 */
	public String generarIbanRandom() {
		String iban = "ES";

		// el banco lo de uno de los existentes para poder localizar swfit
		List<Bancos> listaBancos = bancosRepository.findAll();
		int indice = 0;
		if (listaBancos.size() > 1) {
			indice = semilla.nextInt(0, listaBancos.size() - 1);
		}
		Bancos banObj = listaBancos.get(indice);
		String banco = banObj.getCodigo();

		// generamos una sucursal y cuenta aleatoria
		String oficina = String.format("%1$" + 4 + "s", Integer.toString(semilla.nextInt(0, 9999))).replace(' ', '0');
		String cuenta = String.format("%1$" + 10 + "s", Integer.toString(semilla.nextInt(0, 999999999))).replace(' ',
				'0');
		String dc = "" + Integer.toString(obtenerDigitosControlCCC(banco, oficina, cuenta)[0])
				+ Integer.toString(obtenerDigitosControlCCC(banco, oficina, cuenta)[1]);

		iban += obtenerDigitosControlIban(banco, oficina, dc, cuenta) + banco + oficina + dc + cuenta;

		return iban;
	}	
	
	/**
	 * Valida un iban
	 * 
	 * @param iban
	 * @return
	 */
	public boolean validarIban(String iban) {		
		IBANValidator ibanValidator = new IBANValidator();
		return ibanValidator.isValid(iban);
		
	}		
	
	/**
	 * Iban formateado para escritura
	 * @param iban
	 * @return
	 */
	public String formatearIban(String iban) {
		//string con el iban formateado: grupos de 4 con separacion espacio entre ellos
		String ibanFormateado = iban.substring(0, 4) + " ";
		ibanFormateado += iban.substring(4, 8) + " ";
		ibanFormateado += iban.substring(8, 12) + " ";
		ibanFormateado += iban.substring(12, 16) + " ";
		ibanFormateado += iban.substring(16, 20) + " ";
		ibanFormateado += iban.substring(20, 24);
		
		return ibanFormateado;
	}
	
	/**
	 * CCC formateado para escritura
	 * @param ccc
	 * @return
	 */	
	public String formatearCcc(String ccc) {
		// string con el ccc formateado: dos grupos de 4, un grupo de 2 y un grupo de 10
		//con separacion espacio entre ellos
		String cccFormateado = ccc.substring(0, 4) + " ";
		cccFormateado += ccc.substring(4, 8) + " ";
		cccFormateado += ccc.substring(8, 10) + " ";
		cccFormateado += ccc.substring(10, 20);
		
		return cccFormateado;
	}
	

	/**
	 * Generar un BIC asociado a un banco
	 * 
	 * @param banco
	 * @return
	 */
	public String generarBicAsociado(String banco) {
		Bancos bancoObj = bancosRepository.findFirstByCodigo(banco);
		String bic = bancoObj.getBic();
		//nos inventamos los ultimos 3 digitos que situarian una oficina
		//internamente en el banco
		bic += String.format("%1$" + 3 + "s", Integer.toString(semilla.nextInt(0, 999))).replace(' ', '0');
		return bic;
	}

	/**
	 * Genera una tareja aleatoria de credito
	 * @param prefijo
	 * @return
	 */
	public String generarTarjetaCredito(String prefijo) {
		CreditCardNumberGenerator ccng = new CreditCardNumberGenerator();

		String tipo = null;
		if (prefijo == null || prefijo.isEmpty()) {
			int indice = semilla.nextInt(0, PREFIJO_TARJETA.length - 1);
			tipo = PREFIJO_TARJETA[indice];
		} else {
			tipo = prefijo;
		}


		return ccng.generate(tipo, 16);
	}
	
	/**
	 * Formatea una tarjeta de credito
	 * 
	 * @param tarjeta
	 * @return
	 */
	public String formatearTarjeta(String tarjeta) {
		// string con la tarjeta formateada: grupos de 4 con separacion espacio entre ellos
        String tarjetaFormateada = tarjeta.substring(0, 4) + " ";
        tarjetaFormateada += tarjeta.substring(4, 8) + " ";
        tarjetaFormateada += tarjeta.substring(8, 12) + " ";
        tarjetaFormateada += tarjeta.substring(12, 16);
        
        return tarjetaFormateada;
	}
	
	/**
	 * Devuelve una fecha de expiracion para una tarjeta
	 * @return
	 */
	public String fechaExpiracionTarjeta() {
		Calendar cal = Calendar.getInstance();
		Calendar calF = Calendar.getInstance();
		calF.add(Calendar.YEAR, 5);
		Date fechaCaducidad = CommonUtil.getFechaAleatoria(cal.getTime(), calF.getTime());
		// formatearla como MM/YY
		return CommonUtil.getFechaFormateada(fechaCaducidad, "MM/yy");
	}

	/**
	 * Devuelve un cvc para una tarjeta de creido
	 * @return
     */
	public String generarCvc() {
		return semilla.nextInt(100, 999) + "";
	}
	
	/**
	 * Valida una tarjeta de credito
	 * 
	 * @param tarjeta
	 * @return
	 */
	public boolean validarTarjetaCredito(String tarjeta) {
		CardValidationResult valida = RegexCardValidator.isValid(tarjeta);
		return valida.isValid();
	}	
	
	/**
	 * Recupera el nombre del banco por su codigo
	 * @param codigo
	 * @return
	 */
	public String dameBancoPorCodigo(String codigo) {
		Bancos bancoObj = bancosRepository.findFirstByCodigo(codigo);
		return bancoObj.getNombre();
	}
	
	/**
	 * Conformar los datos de una cuenta bancaria aleatoria a devolver
	 * @return
	 */
	public Cuenta conformarCuenta() {
		Cuenta cuenta = new Cuenta();
        
		// cuenta bancaria
		cuenta.setIban(generarIbanRandom());
		cuenta.setIbanFormateado(formatearIban(cuenta.getIban()));
		cuenta.setCcc(cuenta.getIban().substring(4));
		cuenta.setCccFormateado(formatearCcc(cuenta.getCcc()));
		
		cuenta.setBic(generarBicAsociado(cuenta.getIban().substring(4, 8)));
		cuenta.setEntidad(dameBancoPorCodigo(cuenta.getIban().substring(4, 8)));
				
		
		return cuenta;
		
	}
	
	/**
	 * Conformar los datos de una tarjeta bancaria aleatoria a devolver
	 * @param tipoTarjeta Numerico del tipo tarjeta generar
	 * @return
	 */
	public Tarjeta conformarTarjeta(String tipoTarjeta) {
		Tarjeta tarjeta = new Tarjeta();
		
		String tipoTarjetaRevi;
		if (tipoTarjeta != null && !tipoTarjeta.isEmpty()) {
			tipoTarjetaRevi = tipoTarjeta;
		} else {
			tipoTarjetaRevi = PREFIJO_TARJETA[semilla.nextInt(0, PREFIJO_TARJETA.length - 1)];
		}		
		
		tarjeta.setTarjeta(generarTarjetaCredito(tipoTarjetaRevi));
		tarjeta.setTarjetaFormateada(formatearTarjeta(tarjeta.getTarjeta()));
		tarjeta.setExpiracionCredito(fechaExpiracionTarjeta());
		tarjeta.setCvc(generarCvc());
		
		
		PrefijoTarjeta prefijo = PrefijoTarjeta.getByCodigoInicio(tipoTarjetaRevi);
		if (prefijo != null) {
			tarjeta.setTipoTarjeta(prefijo.getNombre());
		}
		
		
		return tarjeta;
	}
	
}
