package es.jortri.generadores.entityreturn;

public class CcaaReturn {

	private String id;
	private String nombre;
	
	public CcaaReturn() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "CcaaReturn [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
