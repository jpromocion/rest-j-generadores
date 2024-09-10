package es.jortri.generadores.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "security_api_keys")
public class SecurityApiKeys implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "apikey")
	private String apikey;
	
	public SecurityApiKeys() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	@Override
	public String toString() {
		return "SecurityApiKeys [id=" + id + ", apikey=" + apikey  + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		SecurityApiKeys other = (SecurityApiKeys) obj;
		return id == other.id;
	}
	
		
	
}
