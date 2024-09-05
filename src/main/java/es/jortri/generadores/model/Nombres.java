package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nombres")
public class Nombres implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
		
	@Column(name = "genero")
	private String genero;
	
	public Nombres() {
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
		
	@Override
	public String toString() {
		return "Nombre [id=" + id + ", nombre=" + nombre + ", genero=" + genero + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Nombres other = (Nombres) obj;
		return id == other.id;
	}

}
