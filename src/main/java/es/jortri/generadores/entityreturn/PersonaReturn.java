package es.jortri.generadores.entityreturn;

public class PersonaReturn {

	private String nif;
	private String nie;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String genero;
	private String nombreCompleto;
	private String fechaNacimiento;
	private String edad;
	private String telefonoMovil;
	private String telefonoFijo;
	private String login;
	private String email;
	private String password;
	private DireccionCompletaReturn direccion;
	private String iban;
	private String bic;
	private String tarjetaCredito;
	private String cvc;
	private String expiracionCredito;
	private String tipoTarjeta;
	private String nss;
	private String pasaporte;

	public PersonaReturn() {
		super();
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNie() {
		return nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	
	public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public DireccionCompletaReturn getDireccion() {
		return direccion;
	}
	
	public void setDireccion(DireccionCompletaReturn direccion) {
		this.direccion = direccion;
	}
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String getBic() {
		return bic;
	}
	
	public void setBic(String bic) {
		this.bic = bic;
	}
	
	public String getTarjetaCredito() {
		return tarjetaCredito;
	}
	
	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getExpiracionCredito() {
		return expiracionCredito;
	}

	public void setExpiracionCredito(String expiracionCredito) {
		this.expiracionCredito = expiracionCredito;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	
	public String getNss() {
		return nss;
	}
	
	public void setNss(String nss) {
		this.nss = nss;
	}
	
	public String getPasaporte() {
		return pasaporte;
	}
	
	public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

	@Override
	public String toString() {
		return "PersonaReturn [nif=" + nif + ", nie=" + nie + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", genero=" + genero + ", nombreCompleto=" + nombreCompleto
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", telefonoMovil=" + telefonoMovil
				+ ", telefonoFijo=" + telefonoFijo + ", login=" + login + ", email=" + email + ", password=" + password
				+ ", direccion=" + direccion + ", iban=" + iban + ", bic=" + bic + ", tarjetaCredito=" + tarjetaCredito
				+ ", cvc=" + cvc + ", expiracionCredito=" + expiracionCredito + ", tipoTarjeta=" + tipoTarjeta
				+ ", nss=" + nss + ", pasaporte=" + pasaporte + "]";
	}

}
