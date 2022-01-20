package com.br.aircraft.api.domain.enums;

public enum EnumMarca {
	
	AIRBUS("Airbus"),
	EMBRAER("Embraer"),
	MCDONNEL_DOUGLAS("McDonnel Douglas"),
	BOEING("Boeing"),
	BOMBARDIER("Bombardier"),
	LEARJET("Learjet"),
	ANTONOV("Antonov"),
	CESSNA("Cessna");
	
	private String marca;
	
	EnumMarca(String string) {
	this.marca = string;
	}

	public static boolean validarMarca(String inputMarca){
		
		for (EnumMarca item : EnumMarca.values()) {
			if(item.marca.equals(inputMarca) ) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
