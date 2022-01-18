package com.br.aircraft.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.br.aircraft.api.domain.dto.AeronaveDTO;
import com.br.aircraft.api.domain.model.Aeronave;


@Component
public class AeronaveDtoAssembler {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public AeronaveDTO toModel(Aeronave aeronave) {
        return modelMapper.map(aeronave, AeronaveDTO.class);
    }
    
    public List<AeronaveDTO> toCollectionModel(List<Aeronave> Aeronaves) {
        return Aeronaves.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    } 
    
    public List<AeronaveDTO> toCollectionModelPage(Page<Aeronave> page) {
        return page.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    }
}
