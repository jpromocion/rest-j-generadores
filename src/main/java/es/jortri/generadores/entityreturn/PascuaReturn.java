package es.jortri.generadores.entityreturn;

import java.util.Date;

public class PascuaReturn {

	//fecha formato dd/mm/yyyy de los dias reseñables
	private Date fechaDomingoRamos;
	private Date fechaLunesSanto;
	private Date fechaMartesSanto;
	private Date fechaMiercolesSanto;
	private Date fechaJuevesSanto;
	private Date fechaViernesSanto;
	private Date fechaSabadoSanto;
	private Date fechaDomingoResurreccion;
	
	//fecha formato dd/mm/yyyy. Periodo de 2 semanas "vacacional", el lunes de la semana santa, y el domingo de la siguiente semana
	private Date fechaLunesPeriodoVacacional;
	private Date fechaDomingoPeriodoVacacional;
	
	public PascuaReturn() {
		super();
	}
	
	public Date getFechaDomingoRamos() {
		return fechaDomingoRamos;
	}
	public void setFechaDomingoRamos(Date fechaDomingoRamos) {
		this.fechaDomingoRamos = fechaDomingoRamos;
	}
	public Date getFechaLunesSanto() {
		return fechaLunesSanto;
	}
	public void setFechaLunesSanto(Date fechaLunesSanto) {
		this.fechaLunesSanto = fechaLunesSanto;
	}
	public Date getFechaMartesSanto() {
		return fechaMartesSanto;
	}
	public void setFechaMartesSanto(Date fechaMartesSanto) {
		this.fechaMartesSanto = fechaMartesSanto;
	}
	public Date getFechaMiercolesSanto() {
		return fechaMiercolesSanto;
	}
	public void setFechaMiercolesSanto(Date fechaMiercolesSanto) {
		this.fechaMiercolesSanto = fechaMiercolesSanto;
	}
	public Date getFechaJuevesSanto() {
		return fechaJuevesSanto;
	}
	public void setFechaJuevesSanto(Date fechaJuevesSanto) {
		this.fechaJuevesSanto = fechaJuevesSanto;
	}
	public Date getFechaViernesSanto() {
		return fechaViernesSanto;
	}
	public void setFechaViernesSanto(Date fechaViernesSanto) {
		this.fechaViernesSanto = fechaViernesSanto;
	}
	public Date getFechaSabadoSanto() {
		return fechaSabadoSanto;
	}
	public void setFechaSabadoSanto(Date fechaSabadoSanto) {
		this.fechaSabadoSanto = fechaSabadoSanto;
	}
	public Date getFechaDomingoResurreccion() {
		return fechaDomingoResurreccion;
	}
	public void setFechaDomingoResurreccion(Date fechaDomingoResurreccion) {
		this.fechaDomingoResurreccion = fechaDomingoResurreccion;
	}
	public Date getFechaLunesPeriodoVacacional() {
		return fechaLunesPeriodoVacacional;
	}
	public void setFechaLunesPeriodoVacacional(Date fechaLunesPeriodoVacacional) {
		this.fechaLunesPeriodoVacacional = fechaLunesPeriodoVacacional;
	}
	public Date getFechaDomingoPeriodoVacacional() {
		return fechaDomingoPeriodoVacacional;
	}
	public void setFechaDomingoPeriodoVacacional(Date fechaDomingoPeriodoVacacional) {
		this.fechaDomingoPeriodoVacacional = fechaDomingoPeriodoVacacional;
	}

	@Override
	public String toString() {
		return "PascuaReturn [fechaDomingoRamos=" + fechaDomingoRamos + ", fechaLunesSanto=" + fechaLunesSanto
				+ ", fechaMartesSanto=" + fechaMartesSanto + ", fechaMiercolesSanto=" + fechaMiercolesSanto
				+ ", fechaJuevesSanto=" + fechaJuevesSanto + ", fechaViernesSanto=" + fechaViernesSanto
				+ ", fechaSabadoSanto=" + fechaSabadoSanto + ", fechaDomingoResurreccion=" + fechaDomingoResurreccion
				+ ", fechaLunesPeriodoVacacional=" + fechaLunesPeriodoVacacional + ", fechaDomingoPeriodoVacacional="
				+ fechaDomingoPeriodoVacacional + "]";
	}
}
