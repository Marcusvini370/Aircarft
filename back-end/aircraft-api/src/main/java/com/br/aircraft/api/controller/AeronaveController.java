package com.br.aircraft.api.controller;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.br.aircraft.api.assembler.AeronaveInputDissasembler;
import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.model.Aeronave;
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
	private AeronaveInputDissasembler aeronaveInputDissasembler;
	
	@Autowired
	private RegisterAeronave registerAeronave;
	
	
	@GetMapping
	public List<AeronaveDTO> findAll(){
		return aeronaveDtoAssembler.toCollectionModel(aeronaveRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aeronave> findById(@PathVariable Long id) {
		
		Aeronave aeronave = registerAeronave.find(id);
		
		if(aeronave != null) {
			return  ResponseEntity.ok(aeronave);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public AeronaveDTO saveAeronave(@RequestBody AeronaveInput aeronaveInput ) {
		
		Aeronave aeronave = aeronaveInputDissasembler.toDomainObject(aeronaveInput);
		
		aeronave = registerAeronave.save(aeronave);
		
		return aeronaveDtoAssembler.toModel(aeronave);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aeronave> updateAeronave(@PathVariable Long id, @RequestBody AeronaveInput aeronaveInput ) {
		Aeronave aeronaveAtual = registerAeronave.find(id);
		
		if(aeronaveAtual != null) {
			
			aeronaveInputDissasembler.copyToDomainObject(aeronaveInput, aeronaveAtual);
			
			aeronaveAtual = registerAeronave.save(aeronaveAtual);
			
			return ResponseEntity.ok(aeronaveAtual);
		}
		
			return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		 aeronaveRepository.deleteById(id);
	}
	
	
	

}
