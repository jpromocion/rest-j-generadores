package es.jortri.generadores.enumerados;

public enum PrefijoTarjeta {
	
	AMERICAN("3","American Express","americanexpress"), VISA("4","Visa","visa"), MASTECARD("5","Mastercard","mastercard"), DISCOVER("6", "Discover","discover");

	private String codigoInicio;	
	private String nombre;
	private String valorParametro;
	
	private PrefijoTarjeta (String codigoInicio, String nombre, String valorParametro){
		this.codigoInicio = codigoInicio;
		this.nombre = nombre;		
		this.valorParametro = valorParametro;
	}	
	
	public String getCodigoInicio() {
		return codigoInicio;
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
	public static PrefijoTarjeta getByCodigoInicio(String codigoInicio) {
        for (PrefijoTarjeta prefijo : PrefijoTarjeta.values()) {
            if (prefijo.getCodigoInicio().equals(codigoInicio)) {
                return prefijo;
            }
        }
        return null;
    }
	
	/**
	 * Recuperar enumerado por el valor del parametro
	 * @param valorParametro
	 * @return
	 */
	public static PrefijoTarjeta getByValorParametro(String valorParametro) {
		for (PrefijoTarjeta prefijo : PrefijoTarjeta.values()) {
			if (prefijo.getValorParametro().equals(valorParametro.toLowerCase())) {
				return prefijo;
			}
		}
		return null;
	}
	

	



}
