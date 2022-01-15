package com.br.aircraft.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.aircraft.api.assembler.AeronaveDtoAssembler;
import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.repository.AeronaveRepository;
import com.br.aircraft.api.domain.service.RegisterAeronave;

@RestController
@RequestMapping(value = "/aeronaves")
public class AeronaveController {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private AeronaveDtoAssembler aeronaveDtoAssembler;

	@Autowired
	private RegisterAeronave registerAeronave;

	@GetMapping
	public List<AeronaveDTO> findAll() {
		return aeronaveDtoAssembler.toCollectionModel(aeronaveRepository.findAll());
	}

	@GetMapping("/{id}")
	public AeronaveDTO findById(@PathVariable Long id) {
		return aeronaveDtoAssembler.toModel(registerAeronave.BuscarOuFalhar(id));
	}

	@PostMapping
	public AeronaveDTO saveAeronave(@RequestBody @Valid AeronaveInput aeronaveInput) {
		return registerAeronave.save(aeronaveInput);
	}

	@PutMapping("/{id}")
	public AeronaveDTO updateAeronave(@PathVariable Long id, @RequestBody @Valid AeronaveInput aeronaveInput) {
		return registerAeronave.update(id, aeronaveInput);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		registerAeronave.delete(id);
	}
	
	@GetMapping("/sellers")
	public List<AeronaveDTO> findSellers() {
		return aeronaveDtoAssembler.toCollectionModel(aeronaveRepository.findVendidosByAeronave());
	}

}
