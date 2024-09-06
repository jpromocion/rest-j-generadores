package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "codpostales")
public class Codpostales implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	private Integer id;	
	
	@Column(name = "codpostal")
	private String codpostal;
	
	@Column(name = "idprovincias")
	private String idprovincias;
	
	@Column(name = "inemunicipios")
	private String inemunicipios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	public String getIdprovincias() {
		return idprovincias;
	}

	public void setIdprovincias(String idprovincias) {
		this.idprovincias = idprovincias;
	}

	public String getInemunicipios() {
		return inemunicipios;
	}

	public void setInemunicipios(String inemunicipios) {
		this.inemunicipios = inemunicipios;
	}
	
	@Override
	public String toString() {
		return "Codpostales [id=" + id + ", codpostal=" + codpostal + ", idprovincias=" + idprovincias
				+ ", inemunicipios=" + inemunicipios + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Codpostales other = (Codpostales) obj;
		return id != null && id.equals(other.id);
	}

	
}
