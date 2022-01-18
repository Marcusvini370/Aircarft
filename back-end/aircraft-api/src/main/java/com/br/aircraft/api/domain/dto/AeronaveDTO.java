package com.br.aircraft.api.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AeronaveDTO {
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "E175")
	private String nome;
	
	@ApiModelProperty(example = "Embraer")
	private String marca;
	
	@ApiModelProperty(example = "2020")
	private Integer ano;
	
	@ApiModelProperty(example = "Peso Máximo de Decolagem 40.370 kg / 89.000 libras")
	private String descricao;
	
	@ApiModelProperty(example = "false")
	private Boolean vendido;

}
