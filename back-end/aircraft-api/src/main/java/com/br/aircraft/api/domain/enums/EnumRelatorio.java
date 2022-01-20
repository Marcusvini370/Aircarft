package com.br.aircraft.api.domain.enums;

import lombok.Getter;

@Getter
public enum EnumRelatorio {
	
	SEMANAL("relatorio-semanal"),
	VENDIDAS("aeronaves-vendidas"),
	DISPONIVEIS("aeronaves-nao-vendidas");
	
private String relatorio;
	
	EnumRelatorio(String string) {
	this.relatorio = string;
	}

}
