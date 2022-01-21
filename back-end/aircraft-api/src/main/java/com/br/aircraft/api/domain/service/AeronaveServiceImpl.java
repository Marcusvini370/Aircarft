package com.br.aircraft.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.aircraft.api.assembler.AeronaveDtoAssembler;
import com.br.aircraft.api.assembler.AeronaveInputDissasembler;
import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.dto.search.GrupoDTO;
import com.br.aircraft.api.domain.dto.search.GrupoNaoVendidasDTO;
import com.br.aircraft.api.domain.dto.search.GrupoSemanaDTO;
import com.br.aircraft.api.domain.enums.EnumMarca;
import com.br.aircraft.api.domain.exception.AeronaveDadoInvalidoException;
import com.br.aircraft.api.domain.exception.AeronaveNotFoundException;
import com.br.aircraft.api.domain.model.Aeronave;
import com.br.aircraft.api.domain.repository.AeronaveRepository;

@Service
public class AeronaveServiceImpl implements AeronaveService {

	private static final String MSG_AERONAVE_NAO_ENCOTNADA = "Não existe um cadastro de aeronave com código %d";
	private static final String MSG_AERONAVE_MARCA_ERRADA = "O nome da marca '%s' foi digitado incorretamente, por favor corrija e tente novamente. ";
	private static final String MSG_AERONAVE_ANO_ERRADO = "O ano da aeronave '%d' foi digitado incorretamente, por favor digite um ano válido no intervalo de 1920 a 2022. ";

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private AeronaveInputDissasembler aeronaveInputDissasembler;

	@Autowired
	private AeronaveDtoAssembler aeronaveDtoAssembler;

	@Override
	@Transactional
	public List<AeronaveDTO> findAll() {
		return aeronaveDtoAssembler.toCollectionModel(aeronaveRepository.findAll());
	}

	@Override
	@Transactional
	public Page<AeronaveDTO> findAllPage(Pageable pageable) {
		Page<Aeronave> aeronavesPage = aeronaveRepository.findAll(pageable);
		List<AeronaveDTO> aeronavesDTO = aeronaveDtoAssembler.toCollectionModel(aeronavesPage.getContent());
		Page<AeronaveDTO> aeronavesDtoPage = new PageImpl<>(aeronavesDTO, pageable, aeronavesPage.getTotalElements());
		return aeronavesDtoPage;
	}

	@Override
	@Transactional
	public AeronaveDTO findById(Long id) {
		return aeronaveDtoAssembler.toModel(BuscarOuFalhar(id));
	}

	public void validaCampos(AeronaveInput aeronaveInput) {
		if (aeronaveInput.getAno() < 1920 || aeronaveInput.getAno() > 2022) {
			throw new AeronaveDadoInvalidoException(String.format(MSG_AERONAVE_ANO_ERRADO, aeronaveInput.getAno()));
		}

		if (!EnumMarca.isValidarMarca(aeronaveInput.getMarca())) {
			throw new AeronaveDadoInvalidoException(String.format(MSG_AERONAVE_MARCA_ERRADA, aeronaveInput.getMarca()));
		}
	}

	@Override
	@Transactional
	public AeronaveDTO save(AeronaveInput aeronaveInput) {
		validaCampos(aeronaveInput);

		Aeronave aeronave = aeronaveInputDissasembler.toDomainObject(aeronaveInput);
		return aeronaveDtoAssembler.toModel(aeronaveRepository.save(aeronave));
	}

	@Override
	@Transactional
	public AeronaveDTO update(Long id, AeronaveInput aeronaveInput) {
		validaCampos(aeronaveInput);

		Aeronave aeronaveAtual = BuscarOuFalhar(id);
		aeronaveInputDissasembler.copyToDomainObject(aeronaveInput, aeronaveAtual);

		return aeronaveDtoAssembler.toModel(aeronaveRepository.save(aeronaveAtual));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			aeronaveRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new AeronaveNotFoundException(String.format(MSG_AERONAVE_NAO_ENCOTNADA, id));

		}
	}

	public Aeronave BuscarOuFalhar(Long id) {
		return aeronaveRepository.findById(id)
				.orElseThrow(() -> new AeronaveNotFoundException(String.format(MSG_AERONAVE_NAO_ENCOTNADA, id)));
	}

	@Override
	public GrupoSemanaDTO findRegistroSemanal() {
		OffsetDateTime dataDiasAtras = OffsetDateTime.now().minusDays(7);
		return aeronaveRepository.aeronavesRegistradasSemana(dataDiasAtras);
	}

	@Override
	public List<GrupoDTO> findDecada() {
		return aeronaveRepository.aeronavesPorDecada();
	}

	@Override
	public List<GrupoDTO> findMarcaQuantidade() {
		return aeronaveRepository.aeronaveMarcaAndQuantidade();
	}

	@Override
	public GrupoNaoVendidasDTO findNoSellers() {
		return aeronaveRepository.aeronavesNaoVendidas();
	}

	@Override
	public Page<AeronaveDTO> findByNomeContaining(String nome, Pageable pageable) {
		return aeronaveDtoAssembler
				.toCollectionModelPage(aeronaveRepository.findByNomeContaining(nome.toUpperCase(), pageable));
	}

	@Override
	public List<AeronaveDTO> findByModel(String nome) {
		return aeronaveDtoAssembler.toCollectionModel(aeronaveRepository.findByNomeContaining(nome.toUpperCase()));
	}

}
