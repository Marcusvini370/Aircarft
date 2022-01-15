package com.br.aircraft.api.domain.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.aircraft.api.assembler.AeronaveDtoAssembler;
import com.br.aircraft.api.assembler.AeronaveInputDissasembler;
import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.exception.AeronaveNotFoundException;
import com.br.aircraft.api.domain.model.Aeronave;
import com.br.aircraft.api.domain.repository.AeronaveRepository;

@Service
public class RegisterAeronave {
	
	private static final String MSG_AERONAVE_NAO_ENCOTNADA = "Não existe um cadastro de aeronave com código %d";
	
	@Autowired
	private AeronaveRepository aeronaveRepository;
	
	@Autowired
	private AeronaveInputDissasembler aeronaveInputDissasembler;
	
	@Autowired
	private AeronaveDtoAssembler aeronaveDtoAssembler;

	
	@Transactional
	public AeronaveDTO save(AeronaveInput aeronaveInput) {
		Aeronave aeronave = aeronaveInputDissasembler.toDomainObject(aeronaveInput);
		return  aeronaveDtoAssembler.toModel(aeronaveRepository.save(aeronave));
	}
	
	@Transactional
	public AeronaveDTO update(Long id,AeronaveInput aeronaveInput) {
		Aeronave aeronaveAtual = BuscarOuFalhar(id);
		aeronaveInputDissasembler.copyToDomainObject(aeronaveInput, aeronaveAtual);
		return  aeronaveDtoAssembler.toModel(aeronaveRepository.save(aeronaveAtual));
	}
	
	
	@Transactional
	public void delete(Long id) {
		try {
		 aeronaveRepository.deleteById(id);
		
		}catch(EmptyResultDataAccessException e){
			throw new AeronaveNotFoundException(String.format(MSG_AERONAVE_NAO_ENCOTNADA, id)); 
			
		}
	}
	
	public Aeronave BuscarOuFalhar(Long id) {
		return aeronaveRepository.findById(id).orElseThrow(
				() -> new AeronaveNotFoundException(String.format(MSG_AERONAVE_NAO_ENCOTNADA, id)));
	}
	
	
}
