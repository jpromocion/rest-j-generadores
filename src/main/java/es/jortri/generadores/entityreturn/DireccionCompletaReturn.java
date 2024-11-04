package es.jortri.generadores.entityreturn;

public class DireccionCompletaReturn {

	private String direccion;
	private String numVia;
	private String kilometro;
	private String bloque;
	private String portal;
	private String escalera;
	private String planta;
	private String puerta;	
	private String codPostal;
	private String ineMunicipio;
	private String municipio;
	private String ineProvincia;
	private String provincia;
	private String ineCcaa;
	private String ccaa;
	private String direccionAMedio;
	private String direccionCompleta;
	private String referenciaCatastral;
	
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
	
	public String getKilometro() {
		return kilometro;
	}

	public void setKilometro(String kilometro) {
		this.kilometro = kilometro;
	}

	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getDireccionAMedio() {
		return direccionAMedio;
	}

	public void setDireccionAMedio(String direccionAMedio) {
		this.direccionAMedio = direccionAMedio;
	}
	
	public String getReferenciaCatastral() {
		return referenciaCatastral;
	}
	
	public void setReferenciaCatastral(String referenciaCatastral) {
		this.referenciaCatastral = referenciaCatastral;
	}

	public void fijarDireccionCompleta() {
        this.direccionCompleta = this.direccion + ", " + this.numVia;
        if (this.kilometro != null && !this.kilometro.isEmpty()) {
        	this.direccionCompleta = this.direccionCompleta + ", Km " + this.kilometro;
        }
		if (this.bloque != null && !this.bloque.isEmpty()) {
			this.direccionCompleta = this.direccionCompleta + ", Bloque " + this.bloque;
		}
		if (this.portal != null && !this.portal.isEmpty()) {
			this.direccionCompleta = this.direccionCompleta + ", Portal " + this.portal;
		}
		if (this.escalera != null && !this.escalera.isEmpty()) {
			this.direccionCompleta = this.direccionCompleta + ", Escalera " + this.escalera;
		}
		this.direccionCompleta = this.direccionCompleta + ", " + this.planta + " " + this.puerta;
		this.direccionCompleta = this.direccionCompleta + ".";
		//llegado a esto punto, consideramos a esto la direccion a medio
		this.direccionAMedio = this.direccionCompleta;
		//la direccion completa, añade CP y los datos de municipio, provincia, ccaa
        this.direccionCompleta = this.direccionCompleta + " " + this.codPostal + " " 
        		+ this.municipio + ". " + this.provincia + " (" + this.ccaa + ").";
    }
	
	@Override
	public String toString() {
		return "DireccionCompletaReturn [direccion=" + direccion + ", numVia=" + numVia + ", kilometro=" + kilometro
				+ ", bloque=" + bloque + ", portal=" + portal + ", escalera=" + escalera + ", planta=" + planta
				+ ", puerta=" + puerta + ", codPostal=" + codPostal + ", ineMunicipio=" + ineMunicipio + ", municipio="
				+ municipio + ", ineProvincia=" + ineProvincia + ", provincia=" + provincia + ", ineCcaa=" + ineCcaa
				+ ", ccaa=" + ccaa + ", direccionAMedio=" + direccionAMedio + ", direccionCompleta=" + direccionCompleta
				+ ", referenciaCatastral=" + referenciaCatastral + "]";
	}
		
	
}
