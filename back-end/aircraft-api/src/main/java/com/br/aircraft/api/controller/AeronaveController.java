package com.br.aircraft.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.service.AeronaveService;

@RestController
@RequestMapping(value = "/aeronaves")
public class AeronaveController {

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
	public ResponseEntity<GrupoSemanaDTO> findRegistroSemanal() {
		return ResponseEntity.ok(aeronaveService.findRegistroSemanal());
	}

	@GetMapping("/marca-quantidade")
	public ResponseEntity<List<GrupoDTO>> findMarcaQuantidade() {
		return ResponseEntity.ok(aeronaveService.findMarcaQuantidade());
	}

	@GetMapping("/decada")
	public ResponseEntity<List<GrupoDTO>> findDecada() {
		return ResponseEntity.ok(aeronaveService.findDecada());
	}

	@GetMapping("/no-sellers")
	public ResponseEntity<GrupoNaoVendidasDTO> findNoSellers() {
		return ResponseEntity.ok(aeronaveService.findNoSellers());
	}
	
	@GetMapping("/find/{nome}")
	public ResponseEntity<List<AeronaveDTO>> findModel(@PathVariable String nome){
		return ResponseEntity.ok(aeronaveService.findByModel(nome));
	}

	@GetMapping("/page")
	public ResponseEntity<Page<AeronaveDTO>> listarAeronavesPage(
			@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
		return ResponseEntity.ok(aeronaveService.findAllPage(pageable));
	}

	@GetMapping("/find/{nome}/page")
	public ResponseEntity<List<AeronaveDTO>> findModeloPage(@PathVariable String nome,
			@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
		return ResponseEntity.ok(aeronaveService.findByNomeContaining(nome, pageable));
	}

}
