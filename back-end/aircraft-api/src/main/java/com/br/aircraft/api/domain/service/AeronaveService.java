package com.br.aircraft.api.domain.service;

import java.util.List;

import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;

public interface AeronaveService {

	List<AeronaveDTO> findAll();

	AeronaveDTO findById(Long id);

	AeronaveDTO update(Long id, AeronaveInput aeronaveInput);

	void delete(Long id);

	AeronaveDTO save(AeronaveInput aeronaveInput);

}
