package es.jortri.generadores.entityreturn;

public class MunicipioReturn {
	
	private String id;
	private String nombre;

	public MunicipioReturn() {
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
		return "MunicipioReturn [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
