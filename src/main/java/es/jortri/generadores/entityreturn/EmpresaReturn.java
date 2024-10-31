package es.jortri.generadores.entityreturn;

public class EmpresaReturn {
	
	private String cif;
	private String nombre;
	private String fechaCreacion;
	private String telefono;
	private String fax;
	private String email;
	private DireccionCompletaReturn direccion;
	private String cnae;
	private String actividad;
	private String paginaWeb;
	
	public EmpresaReturn() {
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
	
	public String getPaginaWeb() {
		return paginaWeb;
	}
	
	public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

	public DireccionCompletaReturn getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionCompletaReturn direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "EmpresaReturn [cif=" + cif + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + ", telefono="
                + telefono + ", fax=" + fax + ", email=" + email + ", direccion=" + direccion + ", cnae=" + cnae
                + ", actividad=" + actividad + ", paginaWeb=" + paginaWeb + "]";
	}
	
	
	
}
