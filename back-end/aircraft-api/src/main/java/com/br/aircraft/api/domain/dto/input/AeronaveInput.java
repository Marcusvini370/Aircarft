package com.br.aircraft.api.domain.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AeronaveInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String marca;
	
	@NotNull
	private Integer ano;

	private String descricao;
	
	@NotNull
	private Boolean vendido;
	

}
