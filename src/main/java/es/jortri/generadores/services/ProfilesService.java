package es.jortri.generadores.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.model.Apellidos;
import es.jortri.generadores.model.Nombres;
import es.jortri.generadores.model.Persona;
import es.jortri.generadores.repository.ApellidosRepository;
import es.jortri.generadores.repository.NombresRepository;
import es.jortri.generadores.util.CommonUtil;

@Service
public class ProfilesService {

	@Autowired
	private NombresRepository nombresRepository;

	@Autowired
	private ApellidosRepository apellidosRepository;

	private DoiService doiUtil = new DoiService();

	private Random semilla;

	private static final int MAX_EDAD = 100;

	private static final int MIN_EDAD = 18;

	private static final char PREFIJO_TLF_MOVIL = '6';

	public static String PREFIJOS_TLF_FIJOS[] = { "945", "967", "965", "966", "950", "984", "985", "920", "924", "971",
			"93", "947", "927", "956", "942", "964", "926", "957", "981", "969", "972", "958", "949", "943", "959",
			"974", "953", "987", "973", "982", "91", "951", "952", "968", "948", "988", "979", "928", "986", "941",
			"923", "921", "954", "955", "975", "977", "922", "978", "925", "960", "961", "962", "963", "983", "944",
			"946", "980", "976" };

	public ProfilesService() {
		this.semilla = new Random();
	}

	/**
	 * Generamos un nuemero de telefono movil aleatorio
	 * 
	 * @return
	 */
	public String generarNumTelefonoMovil() {
		int restoTlfInt = semilla.nextInt(1, 99999999);
		String restoTlf = Integer.toString(restoTlfInt);
		return PREFIJO_TLF_MOVIL + String.format("%1$" + 8 + "s", restoTlf).replace(' ', '0');
	}

	/**
	 * Generamos un numero de telefono fijo aleatorio
	 * 
	 * @return
	 */
	public String generarNumTelefonoFijo() {

		int indice = semilla.nextInt(0, PREFIJOS_TLF_FIJOS.length - 1);
		String prefijo = PREFIJOS_TLF_FIJOS[indice];
		int maximo = 999999;
		if (prefijo.length() == 2) {
			maximo = 9999999;
		}

		int restoTlfInt = semilla.nextInt(1, maximo);
		String restoTlf = Integer.toString(restoTlfInt);

		return prefijo + restoTlf;
	}

	/**
	 * Conformar los datos de una persona completamente aleatoria a devolver
	 * 
	 * @return
	 */
	public Persona conformarPersona() {
		Persona persona = new Persona();
		persona.setNif(doiUtil.getNif());
		persona.setNie(doiUtil.getNie());

		// buscamos un id de nombre aleatorio
		Nombres nombrePri = nombresRepository.findFirstByOrderByIdAsc();
		Nombres nombreUlt = nombresRepository.findFirstByOrderByIdDesc();
		int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());
		Nombres nombre = nombresRepository.findById(randomSele).get();
		persona.setNombre(nombre.getNombre());

		// bucamos tambien aleatoriamente los apelliudoos
		Apellidos apePri = apellidosRepository.findFirstByOrderByIdAsc();
		Apellidos apeUlt = apellidosRepository.findFirstByOrderByIdDesc();
		randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido1 = apellidosRepository.findById(randomSele).get();
		persona.setApellido1(apellido1.getApellido());
		randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido2 = apellidosRepository.findById(randomSele).get();
		persona.setApellido2(apellido2.getApellido());

		// el genero es obtenido del nombre
		if ("h".equals(nombre.getGenero()))
			persona.setGenero("hombre");
		else if ("m".equals(nombre.getGenero()))
			persona.setGenero("mujer");
		else
			persona.setGenero("indefinido");

		// nombre completo lo formamos
		persona.setNombreCompleto(persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2());

		// fecha nacimiento
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -MIN_EDAD);
		Calendar calF = Calendar.getInstance();
		calF.add(Calendar.YEAR, -MAX_EDAD);
		Date fechaNacimiento = CommonUtil.getFechaAleatoria(calF.getTime(), cal.getTime());
		// fijamos la fecha en formato dd/mm/yyyy
		persona.setFechaNacimiento(CommonUtil.getFechaFormateada(fechaNacimiento));
		cal = Calendar.getInstance();
		persona.setEdad(Integer.toString(CommonUtil.calcularEdad(fechaNacimiento, cal.getTime())));

		// telefonos
		persona.setTelefonoMovil(generarNumTelefonoMovil());
		persona.setTelefonoFijo(generarNumTelefonoFijo());

		return persona;

	}

}
