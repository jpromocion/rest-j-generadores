package es.jortri.generadores.services;

import java.util.Random;

import org.springframework.stereotype.Service;

import es.jortri.generadores.enumerados.TipoMatricula;
import es.jortri.generadores.util.CommonUtil;
import es.jortri.generadores.util.vin.VinGeneratorUtils;
import es.jortri.generadores.util.vin.VinValidatorUtils;

@Service
public class VehicleServices {

	private Random semilla;
	

	public static final String PREFIJO_TEMPRORAL_EMPRESA[] = { "S", "V"};
	
	public static final String PREFIJO_DIPLOMATICA[] = { "CD", "CC", "TA", "OI"};	
	
	public VehicleServices() {
		this.semilla = new Random();
	}	
	
	/**
	 * Genera una matricula para un turismo
	 * @return
	 */
	public String generarMatriculaTurismo() {
		String matricula = "";
		
		//El sistema actual de numeración de las matrículas utiliza el formato NNNN LLL donde
		//  -NNNN es un número secuencial de cuatro cifras desde el 0000 al 9999,
		//  -LLL son tres letras secuenciales desde BBB hasta ZZZ que se incrementan cuando el número llega a 9999. 
		//   Las letras utilizadas son las consonantes B, C, D, F, G, H, J, K, L, M, N, P, R, S, T, V, W, X, Y y Z.​
		int numero = semilla.nextInt(0, 9999);
		matricula = CommonUtil.ponCerosIzquierda(Integer.toString(numero), 4);
		matricula += CommonUtil.generarLetrasAleatorias(3, "BCDFGHJKLMNPQRSTVWXYZ");
		
		return matricula;
	
	}
	
	/**
	 * Genera una matricula para un ciclomotor
	 * 
	 * @return
	 */
	public String generarMatriculaCiclomotor() {
		String matricula = "C";
		
		matricula += generarMatriculaTurismo();		
		
		return matricula;
	}
	
	/**
	 * Genera una matricula para un remolque
	 * 
	 * @return
	 */
	public String generarMatriculaRemolque() {
		String matricula = "R";
		
		matricula += generarMatriculaTurismo();		
		
		return matricula;	
	}
	
	/**
	 * Genera una matricula para un vehiculo especial
	 * 
	 * @return
	 */
	public String generarMatriculaEspecial() {
		String matricula = "E";
		
		matricula += generarMatriculaTurismo();	
		
		return matricula;	
	}
	
	/**
	 * Genera una matricula para un vehiculo turistico
	 * 
	 * @return
	 */
	public String generarMatriculaTuristico() {
		String matricula = "T";
		
		matricula += generarMatriculaTurismo();	
		
		return matricula;
	}
	
	/**
	 * Genera una matricula para un vehiculo historico
	 * 
	 * @return
	 */
	public String generarMatriculaHistorico() {
		String matricula = "H";
		
		matricula += generarMatriculaTurismo();	
		
		return matricula;
	}
	
	/**
	 * Genera una matricula temporal para un particular
	 * 
	 * @return
	 */
	public String generarMatriculaTemporalParticular() {
		String matricula = "P";

		matricula += generarMatriculaTurismo();

		return matricula;
	}
	
	/**
	 * Genera una matricula temporal para una empresa
	 * 
	 * @return
	 */
	public String generarMatriculaTemporalEmpresa() {
		int indice = semilla.nextInt(0, PREFIJO_TEMPRORAL_EMPRESA.length);
		String matricula = PREFIJO_TEMPRORAL_EMPRESA[indice];

        matricula += generarMatriculaTurismo();

        return matricula;
	}
	
	/**
	 * Genera una matricula para un vehiculo diplomático
	 * 
	 * @return
	 */
	public String generarMatriculaDiplomatica() {		
		int indice = semilla.nextInt(0, PREFIJO_DIPLOMATICA.length);
		String matricula = PREFIJO_DIPLOMATICA[indice];		
        
		int pais = semilla.nextInt(0, 999);
		matricula += " " + pais + " ";
        
		int numero = semilla.nextInt(0, 999);
		matricula += CommonUtil.ponCerosIzquierda(Integer.toString(numero), 3);        
        
        return matricula;
	}
	
	
	
	
	
	/**
	 * Generar un numero de matricula
	 * 
	 * @param tipoMatricula
	 * @return
	 */
	public String conformarMatricula(TipoMatricula tipoMatricula) {
		String matricula = "";
		
		if (tipoMatricula == TipoMatricula.TURISMO) {
			matricula = generarMatriculaTurismo();
		} else if (tipoMatricula == TipoMatricula.CICLOMOTOR) {
			matricula = generarMatriculaCiclomotor();
		} else if (tipoMatricula == TipoMatricula.REMOLQUE) {
			matricula = generarMatriculaRemolque();
		} else if (tipoMatricula == TipoMatricula.ESPECIAL) {
			matricula = generarMatriculaEspecial();
		} else if (tipoMatricula == TipoMatricula.TURISTICO) {
			matricula = generarMatriculaTuristico();		
		} else if (tipoMatricula == TipoMatricula.HISTORICO) {
			matricula = generarMatriculaHistorico();		
		} else if (tipoMatricula == TipoMatricula.TEMPORAL_PARTICULAR) {
			matricula = generarMatriculaTemporalParticular();
		} else if (tipoMatricula == TipoMatricula.TEMPORAL_EMPRESA) {
			matricula = generarMatriculaTemporalEmpresa();
		} else if (tipoMatricula == TipoMatricula.DIPLOMATICA) {
			matricula = generarMatriculaDiplomatica();
		}
		
		return matricula;
	}
	
	/**
	 * Generar un numero de bastidor
	 * 
	 * @return
	 */
	public String conformarBastidor() {
		//utilizo la libreria de https://github.com/mkyrychenko/vin-utils
		//NOTA: al final no, da excepcion al buscar el vin-prefixes.txt
		//por lo que acabo incluyendo todo el codigo en apliocacion
		return VinGeneratorUtils.getRandomVin();
	}	
	
	/**
	 * Valida un numero de bastidor
	 * 
	 * @param vin
	 * @return
	 */
	public boolean validarBastidor(String vin) {
		//utilizo la libreria de https://github.com/mkyrychenko/vin-utils
		//NOTA: al final no, da excepcion al buscar el vin-prefixes.txt
		//por lo que acabo incluyendo todo el codigo en apliocacion
		return VinValidatorUtils.isValidVin(vin);
		
	}		
	
}
