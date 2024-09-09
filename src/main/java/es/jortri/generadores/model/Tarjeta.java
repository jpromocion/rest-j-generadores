package es.jortri.generadores.model;

public class Tarjeta {
	
	private String tarjeta;
	private String tarjetaFormateada;
	private String expiracionCredito;
	private String cvc;
	private String tipoTarjeta;

	public Tarjeta() {
		super();
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getTarjetaFormateada() {
		return tarjetaFormateada;
	}

	public void setTarjetaFormateada(String tarjetaFormateada) {
		this.tarjetaFormateada = tarjetaFormateada;
	}

	public String getExpiracionCredito() {
		return expiracionCredito;
	}

	public void setExpiracionCredito(String expiracionCredito) {
		this.expiracionCredito = expiracionCredito;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	
	@Override
	public String toString() {
		return "Tarjeta [tarjeta=" + tarjeta + ", tarjetaFormateada=" + tarjetaFormateada + ", expiracionCredito="
				+ expiracionCredito + ", cvc=" + cvc + ", tipoTarjeta=" + tipoTarjeta + "]";
	}
	
}
