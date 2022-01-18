package com.br.aircraft.api.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.aircraft.api.assembler.AeronaveDtoAssembler;
import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.model.Aeronave;
import com.br.aircraft.api.domain.repository.AeronaveRepository;
import com.br.aircraft.api.domain.service.AeronaveService;

@RestController
@RequestMapping(value = "/aeronaves")
public class AeronaveController {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private AeronaveDtoAssembler aeronaveDtoAssembler;

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
	public GrupoSemanaDTO findRegistroSemanal() {
		return aeronaveService.findRegistroSemanal();
	}

	@GetMapping("/marca-quantidade")
	public List<GrupoDTO> findMarcaQuantidade() {
		return aeronaveService.findMarcaQuantidade();
	}

	@GetMapping("/decada")
	public List<GrupoDTO> findDecada() {
		return aeronaveService.findDecada();
	}

	@GetMapping("/no-sellers")
	public GrupoNaoVendidasDTO findNoSellers() {
		return aeronaveService.findNoSellers();
	}

	@GetMapping("/find/{nome}")
	public List<AeronaveDTO> findModelo(@PathVariable String nome) {
		return aeronaveService.findModel(nome);
	}

	@GetMapping("/page")
	public Page<AeronaveDTO> listarAeronavesPage(@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
		return aeronaveService.findAllPage(pageable);
	}

	//@GetMapping("/find/{nome}/page")
	//public ResponseEntity<Page<AeronaveDTO>> findModeloPage(@PathVariable String nome,
		//	@PageableDefault(size = 5, sort = "nome") Pageable pageable) {

		//Page<AeronaveDTO> list = null;

		//if (nome == null || (nome != null && nome.trim().isEmpty()) || nome.equalsIgnoreCase("undefined")) {

			//list = aeronaveService.findAllPage(pageable);

	//	} else {

	//	//	list =  aeronaveDtoAssembler.toCollectionModel aeronaveRepository.findByNomeContaining(nome, pageable));
	//	}

	//	return ResponseEntity.ok().build();
	//}

}
