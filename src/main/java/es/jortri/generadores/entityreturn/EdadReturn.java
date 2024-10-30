package es.jortri.generadores.entityreturn;

public class EdadReturn {
	
	//datos absolutos en cada medida
	private Long anyos;
	private Long meses;
	private Long dias;
	private Long horas;
	private Long minutos;
	private Long segundos;
	
	//datos relativos enteros en cada medida
	private Long anyosRelativos;
	private Long mesesRelativos;
	private Long diasRelativos;
	private Long horasRelativos;
	private Long minutosRelativos;
	private Long segundosRelativos;
	
	public EdadReturn() {
		super();
	}

	public Long getAnyos() {
		return anyos;
	}

	public void setAnyos(Long anyos) {
		this.anyos = anyos;
	}

	public Long getMeses() {
		return meses;
	}

	public void setMeses(Long meses) {
		this.meses = meses;
	}

	public Long getDias() {
		return dias;
	}

	public void setDias(Long dias) {
		this.dias = dias;
	}

	public Long getHoras() {
		return horas;
	}

	public void setHoras(Long horas) {
		this.horas = horas;
	}

	public Long getMinutos() {
		return minutos;
	}

	public void setMinutos(Long minutos) {
		this.minutos = minutos;
	}

	public Long getSegundos() {
		return segundos;
	}

	public void setSegundos(Long segundos) {
		this.segundos = segundos;
	}

	public Long getAnyosRelativos() {
		return anyosRelativos;
	}

	public void setAnyosRelativos(Long anyosRelativos) {
		this.anyosRelativos = anyosRelativos;
	}

	public Long getMesesRelativos() {
		return mesesRelativos;
	}

	public void setMesesRelativos(Long mesesRelativos) {
		this.mesesRelativos = mesesRelativos;
	}

	public Long getDiasRelativos() {
		return diasRelativos;
	}

	public void setDiasRelativos(Long diasRelativos) {
		this.diasRelativos = diasRelativos;
	}

	public Long getHorasRelativos() {
		return horasRelativos;
	}

	public void setHorasRelativos(Long horasRelativos) {
		this.horasRelativos = horasRelativos;
	}

	public Long getMinutosRelativos() {
		return minutosRelativos;
	}

	public void setMinutosRelativos(Long minutosRelativos) {
		this.minutosRelativos = minutosRelativos;
	}

	public Long getSegundosRelativos() {
		return segundosRelativos;
	}

	public void setSegundosRelativos(Long segundosRelativos) {
		this.segundosRelativos = segundosRelativos;
	}
	
	@Override
	public String toString() {
		return "EdadReturn [anyos=" + anyos + ", meses=" + meses + ", dias=" + dias + ", horas=" + horas + ", minutos="
				+ minutos + ", segundos=" + segundos + ", anyosRelativos=" + anyosRelativos + ", mesesRelativos="
				+ mesesRelativos + ", diasRelativos=" + diasRelativos + ", horasRelativos=" + horasRelativos
				+ ", minutosRelativos=" + minutosRelativos + ", segundosRelativos=" + segundosRelativos + "]";
	}
	

}
