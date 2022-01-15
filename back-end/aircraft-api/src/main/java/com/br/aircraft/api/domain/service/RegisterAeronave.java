package com.br.aircraft.api.domain.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.aircraft.api.domain.model.Aeronave;
import com.br.aircraft.api.domain.repository.AeronaveRepository;

@Service
public class RegisterAeronave {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private AeronaveRepository aeronaveRepository;
	
	@Transactional
	public Aeronave save(Aeronave aeronave) {
		return aeronaveRepository.save(aeronave);
	}
	
	@Transactional
	public void delete(Long id) {
		 aeronaveRepository.deleteById(id);
	}
	
	
	public Aeronave find(Long id) {
		return manager.find(Aeronave.class, id);
	}
}
