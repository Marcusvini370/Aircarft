package com.br.aircraft.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.br.aircraft.api.domain.dto.input.AeronaveInput;
import com.br.aircraft.api.domain.model.Aeronave;

@Component
public class AeronaveInputDissasembler {
	
	 @Autowired
	    private ModelMapper modelMapper;
	    
	    public Aeronave toDomainObject(AeronaveInput aeronaveInput) {
	        return modelMapper.map(aeronaveInput, Aeronave.class);
	    }
	    
	   public void copyToDomainObject(AeronaveInput aeronaveInput, Aeronave aeronave) {
	        modelMapper.map(aeronaveInput, aeronave);
	    }

}
