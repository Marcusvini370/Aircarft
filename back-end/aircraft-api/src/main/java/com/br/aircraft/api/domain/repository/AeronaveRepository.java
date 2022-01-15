package com.br.aircraft.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.aircraft.api.domain.model.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
	
	@Query("from Aeronave a where a.vendido = false") // select count(nome) from tb_aeronave where vendido = false;
	List<Aeronave> findVendidosByAeronave();

}
