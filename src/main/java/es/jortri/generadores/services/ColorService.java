package es.jortri.generadores.services;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ColorService {

	/**
	 * Generar un color aleatorio
	 * @return
	 */
	public Color generarColorAleatorio() {
		return new Color((int) (Math.random() * 0x1000000));
	}
	
	/**
	 * Dado un color en RGB transformarlo en hexadecimal
	 */
	public String colorToHex(Color color) {
		return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * Dado un color en RGB con alpha transformarlo en hexadecimal
	 */
	public String colorToHexAlpha(Color color) {
		return String.format("#%02x%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}	
	
	/**
	 * Dado un color en hexadecimal transformarlo en RGB
	 */
	public Color hexToColor(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}
	
	/**
	 * Aclara un color en un cantidad especificada
	 * @param color
	 * @param cantidad
	 * @return
	 */
	public Color aclararColor(Color color, int cantidad) {	
		int r = color.getRed() + cantidad;
        int g = color.getGreen() + cantidad;
        int b = color.getBlue() + cantidad;
        
        if(r > 255) {
            r = 255;
        }
        if(g > 255) {
            g = 255;
        }
        if(b > 255) {
            b = 255;
        }
        
        return new Color(r, g, b);
    }
	
	/**
	 * Oscurece un color en una cantidad especificada
	 * @param color
	 * @param cantidad
	 * @return
	 */
	public Color oscurecerColor(Color color, int cantidad) {
		int r = color.getRed() - cantidad;
		int g = color.getGreen() - cantidad;
		int b = color.getBlue() - cantidad;

		if (r < 0) {
			r = 0;
		}
		if (g < 0) {
			g = 0;
		}
		if (b < 0) {
			b = 0;
		}

		return new Color(r, g, b);
	}
	

	
	/**
	 * Saturar un color en una cantidad especificada
	 * @param color
	 * @param cantidad
	 * @return
	 */
	public Color saturarColor(Color color, float cantidad) {
		float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		
		hsb[1] = Math.max(0.0f, Math.min(1.0f, hsb[1] + cantidad));		
                    
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}
	
	/**
	 * Aplicar el brillo a un color	
	 * @param color
	 * @param cantidad
	 * @return
	 */
	public Color aplicarBrillo(Color color, float percentage) {		
        // Convert the percentage to a value between -1 and 1
        float amount = (percentage / 100) * 2 - 1;
		
		float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		
        // Modify the brightness (clamp the value between 0 and 1)
		hsb[2] = Math.max(0, Math.min(1, hsb[2] + amount));		
		
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	/**
	 * Aplicar un tono a un color
	 * 
	 * @param color
	 * @param hue
	 * @return
	 */
	public Color aplicarMatiz(Color color, float hueChange) {
        float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newHue = (hsbValues[0] + hueChange) % 1.0f;
        if (newHue < 0) {
            newHue += 1.0f;
        }
        return Color.getHSBColor(newHue, hsbValues[1], hsbValues[2]);		
	}
	

	
	/**
	 * Invertir un color
	 * 
	 * @param color
	 * @return
	 */
	public Color invertirColor(Color color) {
		return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
	}
	
	/**
	 * Aplicar un canal alfa a un color
	 * 
	 * @param color
	 * @param alpha
	 * @return
	 */
	public Color aplicarCanalAlfa(Color color, int alpha) {
		int alphaOriginal = color.getAlpha();
		int alphaModificado = (alphaOriginal * alpha) / 100;
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaModificado);
	}

	/**
	 * Mezclar dos colores
	 * 
	 * @param color1
	 * @param color2
	 * @param balance
	 * @return
	 */
	public Color mezclarColores(Color color1, Color color2, double balance) {
		//balance llega en porcentaje de 0 a 100
	    balance = balance / 100;
	    double inverseBalance = 1.0 - balance;
		
        int r = (int) (color2.getRed() * balance + color1.getRed() * inverseBalance);
        int g = (int) (color2.getGreen() * balance + color1.getGreen() * inverseBalance);
        int b = (int) (color2.getBlue() * balance + color1.getBlue() * inverseBalance);
        
        // Clamp values to the range 0-255
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));        
        
        return new Color(r, g, b);		
	}
	
	/**
	 * Generar gradientes de un color sobre otro
	 * 
	 * @param color1
	 * @param color2
	 * @param numGradientes
	 * @return
	 */	
	public List<Color> generarGradientes(Color startColor, Color endColor, int numberOfGradients) {
        List<Color> gradientColors = new ArrayList<>();

        int r1 = startColor.getRed();
        int g1 = startColor.getGreen();
        int b1 = startColor.getBlue();
        
        int r2 = endColor.getRed();
        int g2 = endColor.getGreen();
        int b2 = endColor.getBlue();

        for (int i = 0; i <= numberOfGradients; i++) {
            int r = (int) (r1 + i * (r2 - r1) / (double) numberOfGradients);
            int g = (int) (g1 + i * (g2 - g1) / (double) numberOfGradients);
            int b = (int) (b1 + i * (b2 - b1) / (double) numberOfGradients);

            gradientColors.add(new Color(r, g, b));
        }

        return gradientColors;
	}
	
	/**
	 * Generar colores monocromaticos
	 * 
	 * @param color
	 * @param numColores
	 * @return
	 */	
	public List<Color> generarMonocromaticos(Color baseColor, int numberOfColors) {
        List<Color> colors = new ArrayList<>();
        int red = baseColor.getRed();
        int green = baseColor.getGreen();
        int blue = baseColor.getBlue();
        
        for (int i = 0; i < numberOfColors; i++) {
            int factor = (int) (255 * i / (float) numberOfColors);
            int newRed = Math.min(255, red + factor);
            int newGreen = Math.min(255, green + factor);
            int newBlue = Math.min(255, blue + factor);

            Color newColor = new Color(newRed, newGreen, newBlue);
            colors.add(newColor);
        }

        return colors;
	}

	
	
}
