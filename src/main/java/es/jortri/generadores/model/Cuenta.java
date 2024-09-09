package es.jortri.generadores.model;

public class Cuenta {
	
	private String ccc;
	private String cccFormateado;
	private String iban;
	private String ibanFormateado;
	private String bic;
	private String entidad;
	
	public Cuenta() {
		super();
    }

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public String getCccFormateado() {
		return cccFormateado;
	}

	public void setCccFormateado(String cccFormateado) {
		this.cccFormateado = cccFormateado;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIbanFormateado() {
		return ibanFormateado;
	}

	public void setIbanFormateado(String ibanFormateado) {
		this.ibanFormateado = ibanFormateado;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	@Override
	public String toString() {
		return "Cuenta [ccc=" + ccc + ", cccFormateado=" + cccFormateado + ", iban=" + iban + ", ibanFormateado="
				+ ibanFormateado + ", bic=" + bic + ", entidad=" + entidad + "]";
	}	
	
}
