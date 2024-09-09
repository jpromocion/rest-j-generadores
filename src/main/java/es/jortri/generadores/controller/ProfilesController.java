package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.enumerados.Genero;
import es.jortri.generadores.model.Empresa;
import es.jortri.generadores.model.Persona;
import es.jortri.generadores.services.ProfilesService;
import es.jortri.generadores.util.CommonUtil;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	private ProfilesService profilesService;

	@GetMapping("/person")
	public List<Persona> person(@RequestParam String results, @RequestParam String gender) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		Genero genderRevi = Genero.AMBOS;
		if (gender != null && "male".equals(gender)) {
			genderRevi = Genero.MALE;
		} else if (gender != null && "female".equals(gender)) {
			genderRevi = Genero.FEMALE;
		}

		List<Persona> listaNombres = new ArrayList<Persona>();
		for (int i = 0; i < resultsInt; i++) {
			listaNombres.add(profilesService.conformarPersona(genderRevi));
		}

		return listaNombres;
	}
	
	@GetMapping("/company")
	public List<Empresa> company(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);


		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		for (int i = 0; i < resultsInt; i++) {
			listaEmpresas.add(profilesService.conformarEmpresa());
		}

		return listaEmpresas;
	}	
}
