package com.br.aircraft.api.domain.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.aircraft.api.domain.exception.AeronaveNotFoundException;
import com.br.aircraft.api.domain.model.Aeronave;
import com.br.aircraft.api.domain.repository.AeronaveRepository;

@Service
public class RegisterAeronave {
	
	private static final String MSG_AERONAVE_NAO_ENCOTNADA = "Não existe um cadastro de aeronave com código %d";
	
	@Autowired
	private AeronaveRepository aeronaveRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Aeronave save(Aeronave aeronave) {
		return aeronaveRepository.save(aeronave);
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
	
	
	public Aeronave find(Long id) {
		return manager.find(Aeronave.class, id);
	}
}
