package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	private Integer id;	
	
	@Column(name = "codigoine")
	private String codigoine;
	
	@Column(name = "idprovincias")
	private String idprovincias;
	
	@Column(name = "nombre")
	private String nombre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoine() {
		return codigoine;
	}
	
	public void setCodigoine(String codigoine) {
		this.codigoine = codigoine;
	}
	
	public String getIdprovincias() {
		return idprovincias;
	}
	
	public void setIdprovincias(String idprovincias) {
		this.idprovincias = idprovincias;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}		
	
	@Override
	public String toString() {
		return "Municipios [id=" + id + ", codigoine=" + codigoine + ", idprovincias=" + idprovincias + ", nombre="
				+ nombre + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipios other = (Municipios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
