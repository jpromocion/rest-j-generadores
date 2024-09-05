package es.jortri.generadores.model;

public class Persona {

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

	public Persona() {
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

	@Override
	public String toString() {
		return "Persona [nif=" + nif + ", nie=" + nie + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", genero=" + genero + ", nombreCompleto=" + nombreCompleto
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", telefonoMovil=" + telefonoMovil + ", telefonoFijo=" + telefonoFijo + "]";
	}

}
