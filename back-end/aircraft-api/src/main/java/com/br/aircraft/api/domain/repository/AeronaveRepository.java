package com.br.aircraft.api.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.model.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {

	@Query(value = "select count(vendido) as disponiveis from tb_aeronave where vendido = false", nativeQuery = true)
	GrupoNaoVendidasDTO aeronavesNaoVendidas();

	@Query(value = "select marca as grupo, count(*) as total from tb_aeronave group by marca order by count(*)", nativeQuery = true)
	List<GrupoDTO> aeronaveMarcaAndQuantidade();

	@Query("SELECT count(id) as semanal  FROM Aeronave a where a.created >= :dataDiasAtras")
	GrupoSemanaDTO aeronavesRegistradasSemana(@Param("dataDiasAtras") OffsetDateTime dataDiasAtras);

	@Query(value = "select 'Década de ' || RIGHT(TO_CHAR(aeronave.ano/10, '9999'), 1) || '0' as grupo,\r\n"
			+ "COUNT(*) as total from tb_aeronave aeronave group by grupo ORDER BY grupo;", nativeQuery = true) 
	List<GrupoDTO> aeronavesPorDecada();

	List<Aeronave> findByNomeContaining(String nome);
	

}
