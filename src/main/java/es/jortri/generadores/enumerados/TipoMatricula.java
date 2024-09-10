package es.jortri.generadores.enumerados;

public enum TipoMatricula {

	TURISMO("t","Turismo"), CICLOMOTOR("c","Ciclomotor"), REMOLQUE("r","Remolque"), ESPECIAL("e","Especial"),
	TURISTICO("u","Turístico"), HISTORICO("h","Histórico"), TEMPORAL_PARTICULAR("tp","Temporal particular"), 
	TEMPORAL_EMPRESA("te","Temporal empresa"), DIPLOMATICA("d","Diplomática");

	private String codigo;	
	private String nombre;
	
	private TipoMatricula (String codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;		
	}	
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Recuperar enumerado por el codigo 
	 * @param codigo
	 * @return
	 */
	public static TipoMatricula getByCodigo(String codigo) {
        for (TipoMatricula tipoMatricula : TipoMatricula.values()) {
            if (tipoMatricula.getCodigo().equals(codigo.toLowerCase())) {
                return tipoMatricula;
            }
        }
        return null;
    }

}
