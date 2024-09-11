package es.jortri.generadores.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.entityreturn.EmpresaReturn;
import es.jortri.generadores.entityreturn.PersonaReturn;
import es.jortri.generadores.enumerados.Genero;
import es.jortri.generadores.services.ProfilesService;
import es.jortri.generadores.util.CommonUtil;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	private ProfilesService profilesService;
	
	/**
	 * Obtener una lista de personas de datos aleatorios
	 * @param results
	 * @param gender
	 * @return
	 */
	@GetMapping("/person")
	public List<PersonaReturn> person(@RequestParam String results, @RequestParam Optional<String> gender) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);
		
		String genderOP = gender.orElse("");
		Genero genderRevi = Genero.AMBOS;
		if (genderOP != null && "male".equals(genderOP)) {
			genderRevi = Genero.MALE;
		} else if (genderOP != null && "female".equals(genderOP)) {
			genderRevi = Genero.FEMALE;
		}

		List<PersonaReturn> listaNombres = new ArrayList<PersonaReturn>();
		for (int i = 0; i < resultsInt; i++) {
			listaNombres.add(profilesService.conformarPersona(genderRevi));
		}

		return listaNombres;
	}
	
	/**
	 * Obtener una lista de empresas de datos aleatorios
	 * 
	 * @param results
	 * @return
	 */
	@GetMapping("/company")
	public List<EmpresaReturn> company(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);


		List<EmpresaReturn> listaEmpresas = new ArrayList<EmpresaReturn>();
		for (int i = 0; i < resultsInt; i++) {
			listaEmpresas.add(profilesService.conformarEmpresa());
		}

		return listaEmpresas;
	}	
}
