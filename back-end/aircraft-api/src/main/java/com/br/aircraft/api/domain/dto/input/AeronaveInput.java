package com.br.aircraft.api.domain.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AeronaveInput {
	
	@NotBlank
	@ApiModelProperty(example = "Praetor 500", required = true)
	private String nome;
	
	@NotBlank
	@ApiModelProperty(example = "Embraer", required = true)
	private String marca;
	
	@NotNull
	@ApiModelProperty(example = "2020", required = true)
	private Integer ano;

	@ApiModelProperty(example = "Jato Executivo")
	private String descricao;
	
	@NotNull
	@ApiModelProperty(example = "false", required = true)
	private Boolean vendido;
	

}
