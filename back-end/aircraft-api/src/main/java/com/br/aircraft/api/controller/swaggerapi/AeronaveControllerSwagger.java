package com.br.aircraft.api.controller.swaggerapi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Aeronaves")
public interface AeronaveControllerSwagger {

	@ApiOperation("Lista todas Aeronaves")
	public ResponseEntity<List<AeronaveDTO>> findAll();

	@ApiOperation("Busca Aeronave por id")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID da aeronave inválido", response = Problem.class),
			@ApiResponse(code = 404, message = "Aeronave não encontrada", response = Problem.class) })
	public ResponseEntity<AeronaveDTO> findById(Long id);

	@ApiOperation("Cadastra uma Aeronave")
	@ApiResponses({ @ApiResponse(code = 201, message = "Aeronave cadastrada"), })
	public ResponseEntity<AeronaveDTO> saveAeronave(
			@ApiParam(name = "corpo", value = "Representação de uma nova Aeronave") AeronaveInput aeronaveInput);

	@ApiOperation("Atualiza uma aeronave por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Aeronave atualizada"),
			@ApiResponse(code = 404, message = "Aeronave não encontrada", response = Problem.class) })
	public ResponseEntity<AeronaveDTO> updateAeronave(@ApiParam(value = "ID de uma cidade", example = "1") Long id,
			@ApiParam(name = "corpo", value = "Representação de uma aeronave com os novos dados") AeronaveInput aeronaveInput);

	@ApiOperation("Deleta uma aeronave por id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Aeronave excluída"),
			@ApiResponse(code = 404, message = "Aeronave não encontrada", response = Problem.class) })
	public void deleteById(Long id);

	@ApiOperation("Conta todas aeronaves registrada nos últimos 7 dias")
	public ResponseEntity<GrupoSemanaDTO> findRegistroSemanal();

	@ApiOperation("Exibi quantidade de aeronaves por marca")
	public ResponseEntity<List<GrupoDTO>> findMarcaQuantidade();

	@ApiOperation("Exibi a quantidade de aeronaves por década")
	public ResponseEntity<List<GrupoDTO>> findDecada();

	@ApiOperation("Exibi a quantidade de aeronaves não vendidas")
	public ResponseEntity<GrupoNaoVendidasDTO> findNoSellers();

	@ApiOperation("Busca aeronave pelo modelo")
	public ResponseEntity<List<AeronaveDTO>> findModel(String nome);

	@ApiOperation("Exibi todas aeronaves por paginação")
	public ResponseEntity<Page<AeronaveDTO>> listarAeronavesPage(Pageable pageable);

	@ApiOperation("Busca aeronave pelo modelo com paginação")
	public ResponseEntity<List<AeronaveDTO>> findModeloPage(String nome, Pageable pageable);

}
