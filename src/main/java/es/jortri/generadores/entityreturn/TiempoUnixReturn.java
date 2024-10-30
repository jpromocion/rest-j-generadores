package es.jortri.generadores.entityreturn;

import java.time.LocalDateTime;

public class TiempoUnixReturn {
	
	private Long tiempoUnixUTC;	
	
	private LocalDateTime fechaUTC;
	private LocalDateTime fechaLocal;
	
	public TiempoUnixReturn() {
		super();
	}
	
	public Long getTiempoUnixUTC() {
		return tiempoUnixUTC;
	}

	public void setTiempoUnixUTC(Long tiempoUnixUTC) {
		this.tiempoUnixUTC = tiempoUnixUTC;
	}


	public LocalDateTime getFechaUTC() {
		return fechaUTC;
	}

	public void setFechaUTC(LocalDateTime fechaUTC) {
		this.fechaUTC = fechaUTC;
	}

	public LocalDateTime getFechaLocal() {
		return fechaLocal;
	}

	public void setFechaLocal(LocalDateTime fechaLocal) {
		this.fechaLocal = fechaLocal;
	}
	
	@Override
	public String toString() {
		return "TiempoUnixReturn [tiempoUnixUTC=" + tiempoUnixUTC 
				+ ", fechaUTC=" + fechaUTC + ", fechaLocal=" + fechaLocal + "]";
	}

}
