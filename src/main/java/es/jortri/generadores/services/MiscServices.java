package es.jortri.generadores.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.entityreturn.CcaaReturn;
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
	 * Generar una fecha nacimiento
	 * 
	 * @return
	 */
	public String conformarFechaNacimiento() {
		return CommonUtil.getFechaFormateada(profilesService.generarFechaNacimiento());
	}	
	
	/**
	 * Generar una fecha a futuro
	 * 
	 * @return
	 */
	public String conformarFechaFuturo() {
		return CommonUtil.getFechaFormateada(profilesService.generarFechaFutura(1,100));
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
	
}
