package es.jortri.generadores.enumerados;

public enum TipoHash {
	
	MD5("MD5", "MD5 con longitud de 128-bit"), SHA1("SHA-1", "SHA-1 con longitud 160-bit"), SHA256("SHA-256", "SHA-256 con longitud de 256-bit"), SHA512("SHA-512", "SHA-512 con longitud de 512-bit");
	
	private String tipo;
	private String descripcion;

	TipoHash(String tipo, String descripcion) {
        this.setTipo(tipo);
        this.setDescripcion(descripcion);
    }
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
	/**
	 * Recuperar enumerado por el valor del tipo
	 * @param tipo
	 * @return
	 */
	public static TipoHash getByTipo(String tipo) {
        for (TipoHash tipoEnum : TipoHash.values()) {
            if (tipoEnum.getTipo().equals(tipo)) {
                return tipoEnum;
            }
        }
        return null;
    }


	
}
