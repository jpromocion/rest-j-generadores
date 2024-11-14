package es.jortri.generadores.enumerados;

public enum OpcionFiltroEnum {

	SI("y"), NO("n"), ALEATORIO("a");

	private String valor;
	
	private OpcionFiltroEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	/**
	 * Recuperar enumerado por el codigo de inicio
	 * @param codigoInicio
	 * @return
	 */
	public static OpcionFiltroEnum getByValor(String valor) {
        for (OpcionFiltroEnum opcion : OpcionFiltroEnum.values()) {
            if (opcion.getValor().equals(valor)) {
                return opcion;
            }
        }
        return null;
    }
	
	
}
