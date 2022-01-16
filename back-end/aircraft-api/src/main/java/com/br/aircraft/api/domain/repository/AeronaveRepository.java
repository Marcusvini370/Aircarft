package com.br.aircraft.api.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.aircraft.api.domain.dto.search.GrupoDecadaDTO;
import com.br.aircraft.api.domain.dto.search.GrupoMarcaDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidosDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.model.Aeronave;


@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
	
	
	@Query(value = "select count(vendido) as disponiveis from tb_aeronave where vendido = false", nativeQuery = true)
	List<GrupoNaoVendidosDTO> aeronavesNaoVendidas();
	
	@Query(value = "select marca as marca, count(*) as total from tb_aeronave group by marca order by count(*)",
			nativeQuery = true)
	List<GrupoMarcaDTO> aeronaveMarcaAndQuantidade();
	
	@Query("SELECT count(id) as semanal  FROM Aeronave a where a.created >= :dataDiasAtras")
	List<GrupoSemanaDTO> aeronavesRegistradasSemana(@Param("dataDiasAtras") OffsetDateTime dataDiasAtras);
	
	@Query(value ="select 'Década de ' || RIGHT(TO_CHAR(aeronave.ano/10, '9999'), 1) || '0' as grupo,\r\n"
			+ "COUNT(*) as total from tb_aeronave aeronave group by grupo;",
			nativeQuery = true)
	List<GrupoDecadaDTO> aeronavesPorDecada();

}
