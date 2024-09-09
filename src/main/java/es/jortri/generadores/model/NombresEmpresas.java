package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombres_empresas")
public class NombresEmpresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
		
	
	public NombresEmpresas() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "NombresEmpresas [id=" + id + ", nombre=" + nombre + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NombresEmpresas other = (NombresEmpresas) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	

}
