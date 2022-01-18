package com.br.aircraft.api.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;

public interface AeronaveService {

	List<AeronaveDTO> findAll();

	AeronaveDTO findById(Long id);

	AeronaveDTO update(Long id, AeronaveInput aeronaveInput);

	void delete(Long id);

	AeronaveDTO save(AeronaveInput aeronaveInput);

	Page<AeronaveDTO> findAllPage(Pageable pageable);
	
	List<AeronaveDTO> findByNomeContaining(String nome, Pageable pageable);

	GrupoSemanaDTO findRegistroSemanal();

	List<GrupoDTO> findMarcaQuantidade();

	List<GrupoDTO> findDecada();

	GrupoNaoVendidasDTO findNoSellers();
	

}
