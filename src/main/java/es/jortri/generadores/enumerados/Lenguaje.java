package es.jortri.generadores.enumerados;

public enum Lenguaje {

	SPANISH("1","Español","spanish"), ENGLISH("2","Inglés","english"), LATIN("3","Latín","latin");

	private String codigo;	
	private String nombre;
	private String valorParametro;
	
	private Lenguaje (String codigo, String nombre, String valorParametro){
		this.codigo = codigo;
		this.nombre = nombre;		
		this.valorParametro = valorParametro;
	}	
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getValorParametro() {
		return valorParametro;
	}
	
	/**
	 * Recuperar enumerado por el codigo de inicio
	 * @param codigoInicio
	 * @return
	 */
	public static Lenguaje getByCodigoInicio(String codigoInicio) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.getCodigo().equals(codigoInicio)) {
                return lenguaje;
            }
        }
        return null;
    }
	
	/**
	 * Recuperar enumerado por el valor del parametro
	 * @param valorParametro
	 * @return
	 */
	public static Lenguaje getByValorParametro(String valorParametro) {
		for (Lenguaje lenguaje : Lenguaje.values()) {
			if (lenguaje.getValorParametro().equals(valorParametro.toLowerCase())) {
				return lenguaje;
			}
		}
		return null;
	}
}
