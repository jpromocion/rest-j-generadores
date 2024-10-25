package es.jortri.generadores.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.jortri.generadores.entityreturn.ColorReturn;
import es.jortri.generadores.services.ColorService;
import es.jortri.generadores.util.CommonUtil;

/**
 * Controlador de generación de colores
 */
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/color")
public class ColorController {

	@Autowired
	private ColorService colorService;
	
	/**
	 * Obtener una lista de colores aleatorios
	 * @param results
	 * @return
	 */
	@GetMapping("/color")
	public List<ColorReturn> color(@RequestParam String results) {

		int resultsInt = CommonUtil.revisarNumResultadoMaximo(results, CommonUtil.MAX_RESULTADO_PERMITIDO);

		List<ColorReturn> listaColores = new ArrayList<ColorReturn>();
		for (int i = 0; i < resultsInt; i++) {
			Color color = colorService.generarColorAleatorio();
			ColorReturn colorReturn = new ColorReturn(color.getRed(), color.getGreen(), color.getBlue(), colorService.colorToHex(color));
			listaColores.add(colorReturn);
		}

		return listaColores;
	}		
	
	/**
	 * Convertir hexadecimal a RGB
	 * @param hexa 
	 * @return
	 */
	@GetMapping("/hexToRgb")
	public String hexToRgb(@RequestParam String hexa) {
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa); 
		Color color = colorService.hexToColor(hexaCorre);
        return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
	}	
	
	/**
	 * Convertir RGB a hexadecimal
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	@GetMapping("/rgbToHex")
	public String rgbToHex(@RequestParam Integer red, @RequestParam Integer green, @RequestParam Integer blue) {
		Color color = new Color(red, green, blue);
		return colorService.colorToHex(color);
	}
	
	/**
	 * Aclarar un color
	 * @param hexa
	 * @param amount
	 * @return
	 */
	@GetMapping("/lighten")
	public String lighten(@RequestParam String hexa, @RequestParam Integer amount) {
		
		//controlamos que los valores son entre 0-100
		if (amount < 0) {
			amount = 0;
		}
		if (amount > 100) {
			amount = 100;
		}
		
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa); 
		Color color = colorService.hexToColor(hexaCorre);
		Color colorLighten = colorService.aclararColor(color, amount);
		return colorService.colorToHex(colorLighten);
	}
	
	/**
	 * Oscurecer un color
	 * 
	 * @param hexa
	 * @param amount
	 * @return
	 */
	@GetMapping("/darken")
	public String darken(@RequestParam String hexa, @RequestParam Integer amount) {
		//controlamos que los valores son entre 0-100
		if (amount < 0) {
			amount = 0;
		}
		if (amount > 100) {
			amount = 100;
		}
		
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorDarken = colorService.oscurecerColor(color, amount);
		return colorService.colorToHex(colorDarken);
	}
	
	/**
	 * Modificar el matiz de un color
	 * @param hexa
	 * @param amount
	 * @return
	 */
	@GetMapping("/hue")
	public String hue(@RequestParam String hexa, @RequestParam Float amount) {
		if (amount < -1f) {
			amount = -1f;
		}
		if (amount > 1f) {
			amount = 1f;
		}

		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorHue = colorService.aplicarMatiz(color, amount);
		return colorService.colorToHex(colorHue);
	}
	
	/**
	 * Satura un color
	 * @param hexa
	 * @param amount
	 * @return
	 */
	@GetMapping("/saturate")
	public String saturate(@RequestParam String hexa, @RequestParam Float amount) {
		if (amount < -1f) {
			amount = -1f;
		}
		if (amount > 1f) {
			amount = 1f;
		}		
		
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorSaturate = colorService.saturarColor(color, amount);
		return colorService.colorToHex(colorSaturate);
	}
	
	/**
	 * Aplicar brillo a un color
	 * 
	 * @param hexa
	 * @param amount
	 * @return
	 */
	@GetMapping("/brightness")
	public String brightness(@RequestParam String hexa, @RequestParam Float amount) {
		if (amount < 0f) {
			amount = 0f;
		}
		if (amount > 100f) {
			amount = 100f;
		}
		
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorBrightness = colorService.aplicarBrillo(color, amount);
		return colorService.colorToHex(colorBrightness);
	}
	
	/**
	 * Invertir un color
	 * @param hexa
	 * @return
	 */
	@GetMapping("/invert")
	public String invert(@RequestParam String hexa) {
		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorInvert = colorService.invertirColor(color);
		return colorService.colorToHex(colorInvert);
	}
	
	/**
	 * Aplicar un canal alfa a un color
	 * 
	 * @param hexa
	 * @param alpha
	 * @return
	 */
	@GetMapping("/alpha")
	public ColorReturn alpha(@RequestParam String hexa, @RequestParam Integer alpha) {
		if (alpha < 0) {
			alpha = 0;
		}
		if (alpha > 100) {
			alpha = 100;
		}

		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);
		Color colorAlpha = colorService.aplicarCanalAlfa(color, alpha);
		
		return new ColorReturn(colorAlpha.getRed(), colorAlpha.getGreen(), colorAlpha.getBlue(), colorAlpha.getAlpha(), colorService.colorToHexAlpha(colorAlpha));
	}
	
	/**
	 * Mezclar dos colores
	 * 
	 * @param hexa1
	 * @param hexa2
	 * @param amount
	 * @return
	 */
	@GetMapping("/mix")
	public String mix(@RequestParam String hexa1, @RequestParam String hexa2, @RequestParam Float amount) {
		if (amount < 0f) {
			amount = 0f;
		}
		if (amount > 100f) {
			amount = 100f;
		}

		String hexaCorre1 = CommonUtil.corregirHexadecimal(hexa1);
		String hexaCorre2 = CommonUtil.corregirHexadecimal(hexa2);
		Color color1 = colorService.hexToColor(hexaCorre1);
		Color color2 = colorService.hexToColor(hexaCorre2);
		Color colorMix = colorService.mezclarColores(color1, color2, amount);
		return colorService.colorToHex(colorMix);
	}
	
	/**
	 * Genera una lista de colores gradientes
	 * @param hexa1
	 * @param hexa2
	 * @param numberOfGradients
	 * @return
	 */
	@GetMapping("/gradient")
	public List<ColorReturn> gradient(@RequestParam String hexa1, @RequestParam String hexa2,
			@RequestParam Integer numberOfGradients) {
		int numberOfGradientsInt = CommonUtil.revisarNumResultadoMaximo(numberOfGradients.toString(),
				1000);

		String hexaCorre1 = CommonUtil.corregirHexadecimal(hexa1);
		String hexaCorre2 = CommonUtil.corregirHexadecimal(hexa2);
		Color color1 = colorService.hexToColor(hexaCorre1);
		Color color2 = colorService.hexToColor(hexaCorre2);

		List<Color> listaColores = colorService.generarGradientes(color1, color2, --numberOfGradientsInt);
		List<ColorReturn> listaColoresRet = new ArrayList<ColorReturn>();
		listaColores.forEach(color -> {
			ColorReturn colorReturn = new ColorReturn(color.getRed(), color.getGreen(), color.getBlue(),
					colorService.colorToHex(color));
			listaColoresRet.add(colorReturn);
		});

		return listaColoresRet;
	}
	
	/**
	 * Generar un color monocromaticos
	 * @param hexa
	 * @param numberOfColors
	 * @return
	 */
	@GetMapping("/monochrome")
	public List<ColorReturn> monochrome(@RequestParam String hexa, @RequestParam Integer numberOfColors) {
		int numberOfColorsInt = CommonUtil.revisarNumResultadoMaximo(numberOfColors.toString(), 1000);

		String hexaCorre = CommonUtil.corregirHexadecimal(hexa);
		Color color = colorService.hexToColor(hexaCorre);

		List<Color> listaColores = colorService.generarMonocromaticos(color, numberOfColorsInt);
		List<ColorReturn> listaColoresRet = new ArrayList<ColorReturn>();
		listaColores.forEach(colorMonocromatico -> {
			ColorReturn colorReturn = new ColorReturn(colorMonocromatico.getRed(), colorMonocromatico.getGreen(),
					colorMonocromatico.getBlue(), colorService.colorToHex(colorMonocromatico));
			listaColoresRet.add(colorReturn);
		});

		return listaColoresRet;
	}

	
}
