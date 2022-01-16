package com.br.aircraft.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

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

import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.dto.search.GrupoDecadaDTO;
import com.br.aircraft.api.domain.dto.search.GrupoMarcaDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidosDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.repository.AeronaveRepository;
import com.br.aircraft.api.domain.service.AeronaveService;

@RestController
@RequestMapping(value = "/aeronaves")
public class AeronaveController {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	private final AeronaveService aeronaveService;

	public AeronaveController(AeronaveService aeronaveService) {
		this.aeronaveService = aeronaveService;

	}

	@GetMapping
	public ResponseEntity<List<AeronaveDTO>> findAll() {
		return ResponseEntity.ok(aeronaveService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AeronaveDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(aeronaveService.findById(id));
	}

	@PostMapping
	public ResponseEntity<AeronaveDTO> saveAeronave(@RequestBody @Valid AeronaveInput aeronaveInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(aeronaveService.save(aeronaveInput));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AeronaveDTO> updateAeronave(@PathVariable Long id,
			@RequestBody @Valid AeronaveInput aeronaveInput) {
		return ResponseEntity.ok(aeronaveService.update(id, aeronaveInput));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		aeronaveService.delete(id);
	}

	@GetMapping("/registro-semanal")
	public List<GrupoSemanaDTO> findSellers() {
		OffsetDateTime dataDiasAtras = OffsetDateTime.now().minusDays(7);
		return aeronaveRepository.aeronavesRegistradasSemana(dataDiasAtras);
	}

	@GetMapping("/marca-quantidade")
	public List<GrupoMarcaDTO> findSellers2() {
		return aeronaveRepository.aeronaveMarcaAndQuantidade();
	}

	@GetMapping("/decada")
	public List<GrupoDecadaDTO> finddecada() {
		return aeronaveRepository.aeronavesPorDecada();
	}

	@GetMapping("/no-sellers")
	public List<GrupoNaoVendidosDTO> findSellerst() {
		return aeronaveRepository.aeronavesNaoVendidas();
	}

}
