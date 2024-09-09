package es.jortri.generadores.enumerados;

public enum Genero {
	
	MALE("male",1), FEMALE("female",2), AMBOS("",3);


	private String nombre;
	private int id;
	
	private Genero (String nombre, int id){
		this.nombre = nombre;
		this.id = id;
	}	
	
	
	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}	
	
}
