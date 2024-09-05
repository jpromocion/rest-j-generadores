package es.jortri.generadores.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jortri.generadores.model.Apellidos;
import es.jortri.generadores.model.Nombres;
import es.jortri.generadores.model.Persona;
import es.jortri.generadores.repository.ApellidosRepository;
import es.jortri.generadores.repository.NombresRepository;

@Service
public class ProfilesService {
	
	@Autowired
	private NombresRepository nombresRepository;
	
	@Autowired
	private ApellidosRepository apellidosRepository;		
	
	private DoiService doiUtil = new DoiService();	

	private Random semilla;
	
	public ProfilesService() {
		this.semilla = new Random();
	}
	

	public Persona conformarPersona() {
		Persona persona = new Persona();
		persona.setNif(doiUtil.getNif());
		persona.setNie(doiUtil.getNie());
		
		//buscamos un id de nombre aleatorio
		Nombres nombrePri = nombresRepository.findFirstByOrderByIdAsc();
		Nombres nombreUlt = nombresRepository.findFirstByOrderByIdDesc();		
		int randomSele = semilla.nextInt(nombrePri.getId(), nombreUlt.getId());		
		Nombres nombre = nombresRepository.findById(randomSele).get();
		persona.setNombre(nombre.getNombre());
		
		//bucamos tambien aleatoriamente los apelliudoos
		Apellidos apePri = apellidosRepository.findFirstByOrderByIdAsc();
		Apellidos apeUlt = apellidosRepository.findFirstByOrderByIdDesc();	
		randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido1 = apellidosRepository.findById(randomSele).get();
		persona.setApellido1(apellido1.getApellido());
		randomSele = semilla.nextInt(apePri.getId(), apeUlt.getId());
		Apellidos apellido2 = apellidosRepository.findById(randomSele).get();
		persona.setApellido2(apellido2.getApellido());
		
		//el genero es obtenido del nombre
		if ("h".equals(nombre.getGenero()))
			persona.setGenero("hombre");
		else if ("m".equals(nombre.getGenero()))
			persona.setGenero("mujer");
		else
			persona.setGenero("indefinido");
		
		//nombre completo lo formamos
		persona.setNombreCompleto(persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2());
		
		return persona;
		
    }
	
}
