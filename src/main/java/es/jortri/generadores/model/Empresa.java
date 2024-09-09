package es.jortri.generadores.model;

public class Empresa {
	
	private String cif;
	private String nombre;
	private String fechaCreacion;
	private String telefono;
	private String fax;
	private String email;
	private String ccaa;
	private String ccaaIne;	
	private String municipio;
	private String municipioIne;
	private String provincia;
	private String provinciaIne;
	private String direccion;
	private String numerovia;
	private String codigoPostal;
	private String cnae;
	private String actividad;
	
	public Empresa() {
		super();
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMunicipioIne() {
		return municipioIne;
	}

	public void setMunicipioIne(String municipioIne) {
		this.municipioIne = municipioIne;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getProvinciaIne() {
		return provinciaIne;
	}

	public void setProvinciaIne(String provinciaIne) {
		this.provinciaIne = provinciaIne;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumerovia() {
		return numerovia;
	}

	public void setNumerovia(String numerovia) {
		this.numerovia = numerovia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	public String getCcaa() {
		return ccaa;
	}

	public void setCcaa(String ccaa) {
		this.ccaa = ccaa;
	}

	public String getCcaaIne() {
		return ccaaIne;
	}

	public void setCcaaIne(String ccaaIne) {
		this.ccaaIne = ccaaIne;
	}

	@Override
	public String toString() {
		return "Empresa [cif=" + cif + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", telefono="
				+ telefono + ", fax=" + fax + ", email=" + email + ", ccaa=" + ccaa + ", ccaaIne=" + ccaaIne 
				+ ", municipio=" + municipio + ", municipioIne="
				+ municipioIne + ", provincia=" + provincia + ", provinciaIne=" + provinciaIne + ", direccion="
				+ direccion + ", numerovia=" + numerovia + ", codigoPostal=" + codigoPostal + ", cnae=" + cnae
				+ ", actividad=" + actividad + "]";
	}
	
	
	
}
