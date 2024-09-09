package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "palabras")
public class Palabras implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "id")
	private Integer id;
		
	@Column(name = "castellano")
	private String castellano;
	
	@Column(name = "ingles")
	private String ingles;
	
	@Column(name = "latin")
	private String latin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCastellano() {
		return castellano;
	}

	public void setCastellano(String castellano) {
		this.castellano = castellano;
	}

	public String getIngles() {
		return ingles;
	}

	public void setIngles(String ingles) {
		this.ingles = ingles;
	}

	public String getLatin() {
		return latin;
	}

	public void setLatin(String latin) {
		this.latin = latin;
	}
	
	@Override
	public String toString() {
		return "Palabras [id=" + id + ", castellano=" + castellano + ", ingles=" + ingles + ", latin=" + latin + "]";
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
		Palabras other = (Palabras) obj;
		return id == other.id;
	}
	
	
	
}
