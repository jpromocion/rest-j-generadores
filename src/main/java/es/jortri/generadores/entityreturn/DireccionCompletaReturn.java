package es.jortri.generadores.entityreturn;

public class DireccionCompletaReturn {

	private String direccion;
	private String numVia;
	private String codPostal;
	private String ineMunicipio;
	private String municipio;
	private String ineProvincia;
	private String provincia;
	private String ineCcaa;
	private String ccaa;
	private String direccionCompleta;
	
	public DireccionCompletaReturn() {
		super();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNumVia() {
		return numVia;
	}
	
	public void setNumVia(String numVia) {
		this.numVia = numVia;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getIneMunicipio() {
		return ineMunicipio;
	}

	public void setIneMunicipio(String ineMunicipio) {
		this.ineMunicipio = ineMunicipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getIneProvincia() {
		return ineProvincia;
	}

	public void setIneProvincia(String ineProvincia) {
		this.ineProvincia = ineProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getIneCcaa() {
		return ineCcaa;
	}

	public void setIneCcaa(String ineCcaa) {
		this.ineCcaa = ineCcaa;
	}

	public String getCcaa() {
		return ccaa;
	}

	public void setCcaa(String ccaa) {
		this.ccaa = ccaa;
	}
	
	public String getDireccionCompleta() {
		return direccionCompleta;
	}
	
	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}
	
	public void fijarDireccionCompleta() {
        this.direccionCompleta = this.direccion + ", " + this.numVia + ", " + this.codPostal + ", " + this.municipio + " (" + this.provincia + "). " + this.ccaa + ".";
    }
	
	@Override
	public String toString() {
		return "DireccionCompletaReturn [direccion=" + direccion + ", numVia=" + numVia + ", codPostal=" + codPostal
                + ", ineMunicipio=" + ineMunicipio + ", municipio=" + municipio + ", ineProvincia=" + ineProvincia
                + ", provincia=" + provincia + ", ineCcaa=" + ineCcaa + ", ccaa=" + ccaa + ", direccionCompleta="
                + direccionCompleta + "]";
	}
	
}
