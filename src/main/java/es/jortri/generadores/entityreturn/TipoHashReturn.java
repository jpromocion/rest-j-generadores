package es.jortri.generadores.entityreturn;

public class TipoHashReturn {

	private String codigo;

	private String descripcion;
	

	public TipoHashReturn() {
		super();
	}
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "TipoHashReturn [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
}
