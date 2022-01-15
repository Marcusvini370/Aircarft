package com.br.aircraft.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AeronaveDTO {
	
	private Long id;
	private String nome;
	private String marca;
	private Integer ano;
	private String descricao;
	private Boolean vendido;

}
