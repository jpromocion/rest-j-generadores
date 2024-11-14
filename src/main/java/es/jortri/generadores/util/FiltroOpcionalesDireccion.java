package es.jortri.generadores.util;

import java.util.Optional;

import es.jortri.generadores.enumerados.OpcionFiltroEnum;

/**
 * Incdicar y/n segun cada valor opcional de la direcccion, va a ser cargado o no
 */
public class FiltroOpcionalesDireccion {
	
	
	OpcionFiltroEnum km; 
	OpcionFiltroEnum bloque; 
	OpcionFiltroEnum portal; 
	OpcionFiltroEnum escalera;
	OpcionFiltroEnum planta; 
	OpcionFiltroEnum puerta;
	
	public FiltroOpcionalesDireccion() {
		//por defecto los 4 primeros se fijan aleatoriamente y la planta y puerta siempre
		this.km = OpcionFiltroEnum.ALEATORIO;
		this.bloque = OpcionFiltroEnum.ALEATORIO;
		this.portal = OpcionFiltroEnum.ALEATORIO;
		this.escalera = OpcionFiltroEnum.ALEATORIO;
		this.planta = OpcionFiltroEnum.SI;
		this.puerta = OpcionFiltroEnum.SI;
	}

	
	public FiltroOpcionalesDireccion(Optional<String> km, Optional<String> bloque, Optional<String> portal,
			Optional<String> escalera, Optional<String> planta, Optional<String> puerta) {
		this.km = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(km, "a"));
		this.bloque = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(bloque, "a"));
		this.portal = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(portal, "a"));
		this.escalera = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(escalera, "a"));
		this.planta = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(planta, "y"));
		this.puerta = OpcionFiltroEnum.getByValor(CommonUtil.revisarParamYN(puerta, "y"));
	}
	
	public FiltroOpcionalesDireccion(String km, String bloque, String portal, String escalera, String planta,
			String puerta) {
		this.km = OpcionFiltroEnum.getByValor(km);
		this.bloque = OpcionFiltroEnum.getByValor(bloque);
		this.portal = OpcionFiltroEnum.getByValor(portal);
		this.escalera = OpcionFiltroEnum.getByValor(escalera);
		this.planta = OpcionFiltroEnum.getByValor(planta);
		this.puerta = OpcionFiltroEnum.getByValor(puerta);		
	}
	

	public OpcionFiltroEnum getKm() {
		return km;
	}

	public void setKm(OpcionFiltroEnum km) {
		this.km = km;
	}

	public OpcionFiltroEnum getBloque() {
		return bloque;
	}

	public void setBloque(OpcionFiltroEnum bloque) {
		this.bloque = bloque;
	}

	public OpcionFiltroEnum getPortal() {
		return portal;
	}

	public void setPortal(OpcionFiltroEnum portal) {
		this.portal = portal;
	}

	public OpcionFiltroEnum getEscalera() {
		return escalera;
	}

	public void setEscalera(OpcionFiltroEnum escalera) {
		this.escalera = escalera;
	}

	public OpcionFiltroEnum getPlanta() {
		return planta;
	}

	public void setPlanta(OpcionFiltroEnum planta) {
		this.planta = planta;
	}

	public OpcionFiltroEnum getPuerta() {
		return puerta;
	}

	public void setPuerta(OpcionFiltroEnum puerta) {
		this.puerta = puerta;
	}
	
	
	

}
