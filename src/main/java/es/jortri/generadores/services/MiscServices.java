package es.jortri.generadores.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.edumdum.iso.Iso17442;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.entityreturn.CcaaReturn;
import es.jortri.generadores.entityreturn.DireccionCompletaReturn;
import es.jortri.generadores.entityreturn.MunicipioReturn;
import es.jortri.generadores.entityreturn.ProvinciaReturn;
import es.jortri.generadores.model.Ccaa;
import es.jortri.generadores.model.Municipios;
import es.jortri.generadores.model.Provincias;
import es.jortri.generadores.util.CommonUtil;


@Service
public class MiscServices {

	@Autowired
	private ProfilesService profilesService;
	
	
	private Random semilla;
	
	public static String DISTRIBUIDORAS_CUPS_ELECTRICIDAD[] = { "0023", "0024", "0029", "0288", "0363", "0396", 
			"0021", "0022", "0390", "0397", "0026", "0027", "0031", "0401", "0033" };	

	public static String DISTRIBUIDORAS_CUPS_GAS[] = { "0230", "0203", "0218", "0220", "0221", "0222", "0223", 
			"0224", "0226", "0227", "0208", "0225", "0205", "0206", "0022", "0390", "0397", "0229", "0201", 
			"0229", "0211", "0234", "0207", "0238" };	
	
    // Tabla de letras usadas para C y R en el CUPS
    private static final char[] CONTROL_LETTERS_CUPS = {
        'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 
        'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 
        'C', 'K', 'E'
    };	

	
	public MiscServices() {
        this.semilla = new Random();
	}
	
	
	/**
	 * Genera un formato de IMEI
	 * Referencia: https://gist.github.com/abforce/c9a2dabdbe7fab51d7485deeddb67876
	 * @return
	 */
	public static String generateIMEI() {
        int pos;
        int[] str = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int sum = 0;
        int final_digit;
        int t;
        int len_offset;
        int len = 15;
        String imei = "";

        String[] rbi = new String[]{"01", "10", "30", "33", "35", "44", "45", "49", "50", "51", "52", "53", "54", "86", "91", "98", "99"};
        String[] arr = rbi[(int) Math.floor(Math.random() * rbi.length)].split("");
        str[0] = Integer.parseInt(arr[0]);
        str[1] = Integer.parseInt(arr[1]);
        pos = 2;

        while (pos < len - 1) {
            str[pos++] = (int) (Math.floor(Math.random() * 10) % 10);
        }

        len_offset = (len + 1) % 2;
        for (pos = 0; pos < len - 1; pos++) {
            if ((pos + len_offset) % 2 != 0) {
                t = str[pos] * 2;
                if (t > 9) {
                    t -= 9;
                }
                sum += t;
            } else {
                sum += str[pos];
            }
        }

        final_digit = (10 - (sum % 10)) % 10;
        str[len - 1] = final_digit;

        for (int d : str) {
            imei += String.valueOf(d);
        }

        return imei;
    }		
	
	
	/**
	 * Generar un Email
	 * 
	 * @return
	 */
	public String conformarEmails() {
		return profilesService.generarEmail();
	}	
	
	/**
	 * Generar un Password
	 * @param length
	 * @param cases
	 * @param number
	 * @param special
	 * @return
	 */
	public String conformarPassword(int length, String cases, String number, String special) {
		return profilesService.generateCommonTextPassword(length, cases, number, special);
	}
	
	/**
	 * Generar un telefono
	 * 
	 * @return
	 */
	public String conformarTelefono(String tipo) {
		int entero = 0;
		if (tipo == null || tipo.isEmpty()) {
			entero = semilla.nextInt(0, 2);
		} else if (tipo.equals("m")) {
            entero = 0;
        } else {
            entero = 1;
        }
		
		if (entero == 0) {
			return profilesService.generarNumTelefonoMovil();
		} else {
			return profilesService.generarNumTelefonoFijo();
		}
		
	}		
	
	
	
	/**
	 * Generar una ciudad
	 * 
	 * @return
	 */
	public String conformarCiudad() {
		Municipios muni = profilesService.generarMunicipioRandom();
		String denoMuni = "";
		if (muni != null) {
			denoMuni = muni.getNombre();
		}
		return denoMuni;
		
	}	
	
	/**
	 * Generar un cod postal
	 * 
	 * @return
	 */
	public String conformarCodPostal() {
		return profilesService.generarCodPostalRandom();
	}		
	
	/**
	 * Generar un IMEI
	 * 
	 * @return
	 */
	public String conformarImei() {
		return generateIMEI();
	}		
	
	/**
	 * Devolver la lista de ccaa
	 * 
	 * @return
	 */
	public List<CcaaReturn> listarCcaa() {
		List<Ccaa> listaCcaa = profilesService.getTodasCcaa();
		List<CcaaReturn> listaCcaaReturn = new ArrayList<CcaaReturn>();
		if (listaCcaa != null && !listaCcaa.isEmpty()) {
			listaCcaaReturn = listaCcaa.stream().map(ccaa -> {
                CcaaReturn ccaaReturn = new CcaaReturn();
                ccaaReturn.setId(ccaa.getId());
                ccaaReturn.setNombre(ccaa.getNombre());
                return ccaaReturn;
            }).toList();
			
		}
		
		return listaCcaaReturn;
	}
	
	/**
	 * Devolver la lista de provincias de una CCAA
	 * @param idccaa
	 * @return
	 */
	public List<ProvinciaReturn> listarProvincias(String idccaa) {
		List<Provincias> listaProvincias = profilesService.getTodasProvincia(idccaa);
		List<ProvinciaReturn> listaProvinciaReturn = new ArrayList<ProvinciaReturn>();
		if (listaProvincias != null && !listaProvincias.isEmpty()) {
			listaProvinciaReturn = listaProvincias.stream().map(provincia -> {
				ProvinciaReturn provinciaReturn = new ProvinciaReturn();
				provinciaReturn.setId(provincia.getId());
				provinciaReturn.setNombre(provincia.getNombre());
				return provinciaReturn;
			}).toList();

		}
		
		return listaProvinciaReturn;
	}
	
	/**
	 * Devolver la lista de municipios de una provincia
	 * 
	 * @param idprovincia Identificador de la provincia
	 * @return
	 */
	public List<MunicipioReturn> listarMunicipios(String idprovincia) {
		List<Municipios> listaMunicipios = profilesService.getTodosMunicipios(idprovincia);
		List<MunicipioReturn> listaMunicipioReturn = new ArrayList<MunicipioReturn>();
		if (listaMunicipios != null && !listaMunicipios.isEmpty()) {
			listaMunicipioReturn = listaMunicipios.stream().map(municipio -> {
				MunicipioReturn municipioReturn = new MunicipioReturn();
				municipioReturn.setId(municipio.getCodigoine());
				municipioReturn.setNombre(municipio.getNombre());
				return municipioReturn;
			}).toList();

		}

		return listaMunicipioReturn;
	}
	
	


	
	/**
	 * Devolver direccion completa, sobrecargando tratamiento de lo que tiene o no relleno de parametros
	 * de CCAA, provincia, municipio para el servicio de obtener direcciones
	 * @param idCcaa
	 * @param idProvincia
	 * @param idMunicipio
	 * @return
	 */
	public DireccionCompletaReturn conformarDireccionCompletaTrata(String ineCcaa, String ineProvincia, String ineMunicipio) throws IllegalArgumentException {
		
		//Conformemos todas las posibilidades: 
		//  -puede recibir los 3 -> conformarDireccionCompleta directamente
		//  -puede no recibir ninguno -> se generan CCAA -> provincia -> municipio aleatoriamente
		//  -Puede recibir solo CCAA o solo CCAA y provincia, en cuyo caso genera aleatoriamente los restantes
		//  -Pero tambien puede recibir solo provincia y municipio, en cuyo caso debe recuperar los
		//   superiores por la relacion padre-hijo que tienen. 
		//  -Puede recibir solo provincia, en cuyo caso debe recuperar la CCAA y generar aleatoriamente el municipio
		// NOTA. provin/muni son pk conjunta, no se puede recibir separados esos casos lanzaran excepcion
		Ccaa ccaa = null;
		Provincias provincia = null;
		Municipios municipio = null;
		
		if (!ineCcaa.isEmpty() && !ineProvincia.isEmpty() && !ineMunicipio.isEmpty()) {
			ccaa = profilesService.getCcaa(ineCcaa);
			provincia = profilesService.getProvincia(ineProvincia);
			municipio = profilesService.getMunicipioPorIne(ineProvincia,ineMunicipio);
		} else if (ineCcaa.isEmpty() && ineProvincia.isEmpty() && ineMunicipio.isEmpty()) {
			ccaa = profilesService.generarCCAARandom();
			provincia = profilesService.generarProvinciaRandom(ccaa.getId());
			municipio = profilesService.generarMunicipioRandom(provincia.getId());	
		} else if (
					(!ineCcaa.isEmpty() && ineProvincia.isEmpty() && ineMunicipio.isEmpty())
					|| (!ineCcaa.isEmpty() && !ineProvincia.isEmpty() && ineMunicipio.isEmpty())
				) {
			ccaa = profilesService.getCcaa(ineCcaa);
			if (ineProvincia.isEmpty()) {
				provincia = profilesService.generarProvinciaRandom(ccaa.getId());
			} else {
				provincia = profilesService.getProvincia(ineProvincia);
				if (!ineCcaa.equals(provincia.getIdccaa())) {
					throw new IllegalArgumentException("La provincia no pertenece a la CCAA indicada");
				}				
			}
			municipio = profilesService.generarMunicipioRandom(provincia.getId());
		} else if (!ineMunicipio.isEmpty() && !ineProvincia.isEmpty() && ineCcaa.isEmpty()) {
			provincia = profilesService.getProvincia(ineProvincia);
			municipio = profilesService.getMunicipioPorIne(ineProvincia, ineMunicipio);
			if (!ineProvincia.equals(municipio.getIdprovincias())) {
				throw new IllegalArgumentException("El municipio no pertenece a la provincia indicada");
			}
						
			ccaa = profilesService.getCcaa(provincia.getIdccaa());					
		} else if (!ineProvincia.isEmpty() && ineCcaa.isEmpty() && ineMunicipio.isEmpty()) {
			provincia = profilesService.getProvincia(ineProvincia);
			ccaa = profilesService.getCcaa(provincia.getIdccaa());
			municipio = profilesService.generarMunicipioRandom(provincia.getId());
		} else {
			throw new IllegalArgumentException("Los parámetros proporcionados no son adecuados para generar");
		}
		
		
		if (ccaa == null || provincia == null || municipio == null) {
			throw new IllegalArgumentException("No se han encontrado los datos de CCAA, Provincia o Municipio");
		}
		
		return profilesService.conformarDireccionCompleta(ccaa, provincia, municipio);
	}
	
	
	/**
	 * Genera la parte de un codigo promocional correspondiente a un patron
	 * con separacion de guiones indicado
	 * @param pattern
	 * @param charset
	 * @return
	 */
	private String generarCodigoPromocionalPattern(String pattern, String charset) {
        //Se busca el "-" en el patron, se trocea cada parte y se genera para el numero de
		//caracteres de ese trozo un patron aleatorio con CommonUtil.generarLetrasAleatorias
		//y se va concatenando
		String[] partes = pattern.split("-");
		StringBuilder sb = new StringBuilder();
		for (String parte : partes) {
			sb.append(CommonUtil.generarLetrasAleatorias(parte.length(), charset));
			sb.append("-");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
    }

	
	/**
	 * Genera un código promocional
	 * @param charset
	 * @param length
	 * @param pattern
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	private String generarCodigoPromocional(String charset, int length, String pattern, String prefix, String suffix) {
		StringBuilder sb = new StringBuilder();
		if (prefix != null && !prefix.isEmpty()) {
			sb.append(prefix);
			sb.append("-");
		}
		if (pattern != null && !pattern.isEmpty()) {
			sb.append(generarCodigoPromocionalPattern(pattern, charset));
		} else {
			sb.append(CommonUtil.generarLetrasAleatorias(length, charset));
		}
		if (suffix != null && !suffix.isEmpty()) {
			sb.append("-");
			sb.append(suffix);
		}
		return sb.toString();
	}
	
	/**
	 * Generar un codigo promocional
	 * @return
	 */
	public String conformarCodigoPromocional(String charset, int length, String pattern, String prefix, String suffix) {		
		return generarCodigoPromocional(charset, length, pattern, prefix, suffix);
	}
	
	/**
	 * Generar un uuid
	 * https://www.uuidgenerator.net/dev-corner/java
	 * https://www.baeldung.com/java-uuid
	 * @return
	 */
	public String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}

    /**
     * Preparar la generacion de referencia catastral
     * @param tipo
     * @return
     */
    public String conformarReferenciaCatastralTrata(String tipo) {
    	//Si tipo es nulo, asignara aleatoriamente entre u y r
		if (tipo == null || tipo.isEmpty()) {
			tipo = (new Random().nextInt(2) == 0) ? "u" : "r";
		}
		//asignamos aleatoriamente CCAA, provincia y municipio
		Ccaa ccaa = profilesService.generarCCAARandom();
		Provincias provin = profilesService.generarProvinciaRandom(ccaa.getId());
		Municipios muni = profilesService.generarMunicipioRandom(provin.getId());
		return profilesService.conformarReferenciaCatastral(tipo, provin.getId(), muni.getCodigoine());
    }

    /**
     * Mapeo de ferencia catastral para misc services
     * @param referenciaCatastral
     * @return
     */
    public boolean validarReferenciaCatastral(String referenciaCatastral) {
    	return profilesService.validarReferenciaCatastral(referenciaCatastral);
    }
    

    
    /**
     * Calculo los digitos de control de un CUPS
     * @param cups Los 16 caracteres que forman los DDDD CCCC CCCC CCCC (4 digitos distribuidora + 12 digitos libre asignacion)
     * @return
     */
    private static String calculateControlDigitsCUPS(String cups) {
    	
        // Aseguramos que el CUPS tiene 16 dígitos
        if (cups == null || cups.length() != 16 || !cups.matches("\\d+")) {
            throw new IllegalArgumentException("El CUPS debe tener 16 dígitos numéricos.");
        }

        // Convertimos el CUPS a un número natural
        long cupsNumber = Long.parseLong(cups);

        // Paso 1: Dividir el número por 529 y obtener el resto R0
        long r0 = cupsNumber % 529;

        // Paso 2: Dividir R0 por 23 y obtener el cociente C y el resto R
        int c = (int) (r0 / 23);
        int r = (int) (r0 % 23);

        // Paso 3: Obtener las letras correspondientes a C y R
        char controlDigit1 = CONTROL_LETTERS_CUPS[c];
        char controlDigit2 = CONTROL_LETTERS_CUPS[r];

        // Devolver los dígitos de control como una cadena
        return "" + controlDigit1 + controlDigit2;
    }
    
    
    
    /**
     * Genrerar un CUPS
     * @param tipo Tipo de CUPS: e: electrico, g: gas
     * @return CUPS generado
     */
    public String generarCUPS(String tipo) {
    	//si es nulo lo asociamos aleatoriamente en cada intento
		if (tipo == null || tipo.isEmpty()) {
			tipo = (new Random().nextInt(2) == 0) ? "e" : "g";
		}    	
    	
    	
    	String cups = "";
    	//generamos de 20 caracteres (no los 2 opcionales) con formato: LL DDDD CCCC CCCC CCCC EE
    	
    	//LL: "ES" por españa
    	cups += "ES";
    	
    	//DDDD: 4 digitos de la compañia de gas o electcidad segun el tipo
    	if ("e".equals(tipo)) {
    		int indice = semilla.nextInt(0, DISTRIBUIDORAS_CUPS_ELECTRICIDAD.length);
    		String distribuidora= DISTRIBUIDORAS_CUPS_ELECTRICIDAD[indice];
    		cups += distribuidora;
    	} else {
    		int indice = semilla.nextInt(0, DISTRIBUIDORAS_CUPS_GAS.length);
    		String distribuidora= DISTRIBUIDORAS_CUPS_GAS[indice];
    		cups += distribuidora;
    	}
    	
    	//CCCC CCCC CCCC-12 caracteres numéricos de libre asignación (por el distribuidor) a cada 
    	//suministro, en el momento en que le asigna el CUPS.
    	cups += CommonUtil.generarLetrasAleatorias(12, CommonUtil.CARACTERES_NUMERICOS);
    	
    	//EE: 2 caracteres de control. Le pasamos el cups sin el prefijo "ES
    	cups += calculateControlDigitsCUPS(cups.substring(2));
    	
    	return cups;
    }
    
    /**
     * Validar un CUPS
     * NOTA: la validacion se limita a comprobar que es ES, que el tamaño es 20 o 22
     * (por la posibilidad de los digitos opcionales para puntos frontera, medida...) 
     * y que los digitos control son correctos.
     * @param cups CUPS a validar
     * @return true es valido, false no lo es
     */
    public boolean validarCUPS(String cups) {
		// Comprobamos que es un CUPS de 20 o 22 caracteres
		if (cups == null || (cups.length() != 20 && cups.length() != 22)) {
			return false;
		}
		// Comprobamos que empieza por ES
		if (!cups.startsWith("ES")) {
			return false;
		}
		// Comprobamos que los digitos de control son correctos
		String digitosControl = cups.substring(18, 20);
		String digitosControlCalculados = calculateControlDigitsCUPS(cups.substring(2, 18));
		return digitosControl.equals(digitosControlCalculados);
    }
     
    
	/**
	 * Generar un LEI
	 * NOTA: utilizamos libreria https://github.com/EDumdum/iso-17442-java
	 * @return
	 */
    public String generarLEI() {
    	//código alfanumérico único de 20 caracteres que se utiliza para identificar a las entidades legales:
    	//  -4 primeros digitos Local Operating Unit (LOU) -> utilizaremos el de españa siempre "9598"
    	//	-14 digitos alfanumericos unicos que asiga el LOU
    	//	-2 digitos de control. Calculado usando MOD-97-10 especificado por la ISO/IEC 7064 (descrito en el estandar ISO 17442)
    	String lei = "9598";
    	
    	//14 digitos alfanumericos unicos
    	lei += CommonUtil.generarLetrasAleatorias(14, CommonUtil.CARACTERES_ALFANUMERICOS_MAYUS);
    	
    	//2 digitos de control
    	lei = Iso17442.generate(lei);
    	    	
    	return lei;
    }
    
    /**
     * Validar un LEI
     * NOTA: utilizamos libreria https://github.com/EDumdum/iso-17442-java
     * @param lei LEI a validar
     * @return true es valido, false no lo es
     */
    public boolean validarLEI(String lei) {
    	// Comprobamos que es un LEI de 20 caracteres
        if (lei == null || lei.length() != 20) {
        	return false;
        }
        
        // Comprobamos que los digitos de control son correctos
        return Iso17442.isValid(lei);
    }

    
    /**
     * Genera la parte de numero basico de un ISIN segun la clasificacion en españa 
     * @return
     */
    private String generateBasicNumberISIN() {
        Random random = new Random();
        StringBuilder basicNumber = new StringBuilder("0");

        // Generar el primer carácter de la categoría de acuerdo con la clasificación dada
        char category = "012345678LPABS".charAt(random.nextInt("012345678LPABS".length()));
        basicNumber.append(category);

        // Generar los siete caracteres restantes
        for (int i = 0; i < 7; i++) {
            char c;
            int type = random.nextInt(36);
            if (type < 10) {
                c = (char) ('0' + type);
            } else {
                c = (char) ('A' + type - 10);
            }
            basicNumber.append(c);
        }
        return basicNumber.toString();
    }    
    
    /**
     * Genera los digitos de control de un ISIN
     * @param partialISIN Los 11 primeros caracteres del ISIN
     * @return
     */
    private char calculateCheckDigitISIN(String partialISIN) {
        StringBuilder numericISIN = new StringBuilder();
        for (char c : partialISIN.toCharArray()) {
            if (Character.isDigit(c)) {
                numericISIN.append(c);
            } else {
                numericISIN.append((int) c - 55); // Convertir letras a valores numéricos (A=10, B=11, ...)
            }
        }

        // Doblar el valor de una cifra cada dos, comenzando por la primera cifra de la derecha
        int sum = 0;
        boolean doubleDigit = true;
        for (int i = numericISIN.length() - 1; i >= 0; i--) {
            int n = numericISIN.charAt(i) - '0';
            if (doubleDigit) {
                n *= 2;
            }
            sum += n / 10 + n % 10;
            doubleDigit = !doubleDigit;
        }

        // La cifra de control será el complemento de diez de la cifra de las unidades
        int checkDigit = (10 - (sum % 10)) % 10;
        return (char) (checkDigit + '0');
    }    

    /**
     * Genera un ISIN español
     * NOTA: especificacion indicada en https://www.boe.es/boe/dias/2010/09/30/pdfs/BOE-A-2010-15014.pdf
     * @return
     */
    public String generateSpanishISIN() {
        String countryPrefix = "ES";
        String basicNumber = generateBasicNumberISIN();
        String partialISIN = countryPrefix + basicNumber;
        char checkDigit = calculateCheckDigitISIN(partialISIN);
        return partialISIN + checkDigit;
    }
    
	/**
	 * Validar un ISIN
	 * 
	 * @param isin ISIN a validar
	 * @return true es valido, false no lo es
	 */
    public boolean validateISIN(String isin) {
        if (isin.length() != 12) {
            return false;
        }

        char expectedCheckDigit = calculateCheckDigitISIN(isin.substring(0, 11));
        return isin.charAt(11) == expectedCheckDigit;
    }
    

    /**
	 * Generar un NSS
	 * @return
	 */
    public String generateNSS() {
        return profilesService.generateNSS();
    }    
    
    /**
     * Validar un NSS
     * @param nss
     * @return
     */
    public boolean validateNSS(String nss) {
        return profilesService.validateNSS(nss);
    }
    
    
	
}
